package com.dml.majiang;

import java.util.HashMap;
import java.util.Map;

public class MajiangPositionCircle {

	private static Map<MajiangPosition, MajiangPosition> nextClockwiseMap;

	static {
		nextClockwiseMap = new HashMap<>();
		nextClockwiseMap.put(MajiangPosition.dong, MajiangPosition.nan);
		nextClockwiseMap.put(MajiangPosition.nan, MajiangPosition.xi);
		nextClockwiseMap.put(MajiangPosition.xi, MajiangPosition.bei);
		nextClockwiseMap.put(MajiangPosition.bei, MajiangPosition.dong);
	}

	public static MajiangPosition nextClockwise(MajiangPosition current) {
		return nextClockwiseMap.get(current);
	}

}
