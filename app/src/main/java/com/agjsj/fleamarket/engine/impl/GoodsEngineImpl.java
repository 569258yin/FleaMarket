package com.agjsj.fleamarket.engine.impl;

import com.agjsj.fleamarket.bean.Goods;
import com.agjsj.fleamarket.bean.Goodstype;
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
	public void getGoodsOfUser(String  userid, final BaseCallBack.GetAllListCallBack<Goods> callBack) {
		PageJsonData pageJsonData = new PageJsonData(userid);
		String json = GsonUtil.objectToString(pageJsonData);
		String content = getMessageToJson(json);
		goodsService.getGoodsOfUser(content)
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
	public void updateGoods(Goods goods, final BaseCallBack.SendCallBack callBack) {
		if(goods == null){
			callBack.sendResultCallBack(BaseCallBack.SEND_ERROR);
			return;
		}
		String json = GsonUtil.objectToString(goods);
		String content = getMessageToJson(json);
		goodsService.updateGoods(content)
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
	public void deleteGoods(String goodsid,final BaseCallBack.SendCallBack callBack) {
		PageJsonData pageJsonData = new PageJsonData();
		pageJsonData.setId(goodsid);
		String json = GsonUtil.objectToString(pageJsonData);
		String content = getMessageToJson(json);
		goodsService.deleteGoods(content)
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
	public void getGoodsInfo(String goodsid,final BaseCallBack.GetObjCallBack<Goods> callBack) {
		PageJsonData pageJsonData = new PageJsonData(goodsid);
		String json = GsonUtil.objectToString(pageJsonData);
		String content = getMessageToJson(json);
		goodsService.getGoodsInfo(content)
				.subscribeOn(Schedulers.io())  //IO线程加载数据
				.observeOn(AndroidSchedulers.mainThread())  //主线程显示数据
				.subscribe(new Subscriber<String>(){

					@Override
					public void onCompleted() {
					}

					@Override
					public void onError(Throwable e) {
						LogUtil.error("Retrofit2:\n"+ e.getMessage());
						callBack.getResultCallBack(null);
					}
					@Override
					public void onNext(String result) {
						if(result != null){
							IMessage message = getResult(result);
							if(message != null && message.getBody() != null) {
								if (OelementType.SUCCESS == message.getBody().getOelement().getCode()) {
									Goods goods = (Goods) GsonUtil.stringToObjectByBean(message.getBody().getElements(),Goods.class);
									callBack.getResultCallBack(goods);
								}
							}
						}
					}
				});
	}

	@Override
	public void getAllGoodsType(final BaseCallBack.GetAllListCallBack<Goodstype> callBack) {
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
									List<Goodstype> goodstypeList = GsonUtil.getGson().fromJson(message.getBody().getElements(),new TypeToken<List<Goodstype>>(){}.getType());
									callBack.getAllResultCallBack(goodstypeList);
								}
							}
						}
					}
				});
	}

	@Override
	public void refreshGoods(String goodsId, final BaseCallBack.SendCallBack callBack) {
		PageJsonData pageJsonData = new PageJsonData(goodsId);
		String json = GsonUtil.objectToString(pageJsonData);
		String content = getMessageToJson(json);
		goodsService.refreshGoods(content)
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
	public void searchGoods(String text, final BaseCallBack.GetAllListCallBack<Goods> callBack) {
		PageJsonData pageJsonData = new PageJsonData(text);
		String json = GsonUtil.objectToString(pageJsonData);
		String content = getMessageToJson(json);
		goodsService.searchGoods(content)
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
									List<Goods> goodsList = GsonUtil.getGson().fromJson(message.getBody().getElements(),new TypeToken<List<Goods>>(){}.getType());
									callBack.getAllResultCallBack(goodsList);
								}
							}
						}
					}
				});
	}

	@Override
	public void getSearchHot(int num,final BaseCallBack.GetAllListCallBack<String> callBack) {
		PageJsonData pageJsonData = new PageJsonData();
		pageJsonData.setPageSize(num);
		String json = GsonUtil.objectToString(pageJsonData);
		String content = getMessageToJson(json);
		goodsService.getSearchHot(content)
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
									List<String> goodsList = GsonUtil.getGson().fromJson(message.getBody().getElements(),new TypeToken<List<String>>(){}.getType());
									callBack.getAllResultCallBack(goodsList);
								}
							}
						}
					}
				});
	}

}
