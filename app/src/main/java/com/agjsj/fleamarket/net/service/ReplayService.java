package com.agjsj.fleamarket.net.service;

import com.agjsj.fleamarket.params.ConstantValue;

import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by YH on 2017/4/8.
 */

public interface ReplayService {
    @POST(ConstantValue.URL_SEND_GOODS_REPLAY)
    Observable<String> sendReplay(@Body String content);

    @POST(ConstantValue.URL_GET_GOODS_REPLAY)
    Observable<String> getAllReplayOfgoodsid(@Body String content);

    @POST(ConstantValue.URL_SEND_GOODS_TO_REPLAY)
    Observable<String> sendToReplay(@Body String content);

}
