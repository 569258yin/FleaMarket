package com.agjsj.fleamarket.view;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.agjsj.fleamarket.R;
import com.agjsj.fleamarket.params.GlobalParams;
import com.agjsj.fleamarket.view.manager.BaseUI;

/**
 * Created by YH on 2017/5/1.
 */
public class MeUI extends BaseUI{

    public MeUI(Context context, FragmentManager mFragmentManager) {
        super(context, mFragmentManager);
    }

    @Override
    public void init() {
        showInMiddle = (ViewGroup) View.inflate(context,R.layout.activity_me,null);
    }

    @Override
    public void setListener() {

    }

    @Override
    public void refreshView() {

    }

    @Override
    public int getID() {
        return GlobalParams.VIEW_ME;
    }
}
