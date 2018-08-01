package com.dml.majiang.action;

import com.dml.majiang.ju.Ju;

public interface MajiangPlayerMoActionStatisticsListener extends MajiangPlayerActionStatisticsListener {
	public void update(MajiangMoAction moAction, Ju ju) throws Exception;
}
