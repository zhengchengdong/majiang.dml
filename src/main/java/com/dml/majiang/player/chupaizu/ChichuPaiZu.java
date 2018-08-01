package com.dml.majiang.player.chupaizu;

import java.nio.ByteBuffer;

import com.dml.majiang.pai.MajiangPai;
import com.dml.majiang.pai.fenzu.Shunzi;
import com.dml.majiang.serializer.ByteBufferAble;
import com.dml.majiang.serializer.ByteBufferSerializer;

public class ChichuPaiZu implements ByteBufferAble {
	private MajiangPai chijinpai;
	private Shunzi shunzi;
	private String dachuPlayerId;
	private String chiPlayerId;

	public ChichuPaiZu() {
	}

	public ChichuPaiZu(MajiangPai chijinpai, Shunzi shunzi, String dachuPlayerId, String chiPlayerId) {
		this.chijinpai = chijinpai;
		this.shunzi = shunzi;
		this.dachuPlayerId = dachuPlayerId;
		this.chiPlayerId = chiPlayerId;
	}

	public MajiangPai getChijinpai() {
		return chijinpai;
	}

	public void setChijinpai(MajiangPai chijinpai) {
		this.chijinpai = chijinpai;
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
		bb.put((byte) chijinpai.ordinal());
		ByteBufferSerializer.objToByteBuffer(shunzi, bb);
		ByteBufferSerializer.stringToByteBuffer(dachuPlayerId, bb);
		ByteBufferSerializer.stringToByteBuffer(chiPlayerId, bb);
	}

	@Override
	public void fillByByteBuffer(ByteBuffer bb) throws Throwable {
		chijinpai = MajiangPai.valueOf(Byte.toUnsignedInt(bb.get()));
		shunzi = ByteBufferSerializer.byteBufferToObj(bb);
		dachuPlayerId = ByteBufferSerializer.byteBufferToString(bb);
		chiPlayerId = ByteBufferSerializer.byteBufferToString(bb);
	}

}
