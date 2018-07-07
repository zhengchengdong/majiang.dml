package com.dml.majiang;

/**
 * 最常见的打出手牌
 * 
 * @author Neo
 *
 */
public class DachushoupaiDaActionProcessor implements MajiangPlayerDaActionProcessor {

	@Override
	public void process(String playerId, MajiangDaAction action, Ju ju) throws Exception {
		Pan currentPan = ju.getCurrentPan();
		currentPan.playerDaChuPai(playerId, action.getPai());
	}

}
