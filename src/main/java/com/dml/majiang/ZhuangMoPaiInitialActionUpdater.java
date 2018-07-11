package com.dml.majiang;

public class ZhuangMoPaiInitialActionUpdater implements MajiangPlayerInitialActionUpdater {

	@Override
	public void updateActions(Ju ju) throws Exception {
		Pan currentPan = ju.getCurrentPan();
		currentPan.addPlayerActionCandidate(new MajiangMoAction(currentPan.getZhuangPlayerId(), new LundaoMopai()));
		currentPan.updatePublicWaitingPlayerIdToZhuang();
	}

}
