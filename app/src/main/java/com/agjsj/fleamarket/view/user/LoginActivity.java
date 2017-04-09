package com.agjsj.fleamarket.view.user;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

import com.agjsj.fleamarket.view.MainActivity;
import com.agjsj.fleamarket.R;
import com.agjsj.fleamarket.bean.UserAccount;
import com.agjsj.fleamarket.engine.UserEngine;
import com.agjsj.fleamarket.util.BeanFactory;
import com.agjsj.fleamarket.view.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
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
        UserAccount userAccount = new UserAccount(etUsername.getText().toString(),etPassword.getText().toString());
        UserEngine userEngine = BeanFactory.getImpl(UserEngine.class);
        userEngine.login(userAccount, new UserEngine.LoginCallBack() {
            @Override
            public void loginResponse(int responseCode) {
                if(responseCode == UserEngine.LOGIN_YES){
                    startActivity(MainActivity.class,null,true);
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




}
