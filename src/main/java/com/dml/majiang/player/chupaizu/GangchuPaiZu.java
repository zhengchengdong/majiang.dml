package com.dml.majiang.player.chupaizu;

import java.nio.ByteBuffer;

import com.dml.majiang.pai.fenzu.GangType;
import com.dml.majiang.pai.fenzu.Gangzi;
import com.dml.majiang.serializer.ByteBufferAble;
import com.dml.majiang.serializer.ByteBufferSerializer;

public class GangchuPaiZu implements ByteBufferAble {

	private Gangzi gangzi;
	private String dachuPlayerId;
	private String gangPlayerId;
	private GangType gangType;

	public GangchuPaiZu() {
	}

	public GangchuPaiZu(Gangzi gangzi, String dachuPlayerId, String gangPlayerId, GangType gangType) {
		this.gangzi = gangzi;
		this.dachuPlayerId = dachuPlayerId;
		this.gangPlayerId = gangPlayerId;
		this.gangType = gangType;
	}

	public Gangzi getGangzi() {
		return gangzi;
	}

	public void setGangzi(Gangzi gangzi) {
		this.gangzi = gangzi;
	}

	public String getDachuPlayerId() {
		return dachuPlayerId;
	}

	public void setDachuPlayerId(String dachuPlayerId) {
		this.dachuPlayerId = dachuPlayerId;
	}

	public String getGangPlayerId() {
		return gangPlayerId;
	}

	public void setGangPlayerId(String gangPlayerId) {
		this.gangPlayerId = gangPlayerId;
	}

	public GangType getGangType() {
		return gangType;
	}

	public void setGangType(GangType gangType) {
		this.gangType = gangType;
	}

	@Override
	public void toByteBuffer(ByteBuffer bb) throws Throwable {
		ByteBufferSerializer.objToByteBuffer(gangzi, bb);
		ByteBufferSerializer.stringToByteBuffer(dachuPlayerId, bb);
		ByteBufferSerializer.stringToByteBuffer(gangPlayerId, bb);
		bb.put((byte) gangType.ordinal());
	}

	@Override
	public void fillByByteBuffer(ByteBuffer bb) throws Throwable {
		gangzi = ByteBufferSerializer.byteBufferToObj(bb);
		dachuPlayerId = ByteBufferSerializer.byteBufferToString(bb);
		gangPlayerId = ByteBufferSerializer.byteBufferToString(bb);
		gangType = GangType.valueOf(bb.get());
	}

}
