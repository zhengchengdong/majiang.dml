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

	public void determinePlayersPosition() throws Exception {
		playersPositionDeterminer.determinePlayersPosition(this);
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

}
