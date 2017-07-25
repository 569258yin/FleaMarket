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
	 * 182.254.240.98:8060
	 * 45.32.86.57
	 * 请求地址FileMaketServer
	 */
	String URL_ROOT = "http://182.254.240.98:8060/FileMaketServer/";
	//=============================================用户相关=========================================================
	/** 用户登录 */
	String URL_USER_LOGIN ="account/loginAccount.action";
	String URL_USER_REGISTER = "account/registerAccount.action";
	String URL_USER_VIRIFY_PHONE = "account/virifyPhone.action";
	/** 检测token是否存在或过期 */
	String URL_CHECK_TOKEN ="account/checkToken.action";
	/** 获取用户信息 */
	String URL_GET_USER ="user/getUserInfo.action";
	String URL_SAVE_USER = "user/saveUserInfo.action";
	String URL_UPDATE_USER = "user/updateUserInfo.action";
	/**=========================================== 商品相关======================================================= */
    String URL_SEND_GOODS = "goods/sendGoods.action";
	String URL_GET_GOODSTYPE = "goods/getGoodsType.action";
	String URL_GET_GOODS_BY_PAGE = "goods/getGoodsByPage.action";
	/** 根据商品类别获取商品 */
	String URL_GET_GOODS_BY_GOODSTYPEID = "goods/getGoodsByGoodsTypeId.action";
	String URL_GET_GOODS_BY_USERID = "goods/getGoodsByUserId.action";
	String URL_GET_GOODS_BY_ID = "goods/getGoodsByGoodsId.action";
	String URL_UPDATE_GOODS = "goods/updateGoods.action";
	String URL_DELETER_GOODS = "goods/deleterGoods.action";
	String URL_REFRESH_GOODS = "goods/refreshGoods.action";
	String URL_SEARCH_GOODS = "goods/searchGoods.action";
	String URL_GET_SEARCH_HOT = "goods/getSearchTop.action";

	/** 发布商品品论 */
	String URL_SEND_GOODS_REPLAY = "goodsReplay/sendReplay.action";
	String URL_GET_GOODS_REPLAY = "goodsReplay/getAllGoodsReplay.action";
	String URL_SEND_GOODS_TO_REPLAY = "goodsReplay/sendToReplay.action";


	//=========================================失物招领=======================================================
	String URL_SEND_FOUNDCASE = "foundCase/sendFoundCase.action";
	String URL_GET_FOUNDCASE = "foundCase/getFoundCaseByType.action";
	String URL_DELETER_FOUNDCASE = "foundCase/getFoundCaseByUserId.action";
	String URL_GET_FOUNDCASE_BY_USERID = "foundCase/getFoundCaseByUserId.action";
	String URL_REFRESH_FOUNDCASE = "foundCase/refreshFoundCase.action";


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

	String TYPE_SEND_FOUNDCASE = "30001";

	String TYPE_GET_FOUNDCASE_BY_TYPE = "30002";
}
