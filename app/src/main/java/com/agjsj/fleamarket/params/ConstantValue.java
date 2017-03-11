package com.agjsj.fleamarket.params;

public interface ConstantValue {
	/**
	 * 编码格式
	 */
	String ENCODING = "UTF-8";
	/**
	 * 代理商ID
	 */
	String AGENTERID = "889931";
	/**
	 * 信息来源
	 */
	String SOURCE = "ivr";
	/**
	 * body的加密算法
	 */
	String COMPRESS = "DES";

	/**
	 * 代理商密码
	 */
	String AGENTER_PASSWORD = "dsf145sd4f89g2154g24s8ge54";

	/**
	 * 加密秘钥
	 */
	String DES_PASSWORD = "4dsa2fre81v1nhyi";


	/**
	 * URL
	 */
	int VIEW_HALL = 0000;
	int VIEW_SECOND = 0002;
	int VIEW_FIRST = 0001;
	int VIEW_SEARCH = 1000;

	/**
	 * 请求地址
	 */
	String URL_ROOT = "http://192.168.5.6:8080/FileMaketServer/";
	/** 用户登录 */
	String URL_USER_LOGIN = "account/login.action";
	/** 检测token是否存在或过期 */
	String URL_CHECK_TOKEN = "account/checkToken.action";
	String URL_USER_REGISTER = "account/register.action";
	String GOODS_URL = "http://192.168.67.107:8080/SHService";
	String GOODS_LIKE_URL = "http://192.168.67.107:8080/SHService";
	String GOODS_REPLAY_URL = "http://192.168.67.107:8080/SHService";
	String GOODS_COLLECT_URL = "http://192.168.67.107:8080/SHService";
	String GOODS_TOREPLAY_URL = "http://192.168.67.107:8080/SHService";
	String FOUND_URL = "http://192.168.67.107:8080/SHService";
	/**
	 * 请求码
	 */

	String TYPE_REGISTER = "10001";
	String TYPE_LOGIN = "10003";
	String TYPE_CHECK_TOKEN = "10002";

}
