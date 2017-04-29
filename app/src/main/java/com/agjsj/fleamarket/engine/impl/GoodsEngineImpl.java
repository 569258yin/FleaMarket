package com.agjsj.fleamarket.engine.impl;

import com.agjsj.fleamarket.bean.Goods;
import com.agjsj.fleamarket.bean.GoodsType;
import com.agjsj.fleamarket.bean.json.PageJsonData;
import com.agjsj.fleamarket.engine.BaseCallBack;
import com.agjsj.fleamarket.engine.BaseEngine;
import com.agjsj.fleamarket.engine.GoodsEngine;
import com.agjsj.fleamarket.net.HttpClient;
import com.agjsj.fleamarket.net.HttpFileReuqest;
import com.agjsj.fleamarket.net.HttpUtils;
import com.agjsj.fleamarket.net.procotal.IMessage;
import com.agjsj.fleamarket.net.service.GoodsService;
import com.agjsj.fleamarket.params.OelementType;
import com.agjsj.fleamarket.util.GsonUtil;
import com.agjsj.fleamarket.util.LogUtil;
import com.google.gson.reflect.TypeToken;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import java.io.File;
import java.util.List;

public class GoodsEngineImpl extends BaseEngine implements GoodsEngine {

	private GoodsService goodsService;

	public GoodsEngineImpl() {
		goodsService = HttpUtils.createService(GoodsService.class);
	}

	@Override
	public void sendGoods(Goods good, final BaseCallBack.SendCallBack callBack) {
		if(good == null){
			callBack.sendResultCallBack(BaseCallBack.SEND_ERROR);
			return;
		}
		String json = GsonUtil.objectToString(good);
		String content = getMessageToJson(json);
		goodsService.sendGoods(content)
				.subscribeOn(Schedulers.io())  //IO线程加载数据
				.observeOn(AndroidSchedulers.mainThread())  //主线程显示数据
				.subscribe(new Subscriber<String>(){

					@Override
					public void onCompleted() {
					}

					@Override
					public void onError(Throwable e) {
						LogUtil.error("Retrofit2:\n"+ e.getMessage());
						callBack.sendResultCallBack(BaseCallBack.SEND_ERROR);
					}
					@Override
					public void onNext(String result) {
						if(result != null){
							IMessage message = getResult(result);
							if(message != null && message.getBody() != null) {
								if (OelementType.SUCCESS == message.getBody().getOelement().getCode()) {
									callBack.sendResultCallBack(BaseCallBack.SEND_OK);

								}
							}
						}
					}
				});

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
	public void getAllGoodsByPage(int start, int count, int type, final BaseCallBack.GetAllListCallBack<Goods> callBack) {
		PageJsonData pageJsonData = new PageJsonData("",start,count,type);
		String json = GsonUtil.objectToString(pageJsonData);
		String content = getMessageToJson(json);
		goodsService.getAllGoodsByPage(content)
				.subscribeOn(Schedulers.io())  //IO线程加载数据
				.observeOn(AndroidSchedulers.mainThread())  //主线程显示数据
				.subscribe(new Subscriber<String>(){
					@Override
					public void onCompleted() {
					}
					@Override
					public void onError(Throwable e) {
						LogUtil.error("Retrofit2:\n"+ e.getMessage());
						callBack.getAllResultCallBack(null);
					}
					@Override
					public void onNext(String result) {
						if(result != null){
							IMessage message = getResult(result);
							if(message != null && message.getBody() != null) {
								if (OelementType.SUCCESS == message.getBody().getOelement().getCode()) {
									List<Goods> list = (List<Goods>) GsonUtil.stringToObjectByType(message.getBody().getElements(),new TypeToken<List<Goods>>(){}.getType());
									callBack.getAllResultCallBack(list);
								}
							}
						}
					}
				});
	}

	@Override
	public void getAllGoodsByGoodsTypeId(int start, int count, int goodsTypeId, final BaseCallBack.GetAllListCallBack<Goods> callBack) {
		PageJsonData pageJsonData = new PageJsonData("",start,count,goodsTypeId);
		String json = GsonUtil.objectToString(pageJsonData);
		String content = getMessageToJson(json);
		goodsService.getAllGoodsByGoodsTypeId(content)
				.subscribeOn(Schedulers.io())  //IO线程加载数据
				.observeOn(AndroidSchedulers.mainThread())  //主线程显示数据
				.subscribe(new Subscriber<String>(){
					@Override
					public void onCompleted() {
					}
					@Override
					public void onError(Throwable e) {
						LogUtil.error("Retrofit2:\n"+ e.getMessage());
						callBack.getAllResultCallBack(null);
					}
					@Override
					public void onNext(String result) {
						if(result != null){
							IMessage message = getResult(result);
							if(message != null && message.getBody() != null) {
								if (OelementType.SUCCESS == message.getBody().getOelement().getCode()) {
									List<Goods> list = (List<Goods>) GsonUtil.stringToObjectByType(message.getBody().getElements(),new TypeToken<List<Goods>>(){}.getType());
									callBack.getAllResultCallBack(list);
								}
							}
						}
					}
				});
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
	public void getAllGoodsType(final BaseCallBack.GetAllListCallBack<GoodsType> callBack) {
		String content = getMessageToJson("");
		goodsService.getAllGoodsType(content)
				.subscribeOn(Schedulers.io())  //IO线程加载数据
				.observeOn(AndroidSchedulers.mainThread())  //主线程显示数据
				.subscribe(new Subscriber<String>(){
					@Override
					public void onCompleted() {
					}
					@Override
					public void onError(Throwable e) {
						LogUtil.error("Retrofit2:\n"+ e.getMessage());
						callBack.getAllResultCallBack(null);
					}
					@Override
					public void onNext(String result) {
						if(result != null){
							IMessage message = getResult(result);
							if(message != null && message.getBody() != null) {
								if (OelementType.SUCCESS == message.getBody().getOelement().getCode()) {
									List<GoodsType> goodstypeList = GsonUtil.getGson().fromJson(message.getBody().getElements(),new TypeToken<List<GoodsType>>(){}.getType());
									callBack.getAllResultCallBack(goodstypeList);
								}
							}
						}
					}
				});
	}

}
