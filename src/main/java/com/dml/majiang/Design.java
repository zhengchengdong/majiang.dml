package com.dml.majiang;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Design {

	/**
	 * 总共几张牌可能的序数牌组
	 */
	private static Map<Integer, List<Long>> paiZhangShuXuShuPaiZuListMap = new HashMap<>();

	/**
	 * 序数牌组和其对应的构型
	 */
	private static Map<Long, List<Integer>> xuShuPaiZuCodeGouXingListMap = new HashMap<>();

	public static void main(String[] args) {
		// 花色: 什么牌，一万，三条，发财等
		// 牌型: 完全描述了指定的牌的集合，什么花色的牌有几张
		// 构型: 面向和牌关心的几个点，单牌个数，对子个数，刻子个数，杠子个数，顺子个数
		// 构型编码: 构形用一个int来编码:低到高--5位单牌个数，4位对子个数，3位刻子个数，3位杠子个数，3位顺子个数
		// 同构: 具有不同牌型的两个牌的集合，他们具有相同的构型集合（可能可以看成多种构型，所以是构型集合），那么他们同构
		// 序数牌组: 完全连的一组序数牌,至少3连,每种牌张数不限（抽象概念，不关心是万筒条，不关心起点）
		// 序数牌组编码: 从小序号到大序号,从低位到高位,每n位的值代表该序号牌的数量

		int maxShouPai = 17;
		int maxTongHuaSePaiZhangShu = 7;// 由于存在鬼牌的原因，同一种牌最多可以有几张
		int maxTongHuaSePaiZhangShuBiCount = 3;// 用几位足够表达（最多）牌张数
		int maxShouPaiXuShuPaiZu = maxShouPai / 3;// 手牌最多可能几个序数牌组

		// 序数牌组是抽象的，其目的是为了计算抽象的构型，显然，不同花色但是相同序数牌组的两组牌一定同构

		// 我们希望直接用序数牌组编码作为数组下标来查询其构型list的索引（这是关键效率优化）。虽然序数牌组编码空间很大，但是很稀疏。
		// 所以其构型list的索引空间其实很小。那么这就有利于实现直接数组下标查询多个序数牌组组合好后的结果。
		// 刚才提到序数牌组编码空间会很大，然而不幸的是在双鬼牌的情况下一种花色的牌有可能多达10张，这样编码空间就会过大。
		// 应对这种情况，我们在游戏过程中的手牌的结构中先按照约定的较小的比如3位一种牌编码，一旦摸牌太多出现3位不够存储的情况就整体改为4位一种牌编码。
		// 构型存储两种结构。。。

		// 对于给定的所有牌张数和最多同花色牌张数，计算其所有可能的序数牌组，目的是前期过滤，缩小计算规模
		calculateXuShuPaiZu(maxShouPai, maxTongHuaSePaiZhangShu, maxTongHuaSePaiZhangShuBiCount);

		// 为序数牌组计算构型
		calculateGouXingForXuShuPaiZu(maxTongHuaSePaiZhangShuBiCount);

	}

	private static void calculateGouXingForXuShuPaiZu(int maxTongHuaSePaiZhangShuBiCount) {
		// TODO Auto-generated method stub

	}

	private static void calculateXuShuPaiZu(int maxShouPai, int maxTongHuaSePaiZhangShu,
			int maxTongHuaSePaiZhangShuBiCount) {
		// TODO Auto-generated method stub

	}

}
