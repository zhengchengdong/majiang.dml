package com.dml.majiang;

/**
 * 连续牌组独立牌组组合构型。 包含有构型编码，和组合出这个构型的连续牌组构型对象和独立牌组构型编码
 * 
 * @author Neo
 *
 */
public class LianXuPaiZuDuLiPaiZuZuHeGouXing implements GouXing {

	private int gouXingCode;

	private LianXuPaiZuGouXing[] lianXuPaiZuGouXingArray;

	private int duLiPaiZuGouXingCode;

	public int getGouXingCode() {
		return gouXingCode;
	}

	public void setGouXingCode(int gouXingCode) {
		this.gouXingCode = gouXingCode;
	}

	public LianXuPaiZuGouXing[] getLianXuPaiZuGouXingArray() {
		return lianXuPaiZuGouXingArray;
	}

	public void setLianXuPaiZuGouXingArray(LianXuPaiZuGouXing[] lianXuPaiZuGouXingArray) {
		this.lianXuPaiZuGouXingArray = lianXuPaiZuGouXingArray;
	}

	public int getDuLiPaiZuGouXingCode() {
		return duLiPaiZuGouXingCode;
	}

	public void setDuLiPaiZuGouXingCode(int duLiPaiZuGouXingCode) {
		this.duLiPaiZuGouXingCode = duLiPaiZuGouXingCode;
	}

}
