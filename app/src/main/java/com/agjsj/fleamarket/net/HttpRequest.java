package com.agjsj.fleamarket.net;

import com.agjsj.fleamarket.util.HttpConnectionUtils;

import java.util.concurrent.Callable;

/**
 * Created by MyPC on 2017/2/26.
 */

public class HttpRequest implements Callable<String>{


    private String requestContext;

    private String url;

    private int timeout = 3000;

    public HttpRequest(String requestContext, String url) {
        this.requestContext = requestContext;
        this.url = url;
    }


    @Override
    public String call() throws Exception {
        return HttpConnectionUtils.sendJsonString(requestContext,url);
    }
}
