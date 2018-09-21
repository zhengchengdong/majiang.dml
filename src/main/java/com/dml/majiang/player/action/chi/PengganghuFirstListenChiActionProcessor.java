package com.dml.majiang.player.action.chi;

import java.util.Set;

import com.dml.majiang.ju.Ju;
import com.dml.majiang.pan.Pan;
import com.dml.majiang.player.MajiangPlayer;
import com.dml.majiang.player.action.MajiangPlayerActionType;

/**
 * 碰杠胡优先的吃，有碰杠胡的情况下不能吃，但要记录吃操作
 * 
 * @author lsc
 *
 */
public class PengganghuFirstListenChiActionProcessor implements MajiangPlayerChiActionProcessor {

	@Override
	public void process(MajiangChiAction action, Ju ju) throws Exception {
		Pan currentPan = ju.getCurrentPan();
		MajiangPlayer player = currentPan.findPlayerById(action.getActionPlayerId());
		MajiangPlayer xiajia = currentPan.findXiajia(player);
		boolean couldChi = true;
		while (true) {
			if (!xiajia.getId().equals(player.getId())) {
				Set<MajiangPlayerActionType> actionTypesSet = xiajia.collectActionCandidatesType();
				if (actionTypesSet.contains(MajiangPlayerActionType.peng)
						|| actionTypesSet.contains(MajiangPlayerActionType.gang)
						|| actionTypesSet.contains(MajiangPlayerActionType.hu)) {
					couldChi = false;
				}
			} else {
				break;
			}
			xiajia = currentPan.findXiajia(xiajia);
		}

		if (couldChi) {
			currentPan.playerChiPai(action.getActionPlayerId(), action.getDachupaiPlayerId(), action.getChijinPai(),
					action.getShunzi());
		}
	}

}
