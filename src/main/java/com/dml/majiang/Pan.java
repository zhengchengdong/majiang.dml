package com.dml.majiang;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 一盘麻将
 * 
 * @author Neo
 *
 */
public class Pan {

	private Map<String, MajiangPlayer> majiangPlayerIdMajiangPlayerMap = new HashMap<>();

	private Map<MajiangPosition, String> menFengMajiangPlayerIdMap = new HashMap<>();

	private String zhuangPlayerId;

	private List<MajiangPai> avaliablePaiList;

	/**
	 * 公示的鬼牌集合,不能行牌
	 */
	private Set<MajiangPai> publicGuipaiSet = new HashSet<>();

	/**
	 * 只玩哪些牌
	 */
	private List<MajiangPai> paiTypeList;

	/**
	 * 给用户看得到的等待箭头，实际等的不一定是他
	 */
	private String publicWaitingPlayerId;

	/**
	 * 当前活跃的那张牌的定位
	 */
	private PaiCursor activePaiCursor;

	private List<byte[]> actionFrameDataList = new ArrayList<>();

	private PanResult result;

	private int panActionFrameBufferSize;

	public void addPlayer(String playerId) {
		MajiangPlayer majiangPlayer = new MajiangPlayer();
		majiangPlayer.setId(playerId);
		majiangPlayerIdMajiangPlayerMap.put(playerId, majiangPlayer);
	}

	public List<String> sortedPlayerIdList() {
		List<String> list = new ArrayList<>(majiangPlayerIdMajiangPlayerMap.keySet());
		Collections.sort(list);
		return list;
	}

	public void updatePlayerMenFeng(String playerId, MajiangPosition menFeng) throws MajiangPlayerNotFoundException {
		MajiangPlayer player = majiangPlayerIdMajiangPlayerMap.get(playerId);
		if (player == null) {
			throw new MajiangPlayerNotFoundException();
		}
		player.setMenFeng(menFeng);
		menFengMajiangPlayerIdMap.put(menFeng, playerId);
	}

	public String playerIdForMenFeng(MajiangPosition menFeng) {
		return menFengMajiangPlayerIdMap.get(menFeng);
	}

	public void publicGuipaiAndRemoveFromList(MajiangPai guipaiType) {
		avaliablePaiList.remove(guipaiType);
		publicGuipaiSet.add(guipaiType);
	}

	public void updatePublicWaitingPlayerIdToZhuang() {
		publicWaitingPlayerId = zhuangPlayerId;
	}

	public void addPlayerActionCandidate(String playerId, MajiangPlayerAction action)
			throws MajiangPlayerNotFoundException {
		MajiangPlayer player = majiangPlayerIdMajiangPlayerMap.get(playerId);
		if (player == null) {
			throw new MajiangPlayerNotFoundException();
		}
		player.addActionCandidate(action);
	}

	public MajiangPlayerAction findPlayerActionCandidate(String playerId, int actionId)
			throws MajiangPlayerNotFoundException {
		MajiangPlayer player = majiangPlayerIdMajiangPlayerMap.get(playerId);
		if (player == null) {
			throw new MajiangPlayerNotFoundException();
		}
		return player.findActionCandidate(actionId);
	}

	public MajiangPosition findMenFengForZhuang() {
		MajiangPlayer zhuangPlayer = majiangPlayerIdMajiangPlayerMap.get(zhuangPlayerId);
		return zhuangPlayer.getMenFeng();
	}

	public MajiangPlayer findPlayerByMenFeng(MajiangPosition playerMenFeng) {
		String playerId = menFengMajiangPlayerIdMap.get(playerMenFeng);
		if (playerId != null) {
			return majiangPlayerIdMajiangPlayerMap.get(playerId);
		} else {
			return null;
		}
	}

	public MajiangPlayer findPlayerById(String playerId) {
		return majiangPlayerIdMajiangPlayerMap.get(playerId);
	}

	public byte[] recordPanActionFrame(MajiangPlayerAction action) {
		PanActionFrame frame = new PanActionFrame(action, new PanValueObject(this));
		byte[] buffer = new byte[panActionFrameBufferSize];
		ByteBuffer bb = ByteBuffer.wrap(buffer);
		try {
			ByteBufferSerializer.objToByteBuffer(frame, bb);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		byte[] frameData = new byte[bb.position()];
		System.arraycopy(buffer, 0, frameData, 0, frameData.length);
		actionFrameDataList.add(frameData);
		return frameData;
	}

	public void playerDaChuPai(String playerId, MajiangPai pai) throws MajiangPlayerNotFoundException {
		MajiangPlayer player = majiangPlayerIdMajiangPlayerMap.get(playerId);
		if (player == null) {
			throw new MajiangPlayerNotFoundException();
		}
		player.daChuPai(pai);
		activePaiCursor = new PlayerLatestDachupaiCursor(playerId);
	}

	public void playerChiPai(String chijinpaiPlayerId, MajiangPai chijinpai, Shunzi chifaShunzi)
			throws MajiangPlayerNotFoundException {

		MajiangPlayer chijinpaiPlayer = majiangPlayerIdMajiangPlayerMap.get(chijinpaiPlayerId);
		if (chijinpaiPlayer == null) {
			throw new MajiangPlayerNotFoundException();
		}

		// 打出牌的一定是上家
		MajiangPlayer dachupaiPlayer = findShangjia(chijinpaiPlayer);
		chijinpaiPlayer.chiPai(dachupaiPlayer, chijinpai, chifaShunzi);

	}

	public MajiangPlayer findShangjia(MajiangPlayer player) {
		MajiangPosition shangjiaMenFeng = MajiangPositionCircle.nextClockwise(player.getMenFeng());
		String shangjiaPlayerId = menFengMajiangPlayerIdMap.get(shangjiaMenFeng);
		while (shangjiaPlayerId == null) {
			shangjiaMenFeng = MajiangPositionCircle.nextClockwise(shangjiaMenFeng);
			shangjiaPlayerId = menFengMajiangPlayerIdMap.get(shangjiaMenFeng);
		}
		return majiangPlayerIdMajiangPlayerMap.get(shangjiaPlayerId);
	}

	public MajiangPlayer findXiajia(MajiangPlayer player) {
		MajiangPosition xiajiaMenFeng = MajiangPositionCircle.nextAntiClockwise(player.getMenFeng());
		String xiajiaPlayerId = menFengMajiangPlayerIdMap.get(xiajiaMenFeng);
		while (xiajiaPlayerId == null) {
			xiajiaMenFeng = MajiangPositionCircle.nextAntiClockwise(xiajiaMenFeng);
			xiajiaPlayerId = menFengMajiangPlayerIdMap.get(xiajiaMenFeng);
		}
		return majiangPlayerIdMajiangPlayerMap.get(xiajiaPlayerId);
	}

	public Map<String, MajiangPlayer> getMajiangPlayerIdMajiangPlayerMap() {
		return majiangPlayerIdMajiangPlayerMap;
	}

	public void setMajiangPlayerIdMajiangPlayerMap(Map<String, MajiangPlayer> majiangPlayerIdMajiangPlayerMap) {
		this.majiangPlayerIdMajiangPlayerMap = majiangPlayerIdMajiangPlayerMap;
	}

	public Map<MajiangPosition, String> getMenFengMajiangPlayerIdMap() {
		return menFengMajiangPlayerIdMap;
	}

	public void setMenFengMajiangPlayerIdMap(Map<MajiangPosition, String> menFengMajiangPlayerIdMap) {
		this.menFengMajiangPlayerIdMap = menFengMajiangPlayerIdMap;
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

	public List<MajiangPai> getPaiTypeList() {
		return paiTypeList;
	}

	public void setPaiTypeList(List<MajiangPai> paiTypeList) {
		this.paiTypeList = paiTypeList;
	}

	public Set<MajiangPai> getPublicGuipaiSet() {
		return publicGuipaiSet;
	}

	public void setPublicGuipaiSet(Set<MajiangPai> publicGuipaiSet) {
		this.publicGuipaiSet = publicGuipaiSet;
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

	public List<byte[]> getActionFrameDataList() {
		return actionFrameDataList;
	}

	public void setActionFrameDataList(List<byte[]> actionFrameDataList) {
		this.actionFrameDataList = actionFrameDataList;
	}

	public PanResult getResult() {
		return result;
	}

	public void setResult(PanResult result) {
		this.result = result;
	}

	public int getPanActionFrameBufferSize() {
		return panActionFrameBufferSize;
	}

	public void setPanActionFrameBufferSize(int panActionFrameBufferSize) {
		this.panActionFrameBufferSize = panActionFrameBufferSize;
	}

}
