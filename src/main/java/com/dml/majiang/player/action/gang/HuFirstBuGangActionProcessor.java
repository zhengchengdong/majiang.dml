package com.dml.majiang.player.action.gang;

import java.util.Set;

import com.dml.majiang.ju.Ju;
import com.dml.majiang.pai.fenzu.GangType;
import com.dml.majiang.pan.Pan;
import com.dml.majiang.player.MajiangPlayer;
import com.dml.majiang.player.action.MajiangPlayerActionType;

/**
 * 胡优先的杠，有胡的情况下不能杠，但要记录杠操作
 * 
 * @author lsc
 *
 */
public class HuFirstBuGangActionProcessor implements MajiangPlayerGangActionProcessor {

	@Override
	public void process(MajiangGangAction action, Ju ju) throws Exception {
		Pan currentPan = ju.getCurrentPan();
		MajiangPlayer player = currentPan.findPlayerById(action.getActionPlayerId());
		MajiangPlayer xiajia = currentPan.findXiajia(player);
		boolean couldGang = true;
		while (true) {
			if (!xiajia.getId().equals(player.getId())) {
				Set<MajiangPlayerActionType> actionTypesSet = xiajia.collectActionCandidatesType();
				if (actionTypesSet.contains(MajiangPlayerActionType.hu)) {
					couldGang = false;
				}
			} else {
				break;
			}
			xiajia = currentPan.findXiajia(xiajia);
		}

		if (couldGang) {
			GangType gangType = action.getGangType();
			if (gangType.equals(GangType.gangdachu)) {
				currentPan.playerGangDachupai(action.getActionPlayerId(), action.getDachupaiPlayerId(),
						action.getPai());
			} else if (gangType.equals(GangType.shoupaigangmo)) {
				currentPan.playerShoupaiGangMo(action.getActionPlayerId(), action.getPai());
			} else if (gangType.equals(GangType.gangsigeshoupai)) {
				currentPan.playerGangSigeshoupai(action.getActionPlayerId(), action.getPai());
			} else if (gangType.equals(GangType.kezigangmo)) {
				currentPan.playerKeziGangMo(action.getActionPlayerId(), action.getPai());
			} else if (gangType.equals(GangType.kezigangshoupai)) {
				currentPan.playerKeziGangShoupai(action.getActionPlayerId(), action.getPai());
			} else {
			}
		}
	}

}
