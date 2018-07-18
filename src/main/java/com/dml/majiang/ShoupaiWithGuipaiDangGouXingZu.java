package com.dml.majiang;

import java.util.List;

/**
 * 有鬼牌当牌的情况下的手牌计算出来的构型组
 * 
 * @author Neo
 *
 */
public class ShoupaiWithGuipaiDangGouXingZu {

	private GuipaiDangPai[] guipaiDangPaiArray;

	private List<GouXing> gouXingList;

	public GuipaiDangPai[] getGuipaiDangPaiArray() {
		return guipaiDangPaiArray;
	}

	public void setGuipaiDangPaiArray(GuipaiDangPai[] guipaiDangPaiArray) {
		this.guipaiDangPaiArray = guipaiDangPaiArray;
	}

	public List<GouXing> getGouXingList() {
		return gouXingList;
	}

	public void setGouXingList(List<GouXing> gouXingList) {
		this.gouXingList = gouXingList;
	}

}
