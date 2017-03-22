package com.agjsj.fleamarket.engine;

import com.agjsj.fleamarket.bean.Goods;
import com.agjsj.fleamarket.bean.GoodsType;
import com.agjsj.fleamarket.net.procotal.IMessage;

import java.io.File;
import java.util.List;

public interface GoodsEngine {

	/**
	 * 发布商品
	 * @param good （包含发布者对象和goodsType对象）
	 * @return
	 */
	public boolean sendGoods(Goods good);

	/**
	 * 上传图片
	 * @param fileList
	 * @return
	 */
	public String uploadImage(List<File> fileList);


	/**
	 * 分页加载指定用户所发布的商品
	 * 
	 * @param userid
	 * @param start
	 * @param count
	 * @return
	 */
	public List<Goods> getGoodsOfUser(String  userid, int start, int count);

	/**
	 * 分页加载所有的商品
	 * 
	 * @param start
	 * @param count
	 * @return
	 */
	public List<Goods> getAllGoodsByPage(int start, int count, int type);

	/**
	 * 更新某件商品
	 * 
	 * @param goods
	 * @return
	 */
	public boolean updateGoods(Goods goods);

	/**
	 * 删除某条商品信息
	 * 
	 * @param goodsid
	 * @return
	 */
	public boolean deleteGoods(Integer goodsid);

	/**
	 * 获取某条商品的详细信息
	 * 
	 * @param goodsid
	 * @return
	 */
	public Goods getGoodsInfo(String goodsid);

	/**
	 * 获取所有产品分类
	 * @return
	 */
	public List<GoodsType> getAllGoodsType();

}
