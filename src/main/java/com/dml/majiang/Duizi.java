package com.dml.majiang;

import java.nio.ByteBuffer;

public class Duizi implements ByteBufferAble {

	private MajiangPai paiType;

	public Duizi() {
	}

	public Duizi(MajiangPai paiType) {
		this.paiType = paiType;
	}

	public MajiangPai getPaiType() {
		return paiType;
	}

	public void setPaiType(MajiangPai paiType) {
		this.paiType = paiType;
	}

	@Override
	public void toByteBuffer(ByteBuffer bb) throws Throwable {
		bb.put((byte) paiType.ordinal());
	}

	@Override
	public void fillByByteBuffer(ByteBuffer bb) throws Throwable {
		paiType = MajiangPai.valueOf(Byte.toUnsignedInt(bb.get()));
	}

}
