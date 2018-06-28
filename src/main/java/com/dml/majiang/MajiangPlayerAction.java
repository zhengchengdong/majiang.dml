package com.dml.majiang;

/**
 * 吃碰杠胡摸打过
 * 
 * @author Neo
 *
 */
public abstract class MajiangPlayerAction {

	private int id;

	public MajiangPlayerAction() {
	}

	public MajiangPlayerAction(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
