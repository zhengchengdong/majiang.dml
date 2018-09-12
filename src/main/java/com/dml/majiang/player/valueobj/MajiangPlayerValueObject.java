package com.dml.majiang.player.valueobj;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

import com.dml.majiang.pai.MajiangPai;
import com.dml.majiang.pai.valueobj.MajiangPaiValueObject;
import com.dml.majiang.player.Hu;
import com.dml.majiang.player.MajiangPlayer;
import com.dml.majiang.player.action.MajiangPlayerAction;
import com.dml.majiang.player.chupaizu.ChichuPaiZu;
import com.dml.majiang.player.chupaizu.GangchuPaiZu;
import com.dml.majiang.player.chupaizu.PengchuPaiZu;
import com.dml.majiang.position.MajiangPosition;
import com.dml.majiang.serializer.ByteBufferAble;
import com.dml.majiang.serializer.ByteBufferSerializer;

public class MajiangPlayerValueObject implements ByteBufferAble {

	private String id;
	/**
	 * 门风
	 */
	private MajiangPosition menFeng;

	/**
	 * 已放入的手牌列表（不包含鬼牌，不包含公开牌）
	 */
	private List<MajiangPai> fangruShoupaiList;

	/**
	 * 已放入的鬼牌手牌列表（全部是鬼牌）
	 */
	private List<MajiangPai> fangruGuipaiList;

	private int totalShoupaiCount;

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

	private int guipaiCount;

	/**
	 * 打出的牌
	 */
	private List<MajiangPai> dachupaiList;

	private List<ChichuPaiZu> chichupaiZuList;
	private List<PengchuPaiZu> pengchupaiZuList;
	private List<GangchuPaiZu> gangchupaiZuList;

	private Hu hu;

	public MajiangPlayerValueObject() {
	}

	public MajiangPlayerValueObject(MajiangPlayer player) {
		id = player.getId();
		menFeng = player.getMenFeng();
		fangruShoupaiList = new ArrayList<>(player.getFangruShoupaiList());
		fangruGuipaiList = new ArrayList<>(player.getFangruGuipaiList());
		totalShoupaiCount = player.countAllFangruShoupai();
		publicPaiList = new ArrayList<>(player.getPublicPaiList());
		guipaiTypeList = new ArrayList<>(player.getGuipaiTypeSet());
		actionCandidates = new ArrayList<>(player.getActionCandidates().values());
		if (player.getGangmoShoupai() != null) {
			gangmoShoupai = new MajiangPaiValueObject(player.getGangmoShoupai());
		}
		guipaiCount = player.countGuipai();
		dachupaiList = new ArrayList<>(player.getDachupaiList());
		chichupaiZuList = new ArrayList<>(player.getChichupaiZuList());
		pengchupaiZuList = new ArrayList<>(player.getPengchupaiZuList());
		gangchupaiZuList = new ArrayList<>(player.getGangchupaiZuList());
		hu = player.getHu();
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

	public List<MajiangPai> getFangruShoupaiList() {
		return fangruShoupaiList;
	}

	public void setFangruShoupaiList(List<MajiangPai> fangruShoupaiList) {
		this.fangruShoupaiList = fangruShoupaiList;
	}

	public List<MajiangPai> getFangruGuipaiList() {
		return fangruGuipaiList;
	}

	public void setFangruGuipaiList(List<MajiangPai> fangruGuipaiList) {
		this.fangruGuipaiList = fangruGuipaiList;
	}

	public int getTotalShoupaiCount() {
		return totalShoupaiCount;
	}

	public void setTotalShoupaiCount(int totalShoupaiCount) {
		this.totalShoupaiCount = totalShoupaiCount;
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

	public int getGuipaiCount() {
		return guipaiCount;
	}

	public void setGuipaiCount(int guipaiCount) {
		this.guipaiCount = guipaiCount;
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

	public Hu getHu() {
		return hu;
	}

	public void setHu(Hu hu) {
		this.hu = hu;
	}

	@Override
	public void toByteBuffer(ByteBuffer bb) throws Throwable {
		ByteBufferSerializer.stringToByteBuffer(id, bb);
		bb.put((byte) menFeng.ordinal());
		majiangPaiListToByteBuffer(fangruShoupaiList, bb);
		majiangPaiListToByteBuffer(fangruGuipaiList, bb);
		bb.putInt(totalShoupaiCount);
		majiangPaiListToByteBuffer(publicPaiList, bb);
		majiangPaiListToByteBuffer(guipaiTypeList, bb);
		ByteBufferSerializer.listToByteBuffer(new ArrayList<>(actionCandidates), bb);
		ByteBufferSerializer.objToByteBuffer(gangmoShoupai, bb);
		bb.putInt(guipaiCount);
		majiangPaiListToByteBuffer(dachupaiList, bb);
		ByteBufferSerializer.listToByteBuffer(new ArrayList<>(chichupaiZuList), bb);
		ByteBufferSerializer.listToByteBuffer(new ArrayList<>(pengchupaiZuList), bb);
		ByteBufferSerializer.listToByteBuffer(new ArrayList<>(gangchupaiZuList), bb);
		ByteBufferSerializer.objToByteBuffer(hu, bb);
	}

	@Override
	public void fillByByteBuffer(ByteBuffer bb) throws Throwable {
		id = ByteBufferSerializer.byteBufferToString(bb);
		menFeng = MajiangPosition.valueOf(bb.get());
		fangruShoupaiList = fillMajiangPaiList(bb);
		fangruGuipaiList = fillMajiangPaiList(bb);
		totalShoupaiCount = bb.getInt();
		publicPaiList = fillMajiangPaiList(bb);
		guipaiTypeList = fillMajiangPaiList(bb);
		actionCandidates = new ArrayList<>();
		ByteBufferSerializer.byteBufferToList(bb).forEach((o) -> actionCandidates.add((MajiangPlayerAction) o));
		gangmoShoupai = ByteBufferSerializer.byteBufferToObj(bb);
		guipaiCount = bb.getInt();
		dachupaiList = fillMajiangPaiList(bb);
		chichupaiZuList = new ArrayList<>();
		ByteBufferSerializer.byteBufferToList(bb).forEach((o) -> chichupaiZuList.add((ChichuPaiZu) o));
		pengchupaiZuList = new ArrayList<>();
		ByteBufferSerializer.byteBufferToList(bb).forEach((o) -> pengchupaiZuList.add((PengchuPaiZu) o));
		gangchupaiZuList = new ArrayList<>();
		ByteBufferSerializer.byteBufferToList(bb).forEach((o) -> gangchupaiZuList.add((GangchuPaiZu) o));
		hu = ByteBufferSerializer.byteBufferToObj(bb);
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
