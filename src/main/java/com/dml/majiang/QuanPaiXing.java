package com.dml.majiang;

import java.util.ArrayList;
import java.util.List;

/**
 * 全牌型
 * 
 * @author neo
 *
 */
public class QuanPaiXing {
	private ShoupaiPaiXing shoupaiPaiXing;
	private List<ChichuPaiZu> chichupaiZuList;
	private List<PengchuPaiZu> pengchupaiZuList;
	private List<GangchuPaiZu> gangchupaiZuList;

	public QuanPaiXing() {
	}

	public QuanPaiXing(ShoupaiPaiXing shoupaiPaiXing, List<ChichuPaiZu> chichupaiZuList,
			List<PengchuPaiZu> pengchupaiZuList, List<GangchuPaiZu> gangchupaiZuList) {
		this.shoupaiPaiXing = shoupaiPaiXing;
		this.chichupaiZuList = new ArrayList<>(chichupaiZuList);
		this.pengchupaiZuList = new ArrayList<>(pengchupaiZuList);
		this.gangchupaiZuList = new ArrayList<>(gangchupaiZuList);
	}

	public ShoupaiPaiXing getShoupaiPaiXing() {
		return shoupaiPaiXing;
	}

	public void setShoupaiPaiXing(ShoupaiPaiXing shoupaiPaiXing) {
		this.shoupaiPaiXing = shoupaiPaiXing;
	}

	public List<ChichuPaiZu> getChichupaiZuList() {
		return chichupaiZuList;
	}

	public void setChichupaiZuList(List<ChichuPaiZu> chichupaiZuList) {
		this.chichupaiZuList = chichupaiZuList;
	}

	public List<PengchuPaiZu> getPengchupaiZuList() {
		return pengchupaiZuList;
	}

	public void setPengchupaiZuList(List<PengchuPaiZu> pengchupaiZuList) {
		this.pengchupaiZuList = pengchupaiZuList;
	}

	public List<GangchuPaiZu> getGangchupaiZuList() {
		return gangchupaiZuList;
	}

	public void setGangchupaiZuList(List<GangchuPaiZu> gangchupaiZuList) {
		this.gangchupaiZuList = gangchupaiZuList;
	}

}
