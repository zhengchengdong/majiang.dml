package com.dml.majiang;

import java.util.List;
import java.util.Random;

public class RandomGuipaiDeterminer implements GuipaiDeterminer {

	private long seed;

	public RandomGuipaiDeterminer() {
	}

	public RandomGuipaiDeterminer(long seed) {
		this.seed = seed;
	}

	@Override
	public void determineGuipai(Ju ju) throws Exception {
		Pan currentPan = ju.getCurrentPan();
		List<MajiangPai> paiTypeList = currentPan.getPaiTypeList();
		MajiangPai guipaiType = paiTypeList.get(new Random(seed).nextInt(paiTypeList.size()));
		currentPan.publicGuipaiAndRemoveFromList(guipaiType);
		for (MajiangPlayer majiangPlayer : currentPan.getMajiangPlayerIdMajiangPlayerMap().values()) {
			majiangPlayer.addGuipaiType(guipaiType);
		}
	}

	public long getSeed() {
		return seed;
	}

	public void setSeed(long seed) {
		this.seed = seed;
	}

}
