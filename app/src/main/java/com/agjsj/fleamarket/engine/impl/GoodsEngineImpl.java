package com.agjsj.fleamarket.engine.impl;

import com.agjsj.fleamarket.view.base.BaseApplication;
import com.agjsj.fleamarket.bean.GoodsType;
import com.agjsj.fleamarket.net.HttpClient;
import com.agjsj.fleamarket.net.HttpFileReuqest;
import com.agjsj.fleamarket.params.ConstantValue;
import com.agjsj.fleamarket.bean.Goods;
import com.agjsj.fleamarket.engine.BaseEngine;
import com.agjsj.fleamarket.engine.GoodsEngine;
import com.agjsj.fleamarket.net.procotal.Body;
import com.agjsj.fleamarket.net.procotal.IMessage;
import com.agjsj.fleamarket.params.OelementType;
import com.agjsj.fleamarket.util.GsonUtil;
import com.google.gson.reflect.TypeToken;

import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.util.List;

public class GoodsEngineImpl extends BaseEngine implements GoodsEngine {


	@Override
	public boolean sendGoods(Goods good) {
		if(good == null){
			return false;
		}
		String json = GsonUtil.objectToString(good);
		Body body = sendJsonToService(json, ConstantValue.TYPE_SEND_GOODS);
		if(body != null){
			if(OelementType.SUCCESS == body.getOelement().getErrorcode()){
				BaseApplication.INSTANCE().setToken(body.getToken());
				return true;
			}
		}
		return false;
	}

	@Override
	public String uploadImage(List<File> fileList) {
		if(fileList == null || fileList.size() <= 0){
			return null;
		}
		return HttpClient.getInstance().submitRequestBlock(new HttpFileReuqest(fileList));
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

	@Override
	public List<GoodsType> getAllGoodsType() {
		Body body = sendJsonToService("", ConstantValue.TYPE_GET_GOODSTYPE);
		if(body != null){
			if(OelementType.SUCCESS == body.getOelement().getErrorcode()){
				String resultJson = body.getElements();
				if(StringUtils.isNotEmpty(resultJson)){
					List<GoodsType> goodstypeList = GsonUtil.getGson().fromJson(resultJson,new TypeToken<List<GoodsType>>(){}.getType());
					return goodstypeList;
				}
			}
		}
		return null;
	}
}
