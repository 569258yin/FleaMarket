package com.agjsj.fleamarket.view;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.agjsj.fleamarket.params.ConstantValue;
import com.agjsj.fleamarket.R;
import com.agjsj.fleamarket.bean.Goods;
import com.agjsj.fleamarket.net.procotal.IMessage;
import com.agjsj.fleamarket.util.LogUtil;
import com.agjsj.fleamarket.view.manager.BaseUI;
import com.agjsj.fleamarket.view.myview.PullToRefreshView;
import com.agjsj.fleamarket.view.myview.PullToRefreshView.OnFooterRefreshListener;
import com.agjsj.fleamarket.view.myview.PullToRefreshView.OnHeaderRefreshListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 第二个简答的界面
 * 
 * @author Administrator
 * 
 */
public class SecondUI extends BaseUI {
	public SecondUI(Context context) {
		super(context);
		LogUtil.info(SecondUI.class, "onCreate");
	}

	private ListView listView;
	// 上拉下滑有关的
	private PullToRefreshView pullToRefresh;
	
	private List<Goods> listData;

	// 第一步：加载layout（布局参数设置）
	// 第二步：初始化layout中控件
	// 第三步：设置监听

	@Override
	public void init() {
		showInMiddle = (LinearLayout) View.inflate(context,
				R.layout.activity_second, null);
		listView = (ListView) findViewById(R.id.list_second);

		pullToRefresh = (PullToRefreshView) findViewById(R.id.ll_pullToRefresh);
		
		listData = new ArrayList<Goods>();
		
		LogUtil.info(HomeUI.class, "初始化参数完毕");
		
		getDataByNet();
		
		pullToRefresh.setOnFooterRefreshListener(new OnFootRefresh());
		pullToRefresh.setOnHeaderRefreshListener(new OnHeadRefresh());
	}

	/**
	 * 下拉刷新时网络请求任务
	 */
	class OnHeadRefresh implements OnHeaderRefreshListener {

		@Override
		public void onHeaderRefresh(PullToRefreshView view) {
			getDataByNet();
			LogUtil.info(HomeUI.class, "下拉刷新");
		}

	}

	/**
	 * 从服务器上获取数据
	 */
	private void getDataByNet() {
		new MyHttpTask<String>() {

			@Override
			protected IMessage doInBackground(String... params) {
				
				return null;
			}

			@Override
			protected void onPostExecute(IMessage result) {
				pullToRefresh.onHeaderRefreshComplete("更新于："
						+ getCurrentDataTime());
				super.onPostExecute(result);
				if (result != null) {
				}
				Goods goods = new Goods();
				goods.setGoodstext("的撒打算的撒打算的啊 啊啊");
				listData.add(goods);
			}
		}.executeProxy("ll");
	}

	/**
	 * 下拉加载时异步任务
	 * 
	 * @author yh
	 * 
	 */
	class OnFootRefresh implements OnFooterRefreshListener {

		@Override
		public void onFooterRefresh(PullToRefreshView view) {
			new MyHttpTask<Integer>() {

				@Override
				protected IMessage doInBackground(Integer... params) {
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					return null;
				}

				@Override
				protected void onPostExecute(IMessage result) {
					pullToRefresh.onFooterRefreshComplete();
					LogUtil.info(HomeUI.class, "下拉加载");
					super.onPostExecute(result);
				}
			}.executeProxy(0);

		}
	}

	@Override
	public void setListener() {

	}

	// tvLastUpdateTime.setText("最后刷新时间："+getLastUpdateTime());
	private String getCurrentDataTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(new Date());
	}

	@Override
	public int getID() {
		return ConstantValue.VIEW_SECOND;
	}
}