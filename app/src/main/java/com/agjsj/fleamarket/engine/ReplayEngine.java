package com.agjsj.fleamarket.engine;

import com.agjsj.fleamarket.bean.Goodsrepaly;
import com.agjsj.fleamarket.net.procotal.IMessage;

import java.util.List;

public interface ReplayEngine {

	/**
	 * 发布评论
	 * @return
	 */
	public boolean sendReplay(Goodsrepaly goodsrepaly);

	/**
	 * 获取某件商品的所有评论
	 * @param goodsid
	 * @return
	 */
	public List<Goodsrepaly> getAllReplayOfgoodsid(String goodsid);

}
