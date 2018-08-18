package com.dml.majiang.player.action.listener.comprehensive;

import com.dml.majiang.ju.Ju;
import com.dml.majiang.pan.Pan;
import com.dml.majiang.player.MajiangPlayer;
import com.dml.majiang.player.action.da.MajiangDaAction;
import com.dml.majiang.player.action.listener.da.MajiangPlayerDaActionStatisticsListener;

/**
 * 检测点炮地胡可能。点炮地胡是最常见的地胡。
 * 
 * @author Neo
 *
 */
public class DianpaoDihuOpportunityDetector implements MajiangPlayerDaActionStatisticsListener {

	/**
	 * 庄打了几次。0没打，1打了一次，2打了多次
	 */
	private int zhuangDaCount;

	public boolean ifDihuOpportunity() {
		return zhuangDaCount == 1;
	}

	@Override
	public void updateForNextPan() {
		zhuangDaCount = 0;
	}

	@Override
	public void update(MajiangDaAction daAction, Ju ju) throws Exception {
		if (zhuangDaCount < 2) {
			Pan currentPan = ju.getCurrentPan();
			MajiangPlayer daPlayer = currentPan.findPlayerById(daAction.getActionPlayerId());
			boolean zhuangDa = currentPan.getZhuangPlayerId().equals(daPlayer.getId());
			if (zhuangDa) {
				zhuangDaCount++;
			}
		}
	}

}
