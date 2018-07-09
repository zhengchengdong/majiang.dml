package com.dml.majiang;

import java.util.HashMap;
import java.util.Map;

public class MajiangPositionCircle {
	private static Map<MajiangPosition, MajiangPosition> nextAntiClockwiseMap;
	private static Map<MajiangPosition, MajiangPosition> nextClockwiseMap;

	static {
		nextAntiClockwiseMap = new HashMap<>();
		nextAntiClockwiseMap.put(MajiangPosition.dong, MajiangPosition.nan);
		nextAntiClockwiseMap.put(MajiangPosition.nan, MajiangPosition.xi);
		nextAntiClockwiseMap.put(MajiangPosition.xi, MajiangPosition.bei);
		nextAntiClockwiseMap.put(MajiangPosition.bei, MajiangPosition.dong);

		nextClockwiseMap = new HashMap<>();
		nextClockwiseMap.put(MajiangPosition.dong, MajiangPosition.bei);
		nextClockwiseMap.put(MajiangPosition.bei, MajiangPosition.xi);
		nextClockwiseMap.put(MajiangPosition.xi, MajiangPosition.nan);
		nextClockwiseMap.put(MajiangPosition.nan, MajiangPosition.dong);
	}

	public static MajiangPosition nextClockwise(MajiangPosition current) {
		return nextClockwiseMap.get(current);
	}

	public static MajiangPosition nextAntiClockwise(MajiangPosition current) {
		return nextAntiClockwiseMap.get(current);
	}

}
