package com.dml.majiang;

/**
 * 手牌麻将牌分组，其实就是麻将牌分组的手牌化。主要包含手牌化后当的情况
 * 
 * @author Neo
 *
 */
public interface ShoupaiMajiangPaiFenZu {

	public void addShoupaiJiesuanPai(ShoupaiJiesuanPai shoupaiJiesuanPai);

	public void fillAllBlankPaiWithBenPai();

}
