package com.agjsj.fleamarket.net.service;

import com.agjsj.fleamarket.params.ConstantValue;
import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by MyPC on 2017/4/29.
 */
public interface FoundCaseService {

    @POST(ConstantValue.URL_SEND_FOUNDCASE)
    Observable<String> sendFoundCase(@Body String content);

    @POST(ConstantValue.URL_GET_FOUNDCASE)
    Observable<String> getFoundCaseByType(@Body String content);
}
