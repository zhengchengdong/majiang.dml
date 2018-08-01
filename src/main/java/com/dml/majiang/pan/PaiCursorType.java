package com.dml.majiang.pan;

public enum PaiCursorType {
	playerLatestDachupai, playerLatestGangchupai;

	private static PaiCursorType[] array = PaiCursorType.values();

	public static PaiCursorType valueOf(int ordinal) {
		return array[ordinal];
	}
}
