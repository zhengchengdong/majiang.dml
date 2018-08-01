package com.dml.majiang.player.action.peng;

import com.dml.majiang.ju.Ju;
import com.dml.majiang.player.action.MajiangPlayerActionStatisticsListener;

public interface MajiangPlayerPengActionStatisticsListener extends MajiangPlayerActionStatisticsListener {
	public void update(MajiangPengAction pengAction, Ju ju) throws Exception;
}
