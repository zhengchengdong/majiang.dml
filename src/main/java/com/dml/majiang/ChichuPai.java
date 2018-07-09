package com.dml.majiang;

import java.nio.ByteBuffer;

public class ChichuPai implements ByteBufferAble {
	private Shunzi shunzi;
	private String dachuPlayerId;
	private String chiPlayerId;

	public ChichuPai() {
	}

	public ChichuPai(Shunzi shunzi, String dachuPlayerId, String chiPlayerId) {
		this.shunzi = shunzi;
		this.dachuPlayerId = dachuPlayerId;
		this.chiPlayerId = chiPlayerId;
	}

	public Shunzi getShunzi() {
		return shunzi;
	}

	public void setShunzi(Shunzi shunzi) {
		this.shunzi = shunzi;
	}

	public String getDachuPlayerId() {
		return dachuPlayerId;
	}

	public void setDachuPlayerId(String dachuPlayerId) {
		this.dachuPlayerId = dachuPlayerId;
	}

	public String getChiPlayerId() {
		return chiPlayerId;
	}

	public void setChiPlayerId(String chiPlayerId) {
		this.chiPlayerId = chiPlayerId;
	}

	@Override
	public void toByteBuffer(ByteBuffer bb) throws Throwable {
		ByteBufferSerializer.objToByteBuffer(shunzi, bb);
		ByteBufferSerializer.stringToByteBuffer(dachuPlayerId, bb);
		ByteBufferSerializer.stringToByteBuffer(chiPlayerId, bb);
	}

	@Override
	public void fillByByteBuffer(ByteBuffer bb) throws Throwable {
		shunzi = ByteBufferSerializer.byteBufferToObj(bb);
		dachuPlayerId = ByteBufferSerializer.byteBufferToString(bb);
		chiPlayerId = ByteBufferSerializer.byteBufferToString(bb);
	}

}
