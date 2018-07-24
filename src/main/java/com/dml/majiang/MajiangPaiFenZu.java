package com.dml.majiang;

/**
 * 麻将规则的牌分组，包括单牌、对子、刻子、杠子、顺子
 * 
 * @author Neo
 *
 */
public interface MajiangPaiFenZu {
	public int countPai(MajiangPai paiType);

	public <T extends ShoupaiMajiangPaiFenZu> T generateShoupaiMajiangPaiFenZuSkeleton();
}