package com.dml.majiang;

import java.util.Arrays;
import java.util.List;

/**
 * 连续牌组牌型。把连续牌左移到顶之后计算出来的牌型。比如三万、四万、四万、五万，左移到顶之后就是一万、二万、二万、三万，再计算牌型
 * 
 * @author neo
 *
 */
public class LianXuPaiZuPaiXing {

	public LianXuPaiZuPaiXing(List<Integer> danpaiXuhaoList, List<Integer> duiziXuhaoList, List<Integer> keziXuhaoList,
			List<Integer> gangziXuhaoList, List<Integer> shunziXuhaoList) {

		for (int xuhao : danpaiXuhaoList) {
			danpaiCode = (short) (danpaiCode & (1 << xuhao));
		}

		int[] tempQuantityArray = new int[9];
		for (int xuhao : duiziXuhaoList) {
			tempQuantityArray[xuhao]++;
		}
		for (int i = 0; i < tempQuantityArray.length; i++) {
			duiziCode = (duiziCode | (tempQuantityArray[i] << (i * 3)));
		}

		Arrays.fill(tempQuantityArray, 0);
		for (int xuhao : keziXuhaoList) {
			tempQuantityArray[xuhao]++;
		}
		for (int i = 0; i < tempQuantityArray.length; i++) {
			keziCode = (keziCode | (tempQuantityArray[i] << (i * 2)));
		}

		Arrays.fill(tempQuantityArray, 0);
		for (int xuhao : gangziXuhaoList) {
			tempQuantityArray[xuhao]++;
		}
		for (int i = 0; i < tempQuantityArray.length; i++) {
			gangziCode = (gangziCode | (tempQuantityArray[i] << (i * 2)));
		}

		Arrays.fill(tempQuantityArray, 0);
		for (int xuhao : shunziXuhaoList) {
			tempQuantityArray[xuhao]++;
		}
		for (int i = 0; i < 7; i++) {
			shunziCode = (short) (shunziCode | (tempQuantityArray[i] << (i * 2)));
		}

	}

	public boolean equals(Object o) {
		LianXuPaiZuPaiXing px = (LianXuPaiZuPaiXing) o;
		return (danpaiCode == px.danpaiCode && duiziCode == px.duiziCode && keziCode == px.keziCode
				&& gangziCode == px.gangziCode && shunziCode == px.shunziCode);
	}

	public int hashCode() {
		return danpaiCode + duiziCode * 10 + keziCode * 100 + gangziCode * 1000 + shunziCode * 10000;
	}

	// 一个short表示单牌: 0-9位的0/1值表示该位置有没有一个单牌
	private short danpaiCode;

	// 一个int表示对子: 每3位表示该位置对子个数，9次
	private int duiziCode;

	// 一个int表示刻子: 每2位表示该位置刻子个数，9次
	private int keziCode;

	// 一个int表示杠子: 每2位表示该位置杠子个数，9次
	private int gangziCode;

	// 一个short表示顺子: 每2位表示该位置顺子个数，7次
	private short shunziCode;

	public short getDanpaiCode() {
		return danpaiCode;
	}

	public void setDanpaiCode(short danpaiCode) {
		this.danpaiCode = danpaiCode;
	}

	public int getDuiziCode() {
		return duiziCode;
	}

	public void setDuiziCode(int duiziCode) {
		this.duiziCode = duiziCode;
	}

	public int getKeziCode() {
		return keziCode;
	}

	public void setKeziCode(int keziCode) {
		this.keziCode = keziCode;
	}

	public int getGangziCode() {
		return gangziCode;
	}

	public void setGangziCode(int gangziCode) {
		this.gangziCode = gangziCode;
	}

	public short getShunziCode() {
		return shunziCode;
	}

	public void setShunziCode(short shunziCode) {
		this.shunziCode = shunziCode;
	}

}
