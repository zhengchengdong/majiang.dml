package com.dml.majiang;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MajiangPlayer {

	private String id;
	/**
	 * 门风
	 */
	private MajiangPosition menFeng;
	private List<MajiangPai> shoupaiList = new ArrayList<>();

	/**
	 * 公开的牌，不能行牌
	 */
	private List<MajiangPai> publicPaiList = new ArrayList<>();

	/**
	 * 标示什么牌是鬼牌
	 */
	private Set<MajiangPai> guipaiTypeSet = new HashSet<>();

	private Map<Integer, MajiangPlayerAction> actionCandidates = new HashMap<>();

	private GouXingCalculator gouXingCalculator = new GouXingCalculator();

	/**
	 * 摸进的牌。只是展示(只能自己看见)，实际在手牌中。
	 */
	private MajiangPai publicMoPai;

	/**
	 * 打出的牌列表
	 */
	private List<MajiangPai> dachupaiList = new ArrayList<>();

	private List<ChichuPai> chichupaiList = new ArrayList<>();
	private List<PengchuPai> pengchupaiList = new ArrayList<>();
	private List<GangchuPai> gangchupaiList = new ArrayList<>();

	public void addGuipaiType(MajiangPai guipaiType) {
		guipaiTypeSet.add(guipaiType);
	}

	public void addShoupai(MajiangPai pai) {
		shoupaiList.add(pai);
		Collections.sort(shoupaiList);
	}

	public void addPaiToGouXingCalculator(MajiangPai pai) {
		if (!guipaiTypeSet.contains(pai)) {
			gouXingCalculator.addPai(pai);
		}
	}

	public void addActionCandidate(MajiangPlayerAction action) {
		actionCandidates.put(action.getId(), action);
	}

	public MajiangPlayerAction findActionCandidate(int actionId) {
		return actionCandidates.get(actionId);
	}

	public void addPublicPai(MajiangPai pai) {
		publicPaiList.add(pai);
	}

	public void generateDaActions() {
		Set<MajiangPai> daPaiSet = new HashSet<>();
		shoupaiList.forEach((shoupai) -> {
			if (!guipaiTypeSet.contains(shoupai)) {
				if (!daPaiSet.contains(shoupai)) {
					int actionId = actionCandidates.size() + 1;
					MajiangDaAction daAction = new MajiangDaAction(actionId, shoupai, id);
					actionCandidates.put(actionId, daAction);
					daPaiSet.add(shoupai);
				}
			}
		});

	}

	public void clearActionCandidates() {
		actionCandidates.clear();
	}

	/**
	 * 不能打鬼牌是常识
	 * 
	 * @param pai
	 */
	public void daChuPai(MajiangPai pai) {
		shoupaiList.remove(pai);
		dachupaiList.add(pai);
		gouXingCalculator.removePai(pai);
		publicMoPai = null;
	}

	public void moPai(MajiangPai pai) {
		addShoupai(pai);
		addPaiToGouXingCalculator(pai);
		publicMoPai = pai;
	}

	public void chiPai(MajiangPlayer dachupaiPlayer, MajiangPai chijinpai, Shunzi chifaShunzi) {
		dachupaiPlayer.removeLatestDachupai();
		MajiangPai pai1 = chifaShunzi.getPai1();
		if (!pai1.equals(chijinpai)) {
			shoupaiList.remove(pai1);
			gouXingCalculator.removePai(pai1);
		}
		MajiangPai pai2 = chifaShunzi.getPai2();
		if (!pai2.equals(chijinpai)) {
			shoupaiList.remove(pai2);
			gouXingCalculator.removePai(pai2);
		}
		MajiangPai pai3 = chifaShunzi.getPai3();
		if (!pai3.equals(chijinpai)) {
			shoupaiList.remove(pai3);
			gouXingCalculator.removePai(pai3);
		}
		ChichuPai chichuPai = new ChichuPai(chifaShunzi, dachupaiPlayer.getId(), id);
		chichupaiList.add(chichuPai);
	}

	private void removeLatestDachupai() {
		dachupaiList.remove(dachupaiList.size() - 1);
	}

	public void tryChiAndGenerateCandidateActions(MajiangPai pai) {
		if (MajiangPai.isXushupai(pai)) {
			Shunzi shunzi1 = gouXingCalculator.tryAndMakeShunziWithPai1(pai);
			if (shunzi1 != null) {
				actionCandidates.put(actionCandidates.size() + 1,
						new MajiangChiAction(actionCandidates.size() + 1, id, pai, shunzi1));
			}

			Shunzi shunzi2 = gouXingCalculator.tryAndMakeShunziWithPai2(pai);
			if (shunzi2 != null) {
				actionCandidates.put(actionCandidates.size() + 1,
						new MajiangChiAction(actionCandidates.size() + 1, id, pai, shunzi2));
			}

			Shunzi shunzi3 = gouXingCalculator.tryAndMakeShunziWithPai3(pai);
			if (shunzi3 != null) {
				actionCandidates.put(actionCandidates.size() + 1,
						new MajiangChiAction(actionCandidates.size() + 1, id, pai, shunzi3));
			}

		}
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

	public List<MajiangPai> getShoupaiList() {
		return shoupaiList;
	}

	public void setShoupaiList(List<MajiangPai> shoupaiList) {
		this.shoupaiList = shoupaiList;
	}

	public Set<MajiangPai> getGuipaiTypeSet() {
		return guipaiTypeSet;
	}

	public void setGuipaiTypeSet(Set<MajiangPai> guipaiTypeSet) {
		this.guipaiTypeSet = guipaiTypeSet;
	}

	public List<MajiangPai> getPublicPaiList() {
		return publicPaiList;
	}

	public void setPublicPaiList(List<MajiangPai> publicPaiList) {
		this.publicPaiList = publicPaiList;
	}

	public Map<Integer, MajiangPlayerAction> getActionCandidates() {
		return actionCandidates;
	}

	public void setActionCandidates(Map<Integer, MajiangPlayerAction> actionCandidates) {
		this.actionCandidates = actionCandidates;
	}

	public GouXingCalculator getGouXingCalculator() {
		return gouXingCalculator;
	}

	public void setGouXingCalculator(GouXingCalculator gouXingCalculator) {
		this.gouXingCalculator = gouXingCalculator;
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

}
