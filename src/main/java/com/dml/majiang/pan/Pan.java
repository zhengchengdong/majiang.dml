package com.dml.majiang.pan;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.dml.majiang.pai.MajiangPai;
import com.dml.majiang.pai.fenzu.Shunzi;
import com.dml.majiang.pan.cursor.PaiCursor;
import com.dml.majiang.pan.cursor.PlayerLatestDachupaiCursor;
import com.dml.majiang.pan.frame.PanActionFrame;
import com.dml.majiang.pan.frame.PanValueObject;
import com.dml.majiang.player.MajiangPlayer;
import com.dml.majiang.player.MajiangPlayerNotFoundException;
import com.dml.majiang.player.action.MajiangPlayerAction;
import com.dml.majiang.player.action.MajiangPlayerActionType;
import com.dml.majiang.position.MajiangPosition;
import com.dml.majiang.position.MajiangPositionUtil;

/**
 * 一盘麻将
 * 
 * @author Neo
 *
 */
public class Pan {

	/**
	 * 编号，代表一局中的第几盘
	 */
	int no;

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

	private List<PanActionFrame> actionFrameList = new ArrayList<>();

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

	public void updatePublicWaitingPlayerIdToDaPlayer() {
		for (MajiangPlayer player : majiangPlayerIdMajiangPlayerMap.values()) {
			for (MajiangPlayerAction action : player.getActionCandidates().values()) {
				if (action.getType().equals(MajiangPlayerActionType.da)) {
					publicWaitingPlayerId = player.getId();
					return;
				}
			}
		}
	}

	public void addPlayerActionCandidate(MajiangPlayerAction action) {
		MajiangPlayer player = majiangPlayerIdMajiangPlayerMap.get(action.getActionPlayerId());
		if (player != null) {
			player.addActionCandidate(action);
		}
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

	public PanActionFrame recordPanActionFrame(MajiangPlayerAction action, long actionTime) {
		PanActionFrame frame = new PanActionFrame(action, new PanValueObject(this), actionTime);
		frame.setNo(actionFrameList.size());
		actionFrameList.add(frame);
		return frame;
	}

	public void playerMoPai(String playerId) throws MajiangPlayerNotFoundException {
		MajiangPlayer player = majiangPlayerIdMajiangPlayerMap.get(playerId);
		if (player == null) {
			throw new MajiangPlayerNotFoundException();
		}
		MajiangPai pai = avaliablePaiList.remove(0);
		player.setGangmoShoupai(pai);
	}

	public void playerDaChuPai(String playerId, MajiangPai pai) throws MajiangPlayerNotFoundException {
		MajiangPlayer player = majiangPlayerIdMajiangPlayerMap.get(playerId);
		if (player == null) {
			throw new MajiangPlayerNotFoundException();
		}
		player.daChuPai(pai);
		activePaiCursor = new PlayerLatestDachupaiCursor(playerId);
	}

	public void playerChiPai(String chijinpaiPlayerId, String dachupaiPlayerId, MajiangPai chijinpai,
			Shunzi chifaShunzi) throws MajiangPlayerNotFoundException {

		MajiangPlayer chijinpaiPlayer = majiangPlayerIdMajiangPlayerMap.get(chijinpaiPlayerId);
		if (chijinpaiPlayer == null) {
			throw new MajiangPlayerNotFoundException();
		}

		MajiangPlayer dachupaiPlayer = majiangPlayerIdMajiangPlayerMap.get(dachupaiPlayerId);
		chijinpaiPlayer.chiPai(dachupaiPlayer, chijinpai, chifaShunzi);

	}

	public void playerPengPai(String pengjinpaiPlayerId, String dachupaiPlayerId, MajiangPai pai)
			throws MajiangPlayerNotFoundException {
		MajiangPlayer pengjinpaiPlayer = majiangPlayerIdMajiangPlayerMap.get(pengjinpaiPlayerId);
		if (pengjinpaiPlayer == null) {
			throw new MajiangPlayerNotFoundException();
		}

		MajiangPlayer dachupaiPlayer = majiangPlayerIdMajiangPlayerMap.get(dachupaiPlayerId);
		pengjinpaiPlayer.pengPai(dachupaiPlayer, pai);
	}

	public void playerGangDachupai(String gangjinpaiPlayerId, String dachupaiPlayerId, MajiangPai pai)
			throws MajiangPlayerNotFoundException {
		MajiangPlayer gangjinpaiPlayer = majiangPlayerIdMajiangPlayerMap.get(gangjinpaiPlayerId);
		if (gangjinpaiPlayer == null) {
			throw new MajiangPlayerNotFoundException();
		}

		MajiangPlayer dachupaiPlayer = majiangPlayerIdMajiangPlayerMap.get(dachupaiPlayerId);
		gangjinpaiPlayer.gangDachupai(dachupaiPlayer, pai);
	}

	public void playerShoupaiGangMo(String playerId, MajiangPai pai) throws MajiangPlayerNotFoundException {
		MajiangPlayer player = majiangPlayerIdMajiangPlayerMap.get(playerId);
		if (player == null) {
			throw new MajiangPlayerNotFoundException();
		}
		player.gangMopai(pai);
	}

	public void playerGangSigeshoupai(String playerId, MajiangPai pai) throws MajiangPlayerNotFoundException {
		MajiangPlayer player = majiangPlayerIdMajiangPlayerMap.get(playerId);
		if (player == null) {
			throw new MajiangPlayerNotFoundException();
		}
		player.gangSigeshoupai(pai);
	}

	public void playerKeziGangMo(String playerId, MajiangPai pai) throws MajiangPlayerNotFoundException {
		MajiangPlayer player = majiangPlayerIdMajiangPlayerMap.get(playerId);
		if (player == null) {
			throw new MajiangPlayerNotFoundException();
		}
		player.keziGangMopai(pai);
	}

	public void playerKeziGangShoupai(String playerId, MajiangPai pai) throws MajiangPlayerNotFoundException {
		MajiangPlayer player = majiangPlayerIdMajiangPlayerMap.get(playerId);
		if (player == null) {
			throw new MajiangPlayerNotFoundException();
		}
		player.keziGangShoupai(pai);
	}

	public void playerClearActionCandidates(String playerId) {
		MajiangPlayer player = majiangPlayerIdMajiangPlayerMap.get(playerId);
		if (player != null) {
			player.clearActionCandidates();
		}
	}

	public void clearAllPlayersActionCandidates() {
		majiangPlayerIdMajiangPlayerMap.values().forEach((player) -> player.clearActionCandidates());
	}

	public MajiangPlayer findShangjia(MajiangPlayer player) {
		MajiangPosition shangjiaMenFeng = MajiangPositionUtil.nextPositionClockwise(player.getMenFeng());
		String shangjiaPlayerId = menFengMajiangPlayerIdMap.get(shangjiaMenFeng);
		while (shangjiaPlayerId == null) {
			shangjiaMenFeng = MajiangPositionUtil.nextPositionClockwise(shangjiaMenFeng);
			shangjiaPlayerId = menFengMajiangPlayerIdMap.get(shangjiaMenFeng);
		}
		return majiangPlayerIdMajiangPlayerMap.get(shangjiaPlayerId);
	}

	public MajiangPlayer findXiajia(MajiangPlayer player) {
		MajiangPosition xiajiaMenFeng = MajiangPositionUtil.nextPositionAntiClockwise(player.getMenFeng());
		String xiajiaPlayerId = menFengMajiangPlayerIdMap.get(xiajiaMenFeng);
		while (xiajiaPlayerId == null) {
			xiajiaMenFeng = MajiangPositionUtil.nextPositionAntiClockwise(xiajiaMenFeng);
			xiajiaPlayerId = menFengMajiangPlayerIdMap.get(xiajiaMenFeng);
		}
		return majiangPlayerIdMajiangPlayerMap.get(xiajiaPlayerId);
	}

	public boolean allPlayerHasNoActionCandidates() {
		for (MajiangPlayer player : majiangPlayerIdMajiangPlayerMap.values()) {
			if (!player.getActionCandidates().isEmpty()) {
				return false;
			}
		}
		return true;
	}

	public boolean allPlayerHasNoHuActionCandidates() {
		for (MajiangPlayer player : majiangPlayerIdMajiangPlayerMap.values()) {
			Set<MajiangPlayerActionType> actionTypesSet = player.collectActionCandidatesType();
			if (actionTypesSet.contains(MajiangPlayerActionType.hu)) {
				return false;
			}
		}
		return true;
	}

	public int countPlayers() {
		return majiangPlayerIdMajiangPlayerMap.size();
	}

	public boolean anyPlayerHu() {
		for (MajiangPlayer player : majiangPlayerIdMajiangPlayerMap.values()) {
			if (player.getHu() != null) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 通常只有一个胡的玩家。在多胡的情况下不能调用此方法，调用后果是不确定查出的是哪一个玩家
	 * 
	 * @return
	 */
	public MajiangPlayer findHuPlayer() {
		for (MajiangPlayer player : majiangPlayerIdMajiangPlayerMap.values()) {
			if (player.getHu() != null) {
				return player;
			}
		}
		return null;
	}

	/**
	 * 多胡的情况下调用此方法,返回所有胡的玩家
	 * 
	 * @return
	 */
	public List<MajiangPlayer> findAllHuPlayers() {
		List<MajiangPlayer> huPlayerList = new ArrayList<>();
		for (MajiangPlayer player : majiangPlayerIdMajiangPlayerMap.values()) {
			if (player.getHu() != null) {
				huPlayerList.add(player);
			}
		}
		return huPlayerList;
	}

	public PanActionFrame findLatestActionFrame() {
		if (!actionFrameList.isEmpty()) {
			return actionFrameList.get(actionFrameList.size() - 1);
		} else {
			return null;
		}
	}

	/**
	 * 找到最后一个不是“过”，并且真正执行的动作
	 */
	public PanActionFrame findNotGuoLatestActionFrame() {
		if (!actionFrameList.isEmpty()) {
			int i = 1;
			PanActionFrame panActionFrame = actionFrameList.get(actionFrameList.size() - i);
			MajiangPlayerAction action = panActionFrame.getAction();
			while (true) {
				if (!action.getType().equals(MajiangPlayerActionType.guo)
						&& !action.isDisabledByHigherPriorityAction()) {
					break;
				} else {
					i++;
					panActionFrame = actionFrameList.get(actionFrameList.size() - i);
					action = panActionFrame.getAction();
				}
			}
			return panActionFrame;
		} else {
			return null;
		}
	}

	public int countAvaliablePai() {
		return avaliablePaiList.size();
	}

	public void updateShoupaiListSortComparatorForAllPlayers(Comparator<MajiangPai> comparator) {
		for (MajiangPlayer player : majiangPlayerIdMajiangPlayerMap.values()) {
			player.setFangruShoupaiListSortComparator(comparator);
		}
	}

	/**
	 * 针对目前所有玩家的候选actions，我们运用最常见的 “胡比碰杠优先，碰杠比吃优先” 优先规则，对同时出现的低优先actions disable掉。
	 * <br/>
	 * 有特殊优先规则的麻将不能调用此方法。
	 */
	public void disablePlayerActionsByHuPengGangChiPriority() {
		for (MajiangPlayer player : majiangPlayerIdMajiangPlayerMap.values()) {
			if (player.hasActionCandidateForType(MajiangPlayerActionType.hu)) {
				for (MajiangPlayer otherPlayer : majiangPlayerIdMajiangPlayerMap.values()) {
					if (!otherPlayer.getId().equals(player.getId())) {
						otherPlayer.disableActionCandidateForType(MajiangPlayerActionType.peng);
						otherPlayer.disableActionCandidateForType(MajiangPlayerActionType.gang);
						otherPlayer.disableActionCandidateForType(MajiangPlayerActionType.chi);
					}
				}
			} else if (player.hasActionCandidateForType(MajiangPlayerActionType.peng)) {
				for (MajiangPlayer otherPlayer : majiangPlayerIdMajiangPlayerMap.values()) {
					if (!otherPlayer.getId().equals(player.getId())) {
						otherPlayer.disableActionCandidateForType(MajiangPlayerActionType.chi);
					}
				}
			} else {
			}
		}
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
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

	public List<PanActionFrame> getActionFrameList() {
		return actionFrameList;
	}

	public void setActionFrameList(List<PanActionFrame> actionFrameList) {
		this.actionFrameList = actionFrameList;
	}

}
