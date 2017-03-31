package com.agjsj.fleamarket.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.agjsj.fleamarket.params.ConstantValue;
import com.agjsj.fleamarket.params.GlobalParams;
import com.agjsj.fleamarket.R;
import com.agjsj.fleamarket.util.LogUtil;
import com.agjsj.fleamarket.view.manager.BaseUI;

import java.util.ArrayList;
import java.util.List;

/**
 * 购彩大厅
 * 
 * @author Administrator
 * 
 */
public class Hall extends BaseUI {
	private ViewPager viewpager;
	private PagerAdapter adpater;
	
	//选项卡
	private String[] titles;
	private List<View> pagers;
	private ImageView underline;
	private int imgWidth;
	private int windowWidth;

	public Hall(Context context) {
		super(context);
		LogUtil.info(Hall.class, "onCreate");
	}
	// 第一步：加载layout（布局参数设置）
	// 第二步：初始化layout中控件
	// 第三步：设置监听

	@Override
	public void init() {
		showInMiddle = (LinearLayout) View.inflate(context, R.layout.activity_hall, null);
		underline = (ImageView) findViewById(R.id.ii_category_selector);
		pagers = new ArrayList<View>();
		viewpager = (ViewPager) findViewById(R.id.viewpager_test);

		LogUtil.info(HomeUI.class, "初始化参数完毕");
		
		initPagers();
		
		adpater = new myPagerAdapter();
		viewpager.setAdapter(adpater);
		
		//初始化选项卡的下划线
		initTabStrip();
	}
	@Override
	public void refreshView() {

	}
	
	//初始化数据
	private void initPagers() {
		titles = new String[]{"福彩","双色球","高频彩" };
		
		
		TextView item;
		item = new TextView(context);
		item.setText("双色球");
		pagers.add(item);
		
		item = new TextView(context);
		item.setText("球");
		pagers.add(item);

		item = new TextView(context);
		item.setText("高频彩");
		pagers.add(item);
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
		int offset = (windowWidth/3 - imgWidth )/2;

		//设置图片初始位置——向右偏移
		Matrix matrix = new Matrix();
		matrix.postTranslate(offset, 0);
		underline.setImageMatrix(matrix );
	}

	private class myPagerAdapter extends PagerAdapter {

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			View view = pagers.get(position);
			container.addView(view);
			return view;
		}
		@Override
		public int getCount() {
			return pagers.size();
		}

		@Override
		public boolean isViewFromObject(View view, Object object) {
			return view == object;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			View view = pagers.get(position);
			container.removeView(view);
		}

		@Override
		public CharSequence getPageTitle(int position) {
			// TODO Auto-generated method stub
			return titles[position];
		}
	}

	//记录viewPager上一个界面的position信息
	private int lastPosition;
	@Override
	public void setListener() {
		viewpager.setOnPageChangeListener(new OnPageChangeListener() {
			@Override
			public void onPageSelected(int position) {

				TranslateAnimation animation =
						new TranslateAnimation(lastPosition*windowWidth/3, position*windowWidth/3, 0, 0);
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
		return GlobalParams.VIEW_HALL;
	}
}
