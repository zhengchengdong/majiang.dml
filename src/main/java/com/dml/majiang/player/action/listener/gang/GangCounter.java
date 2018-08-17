package com.dml.majiang.player.action.listener.gang;

import com.dml.majiang.ju.Ju;
import com.dml.majiang.player.action.gang.MajiangGangAction;

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
