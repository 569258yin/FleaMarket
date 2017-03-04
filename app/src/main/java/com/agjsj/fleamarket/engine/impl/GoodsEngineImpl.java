package com.agjsj.fleamarket.engine.impl;

import com.agjsj.fleamarket.params.ConstantValue;
import com.agjsj.fleamarket.bean.Goods;
import com.agjsj.fleamarket.engine.BaseEngine;
import com.agjsj.fleamarket.engine.GoodsEngine;
import com.agjsj.fleamarket.net.procotal.Body;
import com.agjsj.fleamarket.net.procotal.IMessage;

public class GoodsEngineImpl extends BaseEngine implements GoodsEngine {

	@Override
	public IMessage sendGoods(Goods good) {
		// 1，分装body，生成json数据
		Body body = new Body();
//		body.setBodyStr(good);
		// body.serializableBody();
		String sendinfo = getMessageToJson(body, "20001");
		// 2.向服务器发送数据,获取返回数据并封装成IMessage对象
	//		return sendJsonToService(ConstantValue.GOODS_URL, sendinfo);
		return null;

	}

	@Override
	public IMessage getGoodsOfUser(Integer userid, int start, int count) {
		// 1，分装body，生成json数据
		Body body = new Body();
//		JSONObject jsonObject = new JSONObject();
//		jsonObject.put("userid", userid);
//		jsonObject.put("start", start);
//		jsonObject.put("count", count);
//		body.setBodyStr(jsonObject.toJSONString());
		// body.serializableBody();
		String sendinfo = getMessageToJson(body, "20002");
		// 2.向服务器发送数据,获取返回数据并封装成IMessage对象
//		return sendJsonToService(ConstantValue.GOODS_URL, sendinfo);
		return null;
	}

	@Override
	public IMessage getAllGoods(int start, int count, int type) {
		// 1，分装body，生成json数据
		Body body = new Body();
//		JSONObject jsonObject = new JSONObject();
//		jsonObject.put("start", start);
//		jsonObject.put("count", count);
//		jsonObject.put("condition", type);
//		body.setBodyStr(jsonObject.toJSONString());
		// body.serializableBody();
		String sendinfo = getMessageToJson(body, "20003");
		// 2.向服务器发送数据,并获取返回的内容（json字符串）
//		return sendJsonToService(ConstantValue.GOODS_URL, sendinfo);
		return null;
	}

	@Override
	public IMessage updateGoods(Goods goods) {
		Body body = new Body();
//		body.setBodyStr(goods);
		String sendinfo = getMessageToJson(body, "20004");
//		return sendJsonToService(ConstantValue.GOODS_URL, sendinfo);
		return null;
	}

	@Override
	public IMessage deleteGoods(Integer goodsid) {

		Body body = new Body();
//		JSONObject jsonObject = new JSONObject();
//		jsonObject.put("goodsid", goodsid);
//		body.setBodyStr(jsonObject.toJSONString());
		String sendinfo = getMessageToJson(body, "20005");
//		return sendJsonToService(ConstantValue.GOODS_URL, sendinfo);
		return null;
	}

	@Override
	public IMessage getGoodsInfo(Integer goodsid) {
		Body body = new Body();
//		JSONObject jsonObject = new JSONObject();
//		jsonObject.put("goodsid", goodsid);
//		body.setBodyStr(jsonObject.toJSONString());
		String sendinfo = getMessageToJson(body, "20006");
//		return sendJsonToService(ConstantValue.GOODS_URL, sendinfo);
		return null;
	}
}
