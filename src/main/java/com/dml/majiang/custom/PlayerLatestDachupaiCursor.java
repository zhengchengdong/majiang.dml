package com.dml.majiang.custom;

import java.nio.ByteBuffer;

import com.dml.majiang.pan.PaiCursor;
import com.dml.majiang.pan.PaiCursorType;
import com.dml.majiang.serializer.ByteBufferSerializer;

/**
 * 定位玩家最后打出的牌需要的信息
 * 
 * @author Neo
 *
 */
public class PlayerLatestDachupaiCursor extends PaiCursor {

	private String playerId;

	public PlayerLatestDachupaiCursor() {
		super(PaiCursorType.playerLatestDachupai);
	}

	public PlayerLatestDachupaiCursor(String playerId) {
		super(PaiCursorType.playerLatestDachupai);
		this.playerId = playerId;
	}

	@Override
	protected void contentToByteBuffer(ByteBuffer bb) throws Throwable {
		ByteBufferSerializer.stringToByteBuffer(playerId, bb);
	}

	@Override
	protected void fillContentByByteBuffer(ByteBuffer bb) throws Throwable {
		playerId = ByteBufferSerializer.byteBufferToString(bb);
	}

	public String getPlayerId() {
		return playerId;
	}

	public void setPlayerId(String playerId) {
		this.playerId = playerId;
	}

}
