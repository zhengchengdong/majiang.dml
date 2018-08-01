package com.dml.majiang.pan.result;

public abstract class PanResult {
	private int panNo;
	private long panFinishTime;

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
