package com.dml.majiang.player.shoupai;

import java.nio.ByteBuffer;

import com.dml.majiang.pai.MajiangPai;
import com.dml.majiang.serializer.ByteBufferSerializer;

/**
 * 白板当其他牌
 * 
 * @author lsc
 *
 */
public class BaibanDangPai extends ShoupaiJiesuanPai {

	public static final String dangType = "baibandang";

	private MajiangPai baiban = MajiangPai.baiban;

	private MajiangPai dangpai;

	@Override
	public void toByteBuffer(ByteBuffer bb) throws Throwable {
		ByteBufferSerializer.booleanToByteBuffer(isLastActionPai(), bb);
		bb.put((byte) dangpai.ordinal());
	}

	@Override
	public void fillByByteBuffer(ByteBuffer bb) throws Throwable {
		setLastActionPai(ByteBufferSerializer.byteBufferToBoolean(bb));
		dangpai = MajiangPai.valueOf(bb.get());
	}

	public BaibanDangPai() {
	}

	public BaibanDangPai(MajiangPai dangpai) {
		this.dangpai = dangpai;
	}

	@Override
	public String dangType() {
		return dangType;
	}

	@Override
	public MajiangPai getYuanPaiType() {
		return baiban;
	}

	@Override
	public MajiangPai getZuoyongPaiType() {
		return dangpai;
	}

	@Override
	public ShoupaiJiesuanPai copy() {
		BaibanDangPai newBaibanDangPai = new BaibanDangPai();
		newBaibanDangPai.setBaiban(baiban);
		newBaibanDangPai.setDangpai(dangpai);
		newBaibanDangPai.setLastActionPai(isLastActionPai());
		return newBaibanDangPai;
	}

	@Override
	public boolean dangBenPai() {
		return baiban.equals(dangpai);
	}

	public MajiangPai getBaiban() {
		return baiban;
	}

	public void setBaiban(MajiangPai baiban) {
		this.baiban = baiban;
	}

	public MajiangPai getDangpai() {
		return dangpai;
	}

	public void setDangpai(MajiangPai dangpai) {
		this.dangpai = dangpai;
	}

}
