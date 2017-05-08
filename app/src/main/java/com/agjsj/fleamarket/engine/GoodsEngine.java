package com.agjsj.fleamarket.engine;

import com.agjsj.fleamarket.bean.Goods;
import com.agjsj.fleamarket.bean.Goodstype;

import java.io.File;
import java.util.List;

public interface GoodsEngine {


	/**
	 * 发布商品
	 * @param good （包含发布者对象和goodsType对象）
	 * @return
	 */
	public void sendGoods(Goods good, final BaseCallBack.SendCallBack callBack);

	/**
	 * 上传图片
	 * @param fileList
	 * @return
	 */
	public String uploadImage(List<File> fileList);


	/**
	 * 加载指定用户所发布的商品
	 * @param userid
	 * @return
	 */
	public void getGoodsOfUser(String  userid, final BaseCallBack.GetAllListCallBack<Goods> callBack);

	/**
	 * 分页加载所有的商品
	 * 
	 * @param start
	 * @param count
	 * @return
	 */
	public void getAllGoodsByPage(int start, int count, int type ,final BaseCallBack.GetAllListCallBack<Goods> callBack);

	/**
	 * 分页加载指定类型所有的商品
	 *
	 * @param start
	 * @param count
	 * @return
	 */
	public void getAllGoodsByGoodsTypeId(int start, int count, int goodsTypeId ,final BaseCallBack.GetAllListCallBack<Goods> callBack);

	/**
	 * 更新某件商品
	 * 
	 * @param goods
	 * @return
	 */
	public void updateGoods(Goods goods, final BaseCallBack.SendCallBack callBack);

	/**
	 * 删除某条商品信息
	 * 
	 * @param goodsid
	 * @return
	 */
	public void deleteGoods(String goodsid, final BaseCallBack.SendCallBack callBack);

	/**
	 * 获取某条商品的详细信息
	 * 
	 * @param goodsid
	 * @return
	 */
	public void getGoodsInfo(String goodsid, final BaseCallBack.GetObjCallBack<Goods> callBack);

	/**
	 * 获取所有产品分类
	 * @return
	 */
	public void getAllGoodsType(final BaseCallBack.GetAllListCallBack<Goodstype> callBack);

	/**
	 * 重新置顶
	 * @param goodsId
	 * @param callBack
	 */
	public void refreshGoods(String goodsId, final BaseCallBack.SendCallBack callBack);

	/**
	 * 搜索
	 * @param text
	 * @param callBack
	 */
	public void searchGoods(String text,final BaseCallBack.GetAllListCallBack<Goods> callBack);


	/**
	 * 获取热搜词汇
	 * @param num
	 * @param callBack
	 */
	public void getSearchHot(int num,final BaseCallBack.GetAllListCallBack<String> callBack);

}
