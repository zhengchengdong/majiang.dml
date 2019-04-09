package com.dml.majiang.player.shoupai;

import java.nio.ByteBuffer;

import com.dml.majiang.pai.MajiangPai;
import com.dml.majiang.serializer.ByteBufferSerializer;

/**
 * 鬼牌当的牌。允许当鬼牌本牌。
 * 
 * @author Neo
 *
 */
public class GuipaiDangPai extends ShoupaiJiesuanPai {

	public static final String dangType = "guipaidang";

	private MajiangPai guipai;

	private MajiangPai dangpai;

	public GuipaiDangPai() {
	}

	public GuipaiDangPai(MajiangPai guipai, MajiangPai dangpai) {
		this.guipai = guipai;
		this.dangpai = dangpai;
	}

	@Override
	public ShoupaiJiesuanPai copy() {
		GuipaiDangPai newGuipaiDangPai = new GuipaiDangPai();
		newGuipaiDangPai.setGuipai(guipai);
		newGuipaiDangPai.setDangpai(dangpai);
		newGuipaiDangPai.setLastActionPai(isLastActionPai());
		return newGuipaiDangPai;
	}

	@Override
	public MajiangPai getYuanPaiType() {
		return guipai;
	}

	@Override
	public MajiangPai getZuoyongPaiType() {
		return dangpai;
	}

	@Override
	public String dangType() {
		return dangType;
	}

	@Override
	public boolean dangBenPai() {
		return guipai.equals(dangpai);
	}

	public MajiangPai getGuipai() {
		return guipai;
	}

	public void setGuipai(MajiangPai guipai) {
		this.guipai = guipai;
	}

	public MajiangPai getDangpai() {
		return dangpai;
	}

	public void setDangpai(MajiangPai dangpai) {
		this.dangpai = dangpai;
	}

	@Override
	public void toByteBuffer(ByteBuffer bb) throws Throwable {
		ByteBufferSerializer.booleanToByteBuffer(isLastActionPai(), bb);
		bb.put((byte) guipai.ordinal());
		bb.put((byte) dangpai.ordinal());
	}

	@Override
	public void fillByByteBuffer(ByteBuffer bb) throws Throwable {
		setLastActionPai(ByteBufferSerializer.byteBufferToBoolean(bb));
		guipai = MajiangPai.valueOf(bb.get());
		dangpai = MajiangPai.valueOf(bb.get());
	}

}
