package com.dml.majiang.player.action.mo;

import com.dml.majiang.ju.Ju;
import com.dml.majiang.player.action.MajiangPlayerActionStatisticsListener;

public interface MajiangPlayerMoActionStatisticsListener extends MajiangPlayerActionStatisticsListener {
	public void update(MajiangMoAction moAction, Ju ju) throws Exception;
}
