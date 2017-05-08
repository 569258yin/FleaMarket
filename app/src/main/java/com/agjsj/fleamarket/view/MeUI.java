package com.agjsj.fleamarket.view;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.agjsj.fleamarket.R;
import com.agjsj.fleamarket.bean.UserInfo;
import com.agjsj.fleamarket.dialog.ShowMegDialog;
import com.agjsj.fleamarket.engine.BaseCallBack;
import com.agjsj.fleamarket.engine.UserEngine;
import com.agjsj.fleamarket.params.GlobalParams;
import com.agjsj.fleamarket.util.BeanFactory;
import com.agjsj.fleamarket.util.PicassoUtils;
import com.agjsj.fleamarket.view.base.BaseApplication;
import com.agjsj.fleamarket.view.manager.BaseUI;
import com.agjsj.fleamarket.view.manager.MiddleManager;
import com.agjsj.fleamarket.view.me.MyInfoActivity;
import com.agjsj.fleamarket.view.me.MySendFoundcaseUI;
import com.agjsj.fleamarket.view.me.MySendGoodsUI;
import com.agjsj.fleamarket.view.user.LoginActivity;

/**
 * Created by YH on 2017/5/1.
 */
public class MeUI extends BaseUI {

    @Bind(R.id.iv_user_icon)
    ImageView userIcon;
    @Bind(R.id.tv_user_name)
    TextView userName;
    @Bind(R.id.tv_user_sex)
    TextView userSex;
    @Bind(R.id.ll_my_send)
    LinearLayout mySendGoods;
    @Bind(R.id.ll_my_foundcase)
    LinearLayout mySendFoundcase;
    @Bind(R.id.ll_my_info)
    LinearLayout myInfo;
    @Bind(R.id.ll_logout)
    LinearLayout exit;
    @Bind(R.id.rl_me_normal)
    RelativeLayout rlNomal;
    @Bind(R.id.ll_me_login)
    LinearLayout llLogin;
    @Bind(R.id.btn_me_login)
    Button btnLogin;

    private  UserInfo userInfo;

    public MeUI(Context context, FragmentManager mFragmentManager) {
        super(context, mFragmentManager);
    }

    @Override
    public void init() {
        showInMiddle = (ViewGroup) View.inflate(context, R.layout.activity_me, null);
        ButterKnife.bind(this, showInMiddle);
        initView();
    }


    @Override
    public void setListener() {
        mySendGoods.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (userInfo == null) {
                    return;
                }
                MiddleManager.getInstance().changeUI(MySendGoodsUI.class);
            }
        });

        mySendFoundcase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (userInfo == null) {
                    return;
                }
                MiddleManager.getInstance().changeUI(MySendFoundcaseUI.class);
            }
        });

        myInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (userInfo == null) {
                    return;
                }
                startActivity(MyInfoActivity.class);

            }
        });

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (userInfo == null) {
                    return;
                }
                ShowMegDialog dialog = new ShowMegDialog(context,"提示","确认要退出当前账号吗？");
                dialog.setOnResultListener(new ShowMegDialog.OnResultListener() {
                    @Override
                    public void onOk() {
                        BaseApplication.INSTANCE().setCurrentUser(null);
                        BaseApplication.INSTANCE().deleteCurrentUser();
                        startActivity(LoginActivity.class);
                      }

                    @Override
                    public void onCancel() {

                    }
                });
                dialog.show();

            }
        });
    }

    private void initView(){
        userInfo = BaseApplication.INSTANCE().getCurrentUser();
        if (userInfo != null) {
            UserEngine userEngine = BeanFactory.getImpl(UserEngine.class);
            if (userEngine != null) {
                userEngine.getCurrentUser(userInfo.getUserid(), new BaseCallBack.GetObjCallBack<UserInfo>() {
                    @Override
                    public void getResultCallBack(UserInfo obj) {
                        if(obj != null) {
                            userInfo = obj;
                            BaseApplication.INSTANCE().updateLocalUser(userInfo);
                            rlNomal.setVisibility(View.VISIBLE);
                            llLogin.setVisibility(View.GONE);
                            PicassoUtils.loadResizeImage(userInfo.getUsericon(),70,70,userIcon);
                            userName.setText(userInfo.getNickname());
                            userSex.setText(userInfo.getUsersex() == 0 ? "男" : "女");
                        }
                    }
                });
            }

        } else {
            rlNomal.setVisibility(View.GONE);
            llLogin.setVisibility(View.VISIBLE);
            llLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(LoginActivity.class);
                }
            });
        }
    }

    @Override
    public void refreshView() {
        initView();
    }

    @Override
    public int getID() {
        return GlobalParams.VIEW_ME;
    }
}
