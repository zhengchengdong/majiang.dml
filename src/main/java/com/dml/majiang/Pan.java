package com.dml.majiang;

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

}
