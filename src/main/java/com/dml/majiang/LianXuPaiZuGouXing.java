package com.dml.majiang;

/**
 * 连续牌组构型: 包含有构型编码，和达到这个构型的所有"连续牌组牌型"对象
 * 
 * @author Neo
 *
 */
public class LianXuPaiZuGouXing extends GouXing {

	private LianXuPaiZuPaiXing[] paiXingArrayForGouXing;

	public LianXuPaiZuPaiXing[] getPaiXingArrayForGouXing() {
		return paiXingArrayForGouXing;
	}

	public void setPaiXingArrayForGouXing(LianXuPaiZuPaiXing[] paiXingArrayForGouXing) {
		this.paiXingArrayForGouXing = paiXingArrayForGouXing;
	}

}
