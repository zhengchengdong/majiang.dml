package com.dml.majiang;

/**
 * 谁将要打牌等谁，没人打牌啥也不决定
 * 
 * @author Neo
 *
 */
public class WaitDaPlayerPanPublicWaitingPlayerDeterminer implements CurrentPanPublicWaitingPlayerDeterminer {

	@Override
	public void determinePublicWaitingPlayer(Ju ju) throws Exception {
		Pan currentPan = ju.getCurrentPan();
		currentPan.updatePublicWaitingPlayerIdToDaPlayer();
	}

}
