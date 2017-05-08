package com.agjsj.fleamarket.net.service;

import com.agjsj.fleamarket.params.ConstantValue;
import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by YH on 2017/4/29.
 */
public interface FoundCaseService {

    @POST(ConstantValue.URL_SEND_FOUNDCASE)
    Observable<String> sendFoundCase(@Body String content);

    @POST(ConstantValue.URL_GET_FOUNDCASE)
    Observable<String> getFoundCaseByType(@Body String content);

    @POST(ConstantValue.URL_GET_FOUNDCASE_BY_USERID)
    Observable<String> getFoundCaseByUserId(@Body String content);

    @POST(ConstantValue.URL_DELETER_FOUNDCASE)
    Observable<String> deleteFoundCase(@Body String content);

    @POST(ConstantValue.URL_REFRESH_FOUNDCASE)
    Observable<String> refreshFoundCase(@Body String content);
}
