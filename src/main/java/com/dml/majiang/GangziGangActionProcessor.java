package com.dml.majiang;

public class GangziGangActionProcessor implements MajiangPlayerGangActionProcessor {

	@Override
	public void process(MajiangGangAction action, Ju ju) throws Exception {
		Pan currentPan = ju.getCurrentPan();
		GangType gangType = action.getGangType();
		if (gangType.equals(GangType.gangdachu)) {
			currentPan.playerGangDachupai(action.getActionPlayerId(), action.getDachupaiPlayerId(), action.getPai());
		} else if (gangType.equals(GangType.shoupaigangmo)) {
			currentPan.playerShoupaiGangMo(action.getActionPlayerId(), action.getPai());
		} else if (gangType.equals(GangType.kezigangmo)) {
			currentPan.playerKeziGangMo(action.getActionPlayerId(), action.getPai());
		} else {
		}

	}

}
