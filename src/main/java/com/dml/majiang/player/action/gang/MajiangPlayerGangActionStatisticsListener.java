package com.dml.majiang.player.action.gang;

import com.dml.majiang.ju.Ju;
import com.dml.majiang.player.action.MajiangPlayerActionStatisticsListener;

public interface MajiangPlayerGangActionStatisticsListener extends MajiangPlayerActionStatisticsListener {
	public void update(MajiangGangAction gangAction, Ju ju) throws Exception;
}
