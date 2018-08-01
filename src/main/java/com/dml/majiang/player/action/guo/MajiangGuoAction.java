package com.dml.majiang.player.action.guo;

import java.nio.ByteBuffer;

import com.dml.majiang.player.action.MajiangPlayerAction;
import com.dml.majiang.player.action.MajiangPlayerActionType;

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
