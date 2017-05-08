package com.agjsj.fleamarket.net.service;

import com.agjsj.fleamarket.net.procotal.DesMessage;
import com.agjsj.fleamarket.params.ConstantValue;

import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by YH on 2017/4/8.
 */

public interface UserService {

    @POST(ConstantValue.URL_USER_LOGIN)
    Observable<String> login(@Body String message);


    @POST(ConstantValue.URL_CHECK_TOKEN)
    Observable<String> checkToken(@Body String message);


    @POST(ConstantValue.URL_GET_USER)
    Observable<String> getCurrentUser(@Body String message);

    @POST(ConstantValue.URL_SAVE_USER)
    Observable<String> saveCurrentUser(@Body String message);

    @POST(ConstantValue.URL_UPDATE_USER)
    Observable<String> updateCurrentUser(@Body String message);

    @POST(ConstantValue.URL_USER_REGISTER)
    Observable<String> registerAccount(@Body String message);

    @POST(ConstantValue.URL_USER_VIRIFY_PHONE)
    Observable<String> verifyPhone(@Body String message);


}
