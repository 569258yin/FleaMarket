package com.agjsj.fleamarket.engine;

import com.agjsj.fleamarket.bean.Goods;
import com.agjsj.fleamarket.net.procotal.IMessage;

public interface GoodsEngine {

	/**
	 * 发布商品
	 * 
	 * @param good
	 *            （包含发布者对象和goodsType对象）
	 * @return
	 */
	public IMessage sendGoods(Goods good);

	/**
	 * 分页加载指定用户所发布的商品
	 * 
	 * @param userid
	 * @param start
	 * @param count
	 * @return
	 */
	public IMessage getGoodsOfUser(Integer userid, int start, int count);

	/**
	 * 分页加载所有的商品
	 * 
	 * @param start
	 * @param count
	 * @return
	 */
	public IMessage getAllGoods(int start, int count, int type);

	/**
	 * 更新某件商品
	 * 
	 * @param goods
	 * @return
	 */
	public IMessage updateGoods(Goods goods);

	/**
	 * 删除某条商品信息
	 * 
	 * @param goodsid
	 * @return
	 */
	public IMessage deleteGoods(Integer goodsid);

	/**
	 * 获取某条商品的详细信息
	 * 
	 * @param goodsid
	 * @return
	 */
	public IMessage getGoodsInfo(Integer goodsid);

}
