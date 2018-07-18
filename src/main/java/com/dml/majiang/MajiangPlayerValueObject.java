package com.dml.majiang;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

public class MajiangPlayerValueObject implements ByteBufferAble {

	private String id;
	/**
	 * 门风
	 */
	private MajiangPosition menFeng;
	private FangruShoupaiListValueObject fangruShoupaiList;
	/**
	 * 公开的牌，不能行牌
	 */
	private List<MajiangPai> publicPaiList;

	/**
	 * 标示什么牌是鬼牌
	 */
	private List<MajiangPai> guipaiTypeList;

	private List<MajiangPlayerAction> actionCandidates;

	/**
	 * 刚摸进待处理的手牌（未放入）
	 */
	private MajiangPaiValueObject gangmoShoupai;

	/**
	 * 打出的牌
	 */
	private List<MajiangPai> dachupaiList;

	private List<ChichuPaiZu> chichupaiZuList;
	private List<PengchuPaiZu> pengchupaiZuList;
	private List<GangchuPaiZu> gangchupaiZuList;

	public MajiangPlayerValueObject() {
	}

	public MajiangPlayerValueObject(MajiangPlayer player) {
		id = player.getId();
		menFeng = player.getMenFeng();
		fangruShoupaiList = new FangruShoupaiListValueObject(player.getFangruShoupaiList(), player.getGuipaiTypeSet());
		publicPaiList = new ArrayList<>(player.getPublicPaiList());
		guipaiTypeList = new ArrayList<>(player.getGuipaiTypeSet());
		actionCandidates = new ArrayList<>(player.getActionCandidates().values());
		if (player.getGangmoShoupai() != null) {
			gangmoShoupai = new MajiangPaiValueObject(player.getGangmoShoupai());
		}
		dachupaiList = new ArrayList<>(player.getDachupaiList());
		chichupaiZuList = new ArrayList<>(player.getChichupaiZuList());
		pengchupaiZuList = new ArrayList<>(player.getPengchupaiZuList());
		gangchupaiZuList = new ArrayList<>(player.getGangchupaiZuList());
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public MajiangPosition getMenFeng() {
		return menFeng;
	}

	public void setMenFeng(MajiangPosition menFeng) {
		this.menFeng = menFeng;
	}

	public FangruShoupaiListValueObject getFangruShoupaiList() {
		return fangruShoupaiList;
	}

	public void setFangruShoupaiList(FangruShoupaiListValueObject fangruShoupaiList) {
		this.fangruShoupaiList = fangruShoupaiList;
	}

	public List<MajiangPai> getPublicPaiList() {
		return publicPaiList;
	}

	public void setPublicPaiList(List<MajiangPai> publicPaiList) {
		this.publicPaiList = publicPaiList;
	}

	public List<MajiangPai> getGuipaiTypeList() {
		return guipaiTypeList;
	}

	public void setGuipaiTypeList(List<MajiangPai> guipaiTypeList) {
		this.guipaiTypeList = guipaiTypeList;
	}

	public List<MajiangPlayerAction> getActionCandidates() {
		return actionCandidates;
	}

	public void setActionCandidates(List<MajiangPlayerAction> actionCandidates) {
		this.actionCandidates = actionCandidates;
	}

	public MajiangPaiValueObject getGangmoShoupai() {
		return gangmoShoupai;
	}

	public void setGangmoShoupai(MajiangPaiValueObject gangmoShoupai) {
		this.gangmoShoupai = gangmoShoupai;
	}

	public List<MajiangPai> getDachupaiList() {
		return dachupaiList;
	}

	public void setDachupaiList(List<MajiangPai> dachupaiList) {
		this.dachupaiList = dachupaiList;
	}

	public List<ChichuPaiZu> getChichupaiZuList() {
		return chichupaiZuList;
	}

	public void setChichupaiZuList(List<ChichuPaiZu> chichupaiZuList) {
		this.chichupaiZuList = chichupaiZuList;
	}

	public List<PengchuPaiZu> getPengchupaiZuList() {
		return pengchupaiZuList;
	}

	public void setPengchupaiZuList(List<PengchuPaiZu> pengchupaiZuList) {
		this.pengchupaiZuList = pengchupaiZuList;
	}

	public List<GangchuPaiZu> getGangchupaiZuList() {
		return gangchupaiZuList;
	}

	public void setGangchupaiZuList(List<GangchuPaiZu> gangchupaiZuList) {
		this.gangchupaiZuList = gangchupaiZuList;
	}

	@Override
	public void toByteBuffer(ByteBuffer bb) throws Throwable {
		ByteBufferSerializer.stringToByteBuffer(id, bb);
		bb.put((byte) menFeng.ordinal());
		ByteBufferSerializer.objToByteBuffer(fangruShoupaiList, bb);
		majiangPaiListToByteBuffer(publicPaiList, bb);
		majiangPaiListToByteBuffer(guipaiTypeList, bb);
		ByteBufferSerializer.listToByteBuffer(new ArrayList<>(actionCandidates), bb);
		ByteBufferSerializer.objToByteBuffer(gangmoShoupai, bb);
		majiangPaiListToByteBuffer(dachupaiList, bb);
		ByteBufferSerializer.listToByteBuffer(new ArrayList<>(chichupaiZuList), bb);
		ByteBufferSerializer.listToByteBuffer(new ArrayList<>(pengchupaiZuList), bb);
		ByteBufferSerializer.listToByteBuffer(new ArrayList<>(gangchupaiZuList), bb);
	}

	@Override
	public void fillByByteBuffer(ByteBuffer bb) throws Throwable {
		id = ByteBufferSerializer.byteBufferToString(bb);
		menFeng = MajiangPosition.valueOf(bb.get());
		fangruShoupaiList = ByteBufferSerializer.byteBufferToObj(bb);
		publicPaiList = fillMajiangPaiList(bb);
		guipaiTypeList = fillMajiangPaiList(bb);
		actionCandidates = new ArrayList<>();
		ByteBufferSerializer.byteBufferToList(bb).forEach((o) -> actionCandidates.add((MajiangPlayerAction) o));
		gangmoShoupai = ByteBufferSerializer.byteBufferToObj(bb);
		dachupaiList = fillMajiangPaiList(bb);
		chichupaiZuList = new ArrayList<>();
		ByteBufferSerializer.byteBufferToList(bb).forEach((o) -> chichupaiZuList.add((ChichuPaiZu) o));
		pengchupaiZuList = new ArrayList<>();
		ByteBufferSerializer.byteBufferToList(bb).forEach((o) -> pengchupaiZuList.add((PengchuPaiZu) o));
		gangchupaiZuList = new ArrayList<>();
		ByteBufferSerializer.byteBufferToList(bb).forEach((o) -> gangchupaiZuList.add((GangchuPaiZu) o));
	}

	private void majiangPaiListToByteBuffer(List<MajiangPai> majiangPaiList, ByteBuffer bb) {
		byte[] byteArray = new byte[majiangPaiList.size()];
		for (int i = 0; i < majiangPaiList.size(); i++) {
			byteArray[i] = (byte) majiangPaiList.get(i).ordinal();
		}
		ByteBufferSerializer.byteArrayToByteBuffer(byteArray, bb);
	}

	private List<MajiangPai> fillMajiangPaiList(ByteBuffer bb) {
		List<MajiangPai> list = new ArrayList<>();
		byte[] avaliablePaiByteArray = ByteBufferSerializer.byteBufferTobyteArray(bb);
		for (int i = 0; i < avaliablePaiByteArray.length; i++) {
			int ordinal = (Byte.toUnsignedInt(avaliablePaiByteArray[i]));
			list.add(MajiangPai.valueOf(ordinal));
		}
		return list;
	}

}
