package com.agjsj.fleamarket.engine.impl;

import com.agjsj.fleamarket.params.ConstantValue;
import com.agjsj.fleamarket.engine.BaseEngine;
import com.agjsj.fleamarket.engine.ZanEngine;
import com.agjsj.fleamarket.net.procotal.Body;
import com.agjsj.fleamarket.net.procotal.IMessage;

public class ZanEngineImpl extends BaseEngine implements ZanEngine {

	@Override
	public IMessage zanGoods(Integer userid, Integer goodsid) {

		// 1，分装body，生成json数据
		Body body = new Body();
//		JSONObject jsonObject = new JSONObject();
//		jsonObject.put("userid", userid);
//		jsonObject.put("goodsid", goodsid);
//		body.setBodyStr(jsonObject.toJSONString());
		// body.serializableBody();
		String sendinfo = getMessageToJson(body, "30001");
		// 2.向服务器发送数据,获取返回数据并封装成IMessage对象
//		return sendJsonToService(ConstantValue.GOODS_LIKE_URL, sendinfo);
		return null;

	}

	@Override
	public IMessage cancelZanGoods(Integer userid, Integer goodsid) {
		// 1，分装body，生成json数据
		Body body = new Body();
//		JSONObject jsonObject = new JSONObject();
//		jsonObject.put("userid", userid);
//		jsonObject.put("goodsid", goodsid);
//		body.setBodyStr(jsonObject.toJSONString());
//		// body.serializableBody();
		String sendinfo = getMessageToJson(body, "30002");
		// 2.向服务器发送数据,获取返回数据并封装成IMessage对象
//		return sendJsonToService(ConstantValue.GOODS_LIKE_URL, sendinfo);
		return null;
	}

}
