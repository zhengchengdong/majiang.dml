package com.dml.majiang;

import java.nio.ByteBuffer;

public class MajiangChiAction extends MajiangPlayerAction {

	private MajiangPai chijinPai;
	private Shunzi shunzi;

	public MajiangChiAction() {

	}

	public MajiangChiAction(int id, String actionPlayerId, MajiangPai chijinPai, Shunzi shunzi) {
		super(id, MajiangPlayerActionType.chi, actionPlayerId);
	}

	@Override
	protected void contentToByteBuffer(ByteBuffer bb) throws Throwable {
		bb.put((byte) chijinPai.ordinal());
		ByteBufferSerializer.objToByteBuffer(shunzi, bb);
	}

	@Override
	protected void fillContentByByteBuffer(ByteBuffer bb) throws Throwable {
		chijinPai = MajiangPai.valueOf(Byte.toUnsignedInt(bb.get()));
		shunzi = ByteBufferSerializer.byteBufferToObj(bb);
	}

	public MajiangPai getChijinPai() {
		return chijinPai;
	}

	public void setChijinPai(MajiangPai chijinPai) {
		this.chijinPai = chijinPai;
	}

	public Shunzi getShunzi() {
		return shunzi;
	}

	public void setShunzi(Shunzi shunzi) {
		this.shunzi = shunzi;
	}

}
