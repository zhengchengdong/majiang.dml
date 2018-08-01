package com.dml.majiang.action;

import com.dml.majiang.ju.Ju;

public interface MajiangPlayerDaActionStatisticsListener extends MajiangPlayerActionStatisticsListener {
	public void update(MajiangDaAction daAction, Ju ju) throws Exception;
}
