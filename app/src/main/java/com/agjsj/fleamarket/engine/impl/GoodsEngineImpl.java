package com.agjsj.fleamarket.engine.impl;

import com.agjsj.fleamarket.bean.json.PageJsonData;
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
	public List<Goods> getGoodsOfUser(String userid, int start, int count) {
		return null;
	}

	@Override
	public List<Goods> getAllGoodsByPage(int start, int count, int type) {
		PageJsonData pageJsonData = new PageJsonData("",start,count,type);
		String json = GsonUtil.objectToString(pageJsonData);
		Body body = sendJsonToService(json, ConstantValue.TYPE_GET_GOODS_BY_PAGE);
		if(body != null){
			if(OelementType.SUCCESS == body.getOelement().getErrorcode()){
				List<Goods> list = (List<Goods>) GsonUtil.stringToObjectByType(body.getElements(),new TypeToken<List<Goods>>(){}.getType());
				return list;
			}
		}
		return null;
	}

	@Override
	public boolean updateGoods(Goods goods) {
		return false;
	}

	@Override
	public boolean deleteGoods(Integer goodsid) {
		return false;
	}

	@Override
	public Goods getGoodsInfo(String goodsid) {
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
