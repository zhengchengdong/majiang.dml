package com.dml.majiang.player.action;

import java.nio.ByteBuffer;

import com.dml.majiang.serializer.ByteBufferAble;
import com.dml.majiang.serializer.ByteBufferSerializer;

/**
 * 吃碰杠胡摸打过
 * 
 * @author Neo
 *
 */
public abstract class MajiangPlayerAction implements ByteBufferAble {

	private int id;

	private MajiangPlayerActionType type;

	private String actionPlayerId;

	private boolean disabledByHigherPriorityAction;

	public MajiangPlayerAction() {
	}

	public MajiangPlayerAction(MajiangPlayerActionType type, String actionPlayerId) {
		this.type = type;
		this.actionPlayerId = actionPlayerId;
	}

	@Override
	public void toByteBuffer(ByteBuffer bb) throws Throwable {
		bb.putInt(id);
		bb.put((byte) type.ordinal());
		ByteBufferSerializer.stringToByteBuffer(actionPlayerId, bb);
		contentToByteBuffer(bb);
	}

	protected abstract void contentToByteBuffer(ByteBuffer bb) throws Throwable;

	@Override
	public void fillByByteBuffer(ByteBuffer bb) throws Throwable {
		id = bb.getInt();
		type = MajiangPlayerActionType.valueOf(bb.get());
		actionPlayerId = ByteBufferSerializer.byteBufferToString(bb);
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

	public String getActionPlayerId() {
		return actionPlayerId;
	}

	public void setActionPlayerId(String actionPlayerId) {
		this.actionPlayerId = actionPlayerId;
	}

	public boolean isDisabledByHigherPriorityAction() {
		return disabledByHigherPriorityAction;
	}

	public void setDisabledByHigherPriorityAction(boolean disabledByHigherPriorityAction) {
		this.disabledByHigherPriorityAction = disabledByHigherPriorityAction;
	}

}
