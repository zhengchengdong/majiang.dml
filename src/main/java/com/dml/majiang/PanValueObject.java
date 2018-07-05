package com.dml.majiang;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

public class PanValueObject implements ByteBufferAble {

	private List<MajiangPlayerValueObject> playerList;

	private String zhuangPlayerId;

	private List<MajiangPai> avaliablePaiList;

	/**
	 * 公示的鬼牌集合,不能行牌
	 */
	private List<MajiangPai> publicGuipaiList;

	/**
	 * 给用户看得到的等待箭头，实际等的不一定是他
	 */
	private String publicWaitingPlayerId;

	private PanResult result;

	public PanValueObject() {
	}

	public PanValueObject(Pan pan) {
		playerList = new ArrayList<>();
		pan.getMajiangPlayerIdMajiangPlayerMap().values()
				.forEach((player) -> playerList.add(new MajiangPlayerValueObject(player)));
		zhuangPlayerId = pan.getZhuangPlayerId();
		avaliablePaiList = new ArrayList<>(pan.getAvaliablePaiList());
		publicGuipaiList = new ArrayList<>(pan.getPublicGuipaiSet());
		publicWaitingPlayerId = pan.getPublicWaitingPlayerId();
		result = pan.getResult();
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

	public List<MajiangPai> getAvaliablePaiList() {
		return avaliablePaiList;
	}

	public void setAvaliablePaiList(List<MajiangPai> avaliablePaiList) {
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

	public PanResult getResult() {
		return result;
	}

	public void setResult(PanResult result) {
		this.result = result;
	}

	@Override
	public void toByteBuffer(ByteBuffer bb) throws Throwable {
		ByteBufferSerializer.listToByteBuffer(new ArrayList<>(playerList), bb);
		ByteBufferSerializer.stringToByteBuffer(zhuangPlayerId, bb);
		majiangPaiListToByteBuffer(avaliablePaiList, bb);
		majiangPaiListToByteBuffer(publicGuipaiList, bb);
		ByteBufferSerializer.stringToByteBuffer(publicWaitingPlayerId, bb);
		ByteBufferSerializer.objToByteBuffer(result, bb);
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
		playerList = new ArrayList<>();
		ByteBufferSerializer.byteBufferToList(bb)
				.forEach((player) -> playerList.add((MajiangPlayerValueObject) player));
		zhuangPlayerId = ByteBufferSerializer.byteBufferToString(bb);
		avaliablePaiList = fillMajiangPaiList(bb);
		publicGuipaiList = fillMajiangPaiList(bb);
		publicWaitingPlayerId = ByteBufferSerializer.byteBufferToString(bb);
		result = ByteBufferSerializer.byteBufferToObj(bb);
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
