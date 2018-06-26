package com.dml.majiang;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomMustHasDongPlayersPositionDeterminer implements PlayersPositionDeterminer {

	private long seed;

	public RandomMustHasDongPlayersPositionDeterminer() {
	}

	public RandomMustHasDongPlayersPositionDeterminer(long seed) {
		this.seed = seed;
	}

	@Override
	public void determinePlayersPosition(Ju ju) throws Exception {
		Pan currentPan = ju.getCurrentPan();
		List<String> sortedPlayerIdList = currentPan.sortedPlayerIdList();
		List<MajiangPosition> pList = new ArrayList<>();
		pList.add(MajiangPosition.nan);
		pList.add(MajiangPosition.xi);
		pList.add(MajiangPosition.bei);

		Random r = new Random(seed);
		String dongPlayerId = sortedPlayerIdList.get(r.nextInt(sortedPlayerIdList.size()));
		currentPan.updatePlayerPosition(dongPlayerId, MajiangPosition.dong);
		sortedPlayerIdList.remove(dongPlayerId);

		if (sortedPlayerIdList.size() == 1) {
			currentPan.updatePlayerPosition(sortedPlayerIdList.get(0), MajiangPosition.xi);
		} else {
			while (!sortedPlayerIdList.isEmpty()) {
				String playerId = sortedPlayerIdList.remove(0);
				MajiangPosition position = pList.remove(r.nextInt(pList.size()));
				currentPan.updatePlayerPosition(playerId, position);
			}
		}

	}

}
