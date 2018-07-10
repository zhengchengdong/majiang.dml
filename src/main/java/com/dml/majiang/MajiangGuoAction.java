package com.dml.majiang;

import java.nio.ByteBuffer;

public class MajiangGuoAction extends MajiangPlayerAction {

	public MajiangGuoAction() {

	}

	public MajiangGuoAction(String actionPlayerId) {
		super(MajiangPlayerActionType.guo, actionPlayerId);
	}

	@Override
	protected void contentToByteBuffer(ByteBuffer bb) throws Throwable {
	}

	@Override
	protected void fillContentByByteBuffer(ByteBuffer bb) throws Throwable {
	}
}
