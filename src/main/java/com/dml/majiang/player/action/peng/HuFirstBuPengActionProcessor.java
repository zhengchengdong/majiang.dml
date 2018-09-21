package com.dml.majiang.player.action.peng;

import java.util.Set;

import com.dml.majiang.ju.Ju;
import com.dml.majiang.pan.Pan;
import com.dml.majiang.player.MajiangPlayer;
import com.dml.majiang.player.action.MajiangPlayerActionType;

/**
 * 胡优先的碰，有胡的情况下不能碰，但要记录碰操作
 * 
 * @author lsc
 *
 */
public class HuFirstBuPengActionProcessor implements MajiangPlayerPengActionProcessor {

	@Override
	public void process(MajiangPengAction action, Ju ju) throws Exception {
		Pan currentPan = ju.getCurrentPan();
		MajiangPlayer player = currentPan.findPlayerById(action.getActionPlayerId());
		MajiangPlayer xiajia = currentPan.findXiajia(player);
		boolean couldPeng = true;
		while (true) {
			if (!xiajia.getId().equals(player.getId())) {
				Set<MajiangPlayerActionType> actionTypesSet = xiajia.collectActionCandidatesType();
				if (actionTypesSet.contains(MajiangPlayerActionType.hu)) {
					couldPeng = false;
				}
			} else {
				break;
			}
			xiajia = currentPan.findXiajia(xiajia);
		}

		if (couldPeng) {
			currentPan.playerPengPai(action.getActionPlayerId(), action.getDachupaiPlayerId(), action.getPai());
		}
	}

}
