package com.agjsj.fleamarket.net.service;

import com.agjsj.fleamarket.params.ConstantValue;

import com.agjsj.fleamarket.params.GlobalParams;
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
    @POST(ConstantValue.URL_GET_GOODS_BY_USERID)
    Observable<String> getGoodsOfUser(@Body String content);
    @POST(ConstantValue.URL_UPDATE_GOODS)
    Observable<String> updateGoods(@Body String content);
    @POST(ConstantValue.URL_DELETER_GOODS)
    Observable<String> deleteGoods(@Body String content);
    @POST(ConstantValue.URL_GET_GOODS_BY_ID)
    Observable<String> getGoodsInfo(@Body String content);
    @POST(ConstantValue.URL_GET_GOODSTYPE)
    Observable<String> getAllGoodsType(@Body String content);
    @POST(ConstantValue.URL_REFRESH_GOODS)
    Observable<String> refreshGoods(@Body String content);

    @POST(ConstantValue.URL_SEARCH_GOODS)
    Observable<String> searchGoods(@Body String content);
    @POST(ConstantValue.URL_GET_SEARCH_HOT)
    Observable<String> getSearchHot(@Body String content);


}
