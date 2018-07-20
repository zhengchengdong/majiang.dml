package com.dml.majiang;

import java.nio.ByteBuffer;

public class Gangzi implements MajiangPaiFenZu, ByteBufferAble {
	private MajiangPai paiType;

	public Gangzi() {
	}

	public Gangzi(MajiangPai paiType) {
		this.paiType = paiType;
	}

	@Override
	public ShoupaiGangziZu generateShoupaiMajiangPaiFenZuSkeleton() {
		ShoupaiGangziZu shoupaiGangziZu = new ShoupaiGangziZu();
		shoupaiGangziZu.setGangzi(this);
		return shoupaiGangziZu;
	}

	@Override
	public int countPai(MajiangPai paiType) {
		if (paiType.equals(this.paiType)) {
			return 4;
		} else {
			return 0;
		}
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
