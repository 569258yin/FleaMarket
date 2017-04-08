package com.agjsj.fleamarket.engine.impl;

import com.agjsj.fleamarket.bean.Goods;
import com.agjsj.fleamarket.bean.json.PageJsonData;
import com.agjsj.fleamarket.params.ConstantValue;
import com.agjsj.fleamarket.bean.Goodsrepaly;
import com.agjsj.fleamarket.engine.BaseEngine;
import com.agjsj.fleamarket.engine.ReplayEngine;
import com.agjsj.fleamarket.net.procotal.Body;
import com.agjsj.fleamarket.net.procotal.IMessage;
import com.agjsj.fleamarket.params.OelementType;
import com.agjsj.fleamarket.util.GsonUtil;
import com.google.gson.reflect.TypeToken;

import java.util.List;

public class ReplayEngineImpl extends BaseEngine implements ReplayEngine {


	@Override
	public boolean sendReplay(Goodsrepaly goodsrepaly) {
		if(goodsrepaly == null){
			return  false;
		}
		String json = GsonUtil.objectToString(goodsrepaly);
		Body body = sendJsonToService(json, ConstantValue.TYPE_SEND_GOODS_REPLAY);
		if(body != null){
			if(OelementType.SUCCESS == body.getOelement().getErrorcode()){
				return true;
			}
		}
		return false;
	}

	@Override
	public List<Goodsrepaly> getAllReplayOfgoodsid(String goodsid) {
		PageJsonData pageJsonData = new PageJsonData(goodsid);
		String json = GsonUtil.objectToString(pageJsonData);
		Body body = sendJsonToService(json, ConstantValue.TYPR_GET_GOODS_REPLAY);
		if(body != null){
			if(OelementType.SUCCESS == body.getOelement().getErrorcode()){
				List<Goodsrepaly> list = (List<Goodsrepaly>) GsonUtil.stringToObjectByType(body.getElements(),new TypeToken<List<Goodsrepaly>>(){}.getType());
				return list;
			}
		}
		return null;
	}
}
