package com.agjsj.fleamarket.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import com.agjsj.fleamarket.params.GlobalParams;
import com.agjsj.fleamarket.R;
import com.agjsj.fleamarket.util.LogUtil;
import com.agjsj.fleamarket.view.base.BaseFragment;
import com.agjsj.fleamarket.view.manager.BaseUI;
import com.agjsj.fleamarket.view.school.LostFoundFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * 校园活动
 * 
 * @author YH
 * 
 */
public class SchoolUI extends BaseUI {
	@Bind(R.id.viewpager_school)
	ViewPager viewpager;
	@Bind(R.id.ii_category_selector)
	ImageView underline;
	@Bind(R.id.ii_purchase_school_title)
	TextView tvPurchase;

	private int imgWidth;
	private int windowWidth;
	
	//选项卡
	private String[] titles;
	private List<BaseFragment> pagers;
	private PagerAdapter adpater;


	public SchoolUI(Context context) {
		super(context);
		LogUtil.info(SchoolUI.class, "onCreate");
	}
	// 第一步：加载layout（布局参数设置）
	// 第二步：初始化layout中控件
	// 第三步：设置监听

	@Override
	public void init() {
		showInMiddle = (LinearLayout) View.inflate(context, R.layout.activity_school, null);
		ButterKnife.bind(this, showInMiddle);
		LogUtil.info(HomeUI.class, "初始化参数完毕");

		initPagers();
		adpater = new myPagerAdapter(mFragmentManager);
		viewpager.setAdapter(adpater);

		//初始化选项卡的下划线
		initTabStrip();
	}
	@Override
	public void refreshView() {

	}
	
	//初始化数据
	private void initPagers() {
		pagers = new ArrayList<>();
		BaseFragment lostFragment = new LostFoundFragment();
		pagers.add(lostFragment);
		Bundle lostBundle = new Bundle();
		lostBundle.putInt("Type",0);
		lostFragment.setArguments(lostBundle);

		BaseFragment foundFragment = new LostFoundFragment();
		pagers.add(foundFragment);
		Bundle foundBundle = new Bundle();
		foundBundle.putInt("Type",1);
		lostFragment.setArguments(foundBundle);
	}

	/**
	 * 设置viewpage选项卡头信息
	 */
	private void initTabStrip() {

		//获取屏幕宽度
		windowWidth = GlobalParams.WIN_WIDTH;
		//获取小图片的宽度
		Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.id_category_selector);
		imgWidth = bitmap.getWidth();
		int offset = (windowWidth/2 - imgWidth )/2;

		//设置图片初始位置——向右偏移
		Matrix matrix = new Matrix();
		matrix.postTranslate(offset, 0);
		underline.setImageMatrix(matrix );
	}

	private class myPagerAdapter extends FragmentPagerAdapter {

		public myPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			return pagers.get(position);
		}

		@Override
		public int getCount() {
			return pagers.size();
		}
	}

	//记录viewPager上一个界面的position信息
	private int lastPosition;
	@Override
	public void setListener() {
		viewpager.addOnPageChangeListener(new OnPageChangeListener() {
			@Override
			public void onPageSelected(int position) {

				TranslateAnimation animation =
						new TranslateAnimation(lastPosition*windowWidth/2, position*windowWidth/2, 0, 0);
				lastPosition = position;
				animation.setDuration(300);
				animation.setFillAfter(true); //移动完停止到终点
				underline.startAnimation(animation);
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {

			}
		});
	}
	@Override
	public int getID() {
		return GlobalParams.VIEW_SCHOOL;
	}
}
