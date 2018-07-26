package com.dml.majiang;

import java.util.Set;

/**
 * 胡优先的杠，最普遍的杠。在其他玩家有胡的机会的情况下不能杠。
 * 
 * @author Neo
 *
 */
public class HuFirstGangActionProcessor implements MajiangPlayerGangActionProcessor {

	@Override
	public void process(MajiangGangAction action, Ju ju) throws Exception {
		Pan currentPan = ju.getCurrentPan();
		MajiangPlayer player = currentPan.findPlayerById(action.getActionPlayerId());
		MajiangPlayer xiajia = currentPan.findXiajia(player);
		while (true) {
			if (!xiajia.getId().equals(player.getId())) {
				Set<MajiangPlayerActionType> actionTypesSet = xiajia.collectActionCandidatesType();
				if (actionTypesSet.contains(MajiangPlayerActionType.hu)) {
					throw new HuFirstException();
				}
			} else {
				break;
			}
			xiajia = currentPan.findXiajia(xiajia);
		}

		GangType gangType = action.getGangType();
		if (gangType.equals(GangType.gangdachu)) {
			currentPan.playerGangDachupai(action.getActionPlayerId(), action.getDachupaiPlayerId(), action.getPai());
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
