package com.dml.majiang;

/**
 * 手牌当牌。手牌分杠子、刻子、顺子、对子、单牌时候当的牌。一个牌参与分组会有不同当法，比如就是本牌当本牌或者一万的鬼牌当作4条或者白板当一万。
 * 
 * @author Neo
 *
 */
public interface ShoupaiDangPai {
	public String getDangType();
}
