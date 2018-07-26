package com.dml.majiang;

import java.nio.ByteBuffer;

public class Duizi implements MajiangPaiFenZu, ByteBufferAble {

	private MajiangPai paiType;

	public Duizi() {
	}

	public Duizi(MajiangPai paiType) {
		this.paiType = paiType;
	}

	@Override
	public ShoupaiDuiziZu generateShoupaiMajiangPaiFenZuSkeleton() {
		ShoupaiDuiziZu shoupaiDuiziZu = new ShoupaiDuiziZu();
		shoupaiDuiziZu.setDuiziType(paiType);
		return shoupaiDuiziZu;
	}

	@Override
	public int countPai(MajiangPai paiType) {
		if (paiType.equals(this.paiType)) {
			return 2;
		} else {
			return 0;
		}
	}

	@Override
	public MajiangPai[] toPaiArray() {
		return new MajiangPai[] { paiType, paiType };
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
