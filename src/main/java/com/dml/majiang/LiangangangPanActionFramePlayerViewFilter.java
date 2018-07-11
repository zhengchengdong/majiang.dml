package com.dml.majiang;

/**
 * 暗杠要亮出来
 * 
 * @author Neo
 *
 */
public class LiangangangPanActionFramePlayerViewFilter implements PanActionFramePlayerViewFilter {

	@Override
	public void filter(PanActionFrame frame, String playerId) {

		PanValueObject pan = frame.getPanAfterAction();
		pan.getPlayerList().forEach((player) -> {
			if (player.getId().equals(playerId)) {// 是自己
				// 什么都不过滤，全要看
			} else {// 是其他玩家
				player.setActionCandidates(null);
				player.getGangmoShoupai().setPai(null);
				player.getFangruShoupaiList().setPaiList(null);
			}
		});
	}

}
