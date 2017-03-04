package com.agjsj.fleamarket.engine.impl;

import com.agjsj.fleamarket.params.ConstantValue;
import com.agjsj.fleamarket.engine.BaseEngine;
import com.agjsj.fleamarket.engine.TestEngin;
import com.agjsj.fleamarket.net.procotal.Body;
import com.agjsj.fleamarket.net.procotal.IMessage;
import com.agjsj.fleamarket.util.GsonUtil;

/**
 * Created by MyPC on 2017/1/1.
 */

public class TestEnginImpl extends BaseEngine implements TestEngin {
    @Override
    public void testClientDes(Object o) {
        Body body = new Body();
        body.setOelement(null);
        body.setElements(GsonUtil.objectToString(o));
        String sendinfo = getMessageToJson(body, ConstantValue.TYPE_LOGIN);
        System.out.println(sendinfo);
    }

    @Override
    public void testServerDes(String str) {
        IMessage message = getResult(str);
        System.out.println(message.toString());
    }
}
