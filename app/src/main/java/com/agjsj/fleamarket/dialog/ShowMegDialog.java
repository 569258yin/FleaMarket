package com.agjsj.fleamarket.dialog;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import com.agjsj.fleamarket.R;
import com.agjsj.fleamarket.dialog.base.BaseDialog;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 两个按钮的dialog
 * Created by YH on 2016/11/9.
 */

public class ShowMegDialog extends BaseDialog {
    @Bind(R.id.tv_title_dialog_showmsg)
    TextView tv_title;
    @Bind(R.id.tv_msg_dialog_showmsg)
    TextView tv_msg;
    @Bind(R.id.btn_ok_dialog_showmsg)
    Button btn_ok;
    @Bind(R.id.btn_cancel_dialog_showmsg)
    Button btn_cancek;

    private Context context;
    private String title = "提示";
    private String meessage = "";
    private String okBnt = "确定";
    private String cancelBtn = "取消";
    private boolean isAutoDismiss = true;


    /**
     * @param context
     * @param title   标题
     * @param msg     显示内容
     */
    public ShowMegDialog(Context context, String title, String msg, String okBnt, String cancelBtn) {
        super(context, R.style.Dialog);
        this.context = context;
        this.title = title;
        this.meessage = msg;
        this.okBnt = okBnt;
        this.cancelBtn = cancelBtn;
        init();
    }

    /**
     * @param context
     * @param title   标题
     * @param msg     显示内容
     */
    public ShowMegDialog(Context context, String title, String msg) {
        super(context, R.style.Dialog);
        this.context = context;
        this.title = title;
        this.meessage = msg;
        init();
    }

    public ShowMegDialog(Context context) {
        super(context, R.style.Dialog);
        this.context = context;
        init();
    }

    public interface OnResultListener {
        public void onOk();

        public void onCancel();
    }

    private OnResultListener onResultListener;

    /**
     * 设置监听事件
     *
     * @param onResultListener
     */
    public void setOnResultListener(OnResultListener onResultListener) {
        this.onResultListener = onResultListener;
    }

    private void init() {
        setContentView(R.layout.dialog_showmessage);
        ButterKnife.bind(this);

        this.setCancelable(true);
        tv_title.setText(title);
        tv_msg.setText(meessage);
        btn_ok.setText(okBnt);
        btn_cancek.setText(cancelBtn);

        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onResultListener != null) {
                    onResultListener.onOk();
                }
                if (isAutoDismiss) {
                    dismiss();
                }
            }
        });

        btn_cancek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onResultListener != null) {
                    onResultListener.onCancel();
                }
                if (isAutoDismiss) {
                    dismiss();
                }
            }
        });

    }

    @Override
    public void show() {
        super.show();
    }

    @Override
    public void dismiss() {
        super.dismiss();
    }


    /**
     * 设置内容
     *
     * @param meessage
     */
    public void setText(String meessage) {
        if (tv_msg != null) {
            tv_msg.setText(meessage);
        }
    }

    /**
     * 设置是否点击按钮关闭该Dialog
     *
     * @param autoDismiss
     */
    public void setAutoDismiss(boolean autoDismiss) {
        isAutoDismiss = autoDismiss;
    }
}
