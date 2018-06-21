package com.dml.majiang;

/**
 * 序数牌组
 * 
 * @author Neo
 *
 */
public class XuShuPaiZu {

	private int[] paiQuantityArray;

	private int lian;

	private int totalPai;

	private int atleastGuiPai;

	private int smallCode;

	private long bigCode;

	private boolean bigCodeMode;

	public XuShuPaiZu(int[] paiQuantityArray, int totalPai, int atleastGuiPai, boolean bigCodeMode) {
		this.lian = paiQuantityArray.length;
		this.paiQuantityArray = new int[lian];
		System.arraycopy(paiQuantityArray, 0, this.paiQuantityArray, 0, lian);
		this.totalPai = totalPai;
		this.atleastGuiPai = atleastGuiPai;
		this.bigCodeMode = bigCodeMode;
	}

	public int getLian() {
		return lian;
	}

	public int getTotalPai() {
		return totalPai;
	}

	public int getAtleastGuiPai() {
		return atleastGuiPai;
	}

	public int getSmallCode() {
		return smallCode;
	}

	public long getBigCode() {
		return bigCode;
	}

	public boolean isBigCodeMode() {
		return bigCodeMode;
	}

}
