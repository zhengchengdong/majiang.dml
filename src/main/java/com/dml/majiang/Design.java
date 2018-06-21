package com.dml.majiang;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Design {

	/**
	 * 序数牌组构型索引,小空间序数牌组编码作为数组下标,值是xuShuPaiZuGouXingsArray的下标
	 */
	private static int[] xuShuPaiZuGouXingsIdxArray;

	/**
	 * 序数牌组构型索引,key为大空间序数牌组编码,值是xuShuPaiZuGouXingsArray的下标
	 */
	private static Map<Long, Integer> xuShuPaiZuGouXingsIdxMap = new HashMap<>();

	/**
	 * 一个序数牌组组成手牌的构型
	 */
	private static int[][] yiXuShuPaiZuGouXingsArray;

	/**
	 * 二个序数牌组组成手牌的构型
	 */
	private static int[][] erXuShuPaiZuGouXingsArray;

	/**
	 * 三个序数牌组组成手牌的构型
	 */
	private static int[][] sanXuShuPaiZuGouXingsArray;

	/**
	 * 四个序数牌组组成手牌的构型
	 */
	private static int[][] siXuShuPaiZuGouXingsArray;

	/**
	 * 五个序数牌组组成手牌的构型
	 */
	private static int[][] wuXuShuPaiZuGouXingsArray;

	/**
	 * 字牌组构型索引,字牌组编码作为数组下标,值是ziPaiZuGouXingsArray的下标
	 */
	private static int[] ziPaiZuGouXingsIdxArray;

	/**
	 * 只有字牌组组成手牌的构型,按牌少到牌多排序
	 */
	private static int[][] ziPaiZuGouXingsArray;

	/**
	 * 一个序数牌组和字牌组组成手牌的构型
	 */
	private static int[][] yiXuShuPaiZuAndZiPaiZuGouXingsArray;

	/**
	 * 二个序数牌组和字牌组组成手牌的构型
	 */
	private static int[][] erXuShuPaiZuAndZiPaiZuGouXingsArray;

	/**
	 * 三个序数牌组和字牌组组成手牌的构型
	 */
	private static int[][] sanXuShuPaiZuAndZiPaiZuGouXingsArray;

	/**
	 * 四个序数牌组和字牌组组成手牌的构型
	 */
	private static int[][] siXuShuPaiZuAndZiPaiZuGouXingsArray;

	/**
	 * 五个序数牌组和字牌组组成手牌的构型
	 */
	private static int[][] wuXuShuPaiZuAndZiPaiZuGouXingsArray;

	public static void main(String[] args) {
		// 花色: 什么牌，一万，三条，发财等
		// 牌型: 完全描述了指定的牌的集合，什么花色的牌有几张
		// 构型: 面向和牌关心的几个点，单牌个数，对子个数，刻子个数，杠子个数，顺子个数
		// 构型编码: 构形用一个int来编码:低到高--5位单牌个数，4位对子个数，3位刻子个数，3位杠子个数，3位顺子个数
		// 同构: 具有不同牌型的两个牌的集合，他们具有相同的构型集合（可能可以看成多种构型，所以是构型集合），那么他们同构
		// 序数牌组: 完全连的一组序数牌,至少3连,每种牌张数不限（抽象概念，不关心花色，不关心起点）
		// 序数牌组编码: 从小序号到大序号,从低位到高位,每n位的值代表该序号牌的数量
		// 字牌组: 只关心有几个单牌，几个对子，几个刻子，几个杠子，几个5同牌，几个6同牌，几个7同牌，几个8同牌，几个9同牌，几个10同牌
		// 的一组字牌（抽象概念，不关心花色）
		// 字牌组编码:低到高--3位单牌个数，3位对子个数，3位刻子个数，3位杠子个数，2位5同牌个数，2位6同牌个数，2位7同牌个数，1位8同牌个数，1位9同牌个数，1位10同牌个数

		int maxShouPai = 17;
		int maxGuiPai = 3;
		int maxShouPaiXuShuPaiZu = maxShouPai / 3;// 手牌最多可能几个序数牌组

		// 序数牌组是抽象的，其目的是为了计算抽象的构型，显然，不同花色但是相同序数牌组的两组牌一定同构

		// 我们希望直接用序数牌组编码作为数组下标来查询其构型list的索引（这是关键效率优化）。虽然序数牌组编码空间很大，但是很稀疏。
		// 所以其构型list的索引空间其实很小。那么这就有利于实现直接数组下标查询多个序数牌组组合好后的结果。
		// 刚才提到序数牌组编码空间会很大，然而不幸的是在双鬼牌的情况下一种花色的牌有可能多达10张，这样编码空间就会过大。
		// 应对这种情况，我们在游戏过程中的手牌的结构中先按照约定的较小的比如3位一种牌编码，一旦摸牌太多出现3位不够存储的情况就整体改为4位一种牌编码。
		// 有两种结构的构型list索引，一种是数组，直接用序数牌组编码作为数组下标来查询，用于序数牌组编码空间大小还能接受的情况
		// 另一种是以hashmap作为存储结构，用序数牌组编码作为key来查询，用于序数牌组编码空间太大的情况

		// 对于给定的所有牌张数和最多鬼牌数，计算其所有可能的序数牌组，目的是前期过滤，缩小计算规模，计算结果是一个已经排好序的list
		List<XuShuPaiZu> xuShuPaiZuList = calculateXuShuPaiZu(maxShouPai, maxGuiPai);

		// 为序数牌组计算构型，按牌少到牌多排序
		int[][] xuShuPaiZuGouXingsArray = calculateGouXingForXuShuPaiZu(xuShuPaiZuList);

		// 考虑手牌由多个序数牌组组成。这个时候可以在游戏中每次要判断和的时候都去把各个序数牌组的构型查出来，再做组合计算。这样效率太浪费。
		// 我们希望在这里把多个序数牌组组合好后的所有构型计算好，到时候提供直接查询结果。

		// 之前的xuShuPaiZuList是按照牌少到牌多排好序的，我们将利用这一点来节省多个序数牌组组合好后的结果的地址空间。
		// 首先我们分组合的序数牌组个数讨论，也就是分1个序数牌组，2个序数牌组，3个序数牌组，。。。，maxShouPaiXuShuPaiZu个序数牌组讨论。
		// 考虑2个序数牌组，取第一个序数牌组的时候考虑到还要取第二个序数牌组，所以牌不能取满，至少要给第二个组留3张牌，这样一来，必然在xuShuPaiZuList中取到前面的某一段。
		// 所以2个序数牌组的查询空间必然不会是xuShuPaiZuList.size()*xuShuPaiZuList.size()，而是要小一些。

		// 计算序数牌组组合构型，key是代表几个序数牌组
		Map<Integer, XuShuPaiZuZuHeGouXingGroup> xuShuPaiZuZuHeGouXingGroupMap = calculateXuShuPaiZuZuHeGouXing(
				xuShuPaiZuList, xuShuPaiZuGouXingsArray, maxShouPai, maxGuiPai);

		// 如果手牌中没有任何的序数牌，那就是一些字牌（花牌一般和和牌没什么关系）。这些字牌形成一个字牌组。

		// 对于给定的所有牌张数和最多鬼牌数，计算其所有可能的字牌组，目的是前期过滤，缩小计算规模，计算结果是一个已经排好序的list
		List<ZiPaiZu> ziPaiZuList = calculateZiPaiZu(maxShouPai, maxGuiPai);

		// 为字牌组计算构型，按牌少到牌多排序
		calculateGouXingForZiPaiZu(ziPaiZuList);

		// 最常见的情况是手中既有序数牌组又有字牌组

		// 为序数牌组和字牌组组合的计算构型，按牌少到牌多排序
		calculateGouXingForXuShuPaiZuAndZiPaiZu(xuShuPaiZuList, ziPaiZuList);

	}

	private static void calculateGouXingForXuShuPaiZuAndZiPaiZu(List<XuShuPaiZu> xuShuPaiZuList,
			List<ZiPaiZu> ziPaiZuList) {
		// TODO Auto-generated method stub

	}

	private static void calculateGouXingForZiPaiZu(List<ZiPaiZu> ziPaiZuList) {
		// TODO Auto-generated method stub
		ziPaiZuGouXingsArray = null;
	}

	private static List<ZiPaiZu> calculateZiPaiZu(int maxShouPai, int maxGuiPai) {
		// TODO Auto-generated method stub
		return null;
	}

	private static Map<Integer, XuShuPaiZuZuHeGouXingGroup> calculateXuShuPaiZuZuHeGouXing(
			List<XuShuPaiZu> xuShuPaiZuList, int[][] xuShuPaiZuGouXingsArray, int maxShouPai, int maxGuiPai) {
		// TODO Auto-generated method stub
		return null;
	}

	private static int[][] calculateGouXingForXuShuPaiZu(List<XuShuPaiZu> xuShuPaiZuList) {
		// TODO Auto-generated method stub
		return null;

	}

	private static List<XuShuPaiZu> calculateXuShuPaiZu(int maxShouPai, int maxGuiPai) {
		List<XuShuPaiZu> list = new ArrayList<>();
		for (int shouPai = 3; shouPai <= maxShouPai; shouPai++) {
			calculateXuShuPaiZuForShouPai(shouPai, maxGuiPai, list);
		}
	}

	private static void calculateXuShuPaiZuForShouPai(int shouPai, int maxGuiPai, List<XuShuPaiZu> list) {
		for (int lian = 3; lian <= shouPai; lian++) {
			calculateXuShuPaiZuForShouPaiAndLian(shouPai, maxGuiPai, lian, list);
		}
	}

	private static void calculateXuShuPaiZuForShouPaiAndLian(int shouPai, int maxGuiPai, int lian,
			List<XuShuPaiZu> list) {
		// TODO Auto-generated method stub

	}

}
