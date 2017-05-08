package com.agjsj.fleamarket.view.manager;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.RelativeLayout;

import com.agjsj.fleamarket.R;
import com.agjsj.fleamarket.params.GlobalParams;
import com.agjsj.fleamarket.view.MeUI;
import com.agjsj.fleamarket.view.SchoolUI;
import com.agjsj.fleamarket.view.HomeUI;
import com.agjsj.fleamarket.view.MessageUI;
import com.agjsj.fleamarket.view.send.SendGoodActivity;

import org.apache.commons.lang3.StringUtils;

import java.util.Observable;
import java.util.Observer;


/**
 * 控制底部导航容器
 * 
 * @author Administrator
 * 
 */
public class BottomManager implements Observer,OnClickListener,OnCheckedChangeListener {
	protected static final String TAG = "BottomManager";
	
	private Activity activity;
	
	private boolean animal_tag = false;
	/******************* 第一步：管理对象的创建(单例模式) ***************************************************/
	// 创建一个静态实例
	private static BottomManager instrance;

	// 构造私有
	private BottomManager() {
	}

	// 提供统一的对外获取实例的入口
	public static BottomManager getInstrance() {
		if (instrance == null) {
			instrance = new BottomManager();
		}
		return instrance;
	}

	/*********************************************************************************************/
	/******************* 第二步：初始化各个导航容器及相关控件设置监听 *********************************/

	/********** 底部菜单容器 **********/
	private RelativeLayout bottomMenuContainer;
	/************ 底部导航 ************/
	//	private LinearLayout commonBottom;// 购彩通用导航
	//	private LinearLayout playBottom;// 购彩

	/***************** 导航按钮 ******************/

	//	private TextView playBottomNotice;

	/************ 通用导航底部按钮 ************/
	private RadioGroup rg_tab;
	private ImageView iv_send;



	public void init(Activity activity) {
		this.activity = activity;
		bottomMenuContainer = (RelativeLayout) activity.findViewById(R.id.il_bottom);

		rg_tab = (RadioGroup) activity.findViewById(R.id.rg_main_tab);
		iv_send = (ImageView) activity.findViewById(R.id.iv_main_send);
		// 设置监听
		rg_tab.setOnCheckedChangeListener(this);
		iv_send.setOnClickListener(this);
		setListener();
	}



	private void setListener() {

	}

	//	/*********************************************************************************************/
	//	/****************** 第三步：控制各个导航容器的显示和隐藏 *****************************************/
	//	/**
	//	 * 转换到通用导航
	//	//	 */
	//		public void showCommonBottom() {
	//			if (bottomMenuContainer.getVisibility() == View.GONE || bottomMenuContainer.getVisibility() == View.INVISIBLE) {
	//				bottomMenuContainer.setVisibility(View.VISIBLE);
	//			}
	//			commonBottom.setVisibility(View.VISIBLE);
	//			playBottom.setVisibility(View.INVISIBLE);
	//		}

	/**
	 * 改变底部导航容器显示情况
	 */
	public void changeBottomVisiblity(int type) {
		if (bottomMenuContainer.getVisibility() != type)
			bottomMenuContainer.setVisibility(type);
	}

	/*********************************************************************************************/
	/*********************** 第四步：控制玩法导航内容显示 ********************************************/
	/**


	/*********************************************************************************************/

	@Override
	public void update(Observable observable, Object data) {
		if (data != null && StringUtils.isNumeric(data.toString())) {
			int id = Integer.parseInt(data.toString());
			switch (id){
				case GlobalParams.VIEW_SEARCH:
				case GlobalParams.VIEW_GOODSDETAIL:
				case GlobalParams.VIEW_MY_SEND_FOUNDCASE:
				case GlobalParams.VIEW_MY_SEND_GOODS:
					changeBottomVisiblity(View.GONE);
					break;
				default:
					changeBottomVisiblity(View.VISIBLE);
					break;
			}
		}

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.iv_main_send:
//			if (!animal_tag) {
//				iv_send.startAnimation(
//						AnimationUtils.loadAnimation(activity.getApplicationContext(), R.anim.iv_send_rotate));
//					animal_tag = true;
//			}else{
//				iv_send.startAnimation(
//						AnimationUtils.loadAnimation(activity.getApplicationContext(), R.anim.iv_send_rotate_rollck));
//				animal_tag = false;
//			}
			Intent intent = new Intent(activity, SendGoodActivity.class);
			activity.startActivity(intent);
			break;

		default:
			break;
		}
	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		switch (checkedId) {
		case R.id.rb_main_home:
			MiddleManager.getInstance().changeUI(HomeUI.class);
			break;
		case R.id.rb_main_type:
			MiddleManager.getInstance().changeUI(SchoolUI.class);
			break;
		case R.id.rb_main_message:
			MiddleManager.getInstance().changeUI(MessageUI.class);
			break;
		case R.id.rb_main_me:
			MiddleManager.getInstance().changeUI(MeUI.class);
			break;
		default:
			break;
		}

	}

}
