package com.dml.majiang;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Design {

	/**
	 * 序数牌组构型索引,小空间序数牌组编码作为数组下标,值为 排好序的 List<XuShuPaiZu> 的下标
	 */
	private static int[] xuShuPaiZuGouXingsIdxArray = new int[134217728];// 9连顺子*3位牌数=27,27个1=134217727

	/**
	 * 序数牌组构型索引,key为大空间序数牌组编码,值为 排好序的 List<XuShuPaiZu> 的下标
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
	private static int erXuShuPaiZuGouXingsArrayIdx1Mod;

	/**
	 * 三个序数牌组组成手牌的构型
	 */
	private static int[][] sanXuShuPaiZuGouXingsArray;
	private static int sanXuShuPaiZuGouXingsArrayIdx1Mod;
	private static int sanXuShuPaiZuGouXingsArrayIdx2Mod;

	/**
	 * 四个序数牌组组成手牌的构型
	 */
	private static int[][] siXuShuPaiZuGouXingsArray;
	private static int siXuShuPaiZuGouXingsArrayIdx1Mod;
	private static int siXuShuPaiZuGouXingsArrayIdx2Mod;
	private static int siXuShuPaiZuGouXingsArrayIdx3Mod;

	/**
	 * 五个序数牌组组成手牌的构型
	 */
	private static int[][] wuXuShuPaiZuGouXingsArray;
	private static int wuXuShuPaiZuGouXingsArrayIdx1Mod;
	private static int wuXuShuPaiZuGouXingsArrayIdx2Mod;
	private static int wuXuShuPaiZuGouXingsArrayIdx3Mod;
	private static int wuXuShuPaiZuGouXingsArrayIdx4Mod;

	/**
	 * 字牌组构型索引,字牌组编码作为数组下标,值是ziPaiZuGouXingsArray的下标
	 */
	private static int[] ziPaiZuGouXingsIdxArray = new int[2097152];

	/**
	 * 只有字牌组组成手牌的构型,按牌少到牌多排序
	 */
	private static int[][] ziPaiZuGouXingsArray;

	/**
	 * 一个序数牌组和字牌组组成手牌的构型
	 */
	private static int[][] yiXuShuPaiZuAndZiPaiZuGouXingsArray;
	private static int yiXuShuPaiZuAndZiPaiZuGouXingsArrayIdx1Mod;

	/**
	 * 二个序数牌组和字牌组组成手牌的构型
	 */
	private static int[][] erXuShuPaiZuAndZiPaiZuGouXingsArray;
	private static int erXuShuPaiZuAndZiPaiZuGouXingsArrayIdx1Mod;
	private static int erXuShuPaiZuAndZiPaiZuGouXingsArrayIdx2Mod;

	/**
	 * 三个序数牌组和字牌组组成手牌的构型
	 */
	private static int[][] sanXuShuPaiZuAndZiPaiZuGouXingsArray;
	private static int sanXuShuPaiZuAndZiPaiZuGouXingsArrayIdx1Mod;
	private static int sanXuShuPaiZuAndZiPaiZuGouXingsArrayIdx2Mod;
	private static int sanXuShuPaiZuAndZiPaiZuGouXingsArrayIdx3Mod;

	/**
	 * 四个序数牌组和字牌组组成手牌的构型
	 */
	private static int[][] siXuShuPaiZuAndZiPaiZuGouXingsArray;
	private static int siXuShuPaiZuAndZiPaiZuGouXingsArrayIdx1Mod;
	private static int siXuShuPaiZuAndZiPaiZuGouXingsArrayIdx2Mod;
	private static int siXuShuPaiZuAndZiPaiZuGouXingsArrayIdx3Mod;
	private static int siXuShuPaiZuAndZiPaiZuGouXingsArrayIdx4Mod;

	/**
	 * 五个序数牌组和字牌组组成手牌的构型
	 */
	private static int[][] wuXuShuPaiZuAndZiPaiZuGouXingsArray;
	private static int wuXuShuPaiZuAndZiPaiZuGouXingsArrayIdx1Mod;
	private static int wuXuShuPaiZuAndZiPaiZuGouXingsArrayIdx2Mod;
	private static int wuXuShuPaiZuAndZiPaiZuGouXingsArrayIdx3Mod;
	private static int wuXuShuPaiZuAndZiPaiZuGouXingsArrayIdx4Mod;
	private static int wuXuShuPaiZuAndZiPaiZuGouXingsArrayIdx5Mod;

	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();

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

		// 对于给定的所有牌张数和最多鬼牌数，计算其所有可能的序数牌组，目的是前期过滤，缩小计算规模，计算结果是一个已经排好序的list，按牌少到牌多排序
		List<XuShuPaiZu> xuShuPaiZuList = calculateXuShuPaiZu(maxShouPai, maxGuiPai);

		// 为序数牌组计算构型
		calculateGouXingForXuShuPaiZu(xuShuPaiZuList);

		// 考虑手牌由多个序数牌组组成。这个时候可以在游戏中每次要判断和的时候都去把各个序数牌组的构型查出来，再做组合计算。这样效率太浪费。
		// 我们希望在这里把多个序数牌组组合好后的所有构型计算好，到时候提供直接查询结果。

		// 之前的xuShuPaiZuList是按照牌少到牌多排好序的，我们将利用这一点来节省多个序数牌组组合好后的结果的地址空间。
		// 首先我们分组合的序数牌组个数讨论，也就是分1个序数牌组，2个序数牌组，3个序数牌组，。。。，maxShouPaiXuShuPaiZu个序数牌组讨论。
		// 考虑2个序数牌组，取第一个序数牌组的时候考虑到还要取第二个序数牌组，所以牌不能取满，至少要给第二个组留3张牌，这样一来，必然在xuShuPaiZuList中取到前面的某一段。
		// 所以2个序数牌组的查询空间必然不会是xuShuPaiZuList.size()*xuShuPaiZuList.size()，而是要小一些。

		// 计算序数牌组组合构型
		calculateXuShuPaiZuZuHeGouXing(xuShuPaiZuList, maxShouPai, maxGuiPai, maxShouPaiXuShuPaiZu);

		// 如果手牌中没有任何的序数牌，那就是一些字牌（花牌一般和和牌没什么关系）。这些字牌形成一个字牌组。

		// 对于给定的所有牌张数和最多鬼牌数，计算其所有可能的字牌组，目的是前期过滤，缩小计算规模，计算结果是一个已经排好序的list，按牌少到牌多排序
		List<ZiPaiZu> ziPaiZuList = calculateZiPaiZu(maxShouPai, maxGuiPai);

		// 为字牌组计算构型
		calculateGouXingForZiPaiZu(ziPaiZuList);

		// 最常见的情况是手中既有序数牌组又有字牌组

		// 为序数牌组和字牌组组合的计算构型
		calculateGouXingForXuShuPaiZuAndZiPaiZu(xuShuPaiZuList, ziPaiZuList, maxShouPai, maxGuiPai,
				maxShouPaiXuShuPaiZu);

		long finishTime = System.currentTimeMillis();

		System.out.println("耗时(毫秒):" + (finishTime - startTime));

	}

	private static void calculateGouXingForXuShuPaiZuAndZiPaiZu(List<XuShuPaiZu> xuShuPaiZuList,
			List<ZiPaiZu> ziPaiZuList, int maxShouPai, int maxGuiPai, int maxShouPaiXuShuPaiZu) {
		if (maxShouPaiXuShuPaiZu >= 1) {
			// 先计算数组规模
			int maxi = 0;
			int maxj = 0;
			for (int i = 0; i < xuShuPaiZuList.size(); i++) {
				XuShuPaiZu zu1 = xuShuPaiZuList.get(i);
				int totalPai1 = zu1.getTotalPai();
				int atleastGuiPai1 = zu1.getAtleastGuiPai();
				for (int j = 0; j < ziPaiZuList.size(); j++) {
					ZiPaiZu ziPaiZu = ziPaiZuList.get(j);
					int totalPai = totalPai1 + ziPaiZu.getTotalPai();
					int atleastGuiPai = atleastGuiPai1 + ziPaiZu.getAtleastGuiPai();
					if (totalPai <= maxShouPai) {
						if (atleastGuiPai <= maxGuiPai) {
							maxi = (i > maxi) ? i : maxi;
							maxj = (j > maxj) ? j : maxj;
						}
					} else {
						break;
					}
				}
			}
			yiXuShuPaiZuAndZiPaiZuGouXingsArrayIdx1Mod = (maxj + 1);

			// 再计算组合
			yiXuShuPaiZuAndZiPaiZuGouXingsArray = new int[(maxi + 1) * yiXuShuPaiZuAndZiPaiZuGouXingsArrayIdx1Mod][];
			for (int i = 0; i < xuShuPaiZuList.size(); i++) {
				XuShuPaiZu zu1 = xuShuPaiZuList.get(i);
				int totalPai1 = zu1.getTotalPai();
				int atleastGuiPai1 = zu1.getAtleastGuiPai();
				int[] gouXingArray1 = zu1.getGouXingArray();
				for (int j = 0; j < ziPaiZuList.size(); j++) {
					ZiPaiZu ziPaiZu = ziPaiZuList.get(j);
					int totalPai = totalPai1 + ziPaiZu.getTotalPai();
					int atleastGuiPai = atleastGuiPai1 + ziPaiZu.getAtleastGuiPai();
					if (totalPai <= maxShouPai) {
						if (atleastGuiPai <= maxGuiPai) {
							int[] joindGouXingArray = joinGouXingArray(gouXingArray1, ziPaiZu.getGouXingArray());
							yiXuShuPaiZuAndZiPaiZuGouXingsArray[i * yiXuShuPaiZuAndZiPaiZuGouXingsArrayIdx1Mod
									+ j] = joindGouXingArray;
						}
					} else {
						break;
					}
				}
			}
		}

		if (maxShouPaiXuShuPaiZu >= 2) {
			// 先计算数组规模
			int maxi = 0;
			int maxj = 0;
			int maxk = 0;
			for (int i = 0; i < xuShuPaiZuList.size(); i++) {
				XuShuPaiZu zu1 = xuShuPaiZuList.get(i);
				int totalPai1 = zu1.getTotalPai();
				int atleastGuiPai1 = zu1.getAtleastGuiPai();
				for (int j = i; j < xuShuPaiZuList.size(); j++) {
					XuShuPaiZu zu2 = xuShuPaiZuList.get(j);
					int totalPai2 = totalPai1 + zu2.getTotalPai();
					int atleastGuiPai2 = atleastGuiPai1 + zu2.getAtleastGuiPai();
					if (totalPai2 <= maxShouPai) {
						if (atleastGuiPai2 <= maxGuiPai) {
							for (int k = 0; k < ziPaiZuList.size(); k++) {
								ZiPaiZu ziPaiZu = ziPaiZuList.get(k);
								int totalPai = totalPai2 + ziPaiZu.getTotalPai();
								int atleastGuiPai = atleastGuiPai2 + ziPaiZu.getAtleastGuiPai();
								if (totalPai <= maxShouPai) {
									if (atleastGuiPai <= maxGuiPai) {
										maxi = (i > maxi) ? i : maxi;
										maxj = (j > maxj) ? j : maxj;
										maxk = (k > maxk) ? k : maxk;
									}
								} else {
									break;
								}
							}
						}
					} else {
						break;
					}
				}
			}
			erXuShuPaiZuAndZiPaiZuGouXingsArrayIdx2Mod = (maxk + 1);
			erXuShuPaiZuAndZiPaiZuGouXingsArrayIdx1Mod = (maxj + 1) * erXuShuPaiZuAndZiPaiZuGouXingsArrayIdx2Mod;

			// 再计算组合
			erXuShuPaiZuAndZiPaiZuGouXingsArray = new int[(maxi + 1) * erXuShuPaiZuAndZiPaiZuGouXingsArrayIdx1Mod][];
			for (int i = 0; i < xuShuPaiZuList.size(); i++) {
				XuShuPaiZu zu1 = xuShuPaiZuList.get(i);
				int totalPai1 = zu1.getTotalPai();
				int atleastGuiPai1 = zu1.getAtleastGuiPai();
				for (int j = i; j < xuShuPaiZuList.size(); j++) {
					XuShuPaiZu zu2 = xuShuPaiZuList.get(j);
					int totalPai2 = totalPai1 + zu2.getTotalPai();
					int atleastGuiPai2 = atleastGuiPai1 + zu2.getAtleastGuiPai();
					if (totalPai2 <= maxShouPai) {
						if (atleastGuiPai2 <= maxGuiPai) {
							int[] joindGouXingArray = erXuShuPaiZuGouXingsArray[i * erXuShuPaiZuGouXingsArrayIdx1Mod
									+ j];
							for (int k = 0; k < ziPaiZuList.size(); k++) {
								ZiPaiZu ziPaiZu = ziPaiZuList.get(k);
								int totalPai = totalPai2 + ziPaiZu.getTotalPai();
								int atleastGuiPai = atleastGuiPai2 + ziPaiZu.getAtleastGuiPai();
								if (totalPai <= maxShouPai) {
									if (atleastGuiPai <= maxGuiPai) {
										erXuShuPaiZuAndZiPaiZuGouXingsArray[i
												* erXuShuPaiZuAndZiPaiZuGouXingsArrayIdx1Mod
												+ j * erXuShuPaiZuAndZiPaiZuGouXingsArrayIdx2Mod
												+ k] = joinGouXingArray(joindGouXingArray, ziPaiZu.getGouXingArray());
									}
								} else {
									break;
								}
							}
						}
					} else {
						break;
					}
				}

			}

		}

		if (maxShouPaiXuShuPaiZu >= 3) {
			// 先计算数组规模
			int maxi = 0;
			int maxj = 0;
			int maxk = 0;
			int maxl = 0;
			for (int i = 0; i < xuShuPaiZuList.size(); i++) {
				XuShuPaiZu zu1 = xuShuPaiZuList.get(i);
				int totalPai1 = zu1.getTotalPai();
				int atleastGuiPai1 = zu1.getAtleastGuiPai();
				for (int j = i; j < xuShuPaiZuList.size(); j++) {
					XuShuPaiZu zu2 = xuShuPaiZuList.get(j);
					int totalPai2 = totalPai1 + zu2.getTotalPai();
					int atleastGuiPai2 = atleastGuiPai1 + zu2.getAtleastGuiPai();
					if (totalPai2 <= maxShouPai) {
						if (atleastGuiPai2 <= maxGuiPai) {
							for (int k = j; k < xuShuPaiZuList.size(); k++) {
								XuShuPaiZu zu3 = xuShuPaiZuList.get(k);
								int totalPai3 = totalPai2 + zu3.getTotalPai();
								int atleastGuiPai3 = atleastGuiPai2 + zu3.getAtleastGuiPai();
								if (totalPai3 <= maxShouPai) {
									if (atleastGuiPai3 <= maxGuiPai) {
										for (int l = 0; l < ziPaiZuList.size(); l++) {
											ZiPaiZu ziPaiZu = ziPaiZuList.get(l);
											int totalPai = totalPai3 + ziPaiZu.getTotalPai();
											int atleastGuiPai = atleastGuiPai3 + ziPaiZu.getAtleastGuiPai();
											if (totalPai <= maxShouPai) {
												if (atleastGuiPai <= maxGuiPai) {
													maxi = (i > maxi) ? i : maxi;
													maxj = (j > maxj) ? j : maxj;
													maxk = (k > maxk) ? k : maxk;
													maxl = (l > maxl) ? l : maxl;
												}
											} else {
												break;
											}
										}
									}
								} else {
									break;
								}
							}

						}
					} else {
						break;
					}
				}
			}
			sanXuShuPaiZuAndZiPaiZuGouXingsArrayIdx3Mod = (maxl + 1);
			sanXuShuPaiZuAndZiPaiZuGouXingsArrayIdx2Mod = (maxk + 1) * sanXuShuPaiZuAndZiPaiZuGouXingsArrayIdx3Mod;
			sanXuShuPaiZuAndZiPaiZuGouXingsArrayIdx1Mod = (maxj + 1) * sanXuShuPaiZuAndZiPaiZuGouXingsArrayIdx2Mod;

			// 再计算组合
			sanXuShuPaiZuAndZiPaiZuGouXingsArray = new int[(maxi + 1) * sanXuShuPaiZuAndZiPaiZuGouXingsArrayIdx1Mod][];
			for (int i = 0; i < xuShuPaiZuList.size(); i++) {
				XuShuPaiZu zu1 = xuShuPaiZuList.get(i);
				int totalPai1 = zu1.getTotalPai();
				int atleastGuiPai1 = zu1.getAtleastGuiPai();
				for (int j = i; j < xuShuPaiZuList.size(); j++) {
					XuShuPaiZu zu2 = xuShuPaiZuList.get(j);
					int totalPai2 = totalPai1 + zu2.getTotalPai();
					int atleastGuiPai2 = atleastGuiPai1 + zu2.getAtleastGuiPai();
					if (totalPai2 <= maxShouPai) {
						if (atleastGuiPai2 <= maxGuiPai) {

							for (int k = j; k < xuShuPaiZuList.size(); k++) {
								XuShuPaiZu zu3 = xuShuPaiZuList.get(k);
								int totalPai3 = totalPai2 + zu3.getTotalPai();
								int atleastGuiPai3 = atleastGuiPai2 + zu3.getAtleastGuiPai();
								if (totalPai3 <= maxShouPai) {
									if (atleastGuiPai3 <= maxGuiPai) {
										int[] joindGouXingArray = sanXuShuPaiZuGouXingsArray[i
												* sanXuShuPaiZuGouXingsArrayIdx1Mod
												+ j * sanXuShuPaiZuGouXingsArrayIdx2Mod + k];
										for (int l = 0; l < ziPaiZuList.size(); l++) {
											ZiPaiZu ziPaiZu = ziPaiZuList.get(l);
											int totalPai = totalPai3 + ziPaiZu.getTotalPai();
											int atleastGuiPai = atleastGuiPai3 + ziPaiZu.getAtleastGuiPai();
											if (totalPai <= maxShouPai) {
												if (atleastGuiPai <= maxGuiPai) {
													sanXuShuPaiZuAndZiPaiZuGouXingsArray[i
															* sanXuShuPaiZuAndZiPaiZuGouXingsArrayIdx1Mod
															+ j * sanXuShuPaiZuAndZiPaiZuGouXingsArrayIdx2Mod
															+ k * sanXuShuPaiZuAndZiPaiZuGouXingsArrayIdx3Mod
															+ l] = joinGouXingArray(joindGouXingArray,
																	ziPaiZu.getGouXingArray());
												}
											} else {
												break;
											}
										}
									}
								} else {
									break;
								}
							}
						}
					} else {
						break;
					}
				}

			}

		}

		if (maxShouPaiXuShuPaiZu >= 4) {
			// 先计算数组规模
			int maxi = 0;
			int maxj = 0;
			int maxk = 0;
			int maxl = 0;
			int maxm = 0;
			for (int i = 0; i < xuShuPaiZuList.size(); i++) {
				XuShuPaiZu zu1 = xuShuPaiZuList.get(i);
				int totalPai1 = zu1.getTotalPai();
				int atleastGuiPai1 = zu1.getAtleastGuiPai();
				for (int j = i; j < xuShuPaiZuList.size(); j++) {
					XuShuPaiZu zu2 = xuShuPaiZuList.get(j);
					int totalPai2 = totalPai1 + zu2.getTotalPai();
					int atleastGuiPai2 = atleastGuiPai1 + zu2.getAtleastGuiPai();
					if (totalPai2 <= maxShouPai) {
						if (atleastGuiPai2 <= maxGuiPai) {
							for (int k = j; k < xuShuPaiZuList.size(); k++) {
								XuShuPaiZu zu3 = xuShuPaiZuList.get(k);
								int totalPai3 = totalPai2 + zu3.getTotalPai();
								int atleastGuiPai3 = atleastGuiPai2 + zu3.getAtleastGuiPai();
								if (totalPai3 <= maxShouPai) {
									if (atleastGuiPai3 <= maxGuiPai) {
										for (int l = k; l < xuShuPaiZuList.size(); l++) {
											XuShuPaiZu zu4 = xuShuPaiZuList.get(l);
											int totalPai4 = totalPai3 + zu4.getTotalPai();
											int atleastGuiPai4 = atleastGuiPai3 + zu4.getAtleastGuiPai();
											if (totalPai4 <= maxShouPai) {
												if (atleastGuiPai4 <= maxGuiPai) {
													for (int m = 0; m < ziPaiZuList.size(); m++) {
														ZiPaiZu ziPaiZu = ziPaiZuList.get(m);
														int totalPai = totalPai4 + ziPaiZu.getTotalPai();
														int atleastGuiPai = atleastGuiPai4 + ziPaiZu.getAtleastGuiPai();
														if (totalPai <= maxShouPai) {
															if (atleastGuiPai <= maxGuiPai) {
																maxi = (i > maxi) ? i : maxi;
																maxj = (j > maxj) ? j : maxj;
																maxk = (k > maxk) ? k : maxk;
																maxl = (l > maxl) ? l : maxl;
																maxm = (m > maxm) ? m : maxm;
															}
														} else {
															break;
														}
													}
												}
											} else {
												break;
											}
										}

									}
								} else {
									break;
								}
							}

						}
					} else {
						break;
					}
				}
			}
			siXuShuPaiZuAndZiPaiZuGouXingsArrayIdx4Mod = (maxm + 1);
			siXuShuPaiZuAndZiPaiZuGouXingsArrayIdx3Mod = (maxl + 1) * siXuShuPaiZuAndZiPaiZuGouXingsArrayIdx4Mod;
			siXuShuPaiZuAndZiPaiZuGouXingsArrayIdx2Mod = (maxk + 1) * siXuShuPaiZuAndZiPaiZuGouXingsArrayIdx3Mod;
			siXuShuPaiZuAndZiPaiZuGouXingsArrayIdx1Mod = (maxj + 1) * siXuShuPaiZuAndZiPaiZuGouXingsArrayIdx2Mod;

			// 再计算组合
			siXuShuPaiZuAndZiPaiZuGouXingsArray = new int[(maxi + 1) * siXuShuPaiZuAndZiPaiZuGouXingsArrayIdx1Mod][];
			for (int i = 0; i < xuShuPaiZuList.size(); i++) {
				XuShuPaiZu zu1 = xuShuPaiZuList.get(i);
				int totalPai1 = zu1.getTotalPai();
				int atleastGuiPai1 = zu1.getAtleastGuiPai();
				for (int j = i; j < xuShuPaiZuList.size(); j++) {
					XuShuPaiZu zu2 = xuShuPaiZuList.get(j);
					int totalPai2 = totalPai1 + zu2.getTotalPai();
					int atleastGuiPai2 = atleastGuiPai1 + zu2.getAtleastGuiPai();
					if (totalPai2 <= maxShouPai) {
						if (atleastGuiPai2 <= maxGuiPai) {
							for (int k = j; k < xuShuPaiZuList.size(); k++) {
								XuShuPaiZu zu3 = xuShuPaiZuList.get(k);
								int totalPai3 = totalPai2 + zu3.getTotalPai();
								int atleastGuiPai3 = atleastGuiPai2 + zu3.getAtleastGuiPai();
								if (totalPai3 <= maxShouPai) {
									if (atleastGuiPai3 <= maxGuiPai) {
										for (int l = k; l < xuShuPaiZuList.size(); l++) {
											XuShuPaiZu zu4 = xuShuPaiZuList.get(l);
											int totalPai4 = totalPai3 + zu4.getTotalPai();
											int atleastGuiPai4 = atleastGuiPai3 + zu4.getAtleastGuiPai();
											if (totalPai4 <= maxShouPai) {
												if (atleastGuiPai4 <= maxGuiPai) {
													int[] joindGouXingArray = siXuShuPaiZuGouXingsArray[i
															* siXuShuPaiZuGouXingsArrayIdx1Mod
															+ j * siXuShuPaiZuGouXingsArrayIdx2Mod
															+ k * siXuShuPaiZuGouXingsArrayIdx3Mod + l];
													for (int m = 0; m < ziPaiZuList.size(); m++) {
														ZiPaiZu ziPaiZu = ziPaiZuList.get(m);
														int totalPai = totalPai4 + ziPaiZu.getTotalPai();
														int atleastGuiPai = atleastGuiPai4 + ziPaiZu.getAtleastGuiPai();
														if (totalPai <= maxShouPai) {
															if (atleastGuiPai <= maxGuiPai) {
																siXuShuPaiZuAndZiPaiZuGouXingsArray[i
																		* siXuShuPaiZuAndZiPaiZuGouXingsArrayIdx1Mod
																		+ j * siXuShuPaiZuAndZiPaiZuGouXingsArrayIdx2Mod
																		+ k * siXuShuPaiZuAndZiPaiZuGouXingsArrayIdx3Mod
																		+ l * siXuShuPaiZuAndZiPaiZuGouXingsArrayIdx4Mod
																		+ m] = joinGouXingArray(joindGouXingArray,
																				ziPaiZu.getGouXingArray());
															}
														} else {
															break;
														}
													}
												}
											} else {
												break;
											}

										}
									}
								} else {
									break;
								}
							}
						}
					} else {
						break;
					}
				}

			}

		}

		if (maxShouPaiXuShuPaiZu >= 5) {
			// 先计算数组规模
			int maxi = 0;
			int maxj = 0;
			int maxk = 0;
			int maxl = 0;
			int maxm = 0;
			int maxn = 0;
			for (int i = 0; i < xuShuPaiZuList.size(); i++) {
				XuShuPaiZu zu1 = xuShuPaiZuList.get(i);
				int totalPai1 = zu1.getTotalPai();
				int atleastGuiPai1 = zu1.getAtleastGuiPai();
				for (int j = i; j < xuShuPaiZuList.size(); j++) {
					XuShuPaiZu zu2 = xuShuPaiZuList.get(j);
					int totalPai2 = totalPai1 + zu2.getTotalPai();
					int atleastGuiPai2 = atleastGuiPai1 + zu2.getAtleastGuiPai();
					if (totalPai2 <= maxShouPai) {
						if (atleastGuiPai2 <= maxGuiPai) {
							for (int k = j; k < xuShuPaiZuList.size(); k++) {
								XuShuPaiZu zu3 = xuShuPaiZuList.get(k);
								int totalPai3 = totalPai2 + zu3.getTotalPai();
								int atleastGuiPai3 = atleastGuiPai2 + zu3.getAtleastGuiPai();
								if (totalPai3 <= maxShouPai) {
									if (atleastGuiPai3 <= maxGuiPai) {
										for (int l = k; l < xuShuPaiZuList.size(); l++) {
											XuShuPaiZu zu4 = xuShuPaiZuList.get(l);
											int totalPai4 = totalPai3 + zu4.getTotalPai();
											int atleastGuiPai4 = atleastGuiPai3 + zu4.getAtleastGuiPai();
											if (totalPai4 <= maxShouPai) {
												if (atleastGuiPai4 <= maxGuiPai) {

													for (int m = l; m < xuShuPaiZuList.size(); m++) {
														XuShuPaiZu zu5 = xuShuPaiZuList.get(m);
														int totalPai5 = totalPai4 + zu5.getTotalPai();
														int atleastGuiPai5 = atleastGuiPai4 + zu5.getAtleastGuiPai();
														if (totalPai5 <= maxShouPai) {
															if (atleastGuiPai5 <= maxGuiPai) {
																for (int n = 0; n < ziPaiZuList.size(); n++) {
																	ZiPaiZu ziPaiZu = ziPaiZuList.get(n);
																	int totalPai = totalPai5 + ziPaiZu.getTotalPai();
																	int atleastGuiPai = atleastGuiPai5
																			+ ziPaiZu.getAtleastGuiPai();
																	if (totalPai <= maxShouPai) {
																		if (atleastGuiPai <= maxGuiPai) {
																			maxi = (i > maxi) ? i : maxi;
																			maxj = (j > maxj) ? j : maxj;
																			maxk = (k > maxk) ? k : maxk;
																			maxl = (l > maxl) ? l : maxl;
																			maxm = (m > maxm) ? m : maxm;
																			maxn = (n > maxn) ? n : maxn;
																		}
																	} else {
																		break;
																	}
																}
															}
														} else {
															break;
														}
													}
												}
											} else {
												break;
											}
										}

									}
								} else {
									break;
								}
							}

						}
					} else {
						break;
					}
				}
			}
			wuXuShuPaiZuAndZiPaiZuGouXingsArrayIdx5Mod = (maxn + 1);
			wuXuShuPaiZuAndZiPaiZuGouXingsArrayIdx4Mod = (maxm + 1) * wuXuShuPaiZuAndZiPaiZuGouXingsArrayIdx5Mod;
			wuXuShuPaiZuAndZiPaiZuGouXingsArrayIdx3Mod = (maxl + 1) * wuXuShuPaiZuAndZiPaiZuGouXingsArrayIdx4Mod;
			wuXuShuPaiZuAndZiPaiZuGouXingsArrayIdx2Mod = (maxk + 1) * wuXuShuPaiZuAndZiPaiZuGouXingsArrayIdx3Mod;
			wuXuShuPaiZuAndZiPaiZuGouXingsArrayIdx1Mod = (maxj + 1) * wuXuShuPaiZuAndZiPaiZuGouXingsArrayIdx2Mod;

			// 再计算组合
			wuXuShuPaiZuAndZiPaiZuGouXingsArray = new int[(maxi + 1) * wuXuShuPaiZuAndZiPaiZuGouXingsArrayIdx1Mod][];
			for (int i = 0; i < xuShuPaiZuList.size(); i++) {
				XuShuPaiZu zu1 = xuShuPaiZuList.get(i);
				int totalPai1 = zu1.getTotalPai();
				int atleastGuiPai1 = zu1.getAtleastGuiPai();
				for (int j = i; j < xuShuPaiZuList.size(); j++) {
					XuShuPaiZu zu2 = xuShuPaiZuList.get(j);
					int totalPai2 = totalPai1 + zu2.getTotalPai();
					int atleastGuiPai2 = atleastGuiPai1 + zu2.getAtleastGuiPai();
					if (totalPai2 <= maxShouPai) {
						if (atleastGuiPai2 <= maxGuiPai) {
							for (int k = j; k < xuShuPaiZuList.size(); k++) {
								XuShuPaiZu zu3 = xuShuPaiZuList.get(k);
								int totalPai3 = totalPai2 + zu3.getTotalPai();
								int atleastGuiPai3 = atleastGuiPai2 + zu3.getAtleastGuiPai();
								if (totalPai3 <= maxShouPai) {
									if (atleastGuiPai3 <= maxGuiPai) {
										for (int l = k; l < xuShuPaiZuList.size(); l++) {
											XuShuPaiZu zu4 = xuShuPaiZuList.get(l);
											int totalPai4 = totalPai3 + zu4.getTotalPai();
											int atleastGuiPai4 = atleastGuiPai3 + zu4.getAtleastGuiPai();
											if (totalPai4 <= maxShouPai) {
												if (atleastGuiPai4 <= maxGuiPai) {
													for (int m = l; m < xuShuPaiZuList.size(); m++) {
														XuShuPaiZu zu5 = xuShuPaiZuList.get(m);
														int totalPai5 = totalPai4 + zu5.getTotalPai();
														int atleastGuiPai5 = atleastGuiPai4 + zu5.getAtleastGuiPai();
														if (totalPai5 <= maxShouPai) {
															if (atleastGuiPai5 <= maxGuiPai) {
																int[] joindGouXingArray = wuXuShuPaiZuGouXingsArray[i
																		* wuXuShuPaiZuGouXingsArrayIdx1Mod
																		+ j * wuXuShuPaiZuGouXingsArrayIdx2Mod
																		+ k * wuXuShuPaiZuGouXingsArrayIdx3Mod
																		+ l * wuXuShuPaiZuGouXingsArrayIdx4Mod + m];
																for (int n = 0; n < ziPaiZuList.size(); n++) {
																	ZiPaiZu ziPaiZu = ziPaiZuList.get(n);
																	int totalPai = totalPai5 + ziPaiZu.getTotalPai();
																	int atleastGuiPai = atleastGuiPai5
																			+ ziPaiZu.getAtleastGuiPai();
																	if (totalPai <= maxShouPai) {
																		if (atleastGuiPai <= maxGuiPai) {
																			wuXuShuPaiZuAndZiPaiZuGouXingsArray[i
																					* wuXuShuPaiZuAndZiPaiZuGouXingsArrayIdx1Mod
																					+ j * wuXuShuPaiZuAndZiPaiZuGouXingsArrayIdx2Mod
																					+ k * wuXuShuPaiZuAndZiPaiZuGouXingsArrayIdx3Mod
																					+ l * wuXuShuPaiZuAndZiPaiZuGouXingsArrayIdx4Mod
																					+ m * wuXuShuPaiZuAndZiPaiZuGouXingsArrayIdx5Mod
																					+ n] = joinGouXingArray(
																							joindGouXingArray,
																							ziPaiZu.getGouXingArray());
																		}
																	} else {
																		break;
																	}
																}
															}
														} else {
															break;
														}
													}
												}
											} else {
												break;
											}

										}
									}
								} else {
									break;
								}
							}
						}
					} else {
						break;
					}
				}

			}

		}

	}

	private static void calculateGouXingForZiPaiZu(List<ZiPaiZu> ziPaiZuList) {
		ziPaiZuList.forEach((ziPaiZu) -> ziPaiZu.calculateGouXing());
		ziPaiZuGouXingsArray = new int[ziPaiZuList.size()][];
		for (int i = 0; i < ziPaiZuList.size(); i++) {
			ZiPaiZu ziPaiZu = ziPaiZuList.get(i);
			ziPaiZuGouXingsArray[i] = ziPaiZu.getGouXingArray();
			ziPaiZuGouXingsIdxArray[ziPaiZu.getCode()] = i;
		}
	}

	private static List<ZiPaiZu> calculateZiPaiZu(int maxShouPai, int maxGuiPai) {
		List<ZiPaiZu> result = new ArrayList<>();
		// 低到高
		// 3位单牌个数，3位对子个数，3位刻子个数，3位杠子个数，2位5同牌个数，2位6同牌个数，2位7同牌个数，1位8同牌个数，1位9同牌个数，1位10同牌个数
		for (int code = 1; code < 2097152; code++) {
			int danpai = (code & 7);
			int duizi = ((code >>> 3) & 7);
			int kezi = ((code >>> 6) & 7);
			int gangzi = ((code >>> 9) & 7);
			int wuGe = ((code >>> 12) & 3);
			int liuGe = ((code >>> 14) & 3);
			int qiGe = ((code >>> 16) & 3);
			int baGe = ((code >>> 18) & 1);
			int jiuGe = ((code >>> 19) & 1);
			int shiGe = ((code >>> 20) & 1);

			int totalPai = danpai + duizi * 2 + kezi * 3 + gangzi * 4 + wuGe * 5 + liuGe * 6 + qiGe * 7 + baGe * 8
					+ jiuGe * 9 + shiGe * 10;
			int atleastGuiPai = wuGe * 1 + liuGe * 2 + qiGe * 3 + baGe * 4 + jiuGe * 5 + shiGe * 6;

			if (totalPai <= maxShouPai && atleastGuiPai <= maxGuiPai) {
				result.add(new ZiPaiZu(code,
						new int[] { danpai, duizi, kezi, gangzi, wuGe, liuGe, qiGe, baGe, jiuGe, shiGe }, totalPai,
						atleastGuiPai));
			}
		}
		Collections.sort(result);
		return result;
	}

	private static void calculateXuShuPaiZuZuHeGouXing(List<XuShuPaiZu> xuShuPaiZuList, int maxShouPai, int maxGuiPai,
			int maxShouPaiXuShuPaiZu) {
		if (maxShouPaiXuShuPaiZu >= 1) {
			yiXuShuPaiZuGouXingsArray = new int[xuShuPaiZuList.size()][];
			for (int i = 0; i < xuShuPaiZuList.size(); i++) {
				XuShuPaiZu xuShuPaiZu = xuShuPaiZuList.get(i);
				yiXuShuPaiZuGouXingsArray[i] = xuShuPaiZu.getGouXingArray();
				if (!xuShuPaiZu.isBigCodeMode()) {
					xuShuPaiZuGouXingsIdxArray[xuShuPaiZu.getSmallCode()] = i;
				} else {
					xuShuPaiZuGouXingsIdxMap.put(xuShuPaiZu.getBigCode(), i);
				}
			}
		}

		if (maxShouPaiXuShuPaiZu >= 2) {
			// 先计算数组规模
			int maxi = 0;
			int maxj = 0;
			for (int i = 0; i < xuShuPaiZuList.size(); i++) {
				XuShuPaiZu zu1 = xuShuPaiZuList.get(i);
				int totalPai1 = zu1.getTotalPai();
				int atleastGuiPai1 = zu1.getAtleastGuiPai();
				for (int j = i; j < xuShuPaiZuList.size(); j++) {
					XuShuPaiZu zu2 = xuShuPaiZuList.get(j);
					int totalPai = totalPai1 + zu2.getTotalPai();
					if (totalPai <= maxShouPai) {
						int atleastGuiPai = atleastGuiPai1 + zu2.getAtleastGuiPai();
						if (atleastGuiPai <= maxGuiPai) {
							maxi = (i > maxi) ? i : maxi;
							maxj = (j > maxj) ? j : maxj;
						}
					} else {
						break;
					}
				}
			}
			erXuShuPaiZuGouXingsArrayIdx1Mod = (maxj + 1);

			// 再计算组合
			erXuShuPaiZuGouXingsArray = new int[(maxi + 1) * erXuShuPaiZuGouXingsArrayIdx1Mod][];
			for (int i = 0; i < xuShuPaiZuList.size(); i++) {
				XuShuPaiZu zu1 = xuShuPaiZuList.get(i);
				int totalPai1 = zu1.getTotalPai();
				int atleastGuiPai1 = zu1.getAtleastGuiPai();
				int[] gouXingArray1 = zu1.getGouXingArray();
				for (int j = i; j < xuShuPaiZuList.size(); j++) {
					XuShuPaiZu zu2 = xuShuPaiZuList.get(j);
					int totalPai = totalPai1 + zu2.getTotalPai();
					if (totalPai <= maxShouPai) {
						int atleastGuiPai = atleastGuiPai1 + zu2.getAtleastGuiPai();
						if (atleastGuiPai <= maxGuiPai) {
							int[] joindGouXingArray = joinGouXingArray(gouXingArray1, zu2.getGouXingArray());
							erXuShuPaiZuGouXingsArray[i * erXuShuPaiZuGouXingsArrayIdx1Mod + j] = joindGouXingArray;
						}
					} else {
						break;
					}
				}
			}
		}

		if (maxShouPaiXuShuPaiZu >= 3) {
			// 先计算数组规模
			int maxi = 0;
			int maxj = 0;
			int maxk = 0;
			for (int i = 0; i < xuShuPaiZuList.size(); i++) {
				XuShuPaiZu zu1 = xuShuPaiZuList.get(i);
				int totalPai1 = zu1.getTotalPai();
				int atleastGuiPai1 = zu1.getAtleastGuiPai();
				for (int j = i; j < xuShuPaiZuList.size(); j++) {
					XuShuPaiZu zu2 = xuShuPaiZuList.get(j);
					int totalPai2 = totalPai1 + zu2.getTotalPai();
					int atleastGuiPai2 = atleastGuiPai1 + zu2.getAtleastGuiPai();
					if (totalPai2 <= maxShouPai) {
						if (atleastGuiPai2 <= maxGuiPai) {
							for (int k = j; k < xuShuPaiZuList.size(); k++) {
								XuShuPaiZu zu3 = xuShuPaiZuList.get(k);
								int totalPai3 = totalPai2 + zu3.getTotalPai();
								int atleastGuiPai3 = atleastGuiPai2 + zu3.getAtleastGuiPai();
								if (totalPai3 <= maxShouPai) {
									if (atleastGuiPai3 <= maxGuiPai) {
										maxi = (i > maxi) ? i : maxi;
										maxj = (j > maxj) ? j : maxj;
										maxk = (k > maxk) ? k : maxk;
									}
								} else {
									break;
								}
							}
						}
					} else {
						break;
					}
				}
			}
			sanXuShuPaiZuGouXingsArrayIdx2Mod = (maxk + 1);
			sanXuShuPaiZuGouXingsArrayIdx1Mod = (maxj + 1) * sanXuShuPaiZuGouXingsArrayIdx2Mod;

			// 再计算组合
			sanXuShuPaiZuGouXingsArray = new int[(maxi + 1) * sanXuShuPaiZuGouXingsArrayIdx1Mod][];
			for (int i = 0; i < xuShuPaiZuList.size(); i++) {
				XuShuPaiZu zu1 = xuShuPaiZuList.get(i);
				int totalPai1 = zu1.getTotalPai();
				int atleastGuiPai1 = zu1.getAtleastGuiPai();
				for (int j = i; j < xuShuPaiZuList.size(); j++) {
					XuShuPaiZu zu2 = xuShuPaiZuList.get(j);
					int totalPai2 = totalPai1 + zu2.getTotalPai();
					int atleastGuiPai2 = atleastGuiPai1 + zu2.getAtleastGuiPai();
					if (totalPai2 <= maxShouPai) {
						if (atleastGuiPai2 <= maxGuiPai) {
							int[] joindGouXingArray = erXuShuPaiZuGouXingsArray[i * erXuShuPaiZuGouXingsArrayIdx1Mod
									+ j];
							for (int k = j; k < xuShuPaiZuList.size(); k++) {
								XuShuPaiZu zu3 = xuShuPaiZuList.get(k);
								int totalPai3 = totalPai2 + zu3.getTotalPai();
								int atleastGuiPai3 = atleastGuiPai2 + zu3.getAtleastGuiPai();
								if (totalPai3 <= maxShouPai) {
									if (atleastGuiPai3 <= maxGuiPai) {
										sanXuShuPaiZuGouXingsArray[i * sanXuShuPaiZuGouXingsArrayIdx1Mod
												+ j * sanXuShuPaiZuGouXingsArrayIdx2Mod + k] = joinGouXingArray(
														joindGouXingArray, zu3.getGouXingArray());
									}
								} else {
									break;
								}
							}
						}
					} else {
						break;
					}
				}
			}

		}

		if (maxShouPaiXuShuPaiZu >= 4) {
			// 先计算数组规模
			int maxi = 0;
			int maxj = 0;
			int maxk = 0;
			int maxl = 0;
			for (int i = 0; i < xuShuPaiZuList.size(); i++) {
				XuShuPaiZu zu1 = xuShuPaiZuList.get(i);
				int totalPai1 = zu1.getTotalPai();
				int atleastGuiPai1 = zu1.getAtleastGuiPai();
				for (int j = i; j < xuShuPaiZuList.size(); j++) {
					XuShuPaiZu zu2 = xuShuPaiZuList.get(j);
					int totalPai2 = totalPai1 + zu2.getTotalPai();
					int atleastGuiPai2 = atleastGuiPai1 + zu2.getAtleastGuiPai();
					if (totalPai2 <= maxShouPai) {
						if (atleastGuiPai2 <= maxGuiPai) {
							for (int k = j; k < xuShuPaiZuList.size(); k++) {
								XuShuPaiZu zu3 = xuShuPaiZuList.get(k);
								int totalPai3 = totalPai2 + zu3.getTotalPai();
								int atleastGuiPai3 = atleastGuiPai2 + zu3.getAtleastGuiPai();
								if (totalPai3 <= maxShouPai) {
									if (atleastGuiPai3 <= maxGuiPai) {
										for (int l = k; l < xuShuPaiZuList.size(); l++) {
											XuShuPaiZu zu4 = xuShuPaiZuList.get(l);
											int totalPai4 = totalPai3 + zu4.getTotalPai();
											int atleastGuiPai4 = atleastGuiPai3 + zu4.getAtleastGuiPai();
											if (totalPai4 <= maxShouPai) {
												if (atleastGuiPai4 <= maxGuiPai) {
													maxi = (i > maxi) ? i : maxi;
													maxj = (j > maxj) ? j : maxj;
													maxk = (k > maxk) ? k : maxk;
													maxl = (l > maxl) ? l : maxl;
												}
											} else {
												break;
											}
										}
									}
								} else {
									break;
								}
							}
						}
					} else {
						break;
					}
				}
			}
			siXuShuPaiZuGouXingsArrayIdx3Mod = (maxl + 1);
			siXuShuPaiZuGouXingsArrayIdx2Mod = (maxk + 1) * siXuShuPaiZuGouXingsArrayIdx3Mod;
			siXuShuPaiZuGouXingsArrayIdx1Mod = (maxj + 1) * siXuShuPaiZuGouXingsArrayIdx2Mod;

			// 再计算组合
			siXuShuPaiZuGouXingsArray = new int[(maxi + 1) * siXuShuPaiZuGouXingsArrayIdx1Mod][];
			for (int i = 0; i < xuShuPaiZuList.size(); i++) {
				XuShuPaiZu zu1 = xuShuPaiZuList.get(i);
				int totalPai1 = zu1.getTotalPai();
				int atleastGuiPai1 = zu1.getAtleastGuiPai();
				for (int j = i; j < xuShuPaiZuList.size(); j++) {
					XuShuPaiZu zu2 = xuShuPaiZuList.get(j);
					int totalPai2 = totalPai1 + zu2.getTotalPai();
					int atleastGuiPai2 = atleastGuiPai1 + zu2.getAtleastGuiPai();
					if (totalPai2 <= maxShouPai) {
						if (atleastGuiPai2 <= maxGuiPai) {
							for (int k = j; k < xuShuPaiZuList.size(); k++) {
								XuShuPaiZu zu3 = xuShuPaiZuList.get(k);
								int totalPai3 = totalPai2 + zu3.getTotalPai();
								int atleastGuiPai3 = atleastGuiPai2 + zu3.getAtleastGuiPai();
								if (totalPai3 <= maxShouPai) {
									if (atleastGuiPai3 <= maxGuiPai) {
										int[] joindGouXingArray = sanXuShuPaiZuGouXingsArray[i
												* sanXuShuPaiZuGouXingsArrayIdx1Mod
												+ j * sanXuShuPaiZuGouXingsArrayIdx2Mod + k];
										for (int l = k; l < xuShuPaiZuList.size(); l++) {
											XuShuPaiZu zu4 = xuShuPaiZuList.get(l);
											int totalPai4 = totalPai3 + zu4.getTotalPai();
											int atleastGuiPai4 = atleastGuiPai3 + zu4.getAtleastGuiPai();
											if (totalPai4 <= maxShouPai) {
												if (atleastGuiPai4 <= maxGuiPai) {
													siXuShuPaiZuGouXingsArray[i * siXuShuPaiZuGouXingsArrayIdx1Mod
															+ j * siXuShuPaiZuGouXingsArrayIdx2Mod
															+ k * siXuShuPaiZuGouXingsArrayIdx3Mod
															+ l] = joinGouXingArray(joindGouXingArray,
																	zu4.getGouXingArray());
												}
											} else {
												break;
											}
										}
									}
								} else {
									break;
								}
							}
						}
					} else {
						break;
					}
				}
			}

		}

		if (maxShouPaiXuShuPaiZu >= 5) {
			// 先计算数组规模
			int maxi = 0;
			int maxj = 0;
			int maxk = 0;
			int maxl = 0;
			int maxm = 0;
			for (int i = 0; i < xuShuPaiZuList.size(); i++) {
				XuShuPaiZu zu1 = xuShuPaiZuList.get(i);
				int totalPai1 = zu1.getTotalPai();
				int atleastGuiPai1 = zu1.getAtleastGuiPai();
				for (int j = i; j < xuShuPaiZuList.size(); j++) {
					XuShuPaiZu zu2 = xuShuPaiZuList.get(j);
					int totalPai2 = totalPai1 + zu2.getTotalPai();
					int atleastGuiPai2 = atleastGuiPai1 + zu2.getAtleastGuiPai();
					if (totalPai2 <= maxShouPai) {
						if (atleastGuiPai2 <= maxGuiPai) {
							for (int k = j; k < xuShuPaiZuList.size(); k++) {
								XuShuPaiZu zu3 = xuShuPaiZuList.get(k);
								int totalPai3 = totalPai2 + zu3.getTotalPai();
								int atleastGuiPai3 = atleastGuiPai2 + zu3.getAtleastGuiPai();
								if (totalPai3 <= maxShouPai) {
									if (atleastGuiPai3 <= maxGuiPai) {
										for (int l = k; l < xuShuPaiZuList.size(); l++) {
											XuShuPaiZu zu4 = xuShuPaiZuList.get(l);
											int totalPai4 = totalPai3 + zu4.getTotalPai();
											int atleastGuiPai4 = atleastGuiPai3 + zu4.getAtleastGuiPai();
											if (totalPai4 <= maxShouPai) {
												if (atleastGuiPai4 <= maxGuiPai) {
													for (int m = l; m < xuShuPaiZuList.size(); m++) {
														XuShuPaiZu zu5 = xuShuPaiZuList.get(m);
														int totalPai5 = totalPai4 + zu5.getTotalPai();
														int atleastGuiPai5 = atleastGuiPai4 + zu5.getAtleastGuiPai();
														if (totalPai5 <= maxShouPai) {
															if (atleastGuiPai5 <= maxGuiPai) {
																maxi = (i > maxi) ? i : maxi;
																maxj = (j > maxj) ? j : maxj;
																maxk = (k > maxk) ? k : maxk;
																maxl = (l > maxl) ? l : maxl;
																maxm = (m > maxm) ? m : maxm;
															}
														} else {
															break;
														}
													}
												}
											} else {
												break;
											}
										}
									}
								} else {
									break;
								}
							}
						}
					} else {
						break;
					}
				}
			}
			wuXuShuPaiZuGouXingsArrayIdx4Mod = (maxm + 1);
			wuXuShuPaiZuGouXingsArrayIdx3Mod = (maxl + 1) * wuXuShuPaiZuGouXingsArrayIdx4Mod;
			wuXuShuPaiZuGouXingsArrayIdx2Mod = (maxk + 1) * wuXuShuPaiZuGouXingsArrayIdx3Mod;
			wuXuShuPaiZuGouXingsArrayIdx1Mod = (maxj + 1) * wuXuShuPaiZuGouXingsArrayIdx2Mod;

			// 再计算组合
			wuXuShuPaiZuGouXingsArray = new int[(maxi + 1) * wuXuShuPaiZuGouXingsArrayIdx1Mod][];
			for (int i = 0; i < xuShuPaiZuList.size(); i++) {
				XuShuPaiZu zu1 = xuShuPaiZuList.get(i);
				int totalPai1 = zu1.getTotalPai();
				int atleastGuiPai1 = zu1.getAtleastGuiPai();
				for (int j = i; j < xuShuPaiZuList.size(); j++) {
					XuShuPaiZu zu2 = xuShuPaiZuList.get(j);
					int totalPai2 = totalPai1 + zu2.getTotalPai();
					int atleastGuiPai2 = atleastGuiPai1 + zu2.getAtleastGuiPai();
					if (totalPai2 <= maxShouPai) {
						if (atleastGuiPai2 <= maxGuiPai) {
							for (int k = j; k < xuShuPaiZuList.size(); k++) {
								XuShuPaiZu zu3 = xuShuPaiZuList.get(k);
								int totalPai3 = totalPai2 + zu3.getTotalPai();
								int atleastGuiPai3 = atleastGuiPai2 + zu3.getAtleastGuiPai();
								if (totalPai3 <= maxShouPai) {
									if (atleastGuiPai3 <= maxGuiPai) {
										for (int l = k; l < xuShuPaiZuList.size(); l++) {
											XuShuPaiZu zu4 = xuShuPaiZuList.get(l);
											int totalPai4 = totalPai3 + zu4.getTotalPai();
											int atleastGuiPai4 = atleastGuiPai3 + zu4.getAtleastGuiPai();
											if (totalPai4 <= maxShouPai) {
												if (atleastGuiPai4 <= maxGuiPai) {
													int[] joindGouXingArray = siXuShuPaiZuGouXingsArray[i
															* siXuShuPaiZuGouXingsArrayIdx1Mod
															+ j * siXuShuPaiZuGouXingsArrayIdx2Mod
															+ k * siXuShuPaiZuGouXingsArrayIdx3Mod + l];

													for (int m = l; m < xuShuPaiZuList.size(); m++) {
														XuShuPaiZu zu5 = xuShuPaiZuList.get(m);
														int totalPai5 = totalPai4 + zu5.getTotalPai();
														int atleastGuiPai5 = atleastGuiPai4 + zu5.getAtleastGuiPai();
														if (totalPai5 <= maxShouPai) {
															if (atleastGuiPai5 <= maxGuiPai) {
																wuXuShuPaiZuGouXingsArray[i
																		* wuXuShuPaiZuGouXingsArrayIdx1Mod
																		+ j * wuXuShuPaiZuGouXingsArrayIdx2Mod
																		+ k * wuXuShuPaiZuGouXingsArrayIdx3Mod
																		+ l * wuXuShuPaiZuGouXingsArrayIdx4Mod
																		+ m] = joinGouXingArray(joindGouXingArray,
																				zu5.getGouXingArray());
															}
														} else {
															break;
														}
													}
												}
											} else {
												break;
											}
										}
									}
								} else {
									break;
								}
							}
						}
					} else {
						break;
					}
				}
			}
		}

	}

	private static int[] joinGouXingArray(int[] gouXingArray1, int[] gouXingArray2) {
		int[] result = new int[gouXingArray1.length * gouXingArray2.length];
		int ri = 0;
		for (int i = 0; i < gouXingArray1.length; i++) {
			int gouXing1 = gouXingArray1[i];
			for (int j = 0; j < gouXingArray2.length; j++) {
				result[ri++] = gouXing1 + gouXingArray2[j];
			}
		}
		return result;
	}

	private static void calculateGouXingForXuShuPaiZu(List<XuShuPaiZu> xuShuPaiZuList) {
		int count = 0;
		int total = 0;
		for (XuShuPaiZu xuShuPaiZu : xuShuPaiZuList) {
			System.out.println("计算第" + (++count) + "个序数牌组的构型");
			xuShuPaiZu.calculateGouXing();
			total += xuShuPaiZu.getGouXingArray().length;
		}
		System.out.println("所有序数牌组总共" + total + "个构型");
	}

	private static List<XuShuPaiZu> calculateXuShuPaiZu(int maxShouPai, int maxGuiPai) {
		List<XuShuPaiZu> list = new ArrayList<>();
		for (int shouPai = 3; shouPai <= maxShouPai; shouPai++) {
			calculateXuShuPaiZuForShouPai(shouPai, maxGuiPai, list);
		}
		list.forEach((xuShuPaiZu) -> xuShuPaiZu.calculateCode());
		Collections.sort(list);
		return list;
	}

	private static void calculateXuShuPaiZuForShouPai(int shouPai, int maxGuiPai, List<XuShuPaiZu> list) {
		int maxLian = (shouPai < 9) ? shouPai : 9;
		for (int lian = 3; lian <= maxLian; lian++) {
			calculateXuShuPaiZuForShouPaiAndLian(shouPai, maxGuiPai, lian, list);
		}
	}

	private static void calculateXuShuPaiZuForShouPaiAndLian(int shouPai, int maxGuiPai, int lian,
			List<XuShuPaiZu> list) {
		int maxPaiForXuHao = 4 + maxGuiPai;
		int mod = maxPaiForXuHao + 1;
		int codeSpace = (int) Math.pow(mod, lian);// 3连，一个花色最多7张牌的编码空间是 8的3次方（此编码是用于计算的压缩编码，尽可能小空间）
		int[] paiQuantityArray = new int[lian];
		for (int code = 0; code < codeSpace; code++) {
			int num = code;
			int totalPai = 0;
			int atleastGuiPai = 0;
			boolean bigCodeMode = false;
			boolean gotOne = true;
			for (int i = 0; i < lian; i++) {
				int shang = num / mod;
				if (shang == 0 && i < (lian - 1)) {
					gotOne = false;
					break;
				}
				int yu = num % mod;
				if (yu == 0) {
					gotOne = false;
					break;
				}
				totalPai += yu;
				if (totalPai > shouPai) {
					gotOne = false;
					break;
				}
				atleastGuiPai += ((yu > 4) ? (yu - 4) : 0);
				if (atleastGuiPai > maxGuiPai) {
					gotOne = false;
					break;
				}
				paiQuantityArray[i] = yu;
				if (yu > 7) {
					bigCodeMode = true;
				}
				num = shang;
			}
			if (!gotOne) {
				continue;
			} else {
				if (totalPai != shouPai) {
					continue;
				}
				XuShuPaiZu xuShuPaiZu = new XuShuPaiZu(paiQuantityArray, totalPai, atleastGuiPai, bigCodeMode);
				list.add(xuShuPaiZu);
			}
		}
		System.out.println(shouPai + "张牌" + lian + "连序数牌组计算完毕！已有" + list.size() + "个结果");
	}

}
