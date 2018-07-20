package com.dml.majiang;

public class ShoupaiKeziZu implements ShoupaiMajiangPaiFenZu {
	private Kezi kezi;
	private ShoupaiJiesuanPai pai1;
	private ShoupaiJiesuanPai pai2;
	private ShoupaiJiesuanPai pai3;

	@Override
	public void fillAllBlankPaiWithBenPai() {
		if (pai1 == null) {
			pai1 = new BenPai(kezi.getPaiType());
		}
		if (pai2 == null) {
			pai2 = new BenPai(kezi.getPaiType());
		}
		if (pai3 == null) {
			pai3 = new BenPai(kezi.getPaiType());
		}
	}

	@Override
	public void addShoupaiJiesuanPai(ShoupaiJiesuanPai shoupaiJiesuanPai) {
		if (pai1 == null) {
			pai1 = shoupaiJiesuanPai;
			return;
		}
		if (pai2 == null) {
			pai2 = shoupaiJiesuanPai;
			return;
		}
		if (pai3 == null) {
			pai3 = shoupaiJiesuanPai;
			return;
		}
	}

	public Kezi getKezi() {
		return kezi;
	}

	public void setKezi(Kezi kezi) {
		this.kezi = kezi;
	}

	public ShoupaiJiesuanPai getPai1() {
		return pai1;
	}

	public void setPai1(ShoupaiJiesuanPai pai1) {
		this.pai1 = pai1;
	}

	public ShoupaiJiesuanPai getPai2() {
		return pai2;
	}

	public void setPai2(ShoupaiJiesuanPai pai2) {
		this.pai2 = pai2;
	}

	public ShoupaiJiesuanPai getPai3() {
		return pai3;
	}

	public void setPai3(ShoupaiJiesuanPai pai3) {
		this.pai3 = pai3;
	}

}
