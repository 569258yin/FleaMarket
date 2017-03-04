package com.agjsj.fleamarket.net;

import com.orhanobut.logger.Logger;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 一次只能执行一个任务的 future - worker模式
 */

public class HttpClient {

    private ExecutorService queueManager = Executors.newSingleThreadExecutor();


    private static HttpClient instance = new HttpClient();

    private HttpClient() {

    }

    public static HttpClient getInstance() {
        return instance;
    }


    // 将请求放入队列
    public void submitRequest(String jsonStr,String url) {
        queueManager.submit(new HttpRequest(jsonStr,url));
    }

    // 将请求放入队列，block直到处理完
    public String submitRequestBlock(String jsonStr,String url) {
        try {
            return queueManager.submit(new HttpRequest(jsonStr,url)).get();
        } catch (InterruptedException | ExecutionException e) {
            Logger.e("Exception!!", e);
        }
        return null;
    }

}
