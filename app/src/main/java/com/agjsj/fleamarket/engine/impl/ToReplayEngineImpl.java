package com.agjsj.fleamarket.engine.impl;

import com.agjsj.fleamarket.params.ConstantValue;
import com.agjsj.fleamarket.bean.Torepaly;
import com.agjsj.fleamarket.engine.BaseEngine;
import com.agjsj.fleamarket.engine.ToReplayEngine;
import com.agjsj.fleamarket.net.procotal.Body;
import com.agjsj.fleamarket.net.procotal.IMessage;

public class ToReplayEngineImpl extends BaseEngine implements ToReplayEngine {

	@Override
	public IMessage addToReplay(Torepaly torepaly) {
		// 1，分装body，生成json数据
		Body body = new Body();

//		body.setBodyStr(torepaly);
		// body.serializableBody();
		String sendinfo = getMessageToJson(body, "60001");
		// 2.向服务器发送数据,获取返回数据并封装成IMessage对象
//		return sendJsonToService(ConstantValue.GOODS_TOREPLAY_URL, sendinfo);
		return null;
	}

	@Override
	public IMessage getToReplayOfReplay(Integer goodsReplayid) {
		// 1，分装body，生成json数据
		Body body = new Body();
//		JSONObject jsonObject = new JSONObject();
//		jsonObject.put("goodsreplayid", goodsReplayid);
//		body.setBodyStr(jsonObject.toJSONString());
//		// body.serializableBody();
		String sendinfo = getMessageToJson(body, "60002");
		// 2.向服务器发送数据,获取返回数据并封装成IMessage对象
//		return sendJsonToService(ConstantValue.GOODS_TOREPLAY_URL, sendinfo);
		return null;
	}

	@Override
	public IMessage deleteToReplay(Integer toreplayid) {
		// 1，分装body，生成json数据
		Body body = new Body();
//		JSONObject jsonObject = new JSONObject();
//		jsonObject.put("toreplayid", toreplayid);
//		body.setBodyStr(jsonObject.toJSONString());
		// body.serializableBody();
		String sendinfo = getMessageToJson(body, "60003");
		// 2.向服务器发送数据,获取返回数据并封装成IMessage对象
//		return sendJsonToService(ConstantValue.GOODS_TOREPLAY_URL, sendinfo);
		return null;
	}

}
