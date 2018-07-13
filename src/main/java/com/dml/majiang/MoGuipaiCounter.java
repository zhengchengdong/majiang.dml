package com.dml.majiang;

/**
 * 统计摸了几张鬼牌
 * 
 * @author Neo
 *
 */
public class MoGuipaiCounter implements MajiangPlayerMoActionStatisticsListener {

	private int count = 0;

	@Override
	public void update(MajiangMoAction moAction, Ju ju) throws Exception {
		Pan currentPan = ju.getCurrentPan();
		MajiangPlayer player = currentPan.findPlayerById(moAction.getActionPlayerId());
		if (player.gangmoGuipai()) {
			count++;
		}
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

}
