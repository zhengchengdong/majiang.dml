package com.dml.majiang;

import java.nio.ByteBuffer;

/**
 * 杠后补牌
 * 
 * @author Neo
 *
 */
public class GanghouBupai implements MopaiReason {

	public static final String name = "bugang";

	private MajiangPai gangPai;

	private GangType gangType;

	@Override
	public void toByteBuffer(ByteBuffer bb) throws Throwable {
		bb.put((byte) gangPai.ordinal());
		bb.put((byte) gangType.ordinal());
	}

	@Override
	public void fillByByteBuffer(ByteBuffer bb) throws Throwable {
		gangPai = MajiangPai.valueOf(Byte.toUnsignedInt(bb.get()));
		gangType = GangType.valueOf(bb.get());
	}

	@Override
	public String getName() {
		return name;
	}

	public MajiangPai getGangPai() {
		return gangPai;
	}

	public void setGangPai(MajiangPai gangPai) {
		this.gangPai = gangPai;
	}

	public GangType getGangType() {
		return gangType;
	}

	public void setGangType(GangType gangType) {
		this.gangType = gangType;
	}

}
