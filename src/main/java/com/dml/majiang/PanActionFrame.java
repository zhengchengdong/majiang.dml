package com.dml.majiang;

import java.nio.ByteBuffer;

public class PanActionFrame implements ByteBufferAble {

	private MajiangPlayerAction action;
	private PanValueObject panAfterAction;
	private long actionTime;

	public PanActionFrame() {
	}

	public PanActionFrame(MajiangPlayerAction action, PanValueObject panAfterAction, long actionTime) {
		this.action = action;
		this.panAfterAction = panAfterAction;
		this.actionTime = actionTime;
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

	public long getActionTime() {
		return actionTime;
	}

	public void setActionTime(long actionTime) {
		this.actionTime = actionTime;
	}

	@Override
	public void toByteBuffer(ByteBuffer bb) throws Throwable {
		ByteBufferSerializer.objToByteBuffer(action, bb);
		ByteBufferSerializer.objToByteBuffer(panAfterAction, bb);
		bb.putLong(actionTime);
	}

	@Override
	public void fillByByteBuffer(ByteBuffer bb) throws Throwable {
		action = ByteBufferSerializer.byteBufferToObj(bb);
		panAfterAction = ByteBufferSerializer.byteBufferToObj(bb);
		actionTime = bb.getLong();
	}

	public byte[] toByteArray(int bufferSize) throws Throwable {
		byte[] buffer = new byte[bufferSize];
		ByteBuffer bb = ByteBuffer.wrap(buffer);
		ByteBufferSerializer.objToByteBuffer(this, bb);
		byte[] byteArray = new byte[bb.position()];
		System.arraycopy(buffer, 0, byteArray, 0, byteArray.length);
		return byteArray;
	}

	public static PanActionFrame fromByteArray(byte[] byteArray) {
		try {
			return ByteBufferSerializer.byteBufferToObj(ByteBuffer.wrap(byteArray));
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return null;
	}

}
