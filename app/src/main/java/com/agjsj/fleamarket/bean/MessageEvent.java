package com.agjsj.fleamarket.bean;

/**
 * EventBus 传递事件载体
 * Created by MyPC on 2017/3/19.
 */

public class MessageEvent {

    public final int eventCode;

    public MessageEvent(int eventCode) {
        this.eventCode = eventCode;
    }
}
