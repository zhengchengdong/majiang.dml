package com.dml.majiang.player.action.mo;

import java.nio.ByteBuffer;

import com.dml.majiang.pai.MajiangPai;
import com.dml.majiang.pai.fenzu.GangType;

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

	public GanghouBupai() {
	}

	public GanghouBupai(MajiangPai gangPai, GangType gangType) {
		this.gangPai = gangPai;
		this.gangType = gangType;
	}

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
