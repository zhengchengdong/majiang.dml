package com.dml.majiang.pai.fenzu;

import java.nio.ByteBuffer;

import com.dml.majiang.pai.MajiangPai;
import com.dml.majiang.player.shoupai.ShoupaiKeziZu;
import com.dml.majiang.serializer.ByteBufferAble;

public class Kezi implements MajiangPaiFenZu, ByteBufferAble {

	private MajiangPai paiType;

	public Kezi() {
	}

	public Kezi(MajiangPai paiType) {
		this.paiType = paiType;
	}

	@Override
	public MajiangPai[] toPaiArray() {
		return new MajiangPai[] { paiType, paiType, paiType };
	}

	@Override
	public ShoupaiKeziZu generateShoupaiMajiangPaiFenZuSkeleton() {
		ShoupaiKeziZu shoupaiKeziZu = new ShoupaiKeziZu();
		shoupaiKeziZu.setKezi(this);
		return shoupaiKeziZu;
	}

	@Override
	public int countPai(MajiangPai paiType) {
		if (paiType.equals(this.paiType)) {
			return 3;
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
