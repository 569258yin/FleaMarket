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
//		sendJsonToService(user, ConstantValue.URL_USER_LOGIN, new serviceCallBack() {
//			@Override
//			public void serviceResponse(Body body) {
//				if(ConstantValue.SUCCESS.equals(body.getOelement().getErrorcode())){
//					UserInfo userInfo = (UserInfo) GsonUtil.stringToObjectByBean(body.getElements(),UserInfo.class);
//					if(userInfo !=null){
//						updateCurrentUser(userInfo);
//					}
//				}
//			}
//		});
		Body body = sendJsonToService(user, ConstantValue.TYPE_LOGIN);
		if(body != null){
			if(OelementType.SUCCESS == body.getOelement().getErrorcode()){
//					UserInfo userInfo = (UserInfo) GsonUtil.stringToObjectByBean(body.getElements(),UserInfo.class);
//					if(userInfo !=null){
//						updateCurrentUser(userInfo);
//
//					}
				BaseApplication.INSTANCE().setToken(body.getToken());
				return true;
			}
		}
		return false;
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

	//-------------------------------------获取当前用户------------------------------
	public UserInfo getCurrentUser() {
		SharedPreferences sharedPreferences = BaseApplication.INSTANCE().getSharedPreferences("currentUser", Context.MODE_PRIVATE);
		if (TextUtils.isEmpty(sharedPreferences.getString("id", ""))) {
			return null;
		} else if (new Date().getTime() - sharedPreferences.getLong("lastLoginTime", -1) >= 3600 * 24 * 30) {
			//登录超时，删除本地用户信息
			deleteCurrentUser();
			return null;
		} else {
			//获取用户，并返回
			UserInfo myUser = new UserInfo();
			myUser.setUserid(sharedPreferences.getString("id", ""));
			myUser.setUsersex(sharedPreferences.getInt("userSex", -1));
			myUser.setUseraddress(sharedPreferences.getString("userAddress", ""));
			myUser.setUsername(sharedPreferences.getString("userNickName", ""));
			myUser.setQqnumber(sharedPreferences.getString("qqNumber", ""));
			myUser.setWxnumber(sharedPreferences.getString("wxNumber", ""));
			myUser.setColleage(sharedPreferences.getString("colleage", ""));
			myUser.setSchool(sharedPreferences.getString("school", ""));
			myUser.setUsericon(sharedPreferences.getString("userIcon", ""));
			myUser.setUserphone(sharedPreferences.getString("userPhone", ""));
			return myUser;
		}
	}


	//------------------------------------更新当前用户信息----------------------------
	public void updateCurrentUser(UserInfo myUser) {
		SharedPreferences sharedPreferences = BaseApplication.INSTANCE().getSharedPreferences("currentUser", Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = sharedPreferences.edit();
		editor.putString("id", myUser.getUserid());
		editor.putInt("userSex", myUser.getUsersex());
		editor.putString("userAddress", myUser.getUseraddress());
		editor.putString("userNickName", myUser.getUsername());
		editor.putString("qqNumber", myUser.getQqnumber());
		editor.putString("wxNumber", myUser.getWxnumber());
		editor.putString("colleage", myUser.getColleage());
		editor.putString("school", myUser.getSchool());
		editor.putString("userIcon", myUser.getUsericon());
		editor.putString("userPhone", myUser.getUserphone());
		editor.commit();

	}

	//------------------------------------退出登录--------------------------------
	public void deleteCurrentUser() {
		SharedPreferences sharedPreferences = BaseApplication.INSTANCE().getSharedPreferences("currentUser", Context.MODE_PRIVATE);

		sharedPreferences.edit().clear().commit();
	}

}
