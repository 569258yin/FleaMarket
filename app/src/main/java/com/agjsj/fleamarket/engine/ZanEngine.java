package com.agjsj.fleamarket.engine;

import com.agjsj.fleamarket.net.procotal.IMessage;

public interface ZanEngine {

	/**
	 * 点赞
	 * 
	 * @param userid
	 * @param goodsid
	 * @return
	 */
	public IMessage zanGoods(Integer userid, Integer goodsid);

	/**
	 * 取消赞
	 * 
	 * @param userid
	 * @param goodsid
	 * @return
	 */
	public IMessage cancelZanGoods(Integer userid, Integer goodsid);
}
