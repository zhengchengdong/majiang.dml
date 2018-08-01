package com.dml.majiang.action;

import java.nio.ByteBuffer;

import com.dml.majiang.pai.GangType;
import com.dml.majiang.pai.MajiangPai;
import com.dml.majiang.serializer.ByteBufferSerializer;

public class MajiangGangAction extends MajiangPlayerAction {

	private String dachupaiPlayerId;

	private MajiangPai pai;

	private GangType gangType;

	public MajiangGangAction() {
	}

	public MajiangGangAction(String actionPlayerId, String dachupaiPlayerId, MajiangPai pai, GangType gangType) {
		super(MajiangPlayerActionType.gang, actionPlayerId);
		this.dachupaiPlayerId = dachupaiPlayerId;
		this.pai = pai;
		this.gangType = gangType;
	}

	@Override
	protected void contentToByteBuffer(ByteBuffer bb) throws Throwable {
		ByteBufferSerializer.stringToByteBuffer(dachupaiPlayerId, bb);
		bb.put((byte) pai.ordinal());
		bb.put((byte) gangType.ordinal());
	}

	@Override
	protected void fillContentByByteBuffer(ByteBuffer bb) throws Throwable {
		dachupaiPlayerId = ByteBufferSerializer.byteBufferToString(bb);
		pai = MajiangPai.valueOf(Byte.toUnsignedInt(bb.get()));
		gangType = GangType.valueOf(bb.get());
	}

	public String getDachupaiPlayerId() {
		return dachupaiPlayerId;
	}

	public void setDachupaiPlayerId(String dachupaiPlayerId) {
		this.dachupaiPlayerId = dachupaiPlayerId;
	}

	public MajiangPai getPai() {
		return pai;
	}

	public void setPai(MajiangPai pai) {
		this.pai = pai;
	}

	public GangType getGangType() {
		return gangType;
	}

	public void setGangType(GangType gangType) {
		this.gangType = gangType;
	}

}
