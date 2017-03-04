package com.agjsj.fleamarket.engine;

import com.agjsj.fleamarket.bean.Torepaly;
import com.agjsj.fleamarket.net.procotal.IMessage;

public interface ToReplayEngine {

	/**
	 * 添加回复
	 * 
	 * @param torepaly
	 * @return
	 */
	public IMessage addToReplay(Torepaly torepaly);

	/**
	 * 获取某条评论的所有回复
	 * 
	 * @param goodsReplayid
	 * @return
	 */
	public IMessage getToReplayOfReplay(Integer goodsReplayid);

	/**
	 * 删除某条回复信息
	 * 
	 * @param toreplayid
	 * @return
	 */
	public IMessage deleteToReplay(Integer toreplayid);
}
