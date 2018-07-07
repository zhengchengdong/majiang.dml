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
	private PaiListValueObject shoupaiList;
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
	 * 摸进的牌。只是展示(只能自己看见)，实际在手牌中。
	 */
	private MajiangPai publicMoPai;

	/**
	 * 打出的牌
	 */
	private List<MajiangPai> dachupaiList;

	private List<ChichuPai> chichupaiList;
	private List<PengchuPai> pengchupaiList;
	private List<GangchuPai> gangchupaiList;

	public MajiangPlayerValueObject() {
	}

	public MajiangPlayerValueObject(MajiangPlayer player) {
		id = player.getId();
		menFeng = player.getMenFeng();
		shoupaiList = new PaiListValueObject(player.getShoupaiList());
		publicPaiList = new ArrayList<>(player.getPublicPaiList());
		guipaiTypeList = new ArrayList<>(player.getGuipaiTypeSet());
		actionCandidates = new ArrayList<>(player.getActionCandidates().values());
		publicMoPai = player.getPublicMoPai();
		dachupaiList = new ArrayList<>(player.getDachupaiList());
		chichupaiList = new ArrayList<>(player.getChichupaiList());
		pengchupaiList = new ArrayList<>(player.getPengchupaiList());
		gangchupaiList = new ArrayList<>(player.getGangchupaiList());
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

	public PaiListValueObject getShoupaiList() {
		return shoupaiList;
	}

	public void setShoupaiList(PaiListValueObject shoupaiList) {
		this.shoupaiList = shoupaiList;
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

	public MajiangPai getPublicMoPai() {
		return publicMoPai;
	}

	public void setPublicMoPai(MajiangPai publicMoPai) {
		this.publicMoPai = publicMoPai;
	}

	public List<MajiangPai> getDachupaiList() {
		return dachupaiList;
	}

	public void setDachupaiList(List<MajiangPai> dachupaiList) {
		this.dachupaiList = dachupaiList;
	}

	public List<ChichuPai> getChichupaiList() {
		return chichupaiList;
	}

	public void setChichupaiList(List<ChichuPai> chichupaiList) {
		this.chichupaiList = chichupaiList;
	}

	public List<PengchuPai> getPengchupaiList() {
		return pengchupaiList;
	}

	public void setPengchupaiList(List<PengchuPai> pengchupaiList) {
		this.pengchupaiList = pengchupaiList;
	}

	public List<GangchuPai> getGangchupaiList() {
		return gangchupaiList;
	}

	public void setGangchupaiList(List<GangchuPai> gangchupaiList) {
		this.gangchupaiList = gangchupaiList;
	}

	@Override
	public void toByteBuffer(ByteBuffer bb) throws Throwable {
		ByteBufferSerializer.stringToByteBuffer(id, bb);
		bb.put((byte) menFeng.ordinal());
		ByteBufferSerializer.objToByteBuffer(shoupaiList, bb);
		majiangPaiListToByteBuffer(publicPaiList, bb);
		majiangPaiListToByteBuffer(guipaiTypeList, bb);
		ByteBufferSerializer.listToByteBuffer(new ArrayList<>(actionCandidates), bb);
		if (publicMoPai != null) {
			bb.put((byte) 1);
			bb.put((byte) publicMoPai.ordinal());
		} else {
			bb.put((byte) 0);
		}
		majiangPaiListToByteBuffer(dachupaiList, bb);
		ByteBufferSerializer.listToByteBuffer(new ArrayList<>(chichupaiList), bb);
		ByteBufferSerializer.listToByteBuffer(new ArrayList<>(pengchupaiList), bb);
		ByteBufferSerializer.listToByteBuffer(new ArrayList<>(gangchupaiList), bb);
	}

	@Override
	public void fillByByteBuffer(ByteBuffer bb) throws Throwable {
		id = ByteBufferSerializer.byteBufferToString(bb);
		menFeng = MajiangPosition.valueOf(bb.get());
		shoupaiList = ByteBufferSerializer.byteBufferToObj(bb);
		publicPaiList = fillMajiangPaiList(bb);
		guipaiTypeList = fillMajiangPaiList(bb);
		actionCandidates = new ArrayList<>();
		ByteBufferSerializer.byteBufferToList(bb).forEach((o) -> actionCandidates.add((MajiangPlayerAction) o));
		byte notNull = bb.get();
		if (notNull == 1) {
			publicMoPai = MajiangPai.valueOf(Byte.toUnsignedInt(bb.get()));
		} else {
			publicMoPai = null;
		}
		dachupaiList = fillMajiangPaiList(bb);
		chichupaiList = new ArrayList<>();
		ByteBufferSerializer.byteBufferToList(bb).forEach((o) -> chichupaiList.add((ChichuPai) o));
		pengchupaiList = new ArrayList<>();
		ByteBufferSerializer.byteBufferToList(bb).forEach((o) -> pengchupaiList.add((PengchuPai) o));
		gangchupaiList = new ArrayList<>();
		ByteBufferSerializer.byteBufferToList(bb).forEach((o) -> gangchupaiList.add((GangchuPai) o));
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
