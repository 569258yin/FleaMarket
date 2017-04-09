package com.agjsj.fleamarket.engine;

import com.agjsj.fleamarket.bean.UserAccount;
import com.agjsj.fleamarket.bean.UserInfo;
import com.agjsj.fleamarket.engine.impl.UserEngineImpl;

public interface UserEngine {



	public interface LoginCallBack {
		public void loginResponse(int responseCode);
	}

	public interface GetUserCallBack {
		public void getUserResponse(UserInfo userInfo);
	}


	public int LOGIN_YES = 1;//登陆成功返回码
	public int LOGIN_NO = 0;//登陆失败返回码


	/**
	 * 用户登陆
	 * 
	 * @return
	 */
	public void login(UserAccount user, final LoginCallBack loginCallBack);

	/**
	 * 检测Token是否有效
	 * @param token
	 * @return
     */
	public void checkToken(String token, final LoginCallBack loginCallBack);

	/**
	 * 根据userid获取用户信息
	 * @param userId
	 */
	public void getCurrentUser(String userId, final GetUserCallBack getUserCallBack);

	public boolean register(UserInfo user);
}
