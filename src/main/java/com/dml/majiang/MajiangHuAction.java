package com.dml.majiang;

import java.nio.ByteBuffer;

public class MajiangHuAction extends MajiangPlayerAction {

	private Hu hu;

	public MajiangHuAction() {
	}

	public MajiangHuAction(String actionPlayerId, Hu hu) {
		super(MajiangPlayerActionType.hu, actionPlayerId);
		this.hu = hu;
	}

	@Override
	protected void contentToByteBuffer(ByteBuffer bb) throws Throwable {
		// TODO Auto-generated method stub

	}

	@Override
	protected void fillContentByByteBuffer(ByteBuffer bb) throws Throwable {
		// TODO Auto-generated method stub

	}

	public Hu getHu() {
		return hu;
	}

	public void setHu(Hu hu) {
		this.hu = hu;
	}

}
