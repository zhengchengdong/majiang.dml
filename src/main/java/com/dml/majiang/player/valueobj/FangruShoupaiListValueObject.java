package com.dml.majiang.player.valueobj;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.dml.majiang.pai.MajiangPai;
import com.dml.majiang.serializer.ByteBufferAble;
import com.dml.majiang.serializer.ByteBufferSerializer;

public class FangruShoupaiListValueObject implements ByteBufferAble {

	private List<MajiangPai> putongShoupaiList;
	private List<MajiangPai> guipaiShoupaiList;
	private int totalShoupaiCount;

	public FangruShoupaiListValueObject() {
	}

	public FangruShoupaiListValueObject(List<MajiangPai> fangruShoupaiList, Set<MajiangPai> guipaiTypeSet) {
		putongShoupaiList = new ArrayList<>();
		guipaiShoupaiList = new ArrayList<>();
		fangruShoupaiList.forEach((shouPai) -> {
			if (!guipaiTypeSet.contains(shouPai)) {
				putongShoupaiList.add(shouPai);
			} else {
				guipaiShoupaiList.add(shouPai);
			}
		});
		totalShoupaiCount = fangruShoupaiList.size();
	}

	@Override
	public void toByteBuffer(ByteBuffer bb) throws Throwable {
		majiangPaiListToByteBuffer(putongShoupaiList, bb);
		majiangPaiListToByteBuffer(guipaiShoupaiList, bb);
		bb.putInt(totalShoupaiCount);
	}

	@Override
	public void fillByByteBuffer(ByteBuffer bb) throws Throwable {
		putongShoupaiList = fillMajiangPaiList(bb);
		guipaiShoupaiList = fillMajiangPaiList(bb);
		totalShoupaiCount = bb.getInt();
	}

	private void majiangPaiListToByteBuffer(List<MajiangPai> majiangPaiList, ByteBuffer bb) {
		byte[] byteArray = new byte[majiangPaiList.size()];
		for (int i = 0; i < majiangPaiList.size(); i++) {
			byteArray[i] = (byte) majiangPaiList.get(i).ordinal();
		}
		ByteBufferSerializer.byteArrayToByteBuffer(byteArray, bb);
	}

	private List<MajiangPai> fillMajiangPaiList(ByteBuffer bb) {
		List<MajiangPai> list = new ArrayList<>();
		byte[] avaliablePaiByteArray = ByteBufferSerializer.byteBufferTobyteArray(bb);
		for (int i = 0; i < avaliablePaiByteArray.length; i++) {
			int ordinal = (Byte.toUnsignedInt(avaliablePaiByteArray[i]));
			list.add(MajiangPai.valueOf(ordinal));
		}
		return list;
	}

	public List<MajiangPai> getPutongShoupaiList() {
		return putongShoupaiList;
	}

	public void setPutongShoupaiList(List<MajiangPai> putongShoupaiList) {
		this.putongShoupaiList = putongShoupaiList;
	}

	public List<MajiangPai> getGuipaiShoupaiList() {
		return guipaiShoupaiList;
	}

	public void setGuipaiShoupaiList(List<MajiangPai> guipaiShoupaiList) {
		this.guipaiShoupaiList = guipaiShoupaiList;
	}

	public int getTotalShoupaiCount() {
		return totalShoupaiCount;
	}

	public void setTotalShoupaiCount(int totalShoupaiCount) {
		this.totalShoupaiCount = totalShoupaiCount;
	}

}
