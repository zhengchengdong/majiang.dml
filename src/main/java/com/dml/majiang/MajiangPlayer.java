package com.dml.majiang;

public class MajiangPlayer {
	private String id;
	/**
	 * 门风
	 */
	private MajiangPosition menFeng;
	// - shouPaiList:List<MajiangPai>
	// - actionCandidates:Map<Integer,MajiangPlayerAction>
	// - publicPaiList:List<MajiangPai>//公开的牌，不能行牌
	// - guiPaiList:List<MajiangPai>//手牌中的鬼牌
	// - guiPaiSet:Set<MajiangPai>//标示什么牌是鬼牌
	// - xingPaiMap:Map<MajiangPai,MajiangPai>//什么牌当成了什么牌

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public MajiangPosition getMenFeng() {
		return menFeng;
	}

	public void setMenFeng(MajiangPosition menFeng) {
		this.menFeng = menFeng;
	}

}
