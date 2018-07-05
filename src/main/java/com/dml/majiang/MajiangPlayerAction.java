package com.dml.majiang;

import java.nio.ByteBuffer;

/**
 * 吃碰杠胡摸打过
 * 
 * @author Neo
 *
 */
public abstract class MajiangPlayerAction implements ByteBufferAble {

	private int id;

	private MajiangPlayerActionType type;

	public MajiangPlayerAction() {
	}

	public MajiangPlayerAction(int id, MajiangPlayerActionType type) {
		this.id = id;
		this.type = type;
	}

	@Override
	public void toByteBuffer(ByteBuffer bb) throws Throwable {
		bb.putInt(id);
		bb.put((byte) type.ordinal());
		contentToByteBuffer(bb);
	}

	protected abstract void contentToByteBuffer(ByteBuffer bb) throws Throwable;

	@Override
	public void fillByByteBuffer(ByteBuffer bb) throws Throwable {
		id = bb.getInt();
		type = MajiangPlayerActionType.valueOf(bb.get());
		fillContentByByteBuffer(bb);
	}

	protected abstract void fillContentByByteBuffer(ByteBuffer bb) throws Throwable;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public MajiangPlayerActionType getType() {
		return type;
	}

	public void setType(MajiangPlayerActionType type) {
		this.type = type;
	}

}
