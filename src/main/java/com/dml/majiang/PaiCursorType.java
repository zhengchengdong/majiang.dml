package com.dml.majiang;

public enum PaiCursorType {
	playerLatestDachupai, playerLatestGangchupai;

	private static PaiCursorType[] array = PaiCursorType.values();

	public static PaiCursorType valueOf(int ordinal) {
		return array[ordinal];
	}
}
