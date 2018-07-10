package com.dml.majiang;

public class ShunziChiActionProcessor implements MajiangPlayerChiActionProcessor {

	@Override
	public void process(MajiangChiAction action, Ju ju) throws Exception {
		Pan currentPan = ju.getCurrentPan();
		currentPan.playerChiPai(action.getActionPlayerId(), action.getDachupaiPlayerId(), action.getChijinPai(),
				action.getShunzi());
	}

}
