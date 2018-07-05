package com.dml.majiang;

import java.nio.ByteBuffer;

public class PengchuPai implements ByteBufferAble {

	private Kezi kezi;
	private String dachuPlayerId;
	private String pengPlayerId;

	public Kezi getKezi() {
		return kezi;
	}

	public void setKezi(Kezi kezi) {
		this.kezi = kezi;
	}

	public String getDachuPlayerId() {
		return dachuPlayerId;
	}

	public void setDachuPlayerId(String dachuPlayerId) {
		this.dachuPlayerId = dachuPlayerId;
	}

	public String getPengPlayerId() {
		return pengPlayerId;
	}

	public void setPengPlayerId(String pengPlayerId) {
		this.pengPlayerId = pengPlayerId;
	}

	@Override
	public void toByteBuffer(ByteBuffer bb) throws Throwable {
		ByteBufferSerializer.objToByteBuffer(kezi, bb);
		ByteBufferSerializer.stringToByteBuffer(dachuPlayerId, bb);
		ByteBufferSerializer.stringToByteBuffer(pengPlayerId, bb);
	}

	@Override
	public void fillByByteBuffer(ByteBuffer bb) throws Throwable {
		kezi = ByteBufferSerializer.byteBufferToObj(bb);
		dachuPlayerId = ByteBufferSerializer.byteBufferToString(bb);
		pengPlayerId = ByteBufferSerializer.byteBufferToString(bb);
	}

}
