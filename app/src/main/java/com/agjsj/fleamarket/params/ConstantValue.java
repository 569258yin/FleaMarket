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

	/** 按时间排序 */
	public final static int SELECT_GOODS_BY_TIME = 1;
	/** 按距离排序　*/
	public final static int SELECT_GOODS_BY_ADDRESS = 2;




	/**
	 * 请求地址FileMaketServer
	 */
	String URL_ROOT = "http://192.168.5.8:8080/FileMaketServer/";
	/** 用户登录 */
	String URL_USER_LOGIN ="account/loginAccount.action";
	/** 检测token是否存在或过期 */
	String URL_CHECK_TOKEN ="account/checkToken.action";
	/** 获取用户信息 */
	String URL_GET_USER ="user/getUserInfo.action";
	String URL_USER_REGISTER = "account/register.action";
	/** 商品相关 */
    String URL_SEND_GOODS = "goods/sendGoods.action";
	String URL_GET_GOODSTYPE = "goods/getGoodsType.action";
	String URL_GET_GOODS_BY_PAGE = "goods/getGoodsByPage.action";
	/** 根据商品类别获取商品 */
	String URL_GET_GOODS_BY_GOODSTYPEID = "goods/getGoodsByGoodsTypeId.action";
	/** 发布商品品论 */
	String URL_SEND_GOODS_REPLAY = "goodsReplay/sendReplay.action";
	String URL_GET_GOODS_REPLAY = "goodsReplay/getAllGoodsReplay.action";
	String URL_SEND_GOODS_TO_REPLAY = "goodsReplay/sendToReplay.action";

	String GOODS_URL = "http://192.168.67.107:8080/SHService";
	String GOODS_LIKE_URL = "http://192.168.67.107:8080/SHService";
	String GOODS_REPLAY_URL = "http://192.168.67.107:8080/SHService";
	String GOODS_COLLECT_URL = "http://192.168.67.107:8080/SHService";
	String GOODS_TOREPLAY_URL = "http://192.168.67.107:8080/SHService";
	String FOUND_URL = "http://192.168.67.107:8080/SHService";
	/** 上传文件图片 */
	String IMAGE_UPLOAD = "file/imgUpload.action";
	/**
	 * 请求码
	 */
	/** 注册 */
	String TYPE_REGISTER = "10001";
	/** 登录 */
	String TYPE_LOGIN = "10003";
	/** 获取用户信息 */
	String TYPE_GET_USER = "10004";
	/** 校验token */
	String TYPE_CHECK_TOKEN = "10002";
	/** 发布宝贝 */
	String TYPE_SEND_GOODS = "20001";
	/** 上传图片 */
	String TYPE_UPLOAD_IMAGE = "20002";
	/** 获取商品类别 */
	String TYPE_GET_GOODSTYPE = "20003";
	/** 分页获取所有商品 */
	String TYPE_GET_GOODS_BY_PAGE = "20004";

	String TYPE_SEND_GOODS_REPLAY = "20005";

	String TYPR_GET_GOODS_REPLAY = "20006";

}
