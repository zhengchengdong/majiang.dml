package com.dml.majiang;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class NoHuapaiRandomAvaliablePaiFiller implements AvaliablePaiFiller {

	private long seed;

	public NoHuapaiRandomAvaliablePaiFiller() {
	}

	public NoHuapaiRandomAvaliablePaiFiller(long seed) {
		this.seed = seed;
	}

	@Override
	public void fillAvaliablePai(Ju ju) throws Exception {
		List<MajiangPai> allPaiTypeList = Arrays.asList(MajiangPai.values());
		allPaiTypeList.remove(MajiangPai.chun);
		allPaiTypeList.remove(MajiangPai.xia);
		allPaiTypeList.remove(MajiangPai.qiu);
		allPaiTypeList.remove(MajiangPai.dong);
		allPaiTypeList.remove(MajiangPai.mei);
		allPaiTypeList.remove(MajiangPai.lan);
		allPaiTypeList.remove(MajiangPai.zhu);
		allPaiTypeList.remove(MajiangPai.ju);

		List<MajiangPai> allPaiList = new ArrayList<>();
		allPaiTypeList.forEach((paiType) -> {
			for (int i = 0; i < 4; i++) {
				allPaiList.add(paiType);
			}
		});

		Collections.shuffle(allPaiList, new Random(seed));
		ju.getCurrentPan().setAvaliablePaiList(allPaiList);
		ju.getCurrentPan().setPaiTypeList(allPaiTypeList);

	}

	public long getSeed() {
		return seed;
	}

	public void setSeed(long seed) {
		this.seed = seed;
	}

}
