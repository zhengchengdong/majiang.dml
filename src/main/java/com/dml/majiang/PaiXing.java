package com.dml.majiang;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class PaiXing {

	private List<Danpai> danpaiList;
	private List<Duizi> duiziList;
	private List<Kezi> keziList;
	private List<Gangzi> gangziList;
	private List<Shunzi> shunziList;

	public static PaiXing combine(PaiXing[] paiXingArray) {
		PaiXing newPaiXing = new PaiXing();
		List<Danpai> newDanpaiList = new ArrayList<>();
		List<Duizi> newDuiziList = new ArrayList<>();
		List<Kezi> newKeziList = new ArrayList<>();
		List<Gangzi> newGangziList = new ArrayList<>();
		List<Shunzi> newShunziList = new ArrayList<>();
		newPaiXing.setDanpaiList(newDanpaiList);
		newPaiXing.setDuiziList(newDuiziList);
		newPaiXing.setGangziList(newGangziList);
		newPaiXing.setKeziList(newKeziList);
		newPaiXing.setShunziList(newShunziList);
		for (int i = 0; i < paiXingArray.length; i++) {
			PaiXing paiXing = paiXingArray[i];
			newDanpaiList.addAll(paiXing.danpaiList);
			newDuiziList.addAll(paiXing.duiziList);
			newKeziList.addAll(paiXing.keziList);
			newGangziList.addAll(paiXing.gangziList);
			newShunziList.addAll(paiXing.shunziList);
		}
		return newPaiXing;
	}

	/**
	 * 返回list是因为在确定鬼牌当牌方案的情况下,对于某个鬼牌当的花色有多张牌,又分属不同组的情况（比如两个一万，一个属于一万对子，一个属于一二三万顺子）,产生多个ShoupaiPaiXing
	 * 
	 * @param guipaiDangPaiArray
	 * @return
	 */
	public List<ShoupaiPaiXing> generateShoupaiPaiXingByGuipaiDangPai(GuipaiDangPai[] guipaiDangPaiArray) {
		List<ShoupaiPaiXing> shoupaiPaiXingList = new ArrayList<>();
		int allFenZuCount = danpaiList.size() + duiziList.size() + keziList.size() + gangziList.size()
				+ shunziList.size();
		int guipaiCount = guipaiDangPaiArray.length;
		MajiangPaiFenZu[] allFenZuArray = new MajiangPaiFenZu[allFenZuCount];
		int i = 0;
		for (MajiangPaiFenZu fenZu : danpaiList) {
			allFenZuArray[i++] = fenZu;
		}
		for (MajiangPaiFenZu fenZu : duiziList) {
			allFenZuArray[i++] = fenZu;
		}
		for (MajiangPaiFenZu fenZu : keziList) {
			allFenZuArray[i++] = fenZu;
		}
		for (MajiangPaiFenZu fenZu : gangziList) {
			allFenZuArray[i++] = fenZu;
		}
		for (MajiangPaiFenZu fenZu : shunziList) {
			allFenZuArray[i++] = fenZu;
		}

		List<Integer>[] guipaiDangFenZuIdxListArray = new List[guipaiCount];
		for (int j = 0; j < guipaiCount; j++) {
			List<Integer> guipaiDangFenZuIdxList = new ArrayList<>();
			guipaiDangFenZuIdxListArray[j] = guipaiDangFenZuIdxList;
			GuipaiDangPai guipaiDangPai = guipaiDangPaiArray[j];
			for (int k = 0; k < allFenZuCount; k++) {
				MajiangPaiFenZu fenZu = allFenZuArray[k];
				if (fenZu.countPai(guipaiDangPai.getDangpai()) > 0) {
					guipaiDangFenZuIdxList.add(k);
				}
			}
		}

		int maxZuheCode = 1;
		int[] guipaiDangFenZuIdxListCountArray = new int[guipaiCount];
		for (int j = 0; j < guipaiCount; j++) {
			int count = guipaiDangFenZuIdxListArray[j].size();
			guipaiDangFenZuIdxListCountArray[j] = count;
			maxZuheCode *= count;
		}
		int[] modArray = new int[guipaiCount];
		int mod = 1;
		for (int j = 0; j < guipaiDangFenZuIdxListCountArray.length; j++) {
			modArray[guipaiCount - 1 - j] = mod;
			mod *= guipaiDangFenZuIdxListCountArray[j];
		}

		int[] guipaiDangFenZuIdxZuheArray = new int[guipaiCount];
		for (int code = 0; code < maxZuheCode; code++) {
			int temp = code;
			for (int j = 0; j < modArray.length; j++) {
				int shang = temp / modArray[j];
				guipaiDangFenZuIdxZuheArray[j] = guipaiDangFenZuIdxListArray[j].get(shang);
				temp = temp % modArray[j];
			}
			// guipaiDangFenZuIdxZuheArray是一种组合结果，需要先对其验证是否超当
			// 对哪个分组的什么牌当了几次，这个次数是否合法是需要验证的
			Map<Integer, Map<MajiangPai, Integer>> map = new HashMap<>();
			for (int k = 0; k < guipaiCount; k++) {
				GuipaiDangPai guipaiDangPai = guipaiDangPaiArray[k];
				int fenZuIdx = guipaiDangFenZuIdxZuheArray[k];
				Map<MajiangPai, Integer> mapT = map.get(fenZuIdx);
				if (mapT == null) {
					mapT = new HashMap<>();
					map.put(fenZuIdx, mapT);
				}
				Integer dangCount = mapT.get(guipaiDangPai.getDangpai());
				if (dangCount == null) {
					dangCount = 0;
				}
				mapT.put(guipaiDangPai.getDangpai(), dangCount + 1);
			}
			boolean allSuccess = true;
			for (Entry<Integer, Map<MajiangPai, Integer>> entry : map.entrySet()) {
				MajiangPaiFenZu fenZu = allFenZuArray[entry.getKey()];
				Map<MajiangPai, Integer> mapT = entry.getValue();
				boolean success = true;
				for (Entry<MajiangPai, Integer> entryT : mapT.entrySet()) {
					if (fenZu.countPai(entryT.getKey()) < entryT.getValue()) {// 不合法
						success = false;
						break;
					}
				}
				if (!success) {
					allSuccess = false;
					break;
				}
			}

			if (allSuccess) {// 验证通过
				// 对该种组合生成结果
				ShoupaiPaiXing shoupaiPaiXing = new ShoupaiPaiXing();
				List<ShoupaiDanpai> danpaiList = new ArrayList<>();
				List<ShoupaiDuiziZu> duiziList = new ArrayList<>();
				List<ShoupaiKeziZu> keziList = new ArrayList<>();
				List<ShoupaiGangziZu> gangziList = new ArrayList<>();
				List<ShoupaiShunziZu> shunziList = new ArrayList<>();
				shoupaiPaiXing.setDanpaiList(danpaiList);
				shoupaiPaiXing.setDuiziList(duiziList);
				shoupaiPaiXing.setGangziList(gangziList);
				shoupaiPaiXing.setKeziList(keziList);
				shoupaiPaiXing.setShunziList(shunziList);

				for (int j = 0; j < allFenZuCount; j++) {
					MajiangPaiFenZu fenZu = allFenZuArray[j];
					ShoupaiMajiangPaiFenZu shoupaiFenZu = fenZu.generateShoupaiMajiangPaiFenZuSkeleton();
					for (int k = 0; k < guipaiCount; k++) {
						int guipaiDangFenZuIdx = guipaiDangFenZuIdxZuheArray[k];
						if (guipaiDangFenZuIdx == j) {
							GuipaiDangPai guipaiDangPai = guipaiDangPaiArray[k];
							shoupaiFenZu.addShoupaiJiesuanPai(guipaiDangPai);
						}
					}
					shoupaiFenZu.fillAllBlankPaiWithBenPai();
					if (shoupaiFenZu instanceof ShoupaiDanpai) {
						danpaiList.add((ShoupaiDanpai) shoupaiFenZu);
					} else if (shoupaiFenZu instanceof ShoupaiDuiziZu) {
						duiziList.add((ShoupaiDuiziZu) shoupaiFenZu);
					} else if (shoupaiFenZu instanceof ShoupaiKeziZu) {
						keziList.add((ShoupaiKeziZu) shoupaiFenZu);
					} else if (shoupaiFenZu instanceof ShoupaiGangziZu) {
						gangziList.add((ShoupaiGangziZu) shoupaiFenZu);
					} else if (shoupaiFenZu instanceof ShoupaiShunziZu) {
						shunziList.add((ShoupaiShunziZu) shoupaiFenZu);
					}

				}
				shoupaiPaiXingList.add(shoupaiPaiXing);
			}

		}
		return shoupaiPaiXingList;
	}

	private ShoupaiJiesuanPai makeShoupaiDangPai(Map<MajiangPai, List<MajiangPai>> dangpaiGuipaiListMap,
			MajiangPai shijipai) {
		if (dangpaiGuipaiListMap.containsKey(shijipai)) {
			List<MajiangPai> guipaiList = dangpaiGuipaiListMap.get(shijipai);
			MajiangPai guipai = guipaiList.remove(0);
			if (guipaiList.isEmpty()) {
				dangpaiGuipaiListMap.remove(shijipai);
			}
			return new GuipaiDangPai(guipai, shijipai);
		} else {
			return new BenPai(shijipai);
		}
	}

	public List<Danpai> getDanpaiList() {
		return danpaiList;
	}

	public void setDanpaiList(List<Danpai> danpaiList) {
		this.danpaiList = danpaiList;
	}

	public List<Duizi> getDuiziList() {
		return duiziList;
	}

	public void setDuiziList(List<Duizi> duiziList) {
		this.duiziList = duiziList;
	}

	public List<Kezi> getKeziList() {
		return keziList;
	}

	public void setKeziList(List<Kezi> keziList) {
		this.keziList = keziList;
	}

	public List<Gangzi> getGangziList() {
		return gangziList;
	}

	public void setGangziList(List<Gangzi> gangziList) {
		this.gangziList = gangziList;
	}

	public List<Shunzi> getShunziList() {
		return shunziList;
	}

	public void setShunziList(List<Shunzi> shunziList) {
		this.shunziList = shunziList;
	}

}
