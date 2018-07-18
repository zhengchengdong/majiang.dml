package com.dml.majiang;

/**
 * 构型
 * 
 * @author Neo
 *
 */
public abstract class GouXing {

	/**
	 * 用一个int来编码:低到高--5位单牌个数，4位对子个数，3位刻子个数，3位杠子个数，3位顺子个数
	 */
	private int gouXingCode;

	public int getGouXingCode() {
		return gouXingCode;
	}

	public void setGouXingCode(int gouXingCode) {
		this.gouXingCode = gouXingCode;
	}

}
