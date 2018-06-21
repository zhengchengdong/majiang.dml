package com.dml.majiang;

/**
 * 序数牌组
 * 
 * @author Neo
 *
 */
public class XuShuPaiZu {

	private int[] paiQuantityArray = new int[9];

	private int lian;

	private int totalPai;

	private int atleastGuiPai;

	private int smallCode;

	private long bigCode;

	private boolean bigCodeMode;

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
