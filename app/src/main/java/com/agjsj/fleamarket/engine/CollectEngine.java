package com.agjsj.fleamarket.engine;

import com.agjsj.fleamarket.net.procotal.IMessage;

public interface CollectEngine {

	/**
	 * 收藏某商品
	 * 
	 * @param userid
	 * @param goodsid
	 * @return
	 */
	public IMessage addGoodsCollect(Integer userid, Integer goodsid);

	/**
	 * 删除某条收藏
	 * 
	 * @param gcid
	 * @return
	 */
	public IMessage deleteGoodsCollect(Integer gcid);

	/**
	 * 获取我收藏的所有商品
	 * 
	 * @param userid
	 * @return
	 */
	public IMessage getAllGoodsOfCollect(Integer userid);
}
