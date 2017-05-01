package com.agjsj.fleamarket.engine.impl;

import com.agjsj.fleamarket.bean.FoundCase;
import com.agjsj.fleamarket.bean.GoodsType;
import com.agjsj.fleamarket.bean.json.PageJsonData;
import com.agjsj.fleamarket.engine.BaseCallBack;
import com.agjsj.fleamarket.engine.BaseEngine;
import com.agjsj.fleamarket.engine.FoundEngine;
import com.agjsj.fleamarket.net.HttpUtils;
import com.agjsj.fleamarket.net.procotal.IMessage;
import com.agjsj.fleamarket.net.service.FoundCaseService;
import com.agjsj.fleamarket.params.OelementType;
import com.agjsj.fleamarket.util.GsonUtil;
import com.agjsj.fleamarket.util.LogUtil;
import com.google.gson.reflect.TypeToken;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import java.util.List;

public class FoundEngineImpl extends BaseEngine implements FoundEngine {

	private FoundCaseService foundCaseService;

	public FoundEngineImpl() {
		this.foundCaseService = HttpUtils.createService(FoundCaseService.class);
	}

	@Override
	public void sendFoundcase(FoundCase foundcase, final BaseCallBack.SendCallBack callBack) {
		String json = GsonUtil.objectToString(foundcase);
		String content = getMessageToJson(json);
		foundCaseService.sendFoundCase(content)
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
	public void getFoundcaseOfuser(String userid, BaseCallBack.GetAllListCallBack<FoundCase> callBack) {
		PageJsonData pageJsonData = new PageJsonData(userid);
		String json = GsonUtil.objectToString(pageJsonData);
		String content = getMessageToJson(json);
	}

	@Override
	public void getAllFoundCaseByType(int start, int count, int type,final BaseCallBack.GetAllListCallBack<FoundCase> callBack) {
		PageJsonData pageJsonData = new PageJsonData("",start,count,type);
		String json = GsonUtil.objectToString(pageJsonData);
		String content = getMessageToJson(json);
		foundCaseService.getFoundCaseByType(content)
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
									List<FoundCase> list = (List<FoundCase>) GsonUtil.stringToObjectByType(message.getBody().getElements(),new TypeToken<List<FoundCase>>(){}.getType());
									callBack.getAllResultCallBack(list);
								}
							}
						}
					}
				});
	}

	@Override
	public void deleteFoundcase(String fdcid) {

	}
}
