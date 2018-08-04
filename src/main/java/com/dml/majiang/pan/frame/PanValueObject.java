package com.dml.majiang.pan.frame;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

import com.dml.majiang.pai.MajiangPai;
import com.dml.majiang.pai.valueobj.PaiListValueObject;
import com.dml.majiang.pan.Pan;
import com.dml.majiang.pan.cursor.PaiCursor;
import com.dml.majiang.player.valueobj.MajiangPlayerValueObject;
import com.dml.majiang.serializer.ByteBufferAble;
import com.dml.majiang.serializer.ByteBufferSerializer;

public class PanValueObject implements ByteBufferAble {

	/**
	 * 编号，代表一局中的第几盘
	 */
	private int no;

	private List<MajiangPlayerValueObject> playerList;

	private String zhuangPlayerId;

	private PaiListValueObject avaliablePaiList;

	/**
	 * 公示的鬼牌集合,不能行牌
	 */
	private List<MajiangPai> publicGuipaiList;

	/**
	 * 给用户看得到的等待箭头，实际等的不一定是他
	 */
	private String publicWaitingPlayerId;

	/**
	 * 当前活跃的那张牌的定位
	 */
	private PaiCursor activePaiCursor;

	public PanValueObject() {
	}

	public PanValueObject(Pan pan) {
		no = pan.getNo();
		playerList = new ArrayList<>();
		pan.getMajiangPlayerIdMajiangPlayerMap().values()
				.forEach((player) -> playerList.add(new MajiangPlayerValueObject(player)));
		zhuangPlayerId = pan.getZhuangPlayerId();
		avaliablePaiList = new PaiListValueObject(pan.getAvaliablePaiList());
		publicGuipaiList = new ArrayList<>(pan.getPublicGuipaiSet());
		publicWaitingPlayerId = pan.getPublicWaitingPlayerId();
		activePaiCursor = pan.getActivePaiCursor();
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public List<MajiangPlayerValueObject> getPlayerList() {
		return playerList;
	}

	public void setPlayerList(List<MajiangPlayerValueObject> playerList) {
		this.playerList = playerList;
	}

	public String getZhuangPlayerId() {
		return zhuangPlayerId;
	}

	public void setZhuangPlayerId(String zhuangPlayerId) {
		this.zhuangPlayerId = zhuangPlayerId;
	}

	public PaiListValueObject getAvaliablePaiList() {
		return avaliablePaiList;
	}

	public void setAvaliablePaiList(PaiListValueObject avaliablePaiList) {
		this.avaliablePaiList = avaliablePaiList;
	}

	public List<MajiangPai> getPublicGuipaiList() {
		return publicGuipaiList;
	}

	public void setPublicGuipaiList(List<MajiangPai> publicGuipaiList) {
		this.publicGuipaiList = publicGuipaiList;
	}

	public String getPublicWaitingPlayerId() {
		return publicWaitingPlayerId;
	}

	public void setPublicWaitingPlayerId(String publicWaitingPlayerId) {
		this.publicWaitingPlayerId = publicWaitingPlayerId;
	}

	public PaiCursor getActivePaiCursor() {
		return activePaiCursor;
	}

	public void setActivePaiCursor(PaiCursor activePaiCursor) {
		this.activePaiCursor = activePaiCursor;
	}

	@Override
	public void toByteBuffer(ByteBuffer bb) throws Throwable {
		bb.putInt(no);
		ByteBufferSerializer.listToByteBuffer(new ArrayList<>(playerList), bb);
		ByteBufferSerializer.stringToByteBuffer(zhuangPlayerId, bb);
		ByteBufferSerializer.objToByteBuffer(avaliablePaiList, bb);
		majiangPaiListToByteBuffer(publicGuipaiList, bb);
		ByteBufferSerializer.stringToByteBuffer(publicWaitingPlayerId, bb);
		ByteBufferSerializer.objToByteBuffer(activePaiCursor, bb);
	}

	private void majiangPaiListToByteBuffer(List<MajiangPai> majiangPaiList, ByteBuffer bb) {
		byte[] byteArray = new byte[majiangPaiList.size()];
		for (int i = 0; i < majiangPaiList.size(); i++) {
			byteArray[i] = (byte) majiangPaiList.get(i).ordinal();
		}
		ByteBufferSerializer.byteArrayToByteBuffer(byteArray, bb);
	}

	@Override
	public void fillByByteBuffer(ByteBuffer bb) throws Throwable {
		no = bb.getInt();
		playerList = new ArrayList<>();
		ByteBufferSerializer.byteBufferToList(bb)
				.forEach((player) -> playerList.add((MajiangPlayerValueObject) player));
		zhuangPlayerId = ByteBufferSerializer.byteBufferToString(bb);
		avaliablePaiList = ByteBufferSerializer.byteBufferToObj(bb);
		publicGuipaiList = fillMajiangPaiList(bb);
		publicWaitingPlayerId = ByteBufferSerializer.byteBufferToString(bb);
		activePaiCursor = ByteBufferSerializer.byteBufferToObj(bb);
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