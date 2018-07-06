package com.dml.majiang;

import java.nio.ByteBuffer;

public class MajiangDaAction extends MajiangPlayerAction {

	private MajiangPai pai;

	public MajiangDaAction() {

	}

	public MajiangDaAction(int id, MajiangPai pai) {
		super(id, MajiangPlayerActionType.da);
		this.pai = pai;
	}

	@Override
	protected void contentToByteBuffer(ByteBuffer bb) throws Throwable {
		bb.put((byte) pai.ordinal());
	}

	@Override
	protected void fillContentByByteBuffer(ByteBuffer bb) throws Throwable {
		pai = MajiangPai.valueOf(Byte.toUnsignedInt(bb.get()));
	}

	public MajiangPai getPai() {
		return pai;
	}

	public void setPai(MajiangPai pai) {
		this.pai = pai;
	}

}