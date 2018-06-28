package com.dml.majiang;

public class ZhuangMoPaiInitialActionUpdater implements MajiangPlayerInitialActionUpdater {

	@Override
	public void updateActions(Ju ju) throws Exception {
		Pan currentPan = ju.getCurrentPan();
		currentPan.addPlayerActionCandidate(currentPan.getZhuangPlayerId(), new MajiangMoAction(1));
		currentPan.updatePublicWaitingPlayerIdToZhuang();
	}

}
