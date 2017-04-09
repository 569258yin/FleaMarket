package com.agjsj.fleamarket.net;

/**
 * Created by HengXing on 2017/2/11.
 */

import com.agjsj.fleamarket.params.ConstantValue;
import com.agjsj.fleamarket.util.NetWorkUtils;
import com.agjsj.fleamarket.view.base.BaseApplication;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * 作者：哇牛Aaron
 * 作者简书文章地址: http://www.jianshu.com/users/07a8b5386866/latest_articles
 * 时间: 2016/11/28
 * 功能描述:
 */
public class HttpUtils {
    private Retrofit retrofit;
    private static final int NO_NET_MAX = 60 * 60 * 24 * 7; //7天 无网超时时间
    private static final int NET_MAX = 30; //30秒  有网超时时间
    private static final int TIME_OUT = 15; //http 超时时间15s

    private HttpUtils() {
        Interceptor mInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                if (!NetWorkUtils.networkIsAvailable(BaseApplication.INSTANCE())) {
                    request = request.newBuilder()
                            .removeHeader("Pragma")
                            .header("Cache-Control", "private, only-if-cached, max-stale=" + NO_NET_MAX)
                            .build();
                } else {
                    request = request.newBuilder()
                            //Pragma:no-cache。在HTTP/1.1协议中，它的含义和Cache-Control:no-cache相同。为了确保缓存生效
                            .removeHeader("Pragma")
                            .header("Cache-Control", "private, max-age=" + NET_MAX)//添加缓存请求头
                            .header("Content-Type","application/json;UTF-8")
                            .build();
                }

                return chain.proceed(request);
            }
        };
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient mClient = new OkHttpClient.Builder()
                .addNetworkInterceptor(mInterceptor)
                .addInterceptor(httpLoggingInterceptor)
                .cache(new Cache(new File(BaseApplication.INSTANCE().getCacheDir() + "http")
                        , 1024 * 1024 * 10))
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                .build();
        retrofit = new Retrofit.Builder()
                .baseUrl(ConstantValue.URL_ROOT)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(mClient)
                .build();
    }

    //单例设计模式
//    private static class singRetrofit {
//        private static final HttpUtils instance = new HttpUtils();
//    }

    private static HttpUtils instance;


    public static HttpUtils getInstance() {
        if (instance == null) {
            HttpUtils httpUtils = null;
            synchronized (HttpUtils.class) {
                if(httpUtils == null){
                    httpUtils = new HttpUtils();
                }
                instance = httpUtils;
            }
        }
        return instance;
    }

    public static <T> T createService(Class<T> t) {
        //GetWeatherService getWeatherService = getInstance().retrofit.create(GetWeatherService.class);
        return getInstance().retrofit.create(t);
    }


}
