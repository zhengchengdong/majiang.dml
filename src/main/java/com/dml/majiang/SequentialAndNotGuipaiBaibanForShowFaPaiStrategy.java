package com.dml.majiang;

/**
 * 顺序发牌。白板如果不是鬼牌的话，拿出公示。
 * 
 * @author Neo
 *
 */
public class SequentialAndNotGuipaiBaibanForShowFaPaiStrategy implements FaPaiStrategy {

	private int faPaiCountsForOnePlayer;

	@Override
	public void faPai(Ju ju) throws Exception {
		Pan currentPan = ju.getCurrentPan();
		currentPan.sequentialPlayersInitialMoPai(faPaiCountsForOnePlayer);
		for (MajiangPlayer player : currentPan.getMajiangPlayerIdMajiangPlayerMap().values()) {
			if (!player.getGuipaiTypeSet().contains(MajiangPai.baiban)) {
				player.moveShoupaiToPublicPaiForType(MajiangPai.baiban);
			}
		}
	}

	public int getFaPaiCountsForOnePlayer() {
		return faPaiCountsForOnePlayer;
	}

	public void setFaPaiCountsForOnePlayer(int faPaiCountsForOnePlayer) {
		this.faPaiCountsForOnePlayer = faPaiCountsForOnePlayer;
	}

}
