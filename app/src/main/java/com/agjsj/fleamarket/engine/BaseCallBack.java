package com.agjsj.fleamarket.engine;

import java.util.List;

/**
 * Created by MyPC on 2017/4/29.
 */
public interface BaseCallBack {

    public static final int SEND_OK = 1;
    public static final int SEND_ERROR = 0;

    public interface SendCallBack{
        public void sendResultCallBack(int responseCode);

    }

    public interface ResultCallBack{
        public void sendResultCallBack(int responseCode,String message);
    }

    public interface GetAllListCallBack<T>{
        public void getAllResultCallBack(List<T> list);
    }

    public interface GetObjCallBack<T>{
        public void getResultCallBack(T obj);
    }

}
