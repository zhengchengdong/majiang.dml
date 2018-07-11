package com.dml.majiang;

import java.nio.ByteBuffer;

public class MajiangPaiValueObject implements ByteBufferAble {

	private MajiangPai pai;

	public MajiangPaiValueObject() {
	}

	public MajiangPaiValueObject(MajiangPai pai) {
		this.pai = pai;
	}

	public MajiangPai getPai() {
		return pai;
	}

	public void setPai(MajiangPai pai) {
		this.pai = pai;
	}

	@Override
	public void toByteBuffer(ByteBuffer bb) throws Throwable {
		bb.put((byte) pai.ordinal());
	}

	@Override
	public void fillByByteBuffer(ByteBuffer bb) throws Throwable {
		pai = MajiangPai.valueOf(Byte.toUnsignedInt(bb.get()));
	}

}
