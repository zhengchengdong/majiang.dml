package com.dml.majiang.custom;

import com.dml.majiang.ju.Ju;
import com.dml.majiang.pan.Pan;
import com.dml.majiang.player.ZhuangDeterminer;
import com.dml.majiang.position.MajiangPosition;

public class MenFengDongZhuangDeterminer implements ZhuangDeterminer {

	@Override
	public void determineZhuang(Ju ju) throws Exception {
		Pan currentPan = ju.getCurrentPan();
		String dongPlayerId = currentPan.playerIdForMenFeng(MajiangPosition.dong);
		currentPan.setZhuangPlayerId(dongPlayerId);
	}

}
