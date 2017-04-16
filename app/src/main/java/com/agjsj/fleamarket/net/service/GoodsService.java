package com.agjsj.fleamarket.net.service;

import com.agjsj.fleamarket.params.ConstantValue;

import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by YH on 2017/4/8.
 */

public interface GoodsService {

    @POST(ConstantValue.URL_SEND_GOODS)
    Observable<String> sendGoods(@Body String content);

    @POST(ConstantValue.URL_GET_GOODS_BY_PAGE)
    Observable<String> getAllGoodsByPage(@Body String content);
    @POST(ConstantValue.URL_GET_GOODS_BY_GOODSTYPEID)
    Observable<String> getAllGoodsByGoodsTypeId(@Body String content);

    @POST()
    Observable<String> uploadImage(@Body String content);
    @POST()
    Observable<String> getGoodsOfUser(@Body String content);
    @POST()
    Observable<String> updateGoods(@Body String content);
    @POST()
    Observable<String> deleteGoods(@Body String content);
    @POST()
    Observable<String> getGoodsInfo(@Body String content);
    @POST(ConstantValue.URL_GET_GOODSTYPE)
    Observable<String> getAllGoodsType(@Body String content);
}
