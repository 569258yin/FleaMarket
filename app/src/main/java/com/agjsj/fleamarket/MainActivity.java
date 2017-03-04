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
		hideSoftInputView();


		new Thread() {
			public void run() {

				// for (int i = 0; i < 10; i++) {
				//
				// Goods goods = new Goods();

				// goods.setGoodsid(1);
				// goods.setGoodstitle("商品x");
				// goods.setUserid(1);
				// goods.setGoodstypeid(1);
				// goods.setGoodstext("商品内容");
				// goods.setGoodsmoney(123);
				// goods.setGoodsquality("八成");
				// goods.setGoodsiconnumber(2);
				// goods.setGoodsicon("fdsafas");
				// goods.setUseraddress("用户联系地址");
				// goods.setUserphone("用户联系电话");
				// goods.setGoodslikenumber(0);
				// goods.setGoodsrepalynumber(0);
				// GoodsEngine goodsEngine = new GoodsEngineImpl();
				// IMessage iMessage = goodsEngine.sendGoods(goods);

				// GoodsEngine goodsEngine = new GoodsEngineImpl();
				// IMessage iMessage = goodsEngine.getGoodsOfUser(1, 1, 5);
				// IMessage iMessage = goodsEngine.getAllGoods(0, 5, 0);
				// IMessage iMessage = goodsEngine.updateGoods(goods);

				// IMessage iMessage = goodsEngine.deleteGoods(1);
				// IMessage iMessage = goodsEngine.deleteGoods(2);
				// iMessage.getBody().serializableOelement();
				//
				// iMessage.getBody().getBodyStr();

				// LogUtil.info(iMessage.getBody().getBodyStr());

				// ZanEngine zanEngine = new ZanEngineImpl();
				// IMessage iMessage = zanEngine.zanGoods(1, 3);
				// iMessage.getBody().serializableOelement();
				// LogUtil.info(iMessage.getBody().getBodyStr());

				// ReplayEngine replayEngine = new ReplayEngineImpl();
				// Goodsrepaly goodsrepaly = new Goodsrepaly();
				// goodsrepaly.setUserid(1);
				// goodsrepaly.setGoodsid(3);
				// goodsrepaly.setGoodsreplaycontent("商品评论测试");
				// IMessage iMessage = replayEngine.sendReplay(goodsrepaly);
				// IMessage iMessage = replayEngine.getAllReplayOfgoodsid(3, 1,
				// 5);
				// LogUtil.info(iMessage.getBody().getBodyStr());

				// CollectEngine collectEngine = new CollectEngineImpl();
				// IMessage iMessage = collectEngine.addGoodsCollect(1, 3);

				// ToReplayEngine toReplayEngine = new ToReplayEngineImpl();
				//
				// Torepaly torepaly = new Torepaly();
				// torepaly.setGoodsreplayid(1);
				// torepaly.setTorepalycontext("评论的回复");
				// torepaly.setUserid(1);
				// IMessage iMessage = toReplayEngine.addToReplay(torepaly);
				// IMessage iMessage = toReplayEngine.getToReplayOfReplay(1);
				// FoundEngine foundEngine = new FoundEngineImpl();
				// Foundcase foundcase = new Foundcase();
				// foundcase.setFdctitle("寻物启事");
				// foundcase.setFdccontext("第一条失物招领");
				// foundcase.setFdctime("8624");
				// foundcase.setFdcuseraddress("地址");
				// foundcase.setFdcuserphone("85624254");
				// foundcase.setUserid(1);
				// foundcase.setFdctag(1);

				// foundEngine.sendFoundcase(foundcase);
				// IMessage iMessage = foundEngine.sendFoundcase(foundcase);
				// IMessage iMessage = foundEngine.getFoundcase(0, 2, 0);
				// IMessage iMessage = foundEngine.getFoundcaseOfuser(1, 0, 5);
				// IMessage iMessage = foundEngine.deleteFoundcase(1);
				UserEngine userEngine = new UserEngineImpl();
				List<Integer> list = new ArrayList<Integer>();
				list.add(1);
				list.add(2);
				list.add(3);
				list.add(4);
				list.add(5);
//				IMessage iMessage = userEngine.getUser(list);
//				LogUtil.info(iMessage.getBody().getBodyStr());
				// }

			};
		};

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



	/**
	 * 隐藏软键盘
	 */
	public void hideSoftInputView() {
		InputMethodManager manager = ((InputMethodManager) this.getSystemService(Activity.INPUT_METHOD_SERVICE));
		if (getWindow().getAttributes().softInputMode != WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN) {
			if (getCurrentFocus() != null)
				manager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

	/**隐藏软键盘-一般是EditText.getWindowToken()
	 * @param token
	 */
	public void hideSoftInput(IBinder token) {
		if (token != null) {
			InputMethodManager im = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
			im.hideSoftInputFromWindow(token, InputMethodManager.HIDE_NOT_ALWAYS);
		}
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