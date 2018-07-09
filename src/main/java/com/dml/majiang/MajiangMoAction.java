package com.dml.majiang;

import java.nio.ByteBuffer;

public class MajiangMoAction extends MajiangPlayerAction {

	public MajiangMoAction() {

	}

	public MajiangMoAction(int id, String actionPlayerId) {
		super(id, MajiangPlayerActionType.mo, actionPlayerId);
	}

	@Override
	protected void contentToByteBuffer(ByteBuffer bb) throws Throwable {
	}

	@Override
	protected void fillContentByByteBuffer(ByteBuffer bb) throws Throwable {
	}
}
