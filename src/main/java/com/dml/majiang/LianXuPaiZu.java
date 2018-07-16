package com.dml.majiang;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 连续牌组
 * 
 * @author Neo
 *
 */
public class LianXuPaiZu implements Comparable<LianXuPaiZu> {

	private int[] paiQuantityArray;

	private int lian;

	private int totalPai;

	private int atleastGuiPai;

	private int smallCode;// 每3位

	private long bigCode;// 每4位

	private boolean bigCodeMode;

	private int[] gouXingArray;

	public LianXuPaiZu(int[] paiQuantityArray, int totalPai, int atleastGuiPai, boolean bigCodeMode) {
		this.lian = paiQuantityArray.length;
		this.paiQuantityArray = new int[lian];
		System.arraycopy(paiQuantityArray, 0, this.paiQuantityArray, 0, lian);
		this.totalPai = totalPai;
		this.atleastGuiPai = atleastGuiPai;
		this.bigCodeMode = bigCodeMode;
	}

	/**
	 * 构形编码用一个int来表示:低到高--5位单牌个数，4位对子个数，3位刻子个数，3位杠子个数，3位顺子个数
	 */
	public void calculateGouXing() {
		Set<Integer> gouXingSet = new HashSet<>();
		doCalculateGouXing(0, 0, 0, 0, gouXingSet);
		gouXingArray = new int[gouXingSet.size()];
		int i = 0;
		for (int gouXing : gouXingSet) {
			gouXingArray[i++] = gouXing;
		}
	}

	private void doCalculateGouXing(int yiquShunziGeshu, int yiquGangziGeshu, int yiquKeziGeshu, int yiquDuiziGeshu,
			Set<Integer> gouXingSet) {

		// 计算所有顺子取法
		List<Integer> shunziStartIdxList = new ArrayList(lian - 2);
		int lianxuCount = 0;
		for (int i = 0; i < paiQuantityArray.length; i++) {
			if (paiQuantityArray[i] > 0) {
				lianxuCount++;
				if (lianxuCount == 3) {
					shunziStartIdxList.add(i - 2);
					lianxuCount = 0;
					i -= 2;
				}
			} else {
				lianxuCount = 0;
			}
		}

		// 找出第一个可能的杠子取法
		int gangziIdx = -1;
		for (int i = 0; i < paiQuantityArray.length; i++) {
			if (paiQuantityArray[i] >= 4) {
				gangziIdx = i;
				break;
			}
		}

		// 找出第一个可能的刻子取法
		int keziIdx = -1;
		for (int i = 0; i < paiQuantityArray.length; i++) {
			if (paiQuantityArray[i] >= 3) {
				keziIdx = i;
				break;
			}
		}

		// 找出第一个可能的对子取法
		int duiziIdx = -1;
		for (int i = 0; i < paiQuantityArray.length; i++) {
			if (paiQuantityArray[i] >= 2) {
				duiziIdx = i;
				break;
			}
		}

		// 如果什么都不能取，说明可以记录一个结果
		if (shunziStartIdxList.isEmpty() && gangziIdx == -1 && keziIdx == -1 && duiziIdx == -1) {
			// 构形用一个int来编码:低到高--5位单牌个数，4位对子个数，3位刻子个数，3位杠子个数，3位顺子个数
			int danpaiGeshu = 0;
			for (int i = 0; i < paiQuantityArray.length; i++) {
				danpaiGeshu += paiQuantityArray[i];
			}
			int couXingCode = danpaiGeshu;
			couXingCode = couXingCode | (yiquDuiziGeshu << 5);
			couXingCode = couXingCode | (yiquKeziGeshu << 9);
			couXingCode = couXingCode | (yiquGangziGeshu << 12);
			couXingCode = couXingCode | (yiquShunziGeshu << 15);
			gouXingSet.add(couXingCode);
			return;
		}

		shunziStartIdxList.forEach((shunziStartIdx) -> {
			paiQuantityArray[shunziStartIdx]--;
			paiQuantityArray[shunziStartIdx + 1]--;
			paiQuantityArray[shunziStartIdx + 2]--;
			doCalculateGouXing(yiquShunziGeshu + 1, yiquGangziGeshu, yiquKeziGeshu, yiquDuiziGeshu, gouXingSet);
			paiQuantityArray[shunziStartIdx]++;
			paiQuantityArray[shunziStartIdx + 1]++;
			paiQuantityArray[shunziStartIdx + 2]++;
		});
		if (gangziIdx != -1) {
			paiQuantityArray[gangziIdx] -= 4;
			doCalculateGouXing(yiquShunziGeshu, yiquGangziGeshu + 1, yiquKeziGeshu, yiquDuiziGeshu, gouXingSet);
			paiQuantityArray[gangziIdx] += 4;
		}

		if (keziIdx != -1) {
			paiQuantityArray[keziIdx] -= 3;
			doCalculateGouXing(yiquShunziGeshu, yiquGangziGeshu, yiquKeziGeshu + 1, yiquDuiziGeshu, gouXingSet);
			paiQuantityArray[keziIdx] += 3;
		}

		if (duiziIdx != -1) {
			paiQuantityArray[duiziIdx] -= 2;
			doCalculateGouXing(yiquShunziGeshu, yiquGangziGeshu, yiquKeziGeshu, yiquDuiziGeshu + 1, gouXingSet);
			paiQuantityArray[duiziIdx] += 2;
		}

	}

	public void calculateCode() {
		for (int i = 0; i < paiQuantityArray.length; i++) {
			bigCode = (bigCode | (paiQuantityArray[i] << (i * 4)));
		}
		if (!bigCodeMode) {
			for (int i = 0; i < paiQuantityArray.length; i++) {
				smallCode = (smallCode | (paiQuantityArray[i] << (i * 3)));
			}
		}
	}

	@Override
	public int compareTo(LianXuPaiZu another) {
		if (totalPai < another.totalPai) {
			return -1;
		} else if (totalPai > another.totalPai) {
			return 1;
		} else {
			if (bigCode < another.bigCode) {
				return -1;
			} else if (bigCode > another.bigCode) {
				return 1;
			} else {
				return 0;
			}
		}
	}

	public int getLian() {
		return lian;
	}

	public int getTotalPai() {
		return totalPai;
	}

	public int getAtleastGuiPai() {
		return atleastGuiPai;
	}

	public int getSmallCode() {
		return smallCode;
	}

	public long getBigCode() {
		return bigCode;
	}

	public boolean isBigCodeMode() {
		return bigCodeMode;
	}

	public int[] getGouXingArray() {
		return gouXingArray;
	}

}
