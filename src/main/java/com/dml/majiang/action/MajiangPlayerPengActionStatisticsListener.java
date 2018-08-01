package com.dml.majiang.action;

import com.dml.majiang.ju.Ju;

public interface MajiangPlayerPengActionStatisticsListener extends MajiangPlayerActionStatisticsListener {
	public void update(MajiangPengAction pengAction, Ju ju) throws Exception;
}
