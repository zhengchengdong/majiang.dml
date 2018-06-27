package com.dml.majiang;

public class MenFengDongZhuangDeterminer implements ZhuangDeterminer {

	@Override
	public void determineZhuang(Ju ju) throws Exception {
		Pan currentPan = ju.getCurrentPan();
		String dongPlayerId = currentPan.playerIdForMenFeng(MajiangPosition.dong);
		currentPan.setZhuangPlayerId(dongPlayerId);
	}

}
