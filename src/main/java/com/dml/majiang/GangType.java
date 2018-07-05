package com.dml.majiang;

public enum GangType {
	gangdachu, shoupaigangmo, kezigangmo;
	private static GangType[] array = GangType.values();

	public static GangType valueOf(int ordinal) {
		return array[ordinal];
	}
}
