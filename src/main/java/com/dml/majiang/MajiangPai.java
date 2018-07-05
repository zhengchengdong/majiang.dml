package com.dml.majiang;

public enum MajiangPai {
	yiwan, erwan, sanwan, siwan, wuwan, liuwan, qiwan, bawan, jiuwan, yitong, ertong, santong, sitong, wutong, liutong, qitong, batong, jiutong, yitiao, ertiao, santiao, sitiao, wutiao, liutiao, qitiao, batiao, jiutiao, dongfeng, nanfeng, xifeng, beifeng, hongzhong, facai, baiban, chun, xia, qiu, dong, mei, lan, zhu, ju;
	private static MajiangPai[] array = MajiangPai.values();

	public static MajiangPai valueOf(int ordinal) {
		return array[ordinal];
	}
}
