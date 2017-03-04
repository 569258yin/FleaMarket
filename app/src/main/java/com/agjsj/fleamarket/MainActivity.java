package com.agjsj.fleamarket;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.agjsj.fleamarket.engine.UserEngine;
import com.agjsj.fleamarket.engine.impl.UserEngineImpl;
import com.agjsj.fleamarket.net.procotal.IMessage;
import com.agjsj.fleamarket.params.GlobalParams;
import com.agjsj.fleamarket.view.HomeUI;
import com.agjsj.fleamarket.view.manager.BaseUI;
import com.agjsj.fleamarket.view.manager.BottomManager;
import com.agjsj.fleamarket.view.manager.MiddleManager;
import com.agjsj.fleamarket.view.manager.TitleManager;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends Activity {

	private RelativeLayout middle;// 中间占着位置的容器
	private long lastTime;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.il_main);
		// 获取屏幕的宽度
		// 用于viewPage的计算
		DisplayMetrics metrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metrics);
		GlobalParams.WIN_WIDTH = metrics.widthPixels;

		init();

	}

	private void init() {
		TitleManager manager = TitleManager.getInstance();
		manager.init(this);
		manager.showSearchTitle();

		BottomManager.getInstrance().init(this);
//		 BottomManager.getInstrance().showCommonBottom();

		middle = (RelativeLayout) findViewById(R.id.il_middle);
		MiddleManager.getInstance().setMiddle(middle);

		// 建立观察者和被观察者之间的关系（标题和底部导航添加到观察者的容器里面）
		MiddleManager.getInstance().addObserver(TitleManager.getInstance());
		MiddleManager.getInstance().addObserver(BottomManager.getInstrance());

		// loadFirstUI();
		// MiddleManager.getInstance().changeUI(FirstUI.class);
		MiddleManager.getInstance().changeUI(HomeUI.class);
	}

	/**
	 * 切换界面
	 * 
	 * @param ui
	 */
	protected void changeUI(BaseUI ui) {
		// 切换界面的核心代码
		middle.removeAllViews();
		View child = ui.getChild();
		middle.addView(child);
		child.startAnimation(AnimationUtils.loadAnimation(this,
				R.anim.ia_view_change));
	}

	// 1、切换界面时清理上一个显示内容
	// 2、处理切换动画：简单动画——复杂动画（淡入淡出）
	// 3、切换界面通用处理——增加一个参数（明确切换的目标界面,通用）
	// 4、不使用Handler，任意点击按钮切换

	// a、用户返回键操作捕捉
	// b、响应返回键——切换到历史界面

	//
	// LinkedList<String>——AndroidStack




	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			// boolean result = MiddleManager.getInstance().goBack();
			boolean result = false;
			// 返回键操作失败
			if (!result) {
				// Toast.makeText(MainActivity.this, "是否退出系统", 1).show();
				long currentTime = new Date().getTime();
				if (currentTime - lastTime < 1500) {
					MiddleManager.getInstance().clear();
					// PromptManager.showExitSystem(this);
					android.os.Process.killProcess(android.os.Process.myPid());
				} else {
					Toast.makeText(this, "再按一下退出系统", Toast.LENGTH_SHORT).show();
				}
				lastTime = currentTime;
			}
			return false;
		}
		return super.onKeyDown(keyCode, event);
	}

}