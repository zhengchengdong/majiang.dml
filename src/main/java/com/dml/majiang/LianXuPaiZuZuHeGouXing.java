package com.dml.majiang;

/**
 * 连续牌组组合构型。 包含有构型编码，和组合出这个构型的连续牌组构型对象
 * 
 * @author Neo
 *
 */
public class LianXuPaiZuZuHeGouXing implements GouXing {

	private int gouXingCode;

	private LianXuPaiZuGouXing[] zuHePaiZuGouXingArray;

	public int getGouXingCode() {
		return gouXingCode;
	}

	public void setGouXingCode(int gouXingCode) {
		this.gouXingCode = gouXingCode;
	}

	public LianXuPaiZuGouXing[] getZuHePaiZuGouXingArray() {
		return zuHePaiZuGouXingArray;
	}

	public void setZuHePaiZuGouXingArray(LianXuPaiZuGouXing[] zuHePaiZuGouXingArray) {
		this.zuHePaiZuGouXingArray = zuHePaiZuGouXingArray;
	}

}
