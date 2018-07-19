package com.dml.majiang;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PaiXing {

	private List<MajiangPai> danpaiList;
	private List<Duizi> duiziList;
	private List<Kezi> keziList;
	private List<Gangzi> gangziList;
	private List<Shunzi> shunziList;

	public static PaiXing combine(PaiXing[] paiXingArray) {
		PaiXing newPaiXing = new PaiXing();
		List<MajiangPai> newDanpaiList = new ArrayList<>();
		List<Duizi> newDuiziList = new ArrayList<>();
		List<Kezi> newKeziList = new ArrayList<>();
		List<Gangzi> newGangziList = new ArrayList<>();
		List<Shunzi> newShunziList = new ArrayList<>();
		newPaiXing.setDanpaiList(newDanpaiList);
		newPaiXing.setDuiziList(newDuiziList);
		newPaiXing.setGangziList(newGangziList);
		newPaiXing.setKeziList(newKeziList);
		newPaiXing.setShunziList(newShunziList);
		for (int i = 0; i < paiXingArray.length; i++) {
			PaiXing paiXing = paiXingArray[i];
			newDanpaiList.addAll(paiXing.danpaiList);
			newDuiziList.addAll(paiXing.duiziList);
			newKeziList.addAll(paiXing.keziList);
			newGangziList.addAll(paiXing.gangziList);
			newShunziList.addAll(paiXing.shunziList);
		}
		return newPaiXing;
	}

	public ShoupaiPaiXing generateShoupaiPaiXingByGuipaiDangPai(GuipaiDangPai[] guipaiDangPaiArray) {
		Map<MajiangPai, List<MajiangPai>> dangpaiGuipaiListMap = new HashMap<>();
		for (int i = 0; i < guipaiDangPaiArray.length; i++) {
			GuipaiDangPai guipaiDangPai = guipaiDangPaiArray[i];
			List<MajiangPai> guipaiList = dangpaiGuipaiListMap.get(guipaiDangPai.getDangpai());
			if (guipaiList == null) {
				guipaiList = new ArrayList<>();
			}
			guipaiList.add(guipaiDangPai.getGuipai());
		}

		ShoupaiPaiXing shoupaiPaiXing = new ShoupaiPaiXing();
		List<ShoupaiDanpai> sdanpaiList = new ArrayList<>();
		List<ShoupaiDuiziZu> sduiziList = new ArrayList<>();
		List<ShoupaiKeziZu> skeziList = new ArrayList<>();
		List<ShoupaiGangziZu> sgangziList = new ArrayList<>();
		List<ShoupaiShunziZu> sshunziList = new ArrayList<>();
		shoupaiPaiXing.setDanpaiList(sdanpaiList);
		shoupaiPaiXing.setDuiziList(sduiziList);
		shoupaiPaiXing.setGangziList(sgangziList);
		shoupaiPaiXing.setKeziList(skeziList);
		shoupaiPaiXing.setShunziList(sshunziList);

		for (MajiangPai danpai : danpaiList) {
			ShoupaiDanpai shoupaiDanpai = new ShoupaiDanpai();
			ShoupaiDangPai shoupaiDangPai = makeShoupaiDangPai(dangpaiGuipaiListMap, danpai);
			shoupaiDanpai.setPai(shoupaiDangPai);
			sdanpaiList.add(shoupaiDanpai);
		}

		for (Duizi duizi : duiziList) {
			ShoupaiDuiziZu shoupaiDuiziZu = new ShoupaiDuiziZu();
			shoupaiDuiziZu.setDuiziType(duizi.getPaiType());
			shoupaiDuiziZu.setPai1(makeShoupaiDangPai(dangpaiGuipaiListMap, duizi.getPaiType()));
			shoupaiDuiziZu.setPai2(makeShoupaiDangPai(dangpaiGuipaiListMap, duizi.getPaiType()));
			sduiziList.add(shoupaiDuiziZu);
		}

		for (Kezi kezi : keziList) {
			ShoupaiKeziZu shoupaiKeziZu = new ShoupaiKeziZu();
			shoupaiKeziZu.setKezi(kezi);
			shoupaiKeziZu.setPai1(makeShoupaiDangPai(dangpaiGuipaiListMap, kezi.getPaiType()));
			shoupaiKeziZu.setPai2(makeShoupaiDangPai(dangpaiGuipaiListMap, kezi.getPaiType()));
			shoupaiKeziZu.setPai3(makeShoupaiDangPai(dangpaiGuipaiListMap, kezi.getPaiType()));
			skeziList.add(shoupaiKeziZu);
		}

		for (Gangzi gangzi : gangziList) {
			ShoupaiGangziZu shoupaiGangziZu = new ShoupaiGangziZu();
			shoupaiGangziZu.setGangzi(gangzi);
			shoupaiGangziZu.setPai1(makeShoupaiDangPai(dangpaiGuipaiListMap, gangzi.getPaiType()));
			shoupaiGangziZu.setPai2(makeShoupaiDangPai(dangpaiGuipaiListMap, gangzi.getPaiType()));
			shoupaiGangziZu.setPai3(makeShoupaiDangPai(dangpaiGuipaiListMap, gangzi.getPaiType()));
			shoupaiGangziZu.setPai4(makeShoupaiDangPai(dangpaiGuipaiListMap, gangzi.getPaiType()));
			sgangziList.add(shoupaiGangziZu);
		}

		for (Shunzi shunzi : shunziList) {
			ShoupaiShunziZu shoupaiShunziZu = new ShoupaiShunziZu();
			shoupaiShunziZu.setShunzi(shunzi);
			shoupaiShunziZu.setPai1(makeShoupaiDangPai(dangpaiGuipaiListMap, shunzi.getPai1()));
			shoupaiShunziZu.setPai2(makeShoupaiDangPai(dangpaiGuipaiListMap, shunzi.getPai2()));
			shoupaiShunziZu.setPai3(makeShoupaiDangPai(dangpaiGuipaiListMap, shunzi.getPai3()));
			sshunziList.add(shoupaiShunziZu);
		}
		return shoupaiPaiXing;
	}

	private ShoupaiDangPai makeShoupaiDangPai(Map<MajiangPai, List<MajiangPai>> dangpaiGuipaiListMap,
			MajiangPai shijipai) {
		if (dangpaiGuipaiListMap.containsKey(shijipai)) {
			List<MajiangPai> guipaiList = dangpaiGuipaiListMap.get(shijipai);
			MajiangPai guipai = guipaiList.remove(0);
			if (guipaiList.isEmpty()) {
				dangpaiGuipaiListMap.remove(shijipai);
			}
			return new GuipaiDangPai(guipai, shijipai);
		} else {
			return new BenPai(shijipai);
		}
	}

	public List<MajiangPai> getDanpaiList() {
		return danpaiList;
	}

	public void setDanpaiList(List<MajiangPai> danpaiList) {
		this.danpaiList = danpaiList;
	}

	public List<Duizi> getDuiziList() {
		return duiziList;
	}

	public void setDuiziList(List<Duizi> duiziList) {
		this.duiziList = duiziList;
	}

	public List<Kezi> getKeziList() {
		return keziList;
	}

	public void setKeziList(List<Kezi> keziList) {
		this.keziList = keziList;
	}

	public List<Gangzi> getGangziList() {
		return gangziList;
	}

	public void setGangziList(List<Gangzi> gangziList) {
		this.gangziList = gangziList;
	}

	public List<Shunzi> getShunziList() {
		return shunziList;
	}

	public void setShunziList(List<Shunzi> shunziList) {
		this.shunziList = shunziList;
	}

}
