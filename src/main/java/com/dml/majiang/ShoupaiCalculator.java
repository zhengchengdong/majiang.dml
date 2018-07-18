package com.dml.majiang;

import java.util.ArrayList;
import java.util.List;

/**
 * 手牌计算器
 * 
 * @author Neo
 *
 */
public class ShoupaiCalculator {

	/**
	 * 0-8万,9-17筒,18-26条,27东风,28南风,29西风,30北风,31红中,32发财,33白板
	 */
	private int[] paiQuantityArray = new int[34];

	// public List<GouXing> calculateAllGouXing() {
	// List<GouXing> result = new ArrayList<>();
	// List<LianXuPaiZu> lianXuPaiZuList = new ArrayList<>();
	// List<Integer> duLiPaiIdxList = new ArrayList<>();
	// parseXuShuPai(0, 8, lianXuPaiZuList, duLiPaiIdxList);
	// parseXuShuPai(9, 17, lianXuPaiZuList, duLiPaiIdxList);
	// parseXuShuPai(18, 26, lianXuPaiZuList, duLiPaiIdxList);
	// parseZiPai(duLiPaiIdxList);
	// lianXuPaiZuList.forEach((lianXuPaiZu) -> lianXuPaiZu.calculateCode());
	// Collections.sort(lianXuPaiZuList);
	//
	// // 开始分情况查询构型
	// boolean hasLianXuPaiZu = !lianXuPaiZuList.isEmpty();
	// boolean hasDuLiPaiZu = !duLiPaiIdxList.isEmpty();
	// if (hasLianXuPaiZu && !hasDuLiPaiZu) {// 手牌全由连续牌组组成
	// if (lianXuPaiZuList.size() == 1) {// 只有一个连续牌组
	// LianXuPaiZu lianXuPaiZu = lianXuPaiZuList.get(0);
	// int idx;
	// if (!lianXuPaiZu.isBigCodeMode()) {
	// idx =
	// GouXingCalculator.lianXuPaiZuGouXingsIdxArray[lianXuPaiZu.getSmallCode()];
	// } else {
	// idx =
	// GouXingCalculator.lianXuPaiZuGouXingsIdxMap.get(lianXuPaiZu.getBigCode());
	// }
	// LianXuPaiZuGouXing[] lianXuPaiZuGouXingArray =
	// GouXingCalculator.yiLianXuPaiZuGouXingsArray[idx];
	// for (int i = 0; i < lianXuPaiZuGouXingArray.length; i++) {
	// result.add(lianXuPaiZuGouXingArray[i]);
	// }
	// } else if (lianXuPaiZuList.size() == 2) {// 由两个连续牌组组成
	// LianXuPaiZu lianXuPaiZu1 = lianXuPaiZuList.get(0);
	// int idx1;
	// if (!lianXuPaiZu1.isBigCodeMode()) {
	// idx1 =
	// GouXingCalculator.lianXuPaiZuGouXingsIdxArray[lianXuPaiZu1.getSmallCode()];
	// } else {
	// idx1 =
	// GouXingCalculator.lianXuPaiZuGouXingsIdxMap.get(lianXuPaiZu1.getBigCode());
	// }
	//
	// LianXuPaiZu lianXuPaiZu2 = lianXuPaiZuList.get(1);
	// int idx2;
	// if (!lianXuPaiZu2.isBigCodeMode()) {
	// idx2 =
	// GouXingCalculator.lianXuPaiZuGouXingsIdxArray[lianXuPaiZu2.getSmallCode()];
	// } else {
	// idx2 =
	// GouXingCalculator.lianXuPaiZuGouXingsIdxMap.get(lianXuPaiZu2.getBigCode());
	// }
	//
	// LianXuPaiZuZuHeGouXing[] lianXuPaiZuZuHeGouXingArray =
	// GouXingCalculator.erLianXuPaiZuGouXingsArray[idx1
	// * GouXingCalculator.erLianXuPaiZuGouXingsArrayIdx1Mod + idx2];
	//
	// for (int i = 0; i < lianXuPaiZuZuHeGouXingArray.length; i++) {
	// result.add(lianXuPaiZuZuHeGouXingArray[i]);
	// }
	// } else if (lianXuPaiZuList.size() == 3) {// 由三个连续牌组组成
	// LianXuPaiZu lianXuPaiZu1 = lianXuPaiZuList.get(0);
	// int idx1;
	// if (!lianXuPaiZu1.isBigCodeMode()) {
	// idx1 =
	// GouXingCalculator.lianXuPaiZuGouXingsIdxArray[lianXuPaiZu1.getSmallCode()];
	// } else {
	// idx1 =
	// GouXingCalculator.lianXuPaiZuGouXingsIdxMap.get(lianXuPaiZu1.getBigCode());
	// }
	//
	// LianXuPaiZu lianXuPaiZu2 = lianXuPaiZuList.get(1);
	// int idx2;
	// if (!lianXuPaiZu2.isBigCodeMode()) {
	// idx2 =
	// GouXingCalculator.lianXuPaiZuGouXingsIdxArray[lianXuPaiZu2.getSmallCode()];
	// } else {
	// idx2 =
	// GouXingCalculator.lianXuPaiZuGouXingsIdxMap.get(lianXuPaiZu2.getBigCode());
	// }
	//
	// LianXuPaiZu lianXuPaiZu3 = lianXuPaiZuList.get(2);
	// int idx3;
	// if (!lianXuPaiZu3.isBigCodeMode()) {
	// idx3 =
	// GouXingCalculator.lianXuPaiZuGouXingsIdxArray[lianXuPaiZu3.getSmallCode()];
	// } else {
	// idx3 =
	// GouXingCalculator.lianXuPaiZuGouXingsIdxMap.get(lianXuPaiZu3.getBigCode());
	// }
	//
	// LianXuPaiZuZuHeGouXing[] lianXuPaiZuZuHeGouXingArray =
	// GouXingCalculator.sanLianXuPaiZuGouXingsArray[idx1
	// * GouXingCalculator.sanLianXuPaiZuGouXingsArrayIdx1Mod
	// + idx2 * GouXingCalculator.sanLianXuPaiZuGouXingsArrayIdx2Mod + idx3];
	//
	// for (int i = 0; i < lianXuPaiZuZuHeGouXingArray.length; i++) {
	// result.add(lianXuPaiZuZuHeGouXingArray[i]);
	// }
	// } else if (lianXuPaiZuList.size() == 4) {// 由四个连续牌组组成
	// LianXuPaiZu lianXuPaiZu1 = lianXuPaiZuList.get(0);
	// int idx1;
	// if (!lianXuPaiZu1.isBigCodeMode()) {
	// idx1 =
	// GouXingCalculator.lianXuPaiZuGouXingsIdxArray[lianXuPaiZu1.getSmallCode()];
	// } else {
	// idx1 =
	// GouXingCalculator.lianXuPaiZuGouXingsIdxMap.get(lianXuPaiZu1.getBigCode());
	// }
	//
	// LianXuPaiZu lianXuPaiZu2 = lianXuPaiZuList.get(1);
	// int idx2;
	// if (!lianXuPaiZu2.isBigCodeMode()) {
	// idx2 =
	// GouXingCalculator.lianXuPaiZuGouXingsIdxArray[lianXuPaiZu2.getSmallCode()];
	// } else {
	// idx2 =
	// GouXingCalculator.lianXuPaiZuGouXingsIdxMap.get(lianXuPaiZu2.getBigCode());
	// }
	//
	// LianXuPaiZu lianXuPaiZu3 = lianXuPaiZuList.get(2);
	// int idx3;
	// if (!lianXuPaiZu3.isBigCodeMode()) {
	// idx3 =
	// GouXingCalculator.lianXuPaiZuGouXingsIdxArray[lianXuPaiZu3.getSmallCode()];
	// } else {
	// idx3 =
	// GouXingCalculator.lianXuPaiZuGouXingsIdxMap.get(lianXuPaiZu3.getBigCode());
	// }
	//
	// LianXuPaiZu lianXuPaiZu4 = lianXuPaiZuList.get(3);
	// int idx4;
	// if (!lianXuPaiZu4.isBigCodeMode()) {
	// idx4 =
	// GouXingCalculator.lianXuPaiZuGouXingsIdxArray[lianXuPaiZu4.getSmallCode()];
	// } else {
	// idx4 =
	// GouXingCalculator.lianXuPaiZuGouXingsIdxMap.get(lianXuPaiZu4.getBigCode());
	// }
	//
	// LianXuPaiZuZuHeGouXing[] lianXuPaiZuZuHeGouXingArray =
	// GouXingCalculator.siLianXuPaiZuGouXingsArray[idx1
	// * GouXingCalculator.siLianXuPaiZuGouXingsArrayIdx1Mod
	// + idx2 * GouXingCalculator.siLianXuPaiZuGouXingsArrayIdx2Mod
	// + idx3 * GouXingCalculator.siLianXuPaiZuGouXingsArrayIdx3Mod + idx4];
	//
	// for (int i = 0; i < lianXuPaiZuZuHeGouXingArray.length; i++) {
	// result.add(lianXuPaiZuZuHeGouXingArray[i]);
	// }
	// } else if (lianXuPaiZuList.size() == 5) {// 由五个连续牌组组成
	// LianXuPaiZu lianXuPaiZu1 = lianXuPaiZuList.get(0);
	// int idx1;
	// if (!lianXuPaiZu1.isBigCodeMode()) {
	// idx1 =
	// GouXingCalculator.lianXuPaiZuGouXingsIdxArray[lianXuPaiZu1.getSmallCode()];
	// } else {
	// idx1 =
	// GouXingCalculator.lianXuPaiZuGouXingsIdxMap.get(lianXuPaiZu1.getBigCode());
	// }
	//
	// LianXuPaiZu lianXuPaiZu2 = lianXuPaiZuList.get(1);
	// int idx2;
	// if (!lianXuPaiZu2.isBigCodeMode()) {
	// idx2 =
	// GouXingCalculator.lianXuPaiZuGouXingsIdxArray[lianXuPaiZu2.getSmallCode()];
	// } else {
	// idx2 =
	// GouXingCalculator.lianXuPaiZuGouXingsIdxMap.get(lianXuPaiZu2.getBigCode());
	// }
	//
	// LianXuPaiZu lianXuPaiZu3 = lianXuPaiZuList.get(2);
	// int idx3;
	// if (!lianXuPaiZu3.isBigCodeMode()) {
	// idx3 =
	// GouXingCalculator.lianXuPaiZuGouXingsIdxArray[lianXuPaiZu3.getSmallCode()];
	// } else {
	// idx3 =
	// GouXingCalculator.lianXuPaiZuGouXingsIdxMap.get(lianXuPaiZu3.getBigCode());
	// }
	//
	// LianXuPaiZu lianXuPaiZu4 = lianXuPaiZuList.get(3);
	// int idx4;
	// if (!lianXuPaiZu4.isBigCodeMode()) {
	// idx4 =
	// GouXingCalculator.lianXuPaiZuGouXingsIdxArray[lianXuPaiZu4.getSmallCode()];
	// } else {
	// idx4 =
	// GouXingCalculator.lianXuPaiZuGouXingsIdxMap.get(lianXuPaiZu4.getBigCode());
	// }
	//
	// LianXuPaiZu lianXuPaiZu5 = lianXuPaiZuList.get(4);
	// int idx5;
	// if (!lianXuPaiZu5.isBigCodeMode()) {
	// idx5 =
	// GouXingCalculator.lianXuPaiZuGouXingsIdxArray[lianXuPaiZu5.getSmallCode()];
	// } else {
	// idx5 =
	// GouXingCalculator.lianXuPaiZuGouXingsIdxMap.get(lianXuPaiZu5.getBigCode());
	// }
	//
	// LianXuPaiZuZuHeGouXing[] lianXuPaiZuZuHeGouXingArray =
	// GouXingCalculator.wuLianXuPaiZuGouXingsArray[idx1
	// * GouXingCalculator.wuLianXuPaiZuGouXingsArrayIdx1Mod
	// + idx2 * GouXingCalculator.wuLianXuPaiZuGouXingsArrayIdx2Mod
	// + idx3 * GouXingCalculator.wuLianXuPaiZuGouXingsArrayIdx3Mod
	// + idx4 * GouXingCalculator.wuLianXuPaiZuGouXingsArrayIdx4Mod + idx5];
	//
	// for (int i = 0; i < lianXuPaiZuZuHeGouXingArray.length; i++) {
	// result.add(lianXuPaiZuZuHeGouXingArray[i]);
	// }
	// }
	// } else if (!hasLianXuPaiZu && hasDuLiPaiZu) {// 手牌全由独立牌组成
	// int[] duLiPaiCountArray = new int[10];
	// int totalPai = 0;
	// for (int duLiPaiIdx : duLiPaiIdxList) {
	// int duLiPaiQuantity = paiQuantityArray[duLiPaiIdx];
	// totalPai += duLiPaiQuantity;
	// duLiPaiCountArray[duLiPaiQuantity - 1]++;
	// }
	// DuLiPaiZu duLiPaiZu = new DuLiPaiZu(0, duLiPaiCountArray, totalPai, 0);
	// duLiPaiZu.calculateCode();
	// int idx = GouXingCalculator.duLiPaiZuGouXingsIdxArray[duLiPaiZu.getCode()];
	// DuLiPaiZuGouXing[] duLiPaiZuGouXingArray =
	// GouXingCalculator.duLiPaiZuGouXingsArray[idx];
	// for (int i = 0; i < duLiPaiZuGouXingArray.length; i++) {
	// result.add(duLiPaiZuGouXingArray[i]);
	// }
	// } else {// 手牌由连续牌组和独立牌组组成
	// // TODO
	// }
	// }

	private void parseZiPai(List<Integer> duLiPaiIdxList) {
		for (int i = 27; i <= 33; i++) {
			if (paiQuantityArray[i] > 0) {
				duLiPaiIdxList.add(i);
			}
		}
	}

	private void parseXuShuPai(int startIdx, int endIdx, List<LianXuPaiZu> lianXuPaiZuList,
			List<Integer> duLiPaiIdxList) {
		int lian = 0;
		int totalPai = 0;
		boolean bigCodeMode = false;
		for (int i = startIdx; i <= endIdx; i++) {
			int paiQuantity = paiQuantityArray[i];
			if (paiQuantity > 0) {
				lian++;
				totalPai += paiQuantity;
				if (paiQuantity > 7) {
					bigCodeMode = true;
				}
			} else {
				if (lian >= 3) {
					int[] lianXuPaiZuPaiQuantityArray = new int[lian];
					System.arraycopy(paiQuantityArray, i - lian, lianXuPaiZuPaiQuantityArray, 0, lian);
					lianXuPaiZuList.add(new LianXuPaiZu(lianXuPaiZuPaiQuantityArray, totalPai, 0, bigCodeMode));
				} else {
					for (int j = lian; j > 0; j--) {
						duLiPaiIdxList.add(i - j);
					}
				}
				lian = 0;
			}
		}
		// 收尾
		if (lian >= 3) {
			int[] lianXuPaiZuPaiQuantityArray = new int[lian];
			System.arraycopy(paiQuantityArray, endIdx - lian, lianXuPaiZuPaiQuantityArray, 0, lian);
			lianXuPaiZuList.add(new LianXuPaiZu(lianXuPaiZuPaiQuantityArray, totalPai, 0, bigCodeMode));
		} else {
			for (int j = lian; j > 0; j--) {
				duLiPaiIdxList.add(endIdx - j);
			}
		}
	}

	public void addPai(MajiangPai pai) {
		int paiOrdinal = pai.ordinal();
		paiQuantityArray[paiOrdinal]++;
	}

	public void removePai(MajiangPai pai) {
		int paiOrdinal = pai.ordinal();
		paiQuantityArray[paiOrdinal]--;
	}

	public void removePai(MajiangPai pai, int zhangShu) {
		int paiOrdinal = pai.ordinal();
		paiQuantityArray[paiOrdinal] -= zhangShu;
	}

	public Shunzi tryAndMakeShunziWithPai1(MajiangPai pai1) {
		int paiOrdinal = pai1.ordinal();
		if (paiOrdinal >= 0 && paiOrdinal <= 8) {// 万
			if (paiOrdinal <= 6) {
				if (paiQuantityArray[paiOrdinal + 1] > 0 && paiQuantityArray[paiOrdinal + 2] > 0) {
					Shunzi shunzi = new Shunzi(pai1, MajiangPai.valueOf(paiOrdinal + 1),
							MajiangPai.valueOf(paiOrdinal + 2));
					return shunzi;
				} else {
					return null;
				}
			} else {
				return null;
			}
		} else if (paiOrdinal >= 9 && paiOrdinal <= 17) {// 筒
			if (paiOrdinal <= 15) {
				if (paiQuantityArray[paiOrdinal + 1] > 0 && paiQuantityArray[paiOrdinal + 2] > 0) {
					Shunzi shunzi = new Shunzi(pai1, MajiangPai.valueOf(paiOrdinal + 1),
							MajiangPai.valueOf(paiOrdinal + 2));
					return shunzi;
				} else {
					return null;
				}
			} else {
				return null;
			}
		} else if (paiOrdinal >= 18 && paiOrdinal <= 26) {// 条
			if (paiOrdinal <= 24) {
				if (paiQuantityArray[paiOrdinal + 1] > 0 && paiQuantityArray[paiOrdinal + 2] > 0) {
					Shunzi shunzi = new Shunzi(pai1, MajiangPai.valueOf(paiOrdinal + 1),
							MajiangPai.valueOf(paiOrdinal + 2));
					return shunzi;
				} else {
					return null;
				}
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	public Shunzi tryAndMakeShunziWithPai2(MajiangPai pai2) {
		int paiOrdinal = pai2.ordinal();
		if (paiOrdinal >= 0 && paiOrdinal <= 8) {// 万
			if (paiOrdinal >= 1 && paiOrdinal <= 7) {
				if (paiQuantityArray[paiOrdinal - 1] > 0 && paiQuantityArray[paiOrdinal + 1] > 0) {
					Shunzi shunzi = new Shunzi(MajiangPai.valueOf(paiOrdinal - 1), pai2,
							MajiangPai.valueOf(paiOrdinal + 1));
					return shunzi;
				} else {
					return null;
				}
			} else {
				return null;
			}
		} else if (paiOrdinal >= 9 && paiOrdinal <= 17) {// 筒
			if (paiOrdinal >= 8 && paiOrdinal <= 16) {
				if (paiQuantityArray[paiOrdinal - 1] > 0 && paiQuantityArray[paiOrdinal + 1] > 0) {
					Shunzi shunzi = new Shunzi(MajiangPai.valueOf(paiOrdinal - 1), pai2,
							MajiangPai.valueOf(paiOrdinal + 1));
					return shunzi;
				} else {
					return null;
				}
			} else {
				return null;
			}
		} else if (paiOrdinal >= 18 && paiOrdinal <= 26) {// 条
			if (paiOrdinal >= 17 && paiOrdinal <= 25) {
				if (paiQuantityArray[paiOrdinal - 1] > 0 && paiQuantityArray[paiOrdinal + 1] > 0) {
					Shunzi shunzi = new Shunzi(MajiangPai.valueOf(paiOrdinal - 1), pai2,
							MajiangPai.valueOf(paiOrdinal + 1));
					return shunzi;
				} else {
					return null;
				}
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	public Shunzi tryAndMakeShunziWithPai3(MajiangPai pai3) {
		int paiOrdinal = pai3.ordinal();
		if (paiOrdinal >= 0 && paiOrdinal <= 8) {// 万
			if (paiOrdinal >= 2) {
				if (paiQuantityArray[paiOrdinal - 1] > 0 && paiQuantityArray[paiOrdinal - 2] > 0) {
					Shunzi shunzi = new Shunzi(MajiangPai.valueOf(paiOrdinal - 2), MajiangPai.valueOf(paiOrdinal - 1),
							pai3);
					return shunzi;
				} else {
					return null;
				}
			} else {
				return null;
			}
		} else if (paiOrdinal >= 9 && paiOrdinal <= 17) {// 筒
			if (paiOrdinal >= 11) {
				if (paiQuantityArray[paiOrdinal - 1] > 0 && paiQuantityArray[paiOrdinal - 2] > 0) {
					Shunzi shunzi = new Shunzi(MajiangPai.valueOf(paiOrdinal - 2), MajiangPai.valueOf(paiOrdinal - 1),
							pai3);
					return shunzi;
				} else {
					return null;
				}
			} else {
				return null;
			}
		} else if (paiOrdinal >= 18 && paiOrdinal <= 26) {// 条
			if (paiOrdinal >= 20) {
				if (paiQuantityArray[paiOrdinal - 1] > 0 && paiQuantityArray[paiOrdinal - 2] > 0) {
					Shunzi shunzi = new Shunzi(MajiangPai.valueOf(paiOrdinal - 2), MajiangPai.valueOf(paiOrdinal - 1),
							pai3);
					return shunzi;
				} else {
					return null;
				}
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	public List<MajiangPai> findAllPaiQuantityIsFour() {
		List<MajiangPai> allGangzi = new ArrayList<>();
		for (int i = 0; i < paiQuantityArray.length; i++) {
			if (paiQuantityArray[i] == 4) {
				allGangzi.add(MajiangPai.valueOf(i));
			}
		}
		return allGangzi;
	}

	public int count(MajiangPai pai) {
		int paiOrdinal = pai.ordinal();
		return paiQuantityArray[paiOrdinal];
	}

	public int[] getPaiQuantityArray() {
		return paiQuantityArray;
	}

	public void setPaiQuantityArray(int[] paiQuantityArray) {
		this.paiQuantityArray = paiQuantityArray;
	}

}
