package com.dml.majiang;

/**
 * 连续牌组独立牌组组合构型。 包含有构型编码，和组合出这个构型的连续牌组构型对象和独立牌组构型编码
 * 
 * @author Neo
 *
 */
public class LianXuPaiZuDuLiPaiZuZuHeGouXing extends GouXing {

	private LianXuPaiZuGouXing[] lianXuPaiZuGouXingArray;

	private DuLiPaiZuGouXing duLiPaiZuGouXing;

	public LianXuPaiZuGouXing[] getLianXuPaiZuGouXingArray() {
		return lianXuPaiZuGouXingArray;
	}

	public void setLianXuPaiZuGouXingArray(LianXuPaiZuGouXing[] lianXuPaiZuGouXingArray) {
		this.lianXuPaiZuGouXingArray = lianXuPaiZuGouXingArray;
	}

	public DuLiPaiZuGouXing getDuLiPaiZuGouXing() {
		return duLiPaiZuGouXing;
	}

	public void setDuLiPaiZuGouXing(DuLiPaiZuGouXing duLiPaiZuGouXing) {
		this.duLiPaiZuGouXing = duLiPaiZuGouXing;
	}

}
