package com.agjsj.fleamarket.params;

public class OelementType {

	/** 请求成功 */
	public static final int SUCCESS = 1;
	/** 服务器数据安全性校验失败  */
	public static final int FAILD = -1;
	/** TOKEN无效 */
	public static final int TOKEN_FAILD = 2;
	/** 网络错误，请检查网络 */
	public static final int NET_ERROR = -2;
	/** 本地检查服务器数据失败 */
	public static final int LOCAL_CHECK_MD5_ERROR = -3;
	/** 开启progressBar  结束 */
	public static final int PROGRESS_START = 101;
	/** 关闭progressbar 结束 */
	public static final int PROGRESS_END = 102;
}
