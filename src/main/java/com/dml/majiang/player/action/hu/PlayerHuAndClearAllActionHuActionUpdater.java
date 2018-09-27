package com.dml.majiang.player.action.hu;

import com.dml.majiang.ju.Ju;
import com.dml.majiang.pan.Pan;
import com.dml.majiang.player.MajiangPlayer;

/**
 * 玩家胡了之后清除自身所有动作
 * 
 * @author lsc
 *
 */
public class PlayerHuAndClearAllActionHuActionUpdater implements MajiangPlayerHuActionUpdater {

	@Override
	public void updateActions(MajiangHuAction huAction, Ju ju) {
		Pan currentPan = ju.getCurrentPan();
		MajiangPlayer huPlayer = currentPan.findPlayerById(huAction.getActionPlayerId());
		huPlayer.clearActionCandidates();
	}

}
