package com.dml.majiang;

/**
 * 暗杠要亮出来
 * 
 * @author Neo
 *
 */
public class LiangangangPanValueObjectPlayerViewFilter implements PanValueObjectPlayerViewFilter {

	@Override
	public void filter(PanValueObject pan, String playerId) {
		pan.getPlayerList().forEach((player) -> {
			if (player.getId().equals(playerId)) {// 是自己
				// 什么都不过滤，全要看
			} else {// 是其他玩家
				player.getActionCandidates().clear();
				player.setPublicMoPai(null);
				player.getShoupaiList().clear();
			}
		});
	}

}
