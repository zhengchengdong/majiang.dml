package com.dml.majiang;

public class ShoupaiGangziZu implements ShoupaiMajiangPaiFenZu {
	private Gangzi gangzi;
	private ShoupaiJiesuanPai pai1;
	private ShoupaiJiesuanPai pai2;
	private ShoupaiJiesuanPai pai3;
	private ShoupaiJiesuanPai pai4;

	@Override
	public ShoupaiGangziZu copy() {
		ShoupaiGangziZu newShoupaiGangziZu = new ShoupaiGangziZu();
		newShoupaiGangziZu.setGangzi(gangzi);
		newShoupaiGangziZu.setPai1(pai1.copy());
		newShoupaiGangziZu.setPai2(pai2.copy());
		newShoupaiGangziZu.setPai3(pai3.copy());
		newShoupaiGangziZu.setPai4(pai4.copy());
		return newShoupaiGangziZu;
	}

	@Override
	public void fillAllBlankPaiWithBenPai() {
		if (pai1 == null) {
			pai1 = new BenPai(gangzi.getPaiType());
		}
		if (pai2 == null) {
			pai2 = new BenPai(gangzi.getPaiType());
		}
		if (pai3 == null) {
			pai3 = new BenPai(gangzi.getPaiType());
		}
		if (pai4 == null) {
			pai4 = new BenPai(gangzi.getPaiType());
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
		if (pai4 == null) {
			pai4 = shoupaiJiesuanPai;
			return;
		}
	}

	public Gangzi getGangzi() {
		return gangzi;
	}

	public void setGangzi(Gangzi gangzi) {
		this.gangzi = gangzi;
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

	public ShoupaiJiesuanPai getPai4() {
		return pai4;
	}

	public void setPai4(ShoupaiJiesuanPai pai4) {
		this.pai4 = pai4;
	}

}
