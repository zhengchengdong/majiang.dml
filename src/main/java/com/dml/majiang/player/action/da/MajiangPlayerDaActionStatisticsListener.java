package com.dml.majiang.player.action.da;

import com.dml.majiang.ju.Ju;
import com.dml.majiang.player.action.MajiangPlayerActionStatisticsListener;

public interface MajiangPlayerDaActionStatisticsListener extends MajiangPlayerActionStatisticsListener {
	public void update(MajiangDaAction daAction, Ju ju) throws Exception;
}
