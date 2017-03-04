package com.agjsj.fleamarket.engine.impl;

import com.agjsj.fleamarket.params.ConstantValue;
import com.agjsj.fleamarket.bean.Foundcase;
import com.agjsj.fleamarket.engine.BaseEngine;
import com.agjsj.fleamarket.engine.FoundEngine;
import com.agjsj.fleamarket.net.procotal.Body;
import com.agjsj.fleamarket.net.procotal.IMessage;

public class FoundEngineImpl extends BaseEngine implements FoundEngine {

	@Override
	public IMessage sendFoundcase(Foundcase foundcase) {

		// 1，分装body，生成json数据
		Body body = new Body();
		body.setOelement(null);
		// body.serializableBody();
		String sendinfo = getMessageToJson(body, "70001");
		// 2.向服务器发送数据,获取返回数据并封装成IMessage对象
//		return sendJsonToService(ConstantValue.FOUND_URL, sendinfo);
		return null;
	}

	@Override
	public IMessage getFoundcase(int start, int count, int type) {

		// 1，分装body，生成json数据
		Body body = new Body();
//		JSONObject jsonObject = new JSONObject();
//		jsonObject.put("fdctag", type);
//		jsonObject.put("start", start);
//		jsonObject.put("count", count);
//		body.setBodyStr(jsonObject.toJSONString());
		// body.serializableBody();
		String sendinfo = getMessageToJson(body, "70002");
		// 2.向服务器发送数据,获取返回数据并封装成IMessage对象
//		return sendJsonToService(ConstantValue.FOUND_URL, sendinfo);
		return null;
	}

	@Override
	public IMessage deleteFoundcase(Integer fdcid) {
		// 1，分装body，生成json数据
		Body body = new Body();
//		JSONObject jsonObject = new JSONObject();
//		jsonObject.put("fdcid", fdcid);
//
//		body.setBodyStr(jsonObject.toJSONString());
		// body.serializableBody();
		String sendinfo = getMessageToJson(body, "70003");
		// 2.向服务器发送数据,获取返回数据并封装成IMessage对象
//		return sendJsonToService(ConstantValue.FOUND_URL, sendinfo);
		return null;
	}

	@Override
	public IMessage getFoundcaseOfuser(Integer userid, int start, int count) {
		// 1，分装body，生成json数据
		Body body = new Body();
//		JSONObject jsonObject = new JSONObject();
//		jsonObject.put("userid", userid);
//		jsonObject.put("start", start);
//		jsonObject.put("count", count);
//		body.setBodyStr(jsonObject.toJSONString());
		// body.serializableBody();
		String sendinfo = getMessageToJson(body, "70004");
		// 2.向服务器发送数据,获取返回数据并封装成IMessage对象
//		return sendJsonToService(ConstantValue.FOUND_URL, sendinfo);
		return null;
	}

}
