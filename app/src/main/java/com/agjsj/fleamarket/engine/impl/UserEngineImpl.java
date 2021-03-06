package com.agjsj.fleamarket.engine.impl;

import android.accounts.Account;
import com.agjsj.fleamarket.bean.json.PageJsonData;
import com.agjsj.fleamarket.engine.BaseCallBack;
import com.agjsj.fleamarket.net.procotal.IMessage;
import com.agjsj.fleamarket.net.service.UserService;
import com.agjsj.fleamarket.net.HttpUtils;
import com.agjsj.fleamarket.util.LogUtil;
import com.agjsj.fleamarket.view.base.BaseApplication;
import com.agjsj.fleamarket.bean.Goodstype;
import com.agjsj.fleamarket.bean.UserAccount;
import com.agjsj.fleamarket.bean.UserInfo;
import com.agjsj.fleamarket.engine.BaseEngine;
import com.agjsj.fleamarket.engine.UserEngine;
import com.agjsj.fleamarket.params.OelementType;
import com.agjsj.fleamarket.util.GsonUtil;
import com.google.gson.reflect.TypeToken;

import org.apache.commons.lang3.StringUtils;

import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class UserEngineImpl extends BaseEngine implements UserEngine {

    private UserService userService;

    public UserEngineImpl() {
        userService = HttpUtils.createService(UserService.class);
    }

    @Override
    public void login(final UserAccount user, final BaseCallBack.SendCallBack loginCallBack) {
        if (user == null) {
            loginCallBack.sendResultCallBack(BaseCallBack.SEND_ERROR);
            return;
        }
        String json = GsonUtil.objectToString(user);
        String content = getMessageToJson(json);
        userService.login(content)
                .subscribeOn(Schedulers.io())  //IO线程加载数据
                .observeOn(AndroidSchedulers.mainThread())  //主线程显示数据
                .subscribe(new Subscriber<String>() {

                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtil.error("Retrofit2:\n" + e.getMessage());
                        loginCallBack.sendResultCallBack(BaseCallBack.SEND_ERROR);
                    }

                    @Override
                    public void onNext(String result) {
                        if (result != null) {
                            IMessage message = getResult(result);
                            if (message != null && message.getBody() != null) {
                                if (OelementType.SUCCESS == message.getBody().getOelement().getCode()) {
                                    BaseApplication.INSTANCE().setToken(message.getBody().getToken());
                                    String userId = message.getBody().getOelement().getMessage();
                                    getCurrentUser(userId, new BaseCallBack.GetObjCallBack<UserInfo>() {
                                        @Override
                                        public void getResultCallBack(UserInfo userInfo) {
                                            if (userInfo != null) {
                                                BaseApplication.INSTANCE().updateLocalUser(userInfo);
                                                loginCallBack.sendResultCallBack(BaseCallBack.SEND_OK);
                                            }
                                        }
                                    });
                                }
                            }
                        }
                    }
                });
    }

    @Override
    public void checkToken(String token, final BaseCallBack.SendCallBack loginCallBack) {
        String content = getMessageToJson("");
        userService.checkToken(content)
                .subscribeOn(Schedulers.io())  //IO线程加载数据
                .observeOn(AndroidSchedulers.mainThread())  //主线程显示数据
                .subscribe(new Subscriber<String>() {

                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        LogUtil.error("Retrofit2:\n" + e.getMessage());
                        loginCallBack.sendResultCallBack(BaseCallBack.SEND_ERROR);
                    }

                    @Override
                    public void onNext(String result) {
                        if (result != null) {
                            IMessage message = getResult(result);
                            if (message != null && message.getBody() != null) {
                                if (OelementType.SUCCESS == message.getBody().getOelement().getCode()) {
                                    String jsonStr = message.getBody().getElements();
                                    if (StringUtils.isNotEmpty(jsonStr)) {
                                        List<Goodstype> goodstypeList = (List<Goodstype>) GsonUtil.stringToObjectByType(jsonStr, new TypeToken<List<Goodstype>>() {
                                        }.getType());
                                        if (goodstypeList != null) {
                                            BaseApplication.INSTANCE().setGoodstypes(goodstypeList);
                                        }
                                    }
                                    loginCallBack.sendResultCallBack(BaseCallBack.SEND_OK);
                                } else {
                                    loginCallBack.sendResultCallBack(BaseCallBack.SEND_ERROR);
                                }
                            }
                        }
                    }
                });
    }

    @Override
    public void getCurrentUser(String userId, final BaseCallBack.GetObjCallBack<UserInfo> getUserCallBack) {
        PageJsonData pageJsonData = new PageJsonData(userId);
        String json = GsonUtil.objectToString(pageJsonData);
        String content = getMessageToJson(json);
        userService.getCurrentUser(content)
                .subscribeOn(Schedulers.io())  //IO线程加载数据
                .observeOn(AndroidSchedulers.mainThread())  //主线程显示数据
                .subscribe(new Subscriber<String>() {

                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtil.error("Retrofit2:\n" + e.getMessage());
                        getUserCallBack.getResultCallBack(null);
                    }

                    @Override
                    public void onNext(String result) {
                        if (result != null) {
                            IMessage message = getResult(result);
                            if (message != null && message.getBody() != null) {
                                if (OelementType.SUCCESS == message.getBody().getOelement().getCode()) {
                                    UserInfo userInfo = (UserInfo) GsonUtil.stringToObjectByBean(message.getBody().getElements(), UserInfo.class);
                                    BaseApplication.INSTANCE().updateLocalUser(userInfo);
                                    getUserCallBack.getResultCallBack(userInfo);
                                }
                            }
                        }
                    }
                });
    }

    @Override
    public void register(UserAccount account,final BaseCallBack.ResultCallBack callBack) {
        if (account == null) {
            callBack.sendResultCallBack(BaseCallBack.SEND_ERROR,"账号不能为空");
            return;
        }
        String json = GsonUtil.objectToString(account);
        String content = getMessageToJson(json);
        userService.registerAccount(content)
                .subscribeOn(Schedulers.io())  //IO线程加载数据
                .observeOn(AndroidSchedulers.mainThread())  //主线程显示数据
                .subscribe(new Subscriber<String>() {

                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtil.error("Retrofit2:\n" + e.getMessage());
                        callBack.sendResultCallBack(BaseCallBack.SEND_ERROR,"网络错误");
                    }

                    @Override
                    public void onNext(String result) {
                        if (result != null) {
                            IMessage message = getResult(result);
                            if (message != null && message.getBody() != null) {
                                if (OelementType.SUCCESS == message.getBody().getOelement().getCode()) {
                                    callBack.sendResultCallBack(BaseCallBack.SEND_OK,"");
                                }else {
                                    callBack.sendResultCallBack(BaseCallBack.SEND_ERROR,message.getBody().getOelement().getMessage());
                                }
                            }
                        }
                    }
                });
    }

    @Override
    public void verifyPhone(String phoneNum,final BaseCallBack.ResultCallBack callBack) {
        PageJsonData pageJsonData = new PageJsonData(phoneNum);
        String json = GsonUtil.objectToString(pageJsonData);
        String content = getMessageToJson(json);
        userService.verifyPhone(content)
                .subscribeOn(Schedulers.io())  //IO线程加载数据
                .observeOn(AndroidSchedulers.mainThread())  //主线程显示数据
                .subscribe(new Subscriber<String>() {

                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtil.error("Retrofit2:\n" + e.getMessage());
                        callBack.sendResultCallBack(BaseCallBack.SEND_ERROR,"网络错误");
                    }

                    @Override
                    public void onNext(String result) {
                        if (result != null) {
                            IMessage message = getResult(result);
                            if (message != null && message.getBody() != null) {
                                if (OelementType.SUCCESS == message.getBody().getOelement().getCode()) {
                                    callBack.sendResultCallBack(BaseCallBack.SEND_OK,"");
                                }else {
                                    callBack.sendResultCallBack(BaseCallBack.SEND_ERROR,message.getBody().getOelement().getMessage());
                                }
                            }
                        }
                    }
                });
    }

    @Override
    public void updateUserInfo(final UserInfo userInfo, final BaseCallBack.SendCallBack callBack) {
        if (userInfo == null) {
            callBack.sendResultCallBack(BaseCallBack.SEND_ERROR);
            return;
        }
        String json = GsonUtil.objectToString(userInfo);
        String content = getMessageToJson(json);
        userService.updateCurrentUser(content)
                .subscribeOn(Schedulers.io())  //IO线程加载数据
                .observeOn(AndroidSchedulers.mainThread())  //主线程显示数据
                .subscribe(new Subscriber<String>() {

                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtil.error("Retrofit2:\n" + e.getMessage());
                        callBack.sendResultCallBack(BaseCallBack.SEND_ERROR);
                    }

                    @Override
                    public void onNext(String result) {
                        if (result != null) {
                            IMessage message = getResult(result);
                            if (message != null && message.getBody() != null) {
                                if (OelementType.SUCCESS == message.getBody().getOelement().getCode()) {
                                    BaseApplication.INSTANCE().updateLocalUser(userInfo);
                                    callBack.sendResultCallBack(BaseCallBack.SEND_OK);
                                }
                            }
                        }
                    }
                });
    }


}
