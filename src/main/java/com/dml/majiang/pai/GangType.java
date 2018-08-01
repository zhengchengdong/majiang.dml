package com.dml.majiang.pai;

public enum GangType {
	gangdachu, shoupaigangmo, kezigangmo, kezigangshoupai, gangsigeshoupai;
	private static GangType[] array = GangType.values();

	public static GangType valueOf(int ordinal) {
		return array[ordinal];
	}
}
