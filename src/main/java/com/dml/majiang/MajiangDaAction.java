package com.dml.majiang;

import java.nio.ByteBuffer;

public class MajiangDaAction extends MajiangPlayerAction {

	private MajiangPai pai;

	public MajiangDaAction() {

	}

	public MajiangDaAction(String actionPlayerId, MajiangPai pai) {
		super(MajiangPlayerActionType.da, actionPlayerId);
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
