package com.dml.majiang;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

public class ShoupaiListValueObject implements ByteBufferAble {

	private List<MajiangPai> paiList;
	private int paiCount;

	public ShoupaiListValueObject() {
	}

	public ShoupaiListValueObject(List<MajiangPai> shoupaiList) {
		paiList = new ArrayList<>(shoupaiList);
		paiCount = shoupaiList.size();
	}

	@Override
	public void toByteBuffer(ByteBuffer bb) throws Throwable {
		majiangPaiListToByteBuffer(paiList, bb);
		bb.putInt(paiCount);
	}

	@Override
	public void fillByByteBuffer(ByteBuffer bb) throws Throwable {
		paiList = fillMajiangPaiList(bb);
		paiCount = bb.getInt();
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

	public List<MajiangPai> getPaiList() {
		return paiList;
	}

	public void setPaiList(List<MajiangPai> paiList) {
		this.paiList = paiList;
	}

	public int getPaiCount() {
		return paiCount;
	}

	public void setPaiCount(int paiCount) {
		this.paiCount = paiCount;
	}

}
