package com.dml.majiang;

/**
 * 本牌。啥也不当。
 * 
 * @author Neo
 *
 */
public class BenPai implements ShoupaiDangPai {

	public static final String dangType = "benpai";

	private MajiangPai pai;

	@Override
	public String getDangType() {
		return dangType;
	}

	public MajiangPai getPai() {
		return pai;
	}

	public void setPai(MajiangPai pai) {
		this.pai = pai;
	}

}
