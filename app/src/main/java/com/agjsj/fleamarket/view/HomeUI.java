package com.agjsj.fleamarket.view;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.agjsj.fleamarket.params.ConstantValue;
import com.agjsj.fleamarket.R;
import com.agjsj.fleamarket.net.procotal.IMessage;
import com.agjsj.fleamarket.util.LogUtil;
import com.agjsj.fleamarket.view.manager.BaseUI;
import com.agjsj.fleamarket.view.myview.PullToRefreshView;
import com.agjsj.fleamarket.view.myview.PullToRefreshView.OnFooterRefreshListener;
import com.agjsj.fleamarket.view.myview.PullToRefreshView.OnHeaderRefreshListener;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 第一个简答的界面
 * @author Administrator
 *
 */
public class HomeUI  extends BaseUI{
	
	//上拉下滑有关的
	private PullToRefreshView pullToRefresh;
	private ListView list_main;
	public HomeUI(Context context) {
		super(context);
		LogUtil.info(HomeUI.class, "onCreate");

	}

	@Override
	public int getID() {
		return ConstantValue.VIEW_FIRST;
	}

	@Override
	public void init() {
		showInMiddle = (LinearLayout) View.inflate(context, R.layout.activity_home, null);
		LogUtil.info(HomeUI.class, "onInit");
		pullToRefresh = (PullToRefreshView) findViewById(R.id.ll_home_pullToRefresh);
		LogUtil.info(HomeUI.class, "onInitPull");
		list_main = (ListView) findViewById(R.id.list_main);



	//	getDataByNet();
		
		LogUtil.info(HomeUI.class, "首次从服务器上获取数据");
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
	private void getDataByNet(){
		new MyHttpTask<String>() {

			@Override
			protected IMessage doInBackground(String... params) {
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				return null;
			}
			@Override
			protected void onPostExecute(IMessage result) {
				pullToRefresh.onHeaderRefreshComplete("更新于："+getCurrentDataTime());
				super.onPostExecute(result);
			}
		}.executeProxy("ll");
	}
	/**
	 * 下拉加载时异步任务
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

	

	//		tvLastUpdateTime.setText("最后刷新时间："+getLastUpdateTime());
	private String getCurrentDataTime(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(new Date());
	}
	@Override
	public void setListener() {
	}
}
