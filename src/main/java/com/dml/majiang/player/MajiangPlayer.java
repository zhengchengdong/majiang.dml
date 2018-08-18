package com.dml.majiang.player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.dml.majiang.pai.MajiangPai;
import com.dml.majiang.pai.XushupaiCategory;
import com.dml.majiang.pai.fenzu.GangType;
import com.dml.majiang.pai.fenzu.Gangzi;
import com.dml.majiang.pai.fenzu.Kezi;
import com.dml.majiang.pai.fenzu.Shunzi;
import com.dml.majiang.player.action.MajiangPlayerAction;
import com.dml.majiang.player.action.MajiangPlayerActionType;
import com.dml.majiang.player.action.chi.MajiangChiAction;
import com.dml.majiang.player.action.da.MajiangDaAction;
import com.dml.majiang.player.action.gang.MajiangGangAction;
import com.dml.majiang.player.action.guo.MajiangGuoAction;
import com.dml.majiang.player.action.peng.MajiangPengAction;
import com.dml.majiang.player.chupaizu.ChichuPaiZu;
import com.dml.majiang.player.chupaizu.GangchuPaiZu;
import com.dml.majiang.player.chupaizu.PengchuPaiZu;
import com.dml.majiang.player.shoupai.ShoupaiCalculator;
import com.dml.majiang.position.MajiangPosition;
import com.dml.majiang.position.MajiangPositionUtil;

public class MajiangPlayer {

	private String id;

	/**
	 * 门风
	 */
	private MajiangPosition menFeng;

	/**
	 * 已放入的手牌列表（包含鬼牌，不包含公开牌）
	 */
	private List<MajiangPai> fangruShoupaiList = new ArrayList<>();

	/**
	 * 刚摸进待处理的手牌（未放入）
	 */
	private MajiangPai gangmoShoupai;

	/**
	 * 公开的牌，不能行牌
	 */
	private List<MajiangPai> publicPaiList = new ArrayList<>();

	/**
	 * 标示什么牌是鬼牌
	 */
	private Set<MajiangPai> guipaiTypeSet = new HashSet<>();

	private Map<Integer, MajiangPlayerAction> actionCandidates = new HashMap<>();

	/**
	 * 不包含鬼牌或者公开牌。刚模来的牌未处理前不加入计算器。
	 */
	private ShoupaiCalculator shoupaiCalculator = new ShoupaiCalculator();

	/**
	 * 打出的牌列表
	 */
	private List<MajiangPai> dachupaiList = new ArrayList<>();

	private List<ChichuPaiZu> chichupaiZuList = new ArrayList<>();
	private List<PengchuPaiZu> pengchupaiZuList = new ArrayList<>();
	private List<GangchuPaiZu> gangchupaiZuList = new ArrayList<>();

	private Hu hu;

	public void addGuipaiType(MajiangPai guipaiType) {
		guipaiTypeSet.add(guipaiType);
	}

	public void addShoupai(MajiangPai pai) {
		fangruShoupaiList.add(pai);
		Collections.sort(fangruShoupaiList);
		if (!guipaiTypeSet.contains(pai)) {
			shoupaiCalculator.addPai(pai);
		}
	}

	public void addActionCandidate(MajiangPlayerAction action) {
		int idForNewAction = actionCandidates.size() + 1;
		action.setId(idForNewAction);
		actionCandidates.put(idForNewAction, action);
	}

	public MajiangPlayerAction findActionCandidate(int actionId) {
		return actionCandidates.get(actionId);
	}

	public void addPublicPai(MajiangPai pai) {
		publicPaiList.add(pai);
	}

	/**
	 * 通常不能打鬼牌
	 */
	public void generateDaActions() {
		Set<MajiangPai> daPaiSet = new HashSet<>();
		fangruShoupaiList.forEach((shoupai) -> {
			if (!guipaiTypeSet.contains(shoupai)) {
				if (!daPaiSet.contains(shoupai)) {
					addActionCandidate(new MajiangDaAction(id, shoupai));
					daPaiSet.add(shoupai);
				}
			}
		});

		if (gangmoShoupai != null && !guipaiTypeSet.contains(gangmoShoupai) && !daPaiSet.contains(gangmoShoupai)) {
			addActionCandidate(new MajiangDaAction(id, gangmoShoupai));
		}

	}

	public void clearActionCandidates() {
		actionCandidates.clear();
	}

	/**
	 * 通常不能打鬼牌
	 * 
	 * @param pai
	 */
	public void daChuPai(MajiangPai pai) {
		fangruShoupai();
		fangruShoupaiList.remove(pai);
		dachupaiList.add(pai);
		shoupaiCalculator.removePai(pai);
	}

	/**
	 * 把刚摸的牌放入手牌
	 */
	public void fangruShoupai() {
		if (gangmoShoupai != null) {
			addShoupai(gangmoShoupai);
			gangmoShoupai = null;
		}
	}

	/**
	 * 把刚摸的牌放入公开牌
	 */
	public void fangruPublicPai() {
		if (gangmoShoupai != null) {
			publicPaiList.add(gangmoShoupai);
			gangmoShoupai = null;
		}
	}

	public void chiPai(MajiangPlayer dachupaiPlayer, MajiangPai chijinpai, Shunzi chifaShunzi) {
		dachupaiPlayer.removeLatestDachupai();
		MajiangPai pai1 = chifaShunzi.getPai1();
		if (!pai1.equals(chijinpai)) {
			fangruShoupaiList.remove(pai1);
			shoupaiCalculator.removePai(pai1);
		}
		MajiangPai pai2 = chifaShunzi.getPai2();
		if (!pai2.equals(chijinpai)) {
			fangruShoupaiList.remove(pai2);
			shoupaiCalculator.removePai(pai2);
		}
		MajiangPai pai3 = chifaShunzi.getPai3();
		if (!pai3.equals(chijinpai)) {
			fangruShoupaiList.remove(pai3);
			shoupaiCalculator.removePai(pai3);
		}
		ChichuPaiZu chichuPaiZu = new ChichuPaiZu(chijinpai, chifaShunzi, dachupaiPlayer.getId(), id);
		chichupaiZuList.add(chichuPaiZu);
	}

	public void pengPai(MajiangPlayer dachupaiPlayer, MajiangPai pai) {
		dachupaiPlayer.removeLatestDachupai();
		fangruShoupaiList.remove(pai);
		fangruShoupaiList.remove(pai);
		shoupaiCalculator.removePai(pai, 2);
		PengchuPaiZu pengchuPaiZu = new PengchuPaiZu(new Kezi(pai), dachupaiPlayer.getId(), id);
		pengchupaiZuList.add(pengchuPaiZu);
	}

	public void gangDachupai(MajiangPlayer dachupaiPlayer, MajiangPai pai) {
		dachupaiPlayer.removeLatestDachupai();
		fangruShoupaiList.remove(pai);
		fangruShoupaiList.remove(pai);
		fangruShoupaiList.remove(pai);
		shoupaiCalculator.removePai(pai, 3);
		GangchuPaiZu gangchuPaiZu = new GangchuPaiZu(new Gangzi(pai), dachupaiPlayer.getId(), id, GangType.gangdachu);
		gangchupaiZuList.add(gangchuPaiZu);
	}

	public void gangMopai(MajiangPai pai) {
		fangruShoupaiList.remove(pai);
		fangruShoupaiList.remove(pai);
		fangruShoupaiList.remove(pai);
		shoupaiCalculator.removePai(pai, 3);
		GangchuPaiZu gangchuPaiZu = new GangchuPaiZu(new Gangzi(pai), null, id, GangType.shoupaigangmo);
		gangchupaiZuList.add(gangchuPaiZu);
		gangmoShoupai = null;
	}

	public void gangSigeshoupai(MajiangPai pai) {
		fangruShoupaiList.remove(pai);
		fangruShoupaiList.remove(pai);
		fangruShoupaiList.remove(pai);
		fangruShoupaiList.remove(pai);
		shoupaiCalculator.removePai(pai, 4);
		GangchuPaiZu gangchuPaiZu = new GangchuPaiZu(new Gangzi(pai), null, id, GangType.gangsigeshoupai);
		gangchupaiZuList.add(gangchuPaiZu);
		fangruShoupai();
	}

	public void keziGangMopai(MajiangPai pai) {
		Iterator<PengchuPaiZu> i = pengchupaiZuList.iterator();
		while (i.hasNext()) {
			PengchuPaiZu pengchuPai = i.next();
			if (pengchuPai.getKezi().getPaiType().equals(pai)) {
				i.remove();
				break;
			}
		}
		GangchuPaiZu gangchuPaiZu = new GangchuPaiZu(new Gangzi(pai), null, id, GangType.kezigangmo);
		gangchupaiZuList.add(gangchuPaiZu);
		gangmoShoupai = null;
	}

	public void keziGangShoupai(MajiangPai pai) {
		Iterator<PengchuPaiZu> i = pengchupaiZuList.iterator();
		while (i.hasNext()) {
			PengchuPaiZu pengchuPai = i.next();
			if (pengchuPai.getKezi().getPaiType().equals(pai)) {
				i.remove();
				break;
			}
		}
		GangchuPaiZu gangchuPaiZu = new GangchuPaiZu(new Gangzi(pai), null, id, GangType.kezigangshoupai);
		gangchupaiZuList.add(gangchuPaiZu);
		fangruShoupaiList.remove(pai);
		shoupaiCalculator.removePai(pai);
		fangruShoupai();
	}

	private void removeLatestDachupai() {
		dachupaiList.remove(dachupaiList.size() - 1);
	}

	public void tryChiAndGenerateCandidateActions(String dachupaiPlayerId, MajiangPai pai) {
		if (MajiangPai.isXushupai(pai)) {
			Shunzi shunzi1 = shoupaiCalculator.tryAndMakeShunziWithPai1(pai);
			if (shunzi1 != null) {
				addActionCandidate(new MajiangChiAction(id, dachupaiPlayerId, pai, shunzi1));
			}

			Shunzi shunzi2 = shoupaiCalculator.tryAndMakeShunziWithPai2(pai);
			if (shunzi2 != null) {
				addActionCandidate(new MajiangChiAction(id, dachupaiPlayerId, pai, shunzi2));
			}

			Shunzi shunzi3 = shoupaiCalculator.tryAndMakeShunziWithPai3(pai);
			if (shunzi3 != null) {
				addActionCandidate(new MajiangChiAction(id, dachupaiPlayerId, pai, shunzi3));
			}

		}
	}

	public void tryPengAndGenerateCandidateAction(String dachupaiPlayerId, MajiangPai pai) {
		int count = shoupaiCalculator.count(pai);
		if (count >= 2) {
			addActionCandidate(new MajiangPengAction(id, dachupaiPlayerId, pai));
		}
	}

	public void tryGangdachuAndGenerateCandidateAction(String dachupaiPlayerId, MajiangPai pai) {
		int count = shoupaiCalculator.count(pai);
		if (count >= 3) {
			addActionCandidate(new MajiangGangAction(id, dachupaiPlayerId, pai, GangType.gangdachu));
		}
	}

	public void tryShoupaigangmoAndGenerateCandidateAction() {
		int count = shoupaiCalculator.count(gangmoShoupai);
		if (count >= 3) {
			addActionCandidate(new MajiangGangAction(id, null, gangmoShoupai, GangType.shoupaigangmo));
		}
	}

	public void tryGangsigeshoupaiAndGenerateCandidateAction() {
		List<MajiangPai> gangpaiList = shoupaiCalculator.findAllPaiQuantityIsFour();
		gangpaiList.forEach(
				(gangpai) -> addActionCandidate(new MajiangGangAction(id, null, gangpai, GangType.gangsigeshoupai)));
	}

	public void tryKezigangshoupaiAndGenerateCandidateAction() {
		for (PengchuPaiZu pengchuPaiZu : pengchupaiZuList) {
			for (MajiangPai fangruShoupai : fangruShoupaiList) {
				if (pengchuPaiZu.getKezi().getPaiType().equals(fangruShoupai)) {
					addActionCandidate(new MajiangGangAction(id, null, fangruShoupai, GangType.kezigangshoupai));
					break;
				}
			}
		}
	}

	public void tryKezigangmoAndGenerateCandidateAction() {
		for (PengchuPaiZu pengchuPaiZu : pengchupaiZuList) {
			if (pengchuPaiZu.getKezi().getPaiType().equals(gangmoShoupai)) {
				addActionCandidate(new MajiangGangAction(id, null, gangmoShoupai, GangType.kezigangmo));
				return;
			}
		}
	}

	public void checkAndGenerateGuoCandidateAction() {
		for (int i = 1; i <= actionCandidates.size(); i++) {
			MajiangPlayerAction action = actionCandidates.get(i);
			if (action.getType().equals(MajiangPlayerActionType.chi)
					|| action.getType().equals(MajiangPlayerActionType.peng)
					|| action.getType().equals(MajiangPlayerActionType.gang)
					|| action.getType().equals(MajiangPlayerActionType.hu)) {
				addActionCandidate(new MajiangGuoAction(id));
				return;
			}
		}
	}

	/**
	 * 刚摸的是否是鬼牌
	 * 
	 * @return
	 */
	public boolean gangmoGuipai() {
		if (gangmoShoupai != null) {
			return guipaiTypeSet.contains(gangmoShoupai);
		} else {
			return false;
		}
	}

	/**
	 * 通常鬼牌即使是字牌也不加入计算
	 * 
	 * @return
	 */
	public boolean hasZipai() {
		for (PengchuPaiZu pengchuPaiZu : pengchupaiZuList) {
			if (MajiangPai.isZipai(pengchuPaiZu.getKezi().getPaiType())) {
				return true;
			}
		}
		for (GangchuPaiZu gangchuPaiZu : gangchupaiZuList) {
			if (MajiangPai.isZipai(gangchuPaiZu.getGangzi().getPaiType())) {
				return true;
			}
		}
		for (MajiangPai shoupai : fangruShoupaiList) {
			if (!guipaiTypeSet.contains(shoupai)) {
				if (MajiangPai.isZipai(shoupai)) {
					return true;
				}
			}
		}
		if (gangmoShoupai != null && !guipaiTypeSet.contains(gangmoShoupai)) {
			if (MajiangPai.isZipai(gangmoShoupai)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 序数牌都是万牌，或者筒牌，或者条牌。通常鬼牌即使是序数牌也不加入计算
	 * 
	 * @return
	 */
	public boolean allXushupaiInSameCategory() {
		Set<XushupaiCategory> cSet = new HashSet<>();
		for (ChichuPaiZu chichuPaiZu : chichupaiZuList) {
			XushupaiCategory xushupaiCategory = XushupaiCategory
					.getCategoryforXushupai(chichuPaiZu.getShunzi().getPai1());
			if (xushupaiCategory != null) {
				cSet.add(xushupaiCategory);
			}
		}
		for (MajiangPai shoupai : fangruShoupaiList) {
			if (!guipaiTypeSet.contains(shoupai)) {
				XushupaiCategory xushupaiCategory = XushupaiCategory.getCategoryforXushupai(shoupai);
				if (xushupaiCategory != null) {
					cSet.add(xushupaiCategory);
				}
			}
		}
		if (gangmoShoupai != null && !guipaiTypeSet.contains(gangmoShoupai)) {
			XushupaiCategory xushupaiCategory = XushupaiCategory.getCategoryforXushupai(gangmoShoupai);
			if (xushupaiCategory != null) {
				cSet.add(xushupaiCategory);
			}
		}
		return cSet.size() == 1;
	}

	public List<MajiangPai> findGuipaiList() {
		List<MajiangPai> guipaiShoupaiList = new ArrayList<>();
		fangruShoupaiList.forEach((shouPai) -> {
			if (guipaiTypeSet.contains(shouPai)) {
				guipaiShoupaiList.add(shouPai);
			}
		});
		if (gangmoShoupai != null && guipaiTypeSet.contains(gangmoShoupai)) {
			guipaiShoupaiList.add(gangmoShoupai);
		}
		return guipaiShoupaiList;
	}

	public int countGuipai() {
		int count = 0;
		for (MajiangPai shouPai : fangruShoupaiList) {
			if (guipaiTypeSet.contains(shouPai)) {
				count++;
			}
		}
		if (gangmoShoupai != null && guipaiTypeSet.contains(gangmoShoupai)) {
			count++;
		}
		return count;
	}

	public int countChichupaiZu() {
		return chichupaiZuList.size();
	}

	public int countPengchupaiZu() {
		return pengchupaiZuList.size();
	}

	public int countGangchupaiZu() {
		return gangchupaiZuList.size();
	}

	public int countPublicPai() {
		return publicPaiList.size();
	}

	public int countFangruShoupai() {
		return fangruShoupaiList.size();
	}

	public int countDachupai() {
		return dachupaiList.size();
	}

	public boolean ifPengchu(MajiangPai paiType) {
		for (PengchuPaiZu pengchuPaiZu : pengchupaiZuList) {
			if (pengchuPaiZu.getKezi().getPaiType().equals(paiType)) {
				return true;
			}
		}
		return false;
	}

	public boolean ifGangchu(MajiangPai paiType) {
		for (GangchuPaiZu gangchuPaiZu : gangchupaiZuList) {
			if (gangchuPaiZu.getGangzi().getPaiType().equals(paiType)) {
				return true;
			}
		}
		return false;
	}

	public boolean ifGangchu(MajiangPai paiType, GangType gangType) {
		for (GangchuPaiZu gangchuPaiZu : gangchupaiZuList) {
			if (gangchuPaiZu.getGangzi().getPaiType().equals(paiType) && gangchuPaiZu.getGangType().equals(gangType)) {
				return true;
			}
		}
		return false;
	}

	public MajiangPai fengpaiForMenfeng() {
		return MajiangPositionUtil.getFengpaiByPosition(menFeng);
	}

	public Set<MajiangPlayerActionType> collectActionCandidatesType() {
		Set<MajiangPlayerActionType> typesSet = new HashSet<>();
		for (MajiangPlayerAction xiajiaAction : actionCandidates.values()) {
			typesSet.add(xiajiaAction.getType());
		}
		return typesSet;
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

	public ShoupaiCalculator getShoupaiCalculator() {
		return shoupaiCalculator;
	}

	public void setShoupaiCalculator(ShoupaiCalculator shoupaiCalculator) {
		this.shoupaiCalculator = shoupaiCalculator;
	}

	public MajiangPai getGangmoShoupai() {
		return gangmoShoupai;
	}

	public void setGangmoShoupai(MajiangPai gangmoShoupai) {
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

	public Hu getHu() {
		return hu;
	}

	public void setHu(Hu hu) {
		this.hu = hu;
	}

}
