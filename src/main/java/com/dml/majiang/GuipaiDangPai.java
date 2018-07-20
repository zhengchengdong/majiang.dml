package com.dml.majiang;

/**
 * 鬼牌当的牌
 * 
 * @author Neo
 *
 */
public class GuipaiDangPai extends ShoupaiJiesuanPai {

	public static final String dangType = "guipaidang";

	private MajiangPai guipai;

	private MajiangPai dangpai;

	public GuipaiDangPai() {
	}

	public GuipaiDangPai(MajiangPai guipai, MajiangPai dangpai) {
		this.guipai = guipai;
		this.dangpai = dangpai;
	}

	@Override
	public ShoupaiJiesuanPai copy() {
		GuipaiDangPai newGuipaiDangPai = new GuipaiDangPai();
		newGuipaiDangPai.setGuipai(guipai);
		newGuipaiDangPai.setDangpai(dangpai);
		newGuipaiDangPai.setLastActionPai(isLastActionPai());
		return newGuipaiDangPai;
	}

	@Override
	public MajiangPai getYuanPaiType() {
		return guipai;
	}

	@Override
	public MajiangPai getZuoyongPaiType() {
		return dangpai;
	}

	@Override
	public String getDangType() {
		return dangType;
	}

	public MajiangPai getGuipai() {
		return guipai;
	}

	public void setGuipai(MajiangPai guipai) {
		this.guipai = guipai;
	}

	public MajiangPai getDangpai() {
		return dangpai;
	}

	public void setDangpai(MajiangPai dangpai) {
		this.dangpai = dangpai;
	}

}
