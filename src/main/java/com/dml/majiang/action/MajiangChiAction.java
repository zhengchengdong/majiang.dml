package com.dml.majiang.action;

import java.nio.ByteBuffer;

import com.dml.majiang.pai.MajiangPai;
import com.dml.majiang.pai.Shunzi;
import com.dml.majiang.serializer.ByteBufferSerializer;

public class MajiangChiAction extends MajiangPlayerAction {
	private String dachupaiPlayerId;
	private MajiangPai chijinPai;
	private Shunzi shunzi;

	public MajiangChiAction() {

	}

	public MajiangChiAction(String actionPlayerId, String dachupaiPlayerId, MajiangPai chijinPai, Shunzi shunzi) {
		super(MajiangPlayerActionType.chi, actionPlayerId);
		this.dachupaiPlayerId = dachupaiPlayerId;
		this.chijinPai = chijinPai;
		this.shunzi = shunzi;
	}

	@Override
	protected void contentToByteBuffer(ByteBuffer bb) throws Throwable {
		ByteBufferSerializer.stringToByteBuffer(dachupaiPlayerId, bb);
		bb.put((byte) chijinPai.ordinal());
		ByteBufferSerializer.objToByteBuffer(shunzi, bb);
	}

	@Override
	protected void fillContentByByteBuffer(ByteBuffer bb) throws Throwable {
		dachupaiPlayerId = ByteBufferSerializer.byteBufferToString(bb);
		chijinPai = MajiangPai.valueOf(Byte.toUnsignedInt(bb.get()));
		shunzi = ByteBufferSerializer.byteBufferToObj(bb);
	}

	public String getDachupaiPlayerId() {
		return dachupaiPlayerId;
	}

	public void setDachupaiPlayerId(String dachupaiPlayerId) {
		this.dachupaiPlayerId = dachupaiPlayerId;
	}

	public MajiangPai getChijinPai() {
		return chijinPai;
	}

	public void setChijinPai(MajiangPai chijinPai) {
		this.chijinPai = chijinPai;
	}

	public Shunzi getShunzi() {
		return shunzi;
	}

	public void setShunzi(Shunzi shunzi) {
		this.shunzi = shunzi;
	}

}
