package com.dml.majiang.player.shoupai;

import java.nio.ByteBuffer;

import com.dml.majiang.pai.MajiangPai;
import com.dml.majiang.serializer.ByteBufferSerializer;

/**
 * 本牌。啥也不当。
 * 
 * @author Neo
 *
 */
public class BenPai extends ShoupaiJiesuanPai {

	public static final String dangType = "benpai";

	private MajiangPai pai;

	public BenPai() {
	}

	public BenPai(MajiangPai pai) {
		this.pai = pai;
	}

	@Override
	public ShoupaiJiesuanPai copy() {
		BenPai newBenPai = new BenPai();
		newBenPai.setPai(pai);
		newBenPai.setLastActionPai(isLastActionPai());
		return newBenPai;
	}

	@Override
	public MajiangPai getYuanPaiType() {
		return pai;
	}

	@Override
	public MajiangPai getZuoyongPaiType() {
		return pai;
	}

	@Override
	public String dangType() {
		return dangType;
	}

	@Override
	public boolean dangBenPai() {
		return true;
	}

	public MajiangPai getPai() {
		return pai;
	}

	public void setPai(MajiangPai pai) {
		this.pai = pai;
	}

	@Override
	public void toByteBuffer(ByteBuffer bb) throws Throwable {
		ByteBufferSerializer.booleanToByteBuffer(isLastActionPai(), bb);
		bb.put((byte) pai.ordinal());
	}

	@Override
	public void fillByByteBuffer(ByteBuffer bb) throws Throwable {
		setLastActionPai(ByteBufferSerializer.byteBufferToBoolean(bb));
		pai = MajiangPai.valueOf(bb.get());
	}

}
