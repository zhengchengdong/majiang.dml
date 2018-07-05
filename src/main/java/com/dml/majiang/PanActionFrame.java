package com.dml.majiang;

import java.nio.ByteBuffer;

public class PanActionFrame implements ByteBufferAble {

	private MajiangPlayerAction action;
	private PanValueObject panAfterAction;

	public PanActionFrame() {
	}

	public PanActionFrame(MajiangPlayerAction action, PanValueObject panAfterAction) {
		this.action = action;
		this.panAfterAction = panAfterAction;
	}

	public MajiangPlayerAction getAction() {
		return action;
	}

	public void setAction(MajiangPlayerAction action) {
		this.action = action;
	}

	public PanValueObject getPanAfterAction() {
		return panAfterAction;
	}

	public void setPanAfterAction(PanValueObject panAfterAction) {
		this.panAfterAction = panAfterAction;
	}

	@Override
	public void toByteBuffer(ByteBuffer bb) throws Throwable {
		ByteBufferSerializer.objToByteBuffer(action, bb);
		ByteBufferSerializer.objToByteBuffer(panAfterAction, bb);
	}

	@Override
	public void fillByByteBuffer(ByteBuffer bb) throws Throwable {
		action = ByteBufferSerializer.byteBufferToObj(bb);
		panAfterAction = ByteBufferSerializer.byteBufferToObj(bb);
	}

}
