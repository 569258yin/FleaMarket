package com.agjsj.fleamarket.engine;

import com.agjsj.fleamarket.bean.Goodsrepaly;
import com.agjsj.fleamarket.bean.Torepaly;

import java.util.List;

public interface ReplayEngine {

	public static final int SEND_OK = 1;
	public static final int SEND_ERROR = 0;

	public interface SendReplayCallBack {
		public void sendReplayCallback(int responseCode);
	}

	public interface GetAllReplayCallBack {
		public void getAllReplayCallback(List<Goodsrepaly> lists);
	}

	/**
	 * 发布评论
	 * @return
	 */
	public void sendReplay(final Goodsrepaly goodsrepaly, final SendReplayCallBack callBack);

	/**
	 * 获取某件商品的所有评论
	 * @param goodsid
	 * @return
	 */
	public void getAllReplayOfgoodsid(final String goodsid, final GetAllReplayCallBack callBack);


	/**
	 * 回复评论
	 * @param torepaly
	 * @param callBack
	 */
	public void sendToReplay(final Torepaly torepaly, final SendReplayCallBack callBack);

}
