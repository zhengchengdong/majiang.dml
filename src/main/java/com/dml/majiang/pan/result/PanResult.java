package com.dml.majiang.pan.result;

import java.util.List;

import com.dml.majiang.position.MajiangPosition;

public abstract class PanResult {
	private int panNo;
	private long panFinishTime;

	public abstract List<String> allPlayerIds();

	public abstract String findZhuangPlayerId();

	public abstract boolean ifPlayerHu(String playerId);

	public abstract MajiangPosition playerMenFeng(String playerId);

	public abstract String findXiajiaPlayerId(String playerId);

	public int getPanNo() {
		return panNo;
	}

	public void setPanNo(int panNo) {
		this.panNo = panNo;
	}

	public long getPanFinishTime() {
		return panFinishTime;
	}

	public void setPanFinishTime(long panFinishTime) {
		this.panFinishTime = panFinishTime;
	}

}
