package com.agjsj.fleamarket.view.user;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.agjsj.fleamarket.R;
import com.agjsj.fleamarket.bean.UserAccount;
import com.agjsj.fleamarket.engine.BaseCallBack;
import com.agjsj.fleamarket.engine.UserEngine;
import com.agjsj.fleamarket.util.BeanFactory;
import com.agjsj.fleamarket.util.LogUtil;
import com.agjsj.fleamarket.view.MainActivity;
import com.agjsj.fleamarket.view.base.BaseActivity;
import com.agjsj.fleamarket.view.base.BaseApplication;
import com.agjsj.fleamarket.view.manager.MiddleManager;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * Created by MyPC on 2017/2/26.
 */

public class LoginActivity extends BaseActivity{



    @Bind(R.id.et_username)
    EditText etUsername;
    @Bind(R.id.et_password)
    EditText etPassword;
    @Bind(R.id.btn_login)
    Button btnLogin;
    @Bind(R.id.tv_login_register)
    TextView tvRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(RegisterActivity.class,null,false);
            }
        });
    }

    @OnClick(R.id.btn_login)
    public void onClick() {

        if (TextUtils.isEmpty(etUsername.getText())) {
            toast("学号/工号不能为空");

        } else if (TextUtils.isEmpty(etPassword.getText())) {
            toast("请填写密码");

        } else {
            login();
        }


    }


    /**
     * 登录
     */
    private void login() {
        BaseApplication.INSTANCE().deleteCurrentUser();
        UserAccount userAccount = new UserAccount(etUsername.getText().toString(),
                DigestUtils.md5Hex(etPassword.getText().toString()));
        UserEngine userEngine = BeanFactory.getImpl(UserEngine.class);
        userEngine.login(userAccount, new BaseCallBack.SendCallBack() {
            @Override
            public void sendResultCallBack(int responseCode) {
                if(responseCode == BaseCallBack.SEND_OK){
                    EMClient.getInstance().login( BaseApplication.INSTANCE().getCurrentUserName(),BaseApplication.INSTANCE().getCurrentUserPasswd(),new EMCallBack() {//回调
                        @Override
                        public void onSuccess() {
                            EMClient.getInstance().groupManager().loadAllGroups();
                            EMClient.getInstance().chatManager().loadAllConversations();
                            LogUtil.info("main"+ "登录聊天服务器成功！");
                            startActivity(MainActivity.class,null,true);
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
                    toast("抱歉，登录失败");
                }
            }
        });
//        UserNetwork.getInstance().login(etUsername.getText().toString().trim(),
//                etPassword.getText().toString().trim(), new UserNetwork.LoginCallBack() {
//                    @Override
//                    public void loginResponse(int responseCode) {
//                        if (UserNetwork.LOGIN_YES == responseCode) {
//                            startActivity(MainActivity.class, null, true);
//                        } else if (UserNetwork.LOGIN_NO == responseCode) {
//
//                            toast("抱歉，登录失败");
//                        } else {
//                            toast("抱歉，登录失败");
//                        }
//                    }
//                });

    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(0);
        }
        return super.onKeyDown(keyCode, event);
    }



}
