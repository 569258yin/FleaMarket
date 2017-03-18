package com.agjsj.fleamarket.engine;

import com.agjsj.fleamarket.bean.UserInfo;
import com.agjsj.fleamarket.net.procotal.IMessage;

import java.util.List;

public interface UserEngine {

	public boolean register(UserInfo user);

	/**
	 * 用户登陆
	 * 
	 * @return
	 */
	public boolean login(UserInfo user);

	/**
	 * 检测Token是否有效
	 * @param token
	 * @return
     */
	public boolean checkToken(String token);

	/**
	 * 根据userid获取用户信息
	 * @param userId
	 */
	public UserInfo getCurrentUser(String userId);
}
