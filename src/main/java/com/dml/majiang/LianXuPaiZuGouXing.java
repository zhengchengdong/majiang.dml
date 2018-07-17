package com.dml.majiang;

/**
 * 连续牌组构型: 包含有构型编码，和达到这个构型的所有"连续牌组牌型"对象
 * 
 * @author Neo
 *
 */
public class LianXuPaiZuGouXing implements GouXing {

	/**
	 * 用一个int来编码:低到高--5位单牌个数，4位对子个数，3位刻子个数，3位杠子个数，3位顺子个数
	 */
	private int gouXingCode;

	private LianXuPaiZuPaiXing[] paiXingArrayForGouXing;

	public int getGouXingCode() {
		return gouXingCode;
	}

	public void setGouXingCode(int gouXingCode) {
		this.gouXingCode = gouXingCode;
	}

	public LianXuPaiZuPaiXing[] getPaiXingArrayForGouXing() {
		return paiXingArrayForGouXing;
	}

	public void setPaiXingArrayForGouXing(LianXuPaiZuPaiXing[] paiXingArrayForGouXing) {
		this.paiXingArrayForGouXing = paiXingArrayForGouXing;
	}

}
