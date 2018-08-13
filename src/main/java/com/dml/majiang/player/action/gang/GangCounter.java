package com.dml.majiang.player.action.gang;

import com.dml.majiang.ju.Ju;

public class GangCounter implements MajiangPlayerGangActionStatisticsListener {

	private int count = 0;

	@Override
	public void update(MajiangGangAction gangAction, Ju ju) throws Exception {
		count++;
	}

	public int getCount() {
		return count;
	}

}
