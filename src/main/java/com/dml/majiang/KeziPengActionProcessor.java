package com.dml.majiang;

public class KeziPengActionProcessor implements MajiangPlayerPengActionProcessor {

	@Override
	public void process(MajiangPengAction action, Ju ju) throws Exception {
		Pan currentPan = ju.getCurrentPan();
		currentPan.playerPengPai(action.getActionPlayerId(), action.getDachupaiPlayerId(), action.getPai());
	}

}
