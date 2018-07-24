package com.dml.majiang;

/**
 * 手牌麻将牌分组，其实就是麻将牌分组的手牌化。主要包含手牌化后当的情况,和‘最后弄来的牌’
 * 
 * @author Neo
 *
 */
public interface ShoupaiMajiangPaiFenZu {

	public void addShoupaiJiesuanPai(ShoupaiJiesuanPai shoupaiJiesuanPai);

	public void fillAllBlankPaiWithBenPai();

	public <T extends ShoupaiMajiangPaiFenZu> T copy();

}