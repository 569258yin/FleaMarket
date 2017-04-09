package com.agjsj.fleamarket.engine.impl;

import com.agjsj.fleamarket.bean.GoodsType;
import com.agjsj.fleamarket.bean.Goodsrepaly;
import com.agjsj.fleamarket.bean.Torepaly;
import com.agjsj.fleamarket.bean.json.PageJsonData;
import com.agjsj.fleamarket.engine.BaseEngine;
import com.agjsj.fleamarket.engine.ReplayEngine;
import com.agjsj.fleamarket.net.HttpUtils;
import com.agjsj.fleamarket.net.procotal.IMessage;
import com.agjsj.fleamarket.net.service.ReplayService;
import com.agjsj.fleamarket.params.OelementType;
import com.agjsj.fleamarket.util.GsonUtil;
import com.agjsj.fleamarket.util.LogUtil;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ReplayEngineImpl extends BaseEngine implements ReplayEngine {

	private ReplayService replayService;

	public ReplayEngineImpl() {
		this.replayService = HttpUtils.createService(ReplayService.class);
	}

	@Override
	public void sendReplay(Goodsrepaly goodsrepaly, final SendReplayCallBack callBack) {
		String json = GsonUtil.objectToString(goodsrepaly);
		String content = getMessageToJson(json);
		replayService.sendReplay(content)
				.subscribeOn(Schedulers.io())  //IO线程加载数据
				.observeOn(AndroidSchedulers.mainThread())  //主线程显示数据
				.subscribe(new Subscriber<String>(){
					@Override
					public void onCompleted() {
					}
					@Override
					public void onError(Throwable e) {
						LogUtil.error("Retrofit2:\n"+ e.getMessage());
						callBack.sendReplayCallback(SEND_ERROR);
					}
					@Override
					public void onNext(String result) {
						if(result != null){
							IMessage message = getResult(result);
							if(message != null && message.getBody() != null) {
								if (OelementType.SUCCESS == message.getBody().getOelement().getCode()) {
									List<GoodsType> goodstypeList = GsonUtil.getGson().fromJson(message.getBody().getElements(),new TypeToken<List<GoodsType>>(){}.getType());
									callBack.sendReplayCallback(SEND_OK);
								}
							}
						}
					}
				});
	}

	@Override
	public void getAllReplayOfgoodsid(String goodsid, final GetAllReplayCallBack callBack) {
		PageJsonData pageJsonData = new PageJsonData(goodsid);
		String json = GsonUtil.objectToString(pageJsonData);
		String content = getMessageToJson(json);
		replayService.getAllReplayOfgoodsid(content)
				.subscribeOn(Schedulers.io())  //IO线程加载数据
				.observeOn(AndroidSchedulers.mainThread())  //主线程显示数据
				.subscribe(new Subscriber<String>(){
					@Override
					public void onCompleted() {
					}
					@Override
					public void onError(Throwable e) {
						LogUtil.error("Retrofit2:\n"+ e.getMessage());
						callBack.getAllReplayCallback(null);
					}
					@Override
					public void onNext(String result) {
						if(result != null){
							IMessage message = getResult(result);
							if(message != null && message.getBody() != null) {
								if (OelementType.SUCCESS == message.getBody().getOelement().getCode()) {
									List<Goodsrepaly> list = (List<Goodsrepaly>) GsonUtil.stringToObjectByType(message.getBody().getElements(),new TypeToken<List<Goodsrepaly>>(){}.getType());
									callBack.getAllReplayCallback(list);
								}
							}
						}
					}
				});
	}

	@Override
	public void sendToReplay(Torepaly torepaly, final SendReplayCallBack callBack) {
		String json = GsonUtil.objectToString(torepaly);
		String content = getMessageToJson(json);
		replayService.sendToReplay(content)
				.subscribeOn(Schedulers.io())  //IO线程加载数据
				.observeOn(AndroidSchedulers.mainThread())  //主线程显示数据
				.subscribe(new Subscriber<String>(){
					@Override
					public void onCompleted() {
					}
					@Override
					public void onError(Throwable e) {
						LogUtil.error("Retrofit2:\n"+ e.getMessage());
						callBack.sendReplayCallback(SEND_ERROR);
					}
					@Override
					public void onNext(String result) {
						if(result != null){
							IMessage message = getResult(result);
							if(message != null && message.getBody() != null) {
								if (OelementType.SUCCESS == message.getBody().getOelement().getCode()) {
									List<GoodsType> goodstypeList = GsonUtil.getGson().fromJson(message.getBody().getElements(),new TypeToken<List<GoodsType>>(){}.getType());
									callBack.sendReplayCallback(SEND_OK);
								}
							}
						}
					}
				});
	}
}
