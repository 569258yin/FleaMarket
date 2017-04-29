package com.agjsj.fleamarket.engine;

import android.accounts.Account;
import com.agjsj.fleamarket.bean.UserAccount;
import com.agjsj.fleamarket.bean.UserInfo;

public interface UserEngine {
	/**
	 * 用户登陆
	 * 
	 * @return
	 */
	public void login(UserAccount user, final BaseCallBack.SendCallBack loginCallBack);

	/**
	 * 检测Token是否有效
	 * @param token
	 * @return
     */
	public void checkToken(String token, final BaseCallBack.SendCallBack loginCallBack);

	/**
	 * 根据userid获取用户信息
	 * @param userId
	 */
	public void getCurrentUser(String userId, final BaseCallBack.GetObjCallBack<UserInfo> getUserCallBack);

	/**
	 *
	 * 注册账号
	 * @param account
	 * @param callBack
	 */
	public void register(Account account, final BaseCallBack.SendCallBack callBack);

	/**
	 * 更新个人账号信息
	 * @param userInfo
	 * @param callBack
	 */
	public void updateUserInfo(UserInfo userInfo, final BaseCallBack.SendCallBack callBack);
}
