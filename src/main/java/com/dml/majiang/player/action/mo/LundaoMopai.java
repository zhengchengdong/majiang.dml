package com.dml.majiang.player.action.mo;

import java.nio.ByteBuffer;

/**
 * 轮到摸牌
 * 
 * @author Neo
 *
 */
public class LundaoMopai implements MopaiReason {

	public static final String name = "lundao";

	@Override
	public void toByteBuffer(ByteBuffer bb) throws Throwable {
	}

	@Override
	public void fillByByteBuffer(ByteBuffer bb) throws Throwable {
	}

	@Override
	public String getName() {
		return name;
	}

}
