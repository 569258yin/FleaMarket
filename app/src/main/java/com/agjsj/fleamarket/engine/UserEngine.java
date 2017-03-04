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

}
