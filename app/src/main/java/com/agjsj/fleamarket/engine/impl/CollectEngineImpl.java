package com.agjsj.fleamarket.engine.impl;

import com.agjsj.fleamarket.params.ConstantValue;
import com.agjsj.fleamarket.engine.BaseEngine;
import com.agjsj.fleamarket.engine.CollectEngine;
import com.agjsj.fleamarket.net.procotal.Body;
import com.agjsj.fleamarket.net.procotal.IMessage;

public class CollectEngineImpl extends BaseEngine implements CollectEngine {

	@Override
	public IMessage addGoodsCollect(Integer userid, Integer goodsid) {
		// 1，分装body，生成json数据
		Body body = new Body();
//		JSONObject jsonObject = new JSONObject();
//		jsonObject.put("userid", userid);
//		jsonObject.put("goodsid", goodsid);
//		body.setBodyStr(jsonObject.toJSONString());
		// body.serializableBody();
		String sendinfo = getMessageToJson(body, "50001");
		// 2.向服务器发送数据,获取返回数据并封装成IMessage对象
//		return sendJsonToService(ConstantValue.GOODS_COLLECT_URL, sendinfo);

		return null;
	}

	@Override
	public IMessage deleteGoodsCollect(Integer gcid) {
		// 1，分装body，生成json数据
		Body body = new Body();
//		JSONObject jsonObject = new JSONObject();
//		jsonObject.put("gcid", gcid);
//		body.setBodyStr(jsonObject.toJSONString());
//		// body.serializableBody();
		String sendinfo = getMessageToJson(body, "50003");
		// 2.向服务器发送数据,获取返回数据并封装成IMessage对象
//		return sendJsonToService(ConstantValue.GOODS_COLLECT_URL, sendinfo);
		return null;
	}


	@Override
	public IMessage getAllGoodsOfCollect(Integer userid) {
		// 1，分装body，生成json数据
		Body body = new Body();
//		JSONObject jsonObject = new JSONObject();
//		jsonObject.put("userid", userid);
//		body.setBodyStr(jsonObject.toJSONString());
		// body.serializableBody();
		String sendinfo = getMessageToJson(body, "50002");
		// 2.向服务器发送数据,获取返回数据并封装成IMessage对象
//		return sendJsonToService(ConstantValue.GOODS_COLLECT_URL, sendinfo);
		return null;
	}

}
