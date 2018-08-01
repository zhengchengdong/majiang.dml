package com.dml.majiang.pan;

import java.nio.ByteBuffer;

import com.dml.majiang.serializer.ByteBufferAble;

/**
 * 牌游标，用于保存定位牌需要的信息。由于牌没有id,所以定位牌需要通过一些信息的计算来实现。
 * 
 * @author Neo
 *
 */
public abstract class PaiCursor implements ByteBufferAble {

	private PaiCursorType type;

	public PaiCursor() {
	}

	public PaiCursor(PaiCursorType type) {
		this.type = type;
	}

	@Override
	public void toByteBuffer(ByteBuffer bb) throws Throwable {
		bb.put((byte) type.ordinal());
		contentToByteBuffer(bb);
	}

	protected abstract void contentToByteBuffer(ByteBuffer bb) throws Throwable;

	@Override
	public void fillByByteBuffer(ByteBuffer bb) throws Throwable {
		type = PaiCursorType.valueOf(bb.get());
		fillContentByByteBuffer(bb);
	}

	protected abstract void fillContentByByteBuffer(ByteBuffer bb) throws Throwable;

	public PaiCursorType getType() {
		return type;
	}

	public void setType(PaiCursorType type) {
		this.type = type;
	}

}
