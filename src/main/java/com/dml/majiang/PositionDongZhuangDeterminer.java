package com.dml.majiang;

public class PositionDongZhuangDeterminer implements ZhuangDeterminer {

	@Override
	public void determineZhuang(Ju ju) throws Exception {
		Pan currentPan = ju.getCurrentPan();
		String dongPlayerId = currentPan.playerIdForPosition(MajiangPosition.dong);
		currentPan.setZhuangPlayerId(dongPlayerId);
	}

}
