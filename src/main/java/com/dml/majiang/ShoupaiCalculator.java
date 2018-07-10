package com.dml.majiang;

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
