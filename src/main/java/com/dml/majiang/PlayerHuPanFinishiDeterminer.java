package com.dml.majiang;

/**
 * 最常见的，有人胡了就结束
 * 
 * @author Neo
 *
 */
public class PlayerHuPanFinishiDeterminer implements CurrentPanFinishiDeterminer {

	@Override
	public boolean determineToFinishCurrentPan(Ju ju) {
		Pan currentPan = ju.getCurrentPan();
		return currentPan.anyPlayerHu();
	}

}
