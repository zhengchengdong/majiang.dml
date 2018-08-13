package com.dml.majiang.player.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dml.majiang.ju.Ju;
import com.dml.majiang.player.action.da.MajiangDaAction;
import com.dml.majiang.player.action.da.MajiangPlayerDaActionStatisticsListener;
import com.dml.majiang.player.action.gang.MajiangGangAction;
import com.dml.majiang.player.action.gang.MajiangPlayerGangActionStatisticsListener;
import com.dml.majiang.player.action.peng.MajiangPengAction;
import com.dml.majiang.player.action.peng.MajiangPlayerPengActionStatisticsListener;

public class ActionStatisticsListenerManager {

	private Map<String, MajiangPlayerActionStatisticsListener> listenerTypeNameListenerMap = new HashMap<>();

	private List<String> daListenerTypeNameList = new ArrayList<>();
	private List<String> pengListenerTypeNameList = new ArrayList<>();
	private List<String> gangListenerTypeNameList = new ArrayList<>();

	public void addListener(MajiangPlayerActionStatisticsListener listener) {
		String typeName = listener.getClass().getName();
		if (!listenerTypeNameListenerMap.containsKey(typeName)) {
			listenerTypeNameListenerMap.put(typeName, listener);
			if (listener instanceof MajiangPlayerDaActionStatisticsListener) {
				daListenerTypeNameList.add(typeName);
			}
			if (listener instanceof MajiangPlayerPengActionStatisticsListener) {
				pengListenerTypeNameList.add(typeName);
			}
			if (listener instanceof MajiangPlayerGangActionStatisticsListener) {
				gangListenerTypeNameList.add(typeName);
			}
		}
	}

	public void updateDaActionListener(MajiangDaAction daAction, Ju ju) throws Exception {
		for (String typeName : daListenerTypeNameList) {
			MajiangPlayerDaActionStatisticsListener listener = (MajiangPlayerDaActionStatisticsListener) listenerTypeNameListenerMap
					.get(typeName);
			listener.update(daAction, ju);
		}
	}

	public void updatePengActionListener(MajiangPengAction pengAction, Ju ju) throws Exception {
		for (String typeName : pengListenerTypeNameList) {
			MajiangPlayerPengActionStatisticsListener listener = (MajiangPlayerPengActionStatisticsListener) listenerTypeNameListenerMap
					.get(typeName);
			listener.update(pengAction, ju);
		}
	}

	public void updateGangActionListener(MajiangGangAction gangAction, Ju ju) throws Exception {
		for (String typeName : gangListenerTypeNameList) {
			MajiangPlayerGangActionStatisticsListener listener = (MajiangPlayerGangActionStatisticsListener) listenerTypeNameListenerMap
					.get(typeName);
			listener.update(gangAction, ju);
		}
	}

	public <T extends MajiangPlayerActionStatisticsListener> T findListener(Class<T> type) {
		return (T) listenerTypeNameListenerMap.get(type.getName());
	}

}
