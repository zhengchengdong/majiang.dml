package com.dml.majiang;

public enum MajiangPai {
	yiwan, erwan, sanwan, siwan, wuwan, liuwan, qiwan, bawan, jiuwan, yitong, ertong, santong, sitong, wutong, liutong, qitong, batong, jiutong, yitiao, ertiao, santiao, sitiao, wutiao, liutiao, qitiao, batiao, jiutiao, dongfeng, nanfeng, xifeng, beifeng, hongzhong, facai, baiban, chun, xia, qiu, dong, mei, lan, zhu, ju;
	private static MajiangPai[] array = MajiangPai.values();

	public static MajiangPai valueOf(int ordinal) {
		return array[ordinal];
	}

	public static boolean isXushupai(MajiangPai pai) {
		int ordinal = pai.ordinal();
		return (ordinal >= 0 && ordinal <= 26);
	}

	public static boolean isZipai(MajiangPai pai) {
		int ordinal = pai.ordinal();
		return (ordinal >= 26 && ordinal <= 32);
	}

	public static MajiangPai[] xushupaiAndZipaiArray() {
		MajiangPai[] xushupaiAndZipaiArray = new MajiangPai[34];
		System.arraycopy(array, 0, xushupaiAndZipaiArray, 0, 34);
		return xushupaiAndZipaiArray;
	}

	public static MajiangPai[] xushupaiArray() {
		MajiangPai[] xushupaiArray = new MajiangPai[27];
		System.arraycopy(array, 0, xushupaiArray, 0, 27);
		return xushupaiArray;
	}

}
