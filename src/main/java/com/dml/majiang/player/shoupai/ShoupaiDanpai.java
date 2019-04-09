package com.dml.majiang.player.shoupai;

import java.nio.ByteBuffer;

import com.dml.majiang.pai.MajiangPai;
import com.dml.majiang.serializer.ByteBufferAble;
import com.dml.majiang.serializer.ByteBufferSerializer;

/**
 * 手牌分组出来的单牌
 * 
 * @author Neo
 *
 */
public class ShoupaiDanpai implements ShoupaiMajiangPaiFenZu, ByteBufferAble {
	private MajiangPai danpaiType;
	private ShoupaiJiesuanPai pai;

	@Override
	public void toByteBuffer(ByteBuffer bb) throws Throwable {
		bb.put((byte) danpaiType.ordinal());
		ByteBufferSerializer.objToByteBuffer(pai, bb);
	}

	@Override
	public void fillByByteBuffer(ByteBuffer bb) throws Throwable {
		danpaiType = MajiangPai.valueOf(bb.get());
		pai = ByteBufferSerializer.byteBufferToObj(bb);
	}

	@Override
	public void fillAllBlankPaiWithBenPai() {
		if (pai == null) {
			pai = new BenPai(danpaiType);
		}
	}

	@Override
	public void addShoupaiJiesuanPai(ShoupaiJiesuanPai shoupaiJiesuanPai) {
		if (pai == null) {
			pai = shoupaiJiesuanPai;
		}
	}

	@Override
	public ShoupaiDanpai copy() {
		ShoupaiDanpai newShoupaiDanpai = new ShoupaiDanpai();
		newShoupaiDanpai.setDanpaiType(danpaiType);
		newShoupaiDanpai.setPai(pai.copy());
		return newShoupaiDanpai;
	}

	@Override
	public boolean yuanPaiFenZu() {
		return pai.dangBenPai();
	}

	@Override
	public boolean containsLastActionPai() {
		return pai.isLastActionPai();
	}

	@Override
	public int countDangPai(String dangType) {
		int count = 0;
		if (pai.dangType().equals(dangType)) {
			count++;
		}
		return count;
	}

	public MajiangPai getDanpaiType() {
		return danpaiType;
	}

	public void setDanpaiType(MajiangPai danpaiType) {
		this.danpaiType = danpaiType;
	}

	public ShoupaiJiesuanPai getPai() {
		return pai;
	}

	public void setPai(ShoupaiJiesuanPai pai) {
		this.pai = pai;
	}

}
