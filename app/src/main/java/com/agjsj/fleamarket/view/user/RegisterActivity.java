package com.agjsj.fleamarket.view.user;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.agjsj.fleamarket.R;
import com.agjsj.fleamarket.bean.UserAccount;
import com.agjsj.fleamarket.engine.BaseCallBack;
import com.agjsj.fleamarket.engine.UserEngine;
import com.agjsj.fleamarket.util.BeanFactory;
import com.agjsj.fleamarket.util.LogUtil;
import com.agjsj.fleamarket.view.base.BaseActivity;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by YH on 2017/5/7.
 */
public class RegisterActivity extends BaseActivity {

    @Bind(R.id.et_register_account)
    EditText etRegisterAccount;
    @Bind(R.id.et_register_pass)
    EditText etRegisterPass;
    @Bind(R.id.et_register_pass_2)
    EditText etRegisterPass2;
    @Bind(R.id.et_register_phone)
    EditText etRegisterPhone;
    @Bind(R.id.et_register_sign)
    EditText etRegisterSign;
    @Bind(R.id.btn_register_getsign)
    Button btnRegisterGetsign;
    @Bind(R.id.btn_register_register)
    Button btnRegisterRegister;

    private Timer timer;
    private int currentSocre = 60;
    private static final int WAIT_TIME = 60;

    private  boolean isLoad = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        timer = new Timer();

        setListen();
    }

    @Override
    protected void initView() {
        super.initView();

    }



    private void setListen(){
        btnRegisterGetsign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = etRegisterPhone.getText().toString();
                if(StringUtils.isNotEmpty(phone) && phone.length() == 11) {
                    UserEngine userEngine = BeanFactory.getImpl(UserEngine.class);
                    if(userEngine != null) {
                        userEngine.verifyPhone(phone, new BaseCallBack.ResultCallBack() {
                            @Override
                            public void sendResultCallBack(int responseCode, String message) {
                                if(responseCode == BaseCallBack.SEND_OK) {
                                    toast("验证码已发到你的手机!");
                                } else {
                                    toast(message+"");
                                }
                            }
                        });
                        currentSocre = WAIT_TIME;
                        timer.schedule(new TimerTask() {
                            @Override
                            public void run() {
                                if(currentSocre > 0) {
                                    runOnMain(new Runnable() {
                                        @Override
                                        public void run() {
                                            btnRegisterGetsign.setEnabled(false);
                                            btnRegisterGetsign.setText("(" + currentSocre + ")s再次发送");
                                        }
                                    });
                                    currentSocre--;
                                }else {
                                    runOnMain(new Runnable() {
                                        @Override
                                        public void run() {
                                            btnRegisterGetsign.setEnabled(true);
                                            btnRegisterGetsign.setText("获取验证码");
                                        }
                                    });
                                    this.cancel();
                                }
                            }
                        },0,1000);
                    }
                } else {
                    toast("请输入正确的手机号码");
                }
            }
        });
        btnRegisterRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isLoad){
                    return;
                }
                isLoad = true;
                String userAccount = etRegisterAccount.getText().toString();
                String password = etRegisterPass.getText().toString();
                String password_2 = etRegisterPass2.getText().toString();
                String phone = etRegisterPhone.getText().toString();
                String code = etRegisterSign.getText().toString();

                if(StringUtils.isEmpty(userAccount) || userAccount.length() < 6 ||  userAccount.length() > 16){
                    toast("账号不能为null,且账号在6~16位之间");
                    return;
                }
                if (StringUtils.isEmpty(password) || StringUtils.isEmpty(password_2)){
                    toast("密码不能为null");
                    return;
                }
                if (!password.equals(password_2)) {
                    toast("密码不一致");
                    return;
                }
                if (StringUtils.isEmpty(phone)) {
                    toast("手机号不能为null");
                    return;
                }
                if(StringUtils.isEmpty(code)) {
                    toast("验证码不能为null");
                    return;
                }

                UserAccount account = new UserAccount();
                account.setUseraccount(userAccount);
                account.setUserpassword( DigestUtils.md5Hex( password));
                account.setPhoneNum(phone);
                account.setCode(code);

                UserEngine userEngine = BeanFactory.getImpl(UserEngine.class);
                if(userEngine != null) {
                    userEngine.register(account, new BaseCallBack.ResultCallBack() {
                        @Override
                        public void sendResultCallBack(int responseCode, String message) {
                            if (responseCode == BaseCallBack.SEND_OK) {
                                isLoad = false;
                                toast("注册成功");
                                finish();
                            }else {
                                toast("注册失败，原因"+message);
                                isLoad = false;
                            }
                        }
                    });
                }

            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            timer.cancel();
            timer = null;
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}
