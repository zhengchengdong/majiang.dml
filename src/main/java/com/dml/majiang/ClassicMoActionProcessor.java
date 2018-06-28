package com.dml.majiang;

public class ClassicMoActionProcessor implements MajiangPlayerMoActionProcessor {

	@Override
	public void process(String playerId, MajiangMoAction action, Ju ju) throws Exception {
		ju.getCurrentPan().sequentialMoPai(playerId);
	}

}
