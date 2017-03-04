package com.agjsj.fleamarket.engine;

import com.agjsj.fleamarket.bean.Foundcase;
import com.agjsj.fleamarket.net.procotal.IMessage;

public interface FoundEngine {

	/**
	 * 发布失误招领或者寻物启事
	 * 
	 * @param foundcase
	 * @return
	 */
	public IMessage sendFoundcase(Foundcase foundcase);

	/**
	 * 查询我发布的所有失物招领或者寻物启事
	 * 
	 * @param userid
	 * @param start
	 * @param count
	 * @return
	 */
	public IMessage getFoundcaseOfuser(Integer userid, int start, int count);

	/**
	 * 分页加载失误招领信息和寻物启事信息
	 * 
	 * @param start
	 * @param count
	 * @param type
	 * @return
	 */
	public IMessage getFoundcase(int start, int count, int type);

	/**
	 * 删除失误招领或者寻物启事
	 * 
	 * @param fdcid
	 * @return
	 */
	public IMessage deleteFoundcase(Integer fdcid);
}
