package com.agjsj.fleamarket.dialog;

import android.content.Context;
import android.os.Bundle;
import android.widget.ProgressBar;

import com.agjsj.fleamarket.R;
import com.agjsj.fleamarket.dialog.base.BaseDialog;

import butterknife.Bind;

/**
 * 网络请求进度条
 * Created by YH on 2017/3/23.
 */

public class ProgressDialog extends BaseDialog {

    public ProgressDialog(Context context) {
        super(context,R.style.Dialog);
    }

    public ProgressDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    public ProgressDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_progressbar);
    }


    protected void initView() {

    }


}
