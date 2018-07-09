package com.dml.majiang;

/**
 * 构型计算器
 * 
 * @author Neo
 *
 */
public class GouXingCalculator {

	private int[] wanPaiQuantityArray = new int[9];
	private int[] tongPaiQuantityArray = new int[9];
	private int[] tiaoPaiQuantityArray = new int[9];

	private int dongfengQuantity;
	private int nanfengQuantity;
	private int xifengQuantity;
	private int beifengQuantity;
	private int hongzhongQuantity;
	private int facaiQuantity;
	private int baibanQuantity;

	public void addPai(MajiangPai pai) {
		int paiOrdinal = pai.ordinal();
		if (paiOrdinal >= 0 && paiOrdinal <= 8) {// 万
			wanPaiQuantityArray[paiOrdinal]++;
		} else if (paiOrdinal >= 9 && paiOrdinal <= 17) {// 筒
			tongPaiQuantityArray[paiOrdinal - 9]++;
		} else if (paiOrdinal >= 18 && paiOrdinal <= 26) {// 条
			tiaoPaiQuantityArray[paiOrdinal - 18]++;
		} else if (pai.equals(MajiangPai.dongfeng)) {
			dongfengQuantity++;
		} else if (pai.equals(MajiangPai.nanfeng)) {
			nanfengQuantity++;
		} else if (pai.equals(MajiangPai.xifeng)) {
			xifengQuantity++;
		} else if (pai.equals(MajiangPai.beifeng)) {
			beifengQuantity++;
		} else if (pai.equals(MajiangPai.hongzhong)) {
			hongzhongQuantity++;
		} else if (pai.equals(MajiangPai.facai)) {
			facaiQuantity++;
		} else if (pai.equals(MajiangPai.baiban)) {
			baibanQuantity++;
		}
	}

	public void removePai(MajiangPai pai) {
		int paiOrdinal = pai.ordinal();
		if (paiOrdinal >= 0 && paiOrdinal <= 8) {// 万
			wanPaiQuantityArray[paiOrdinal]--;
		} else if (paiOrdinal >= 9 && paiOrdinal <= 17) {// 筒
			tongPaiQuantityArray[paiOrdinal - 9]--;
		} else if (paiOrdinal >= 18 && paiOrdinal <= 26) {// 条
			tiaoPaiQuantityArray[paiOrdinal - 18]--;
		} else if (pai.equals(MajiangPai.dongfeng)) {
			dongfengQuantity--;
		} else if (pai.equals(MajiangPai.nanfeng)) {
			nanfengQuantity--;
		} else if (pai.equals(MajiangPai.xifeng)) {
			xifengQuantity--;
		} else if (pai.equals(MajiangPai.beifeng)) {
			beifengQuantity--;
		} else if (pai.equals(MajiangPai.hongzhong)) {
			hongzhongQuantity--;
		} else if (pai.equals(MajiangPai.facai)) {
			facaiQuantity--;
		} else if (pai.equals(MajiangPai.baiban)) {
			baibanQuantity--;
		}
	}

	public Shunzi tryAndMakeShunziWithPai1(MajiangPai pai1) {
		int paiOrdinal = pai1.ordinal();
		if (paiOrdinal >= 0 && paiOrdinal <= 8) {// 万
			if (paiOrdinal <= 6) {
				if (wanPaiQuantityArray[paiOrdinal + 1] > 0 && wanPaiQuantityArray[paiOrdinal + 2] > 0) {
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
				if (tongPaiQuantityArray[paiOrdinal + 1] > 0 && tongPaiQuantityArray[paiOrdinal + 2] > 0) {
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
				if (tiaoPaiQuantityArray[paiOrdinal + 1] > 0 && tiaoPaiQuantityArray[paiOrdinal + 2] > 0) {
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
				if (wanPaiQuantityArray[paiOrdinal - 1] > 0 && wanPaiQuantityArray[paiOrdinal + 1] > 0) {
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
				if (tongPaiQuantityArray[paiOrdinal - 1] > 0 && tongPaiQuantityArray[paiOrdinal + 1] > 0) {
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
				if (tiaoPaiQuantityArray[paiOrdinal - 1] > 0 && tiaoPaiQuantityArray[paiOrdinal + 1] > 0) {
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
				if (wanPaiQuantityArray[paiOrdinal - 1] > 0 && wanPaiQuantityArray[paiOrdinal - 2] > 0) {
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
				if (tongPaiQuantityArray[paiOrdinal - 1] > 0 && tongPaiQuantityArray[paiOrdinal - 2] > 0) {
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
				if (tiaoPaiQuantityArray[paiOrdinal - 1] > 0 && tiaoPaiQuantityArray[paiOrdinal - 2] > 0) {
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

	public int[] getWanPaiQuantityArray() {
		return wanPaiQuantityArray;
	}

	public void setWanPaiQuantityArray(int[] wanPaiQuantityArray) {
		this.wanPaiQuantityArray = wanPaiQuantityArray;
	}

	public int[] getTongPaiQuantityArray() {
		return tongPaiQuantityArray;
	}

	public void setTongPaiQuantityArray(int[] tongPaiQuantityArray) {
		this.tongPaiQuantityArray = tongPaiQuantityArray;
	}

	public int[] getTiaoPaiQuantityArray() {
		return tiaoPaiQuantityArray;
	}

	public void setTiaoPaiQuantityArray(int[] tiaoPaiQuantityArray) {
		this.tiaoPaiQuantityArray = tiaoPaiQuantityArray;
	}

	public int getDongfengQuantity() {
		return dongfengQuantity;
	}

	public void setDongfengQuantity(int dongfengQuantity) {
		this.dongfengQuantity = dongfengQuantity;
	}

	public int getNanfengQuantity() {
		return nanfengQuantity;
	}

	public void setNanfengQuantity(int nanfengQuantity) {
		this.nanfengQuantity = nanfengQuantity;
	}

	public int getXifengQuantity() {
		return xifengQuantity;
	}

	public void setXifengQuantity(int xifengQuantity) {
		this.xifengQuantity = xifengQuantity;
	}

	public int getBeifengQuantity() {
		return beifengQuantity;
	}

	public void setBeifengQuantity(int beifengQuantity) {
		this.beifengQuantity = beifengQuantity;
	}

	public int getHongzhongQuantity() {
		return hongzhongQuantity;
	}

	public void setHongzhongQuantity(int hongzhongQuantity) {
		this.hongzhongQuantity = hongzhongQuantity;
	}

	public int getFacaiQuantity() {
		return facaiQuantity;
	}

	public void setFacaiQuantity(int facaiQuantity) {
		this.facaiQuantity = facaiQuantity;
	}

	public int getBaibanQuantity() {
		return baibanQuantity;
	}

	public void setBaibanQuantity(int baibanQuantity) {
		this.baibanQuantity = baibanQuantity;
	}

}
