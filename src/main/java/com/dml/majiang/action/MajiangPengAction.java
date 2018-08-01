package com.dml.majiang.action;

import java.nio.ByteBuffer;

import com.dml.majiang.pai.MajiangPai;
import com.dml.majiang.serializer.ByteBufferSerializer;

public class MajiangPengAction extends MajiangPlayerAction {

	private String dachupaiPlayerId;

	private MajiangPai pai;

	public MajiangPengAction() {
	}

	public MajiangPengAction(String actionPlayerId, String dachupaiPlayerId, MajiangPai pai) {
		super(MajiangPlayerActionType.peng, actionPlayerId);
		this.dachupaiPlayerId = dachupaiPlayerId;
		this.pai = pai;
	}

	@Override
	protected void contentToByteBuffer(ByteBuffer bb) throws Throwable {
		ByteBufferSerializer.stringToByteBuffer(dachupaiPlayerId, bb);
		bb.put((byte) pai.ordinal());
	}

	@Override
	protected void fillContentByByteBuffer(ByteBuffer bb) throws Throwable {
		dachupaiPlayerId = ByteBufferSerializer.byteBufferToString(bb);
		pai = MajiangPai.valueOf(Byte.toUnsignedInt(bb.get()));
	}

	public String getDachupaiPlayerId() {
		return dachupaiPlayerId;
	}

	public void setDachupaiPlayerId(String dachupaiPlayerId) {
		this.dachupaiPlayerId = dachupaiPlayerId;
	}

	public MajiangPai getPai() {
		return pai;
	}

	public void setPai(MajiangPai pai) {
		this.pai = pai;
	}

}
