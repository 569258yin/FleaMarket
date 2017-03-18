package com.agjsj.fleamarket.engine.impl;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.agjsj.fleamarket.BaseApplication;
import com.agjsj.fleamarket.params.ConstantValue;
import com.agjsj.fleamarket.bean.UserInfo;
import com.agjsj.fleamarket.engine.BaseEngine;
import com.agjsj.fleamarket.engine.UserEngine;
import com.agjsj.fleamarket.net.procotal.Body;
import com.agjsj.fleamarket.net.procotal.IMessage;
import com.agjsj.fleamarket.params.OelementType;
import com.agjsj.fleamarket.util.GsonUtil;
import com.agjsj.fleamarket.util.LogUtil;

import java.util.Date;
import java.util.List;


public class UserEngineImpl extends BaseEngine implements UserEngine {


	@Override
	public boolean login(final UserInfo user) {
		if(user == null){
			return false;
		}
		String json = GsonUtil.objectToString(user);
		Body body = sendJsonToService(json, ConstantValue.TYPE_LOGIN);
		if(body != null){
			if(OelementType.SUCCESS == body.getOelement().getErrorcode()){
				BaseApplication.INSTANCE().setToken(body.getToken());
				String userId = body.getOelement().getErrormsg();
				UserInfo userInfo = getCurrentUser(userId);
				if(userInfo != null){
					BaseApplication.INSTANCE().updateLocalUser(userInfo);
				}
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean checkToken(String token) {
		Body body = sendJsonToService("", ConstantValue.TYPE_CHECK_TOKEN);
		if(body != null){
			if(OelementType.SUCCESS == body.getOelement().getErrorcode()){
				return true;
			}
		}
		return false;
	}

	@Override
	public UserInfo getCurrentUser(String userId) {
		Body body = sendJsonToService(userId, ConstantValue.TYPE_GET_USER);
		if(body.getOelement().getErrorcode() == OelementType.SUCCESS) {
			UserInfo userInfo = (UserInfo) GsonUtil.stringToObjectByBean(body.getElements(),UserInfo.class);
			return userInfo;
		}
		return null;
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
