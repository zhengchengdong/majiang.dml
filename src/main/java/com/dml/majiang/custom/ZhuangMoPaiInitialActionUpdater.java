package com.dml.majiang.custom;

import com.dml.majiang.action.LundaoMopai;
import com.dml.majiang.action.MajiangMoAction;
import com.dml.majiang.action.MajiangPlayerInitialActionUpdater;
import com.dml.majiang.ju.Ju;
import com.dml.majiang.pan.Pan;

public class ZhuangMoPaiInitialActionUpdater implements MajiangPlayerInitialActionUpdater {

	@Override
	public void updateActions(Ju ju) throws Exception {
		Pan currentPan = ju.getCurrentPan();
		currentPan.addPlayerActionCandidate(new MajiangMoAction(currentPan.getZhuangPlayerId(), new LundaoMopai()));
		currentPan.updatePublicWaitingPlayerIdToZhuang();
	}

}
