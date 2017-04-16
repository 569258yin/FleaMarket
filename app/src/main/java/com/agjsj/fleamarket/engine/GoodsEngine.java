package com.agjsj.fleamarket.engine;

import com.agjsj.fleamarket.bean.Goods;
import com.agjsj.fleamarket.bean.GoodsType;
import com.agjsj.fleamarket.net.procotal.IMessage;

import java.io.File;
import java.util.List;

public interface GoodsEngine {

	public static final int SEND_OK = 1;
	public static final int SEND_ERROR = 0;

	public interface SendGoodsCallBack {
		public void sendGoodsCallback(int responseCode);
	}

	public interface GetAllGoodsCallBack {
		public void getAllGoodsCallback(List<Goods> goodsList);
	}

	public interface GetAllGoodsTypeCallBack {
		public void getAllGoodsTypeCallback(List<GoodsType> goodsTypeList);
	}


	/**
	 * 发布商品
	 * @param good （包含发布者对象和goodsType对象）
	 * @return
	 */
	public void sendGoods(Goods good, final SendGoodsCallBack callBack);

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
	public void getAllGoodsByPage(int start, int count, int type ,final GetAllGoodsCallBack callBack);

	/**
	 * 分页加载指定类型所有的商品
	 *
	 * @param start
	 * @param count
	 * @return
	 */
	public void getAllGoodsByGoodsTypeId(int start, int count, int goodsTypeId ,final GetAllGoodsCallBack callBack);

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
	public void getAllGoodsType(final GetAllGoodsTypeCallBack callBack);

}
