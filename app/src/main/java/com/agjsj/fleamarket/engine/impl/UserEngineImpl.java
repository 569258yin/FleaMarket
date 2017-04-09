package com.agjsj.fleamarket.engine.impl;

import com.agjsj.fleamarket.net.procotal.DesMessage;
import com.agjsj.fleamarket.net.procotal.IMessage;
import com.agjsj.fleamarket.net.service.UserService;
import com.agjsj.fleamarket.net.HttpUtils;
import com.agjsj.fleamarket.util.LogUtil;
import com.agjsj.fleamarket.view.base.BaseApplication;
import com.agjsj.fleamarket.bean.GoodsType;
import com.agjsj.fleamarket.bean.UserAccount;
import com.agjsj.fleamarket.bean.UserInfo;
import com.agjsj.fleamarket.engine.BaseEngine;
import com.agjsj.fleamarket.engine.UserEngine;
import com.agjsj.fleamarket.net.procotal.Body;
import com.agjsj.fleamarket.params.ConstantValue;
import com.agjsj.fleamarket.params.OelementType;
import com.agjsj.fleamarket.util.GsonUtil;
import com.google.gson.reflect.TypeToken;

import org.apache.commons.lang3.StringUtils;

import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class UserEngineImpl extends BaseEngine implements UserEngine {

	private UserService userService;

	public UserEngineImpl() {
		userService = HttpUtils.createService(UserService.class);
	}

	@Override
	public void login(final UserAccount user, final LoginCallBack loginCallBack) {
		if(user == null){
			loginCallBack.loginResponse(LOGIN_NO);
			return;
		}
		String json = GsonUtil.objectToString(user);
		String content = getMessageToJson(json);
		userService.login(content)
				.subscribeOn(Schedulers.io())  //IO线程加载数据
				.observeOn(AndroidSchedulers.mainThread())  //主线程显示数据
				.subscribe(new Subscriber<String>(){

					@Override
					public void onCompleted() {
					}

					@Override
					public void onError(Throwable e) {
						LogUtil.error("Retrofit2:\n"+ e.getMessage());
						loginCallBack.loginResponse(LOGIN_NO);
					}
					@Override
					public void onNext(String result) {
						if(result != null){
							IMessage message = getResult(result);
							if(message != null && message.getBody() != null) {
								if (OelementType.SUCCESS == message.getBody().getOelement().getCode()) {
									BaseApplication.INSTANCE().setToken(message.getBody().getToken());
									String userId = message.getBody().getOelement().getMessage();
									getCurrentUser(userId,new GetUserCallBack(){
										@Override
										public void getUserResponse(UserInfo userInfo) {
											if (userInfo != null) {
												BaseApplication.INSTANCE().updateLocalUser(userInfo);
											}
										}
									});
									loginCallBack.loginResponse(LOGIN_YES);
								}
							}
						}
					}
				});
	}

	@Override
	public void checkToken(String token, final LoginCallBack loginCallBack) {
		String content = getMessageToJson("");
		userService.checkToken(content)
				.subscribeOn(Schedulers.io())  //IO线程加载数据
				.observeOn(AndroidSchedulers.mainThread())  //主线程显示数据
				.subscribe(new Subscriber<String>(){

					@Override
					public void onCompleted() {
					}

					@Override
					public void onError(Throwable e) {
						e.printStackTrace();
						LogUtil.error("Retrofit2:\n"+ e.getMessage());
						loginCallBack.loginResponse(LOGIN_NO);
					}

					@Override
					public void onNext(String result) {
						if(result != null){
							IMessage message = getResult(result);
							if(message != null && message.getBody() != null) {
								if (OelementType.SUCCESS == message.getBody().getOelement().getCode()) {
									String jsonStr = message.getBody().getElements();
									if(StringUtils.isNotEmpty(jsonStr)){
										List<GoodsType> goodstypeList = (List<GoodsType>) GsonUtil.stringToObjectByType(jsonStr,new TypeToken<List<GoodsType>>(){}.getType());
										if(goodstypeList != null){
											BaseApplication.INSTANCE().setGoodstypes(goodstypeList);
										}
									}
									loginCallBack.loginResponse(LOGIN_YES);
								}
							}
						}
					}
				});
	}

	@Override
	public void getCurrentUser(String userId, final GetUserCallBack getUserCallBack) {
		String content = getMessageToJson(userId);
		userService.getCurrentUser(content)
				.subscribeOn(Schedulers.io())  //IO线程加载数据
				.observeOn(AndroidSchedulers.mainThread())  //主线程显示数据
				.subscribe(new Subscriber<String>(){

					@Override
					public void onCompleted() {
					}

					@Override
					public void onError(Throwable e) {
						LogUtil.error("Retrofit2:\n"+ e.getMessage());
						getUserCallBack.getUserResponse(null);
					}

					@Override
					public void onNext(String result) {
						if(result != null){
							IMessage message = getResult(result);
							if(message != null && message.getBody() != null) {
								if (OelementType.SUCCESS == message.getBody().getOelement().getCode()) {
									UserInfo userInfo = (UserInfo) GsonUtil.stringToObjectByBean(message.getBody().getElements(),UserInfo.class);
									getUserCallBack.getUserResponse(userInfo);
								}
							}
						}
					}
				});
	}

	@Override
	public boolean register(UserInfo user) {
		Body body = new Body();
		body.setOelement(null);
		body.setElements(GsonUtil.objectToString(user));
		// body.serializableBody();
		// 获取发送的数据
		String sendinfo = getMessageToJson(body, ConstantValue.TYPE_REGISTER);
		// 发送数据并获取服务器返回的数据
		return false;
	}

}
