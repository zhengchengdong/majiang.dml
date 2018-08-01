package com.dml.majiang.custom;

import com.dml.majiang.action.MajiangGuoAction;
import com.dml.majiang.action.MajiangPlayerGuoActionProcessor;
import com.dml.majiang.ju.Ju;

public class DoNothingGuoActionProcessor implements MajiangPlayerGuoActionProcessor {

	@Override
	public void process(MajiangGuoAction action, Ju ju) throws Exception {
		// 过的话就是啥也不做
	}

}
