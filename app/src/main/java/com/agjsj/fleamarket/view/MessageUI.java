package com.agjsj.fleamarket.view;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.LinearLayout;

import com.agjsj.fleamarket.R;
import com.agjsj.fleamarket.params.GlobalParams;
import com.agjsj.fleamarket.view.manager.BaseUI;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 消息界面
 * @author YH
 * 
 */
public class MessageUI extends BaseUI {
	public MessageUI(Context context, FragmentManager fragmentManager) {
		super(context,fragmentManager);
	}

	// 第一步：加载layout（布局参数设置）
	// 第二步：初始化layout中控件
	// 第三步：设置监听

	@Override
	public void init() {
		showInMiddle = (LinearLayout) View.inflate(context,
				R.layout.activity_message, null);
	}
	@Override
	public void refreshView() {

	}

	@Override
	public void setListener() {

	}

	@Override
	public int getID() {
		return GlobalParams.VIEW_MESSSAGE;
	}
}