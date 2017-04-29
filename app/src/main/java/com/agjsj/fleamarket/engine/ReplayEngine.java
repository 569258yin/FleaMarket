package com.agjsj.fleamarket.engine;

import com.agjsj.fleamarket.bean.Goodsrepaly;
import com.agjsj.fleamarket.bean.Torepaly;

import java.util.List;

public interface ReplayEngine {


	/**
	 * 发布评论
	 * @return
	 */
	public void sendReplay(final Goodsrepaly goodsrepaly, final BaseCallBack.SendCallBack callBack);

	/**
	 * 获取某件商品的所有评论
	 * @param goodsid
	 * @return
	 */
	public void getAllReplayOfgoodsid(final String goodsid, final BaseCallBack.GetAllListCallBack<Goodsrepaly> callBack);


	/**
	 * 回复评论
	 * @param torepaly
	 * @param callBack
	 */
	public void sendToReplay(final Torepaly torepaly, final BaseCallBack.SendCallBack callBack);

}
