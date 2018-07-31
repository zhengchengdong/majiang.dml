package com.dml.majiang;

/**
 * 一局打固定的几盘
 * 
 * @author Neo
 *
 */
public class FixedPanNumbersJuFinishiDeterminer implements JuFinishiDeterminer {

	private int fixedPanNumbers;

	public FixedPanNumbersJuFinishiDeterminer() {
	}

	public FixedPanNumbersJuFinishiDeterminer(int fixedPanNumbers) {
		this.fixedPanNumbers = fixedPanNumbers;
	}

	@Override
	public boolean determineToFinishJu(Ju ju) {
		return (ju.countFinishedPan() >= fixedPanNumbers);
	}

	public int getFixedPanNumbers() {
		return fixedPanNumbers;
	}

	public void setFixedPanNumbers(int fixedPanNumbers) {
		this.fixedPanNumbers = fixedPanNumbers;
	}

}
