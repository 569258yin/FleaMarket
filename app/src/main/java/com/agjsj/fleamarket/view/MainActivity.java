package com.agjsj.fleamarket.view;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.FragmentManager;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import android.widget.Toast;
import com.agjsj.fleamarket.R;
import com.agjsj.fleamarket.bean.MessageEvent;
import com.agjsj.fleamarket.dialog.ProgressDialog;
import com.agjsj.fleamarket.params.GlobalParams;
import com.agjsj.fleamarket.params.OelementType;
import com.agjsj.fleamarket.view.base.BaseActivity;
import com.agjsj.fleamarket.view.manager.BaseUI;
import com.agjsj.fleamarket.view.manager.BottomManager;
import com.agjsj.fleamarket.view.manager.MiddleManager;
import com.agjsj.fleamarket.view.manager.TitleManager;
import com.agjsj.fleamarket.view.user.LoginActivity;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Date;

public class MainActivity extends BaseActivity {

	private RelativeLayout middle;// 中间占着位置的容器
	private long lastTime;
	private ProgressDialog progressDialog;
	private Object middleObj;
	private FragmentManager mFragmentManager;

	@RequiresApi(api = Build.VERSION_CODES.M)
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.il_main);
		mFragmentManager = getSupportFragmentManager();
		// 获取屏幕的宽度
		// 用于viewPage的计算
		DisplayMetrics metrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metrics);
		GlobalParams.WIN_WIDTH = metrics.widthPixels;
		init();

//		initPermission();

		progressDialog = new ProgressDialog(MainActivity.this);

	}

	private static final int READ_PHONE_STATE =100;
	private static final int ACCESS_COARSE_LOCATION_STATE = 101;
	private static final int ACCESS_FINE_LOCATION_STATE = 102;

	@RequiresApi(api = Build.VERSION_CODES.M)
	private void initPermission() {
		if(checkSelfPermission(Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
			// 申请一个（或多个）权限，并提供用于回调返回的获取码（用户定义)
			requestPermissions( new String[]{
					Manifest.permission.READ_PHONE_STATE },READ_PHONE_STATE);
		}
		if(checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){
			requestPermissions( new String[]{
					Manifest.permission.ACCESS_COARSE_LOCATION },ACCESS_COARSE_LOCATION_STATE);
		}
	}

	//动态请求权限
	@Override
	public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
		super.onRequestPermissionsResult(requestCode, permissions, grantResults);
		switch(requestCode) {
			// requestCode即所声明的权限获取码，在checkSelfPermission时传入
			case 1:
				BAIDU_READ_PHONE_STATE:
				if(grantResults[0] == PackageManager.PERMISSION_GRANTED) {
					// 获取到权限，作相应处理（调用定位SDK应当确保相关权限均被授权，否则可能引起定位失败）
				} else{
					// 没有获取到权限，做特殊处理
				}
				break;
			default:
				break;
		}

	}

	private void init() {
		TitleManager manager = TitleManager.getInstance();
		manager.init(this);
		manager.showSearchTitle();

		BottomManager.getInstrance().init(this);
//		 BottomManager.getInstrance().showCommonBottom();

		middle = (RelativeLayout) findViewById(R.id.il_middle);
		MiddleManager.getInstance().init(this,mFragmentManager);
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


	@Subscribe(threadMode = ThreadMode.MAIN)
	public void onMessageEvent(MessageEvent event) {
		switch (event.eventCode){
			case OelementType.TOKEN_FAILD:
				toast("身份验证已过期，请重新登录!");
				Intent intent = new Intent(MainActivity.this, LoginActivity.class);
				startActivity(intent);
				break;
			case OelementType.FAILD:
				toast("请求失败!");
				break;
			case OelementType.NET_ERROR:
				toast("网络错误，请检查你的网络!");
				break;
			case OelementType.LOCAL_CHECK_MD5_ERROR:
				toast("服务器返回的数据不正确，当前数据可能被非法劫持!");
				break;
			case OelementType.PROGRESS_START:
				progressDialog.show();
				break;
			case OelementType.PROGRESS_END:
				progressDialog.dismiss();
				break;
			default:
				break;
		}
	};

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			if(MiddleManager.getInstance().getCurrentUI().getClass() == HomeUI.class
					|| MiddleManager.getInstance().getCurrentUI().getClass() == SchoolUI.class
					|| MiddleManager.getInstance().getCurrentUI().getClass() == MessageUI.class
					){
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
			}else{
				MiddleManager.getInstance().changeUI(HomeUI.class);
			}
			return false;
		}
		return super.onKeyDown(keyCode, event);
	}


	public Object getMiddleObj() {
		return middleObj;
	}

	public void setMiddleObj(Object middleObj) {
		this.middleObj = middleObj;
	}

	public FragmentManager getmFragmentManager() {
		return mFragmentManager;
	}
}