package com.agjsj.fleamarket.dialog.base;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.KeyEvent;
import android.view.MotionEvent;

import butterknife.ButterKnife;

/**
 * Created by YH on 2016/11/9.
 */

public class BaseDialog extends Dialog {
    protected Context context;

    public BaseDialog(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
    }

    public BaseDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    public BaseDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }
}
