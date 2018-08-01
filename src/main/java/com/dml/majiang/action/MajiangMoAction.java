package com.dml.majiang.action;

import java.nio.ByteBuffer;

import com.dml.majiang.serializer.ByteBufferSerializer;

public class MajiangMoAction extends MajiangPlayerAction {

	private MopaiReason reason;

	public MajiangMoAction() {

	}

	public MajiangMoAction(String actionPlayerId, MopaiReason reason) {
		super(MajiangPlayerActionType.mo, actionPlayerId);
		this.reason = reason;
	}

	@Override
	protected void contentToByteBuffer(ByteBuffer bb) throws Throwable {
		ByteBufferSerializer.objToByteBuffer(reason, bb);
	}

	@Override
	protected void fillContentByByteBuffer(ByteBuffer bb) throws Throwable {
		reason = ByteBufferSerializer.byteBufferToObj(bb);
	}

	public MopaiReason getReason() {
		return reason;
	}

	public void setReason(MopaiReason reason) {
		this.reason = reason;
	}

}
