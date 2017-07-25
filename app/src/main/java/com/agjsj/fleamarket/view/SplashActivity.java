package com.agjsj.fleamarket.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.agjsj.fleamarket.engine.BaseCallBack;
import com.agjsj.fleamarket.engine.UserEngine;
import com.agjsj.fleamarket.util.BeanFactory;
import com.agjsj.fleamarket.util.LogUtil;
import com.agjsj.fleamarket.view.base.BaseApplication;
import com.agjsj.fleamarket.view.user.LoginActivity;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;

import java.lang.ref.WeakReference;

/**
 * Description:欢迎页面
 * author: YH
 * Date: 2016/10/12 16:04
 */
public class SplashActivity extends AppCompatActivity {

    private MyHandler handler = new MyHandler(this);

    //防止handler造成内存泄露
    private static class MyHandler extends Handler {
        WeakReference<SplashActivity> mActivity;

        MyHandler(SplashActivity mActivity) {
            this.mActivity = new WeakReference<>(mActivity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            UserEngine userEngine = BeanFactory.getImpl(UserEngine.class);
//            判断是否登陆了
            if (BaseApplication.INSTANCE().getToken() == null || "".equals(BaseApplication.INSTANCE().getToken())) {
                mActivity.get().toLoginActivity();
            } else {
                userEngine.checkToken(BaseApplication.INSTANCE().getToken(), new BaseCallBack.SendCallBack() {
                    @Override
                    public void sendResultCallBack(int responseCode) {
                        if (responseCode == BaseCallBack.SEND_OK){
                            EMClient.getInstance().login(BaseApplication.INSTANCE().getCurrentUserName(),BaseApplication.INSTANCE().getCurrentUserPasswd(),new EMCallBack() {//回调
                                @Override
                                public void onSuccess() {
                                    EMClient.getInstance().groupManager().loadAllGroups();
                                    EMClient.getInstance().chatManager().loadAllConversations();
                                    LogUtil.info("main"+ "登录聊天服务器成功！");
                                    mActivity.get().toMainActivity();
                                }

                                @Override
                                public void onProgress(int progress, String status) {

                                }

                                @Override
                                public void onError(int code, String message) {
                                    LogUtil.info("main"+"登录聊天服务器失败！");
                                }
                            });
                        }else {
                            mActivity.get().toLoginActivity();
                        }
                    }
                });

            }
//            mActivity.get().toMainActivity();

//            mActivity.get().toLoginActivity();
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        //因此状态栏,全屏显示
//        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //不加载布局,通过主题直接显示欢迎logo
//      setContentView(R.layout.activity_splash);
        //先展示logo3秒
        handler.sendEmptyMessageDelayed(0, 50);

    }


    /**
     * 开启主页面
     */
    private void toMainActivity() {

        startActivity(new Intent(this, MainActivity.class));

        finish();
    }

    /**
     * 开启登陆页面
     */
    private void toLoginActivity() {
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
    }
}
