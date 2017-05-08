package com.agjsj.fleamarket.engine;

import com.agjsj.fleamarket.bean.FoundCase;
import com.agjsj.fleamarket.net.procotal.IMessage;

public interface FoundEngine {

	/**
	 * 发布失误招领或者寻物启事
	 * 
	 * @param foundcase
	 * @return
	 */
	public void sendFoundcase(FoundCase foundcase, final BaseCallBack.SendCallBack callBack);

	/**
	 * 查询我发布的所有失物招领或者寻物启事
	 * 
	 * @param userid
	 * @return
	 */
	public void getFoundcaseOfuser(String userid,final BaseCallBack.GetAllListCallBack<FoundCase> callBack);

	/**
	 * 分页加载指定类别失物招领信息
	 * @param start
	 * @param count
	 * @param type  0 丢失  1 捡到
	 * @param callBack
	 */
	public void getAllFoundCaseByType(int start,int count,int type,final BaseCallBack.GetAllListCallBack<FoundCase> callBack);

	/**
	 * 删除失误招领或者寻物启事
	 * 
	 * @param fdcid
	 * @return
	 */
	public void deleteFoundcase(String fdcid, final BaseCallBack.SendCallBack callBack);

	/**
	 * 重新置顶
	 * @param fdcid
	 * @param callBack
	 */
	public void refreshFoundCase(String fdcid, final BaseCallBack.SendCallBack callBack);
}
