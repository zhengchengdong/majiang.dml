package com.dml.majiang;

/**
 * 一局麻将
 * 
 * @author Neo
 *
 */
public class Ju {

	private Pan currentPan;

	private PlayersPositionDeterminer playersPositionDeterminer;
	private ZhuangDeterminer zhuangDeterminerForFirstPan;
	private AvaliablePaiFiller avaliablePaiFiller;
	private GuipaiDeterminer guipaiDeterminer;

	public void determinePlayersPosition() throws Exception {
		playersPositionDeterminer.determinePlayersPosition(this);
	}

	public void determineZhuangForFirstPan() throws Exception {
		zhuangDeterminerForFirstPan.determineZhuang(this);
	}

	public void fillAvaliablePai() throws Exception {
		avaliablePaiFiller.fillAvaliablePai(this);
	}

	public void determineGuipai() throws Exception {
		guipaiDeterminer.determineGuipai(this);
	}

	public Pan getCurrentPan() {
		return currentPan;
	}

	public void setCurrentPan(Pan currentPan) {
		this.currentPan = currentPan;
	}

	public PlayersPositionDeterminer getPlayersPositionDeterminer() {
		return playersPositionDeterminer;
	}

	public void setPlayersPositionDeterminer(PlayersPositionDeterminer playersPositionDeterminer) {
		this.playersPositionDeterminer = playersPositionDeterminer;
	}

	public ZhuangDeterminer getZhuangDeterminerForFirstPan() {
		return zhuangDeterminerForFirstPan;
	}

	public void setZhuangDeterminerForFirstPan(ZhuangDeterminer zhuangDeterminerForFirstPan) {
		this.zhuangDeterminerForFirstPan = zhuangDeterminerForFirstPan;
	}

	public AvaliablePaiFiller getAvaliablePaiFiller() {
		return avaliablePaiFiller;
	}

	public void setAvaliablePaiFiller(AvaliablePaiFiller avaliablePaiFiller) {
		this.avaliablePaiFiller = avaliablePaiFiller;
	}

	public GuipaiDeterminer getGuipaiDeterminer() {
		return guipaiDeterminer;
	}

	public void setGuipaiDeterminer(GuipaiDeterminer guipaiDeterminer) {
		this.guipaiDeterminer = guipaiDeterminer;
	}

}
