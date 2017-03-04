package com.agjsj.fleamarket.engine.impl;

import com.agjsj.fleamarket.params.ConstantValue;
import com.agjsj.fleamarket.bean.Goodsrepaly;
import com.agjsj.fleamarket.engine.BaseEngine;
import com.agjsj.fleamarket.engine.ReplayEngine;
import com.agjsj.fleamarket.net.procotal.Body;
import com.agjsj.fleamarket.net.procotal.IMessage;

public class ReplayEngineImpl extends BaseEngine implements ReplayEngine {

	@Override
	public IMessage sendReplay(Goodsrepaly goodsrepaly) {
		// 1，分装body，生成json数据
		Body body = new Body();

//		body.setBodyStr(goodsrepaly);
		// body.serializableBody();
		String sendinfo = getMessageToJson(body, "40001");
		// 2.向服务器发送数据,获取返回数据并封装成IMessage对象
//		return sendJsonToService(ConstantValue.GOODS_REPLAY_URL, sendinfo);
		return null;

	}

	@Override
	public IMessage getAllReplayOfgoodsid(Integer goodsid, Integer start,
			Integer count) {

		// 1，分装body，生成json数据
		Body body = new Body();
//		JSONObject jsonObject = new JSONObject();
//		jsonObject.put("goodsid", goodsid);
//		jsonObject.put("start", start);
//		jsonObject.put("count", count);
//		body.setBodyStr(jsonObject.toJSONString());
		// body.serializableBody();
		String sendinfo = getMessageToJson(body, "40002");
		// 2.向服务器发送数据,获取返回数据并封装成IMessage对象
//		return sendJsonToService(ConstantValue.GOODS_REPLAY_URL, sendinfo);
		return null;
	}

}
