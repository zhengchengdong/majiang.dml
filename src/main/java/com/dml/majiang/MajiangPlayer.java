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
	private List<MajiangPai> publicPaiList = new ArrayList<>();;
	/**
	 * 手牌中的鬼牌
	 */
	private List<MajiangPai> guipaiList = new ArrayList<>();;
	/**
	 * 标示什么牌是鬼牌
	 */
	private Set<MajiangPai> guipaiTypeSet = new HashSet<>();
	// - xingPaiMap:Map<MajiangPai,MajiangPai>//什么牌当成了什么牌

	private Map<Integer, MajiangPlayerAction> actionCandidates = new HashMap<>();

	private GouXingCalculator gouXingCalculator = new GouXingCalculator();

	/**
	 * 摸进的牌。只是展示，实际在手牌中。
	 */
	private MajiangPai publicMoPai;

	public void addGuipaiType(MajiangPai guipaiType) {
		guipaiTypeSet.add(guipaiType);
	}

	public void addShoupai(MajiangPai pai) {
		shoupaiList.add(pai);
		Collections.sort(shoupaiList);
		if (guipaiTypeSet.contains(pai)) {
			guipaiList.add(pai);
			Collections.sort(guipaiList);
		}
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

	public List<MajiangPai> getGuipaiList() {
		return guipaiList;
	}

	public void setGuipaiList(List<MajiangPai> guipaiList) {
		this.guipaiList = guipaiList;
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

}
