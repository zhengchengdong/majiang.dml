package com.dml.majiang.ju.nextpan;

import java.util.HashMap;
import java.util.Map;

import com.dml.majiang.ju.Ju;

public class AllPlayersReadyCreateNextPanDeterminer implements CreateNextPanDeterminer {

	private Map<String, Boolean> playerReadyMap = new HashMap<>();

	@Override
	public boolean determineToCreateNextPan(Ju ju) {
		if (ju.getCurrentPan() == null) {
			for (boolean ready : playerReadyMap.values()) {
				if (!ready) {
					return false;
				}
			}
			return true;
		} else {
			return false;
		}
	}

}
