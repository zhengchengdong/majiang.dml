package com.dml.majiang;

public class Danpai implements MajiangPaiFenZu {

	private MajiangPai pai;

	public Danpai() {
	}

	public Danpai(MajiangPai pai) {
		this.pai = pai;
	}

	@Override
	public int countPai(MajiangPai paiType) {
		if (paiType.equals(pai)) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public ShoupaiMajiangPaiFenZu generateShoupaiMajiangPaiFenZuSkeleton() {
		ShoupaiDanpai shoupaiDanpai = new ShoupaiDanpai();
		shoupaiDanpai.setDanpaiType(pai);
		return shoupaiDanpai;
	}

	public MajiangPai getPai() {
		return pai;
	}

	public void setPai(MajiangPai pai) {
		this.pai = pai;
	}

}
