package com.agjsj.fleamarket.engine;

import com.agjsj.fleamarket.bean.Goodsrepaly;
import com.agjsj.fleamarket.net.procotal.IMessage;

public interface ReplayEngine {

	/**
	 * 发布评论
	 * 
	 * @param userid
	 * @param goodsid
	 * @return
	 */
	public IMessage sendReplay(Goodsrepaly goodsrepaly);

	/**
	 * 获取某件商品的所有评论
	 * 
	 * @param goodsid
	 * @param start
	 * @param count
	 * @return
	 */
	public IMessage getAllReplayOfgoodsid(Integer goodsid, Integer start,
										  Integer count);

}
