package com.agjsj.fleamarket.view;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.agjsj.fleamarket.adapter.base.OnRecyclerViewImageListener;
import com.agjsj.fleamarket.adapter.base.OnRecyclerViewListener;
import com.agjsj.fleamarket.adapter.goods.GoodsAdapter;
import com.agjsj.fleamarket.bean.Goods;
import com.agjsj.fleamarket.bean.UserInfo;
import com.agjsj.fleamarket.params.ConstantValue;
import com.agjsj.fleamarket.R;
import com.agjsj.fleamarket.net.procotal.IMessage;
import com.agjsj.fleamarket.util.LogUtil;
import com.agjsj.fleamarket.view.manager.BaseUI;
import com.agjsj.fleamarket.view.manager.MiddleManager;
import com.agjsj.fleamarket.view.myview.PullToRefreshView;
import com.agjsj.fleamarket.view.myview.PullToRefreshView.OnFooterRefreshListener;
import com.agjsj.fleamarket.view.myview.PullToRefreshView.OnHeaderRefreshListener;
import com.agjsj.fleamarket.view.search.SearchUI;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 第一个界面，为商品展示界面
 * @author YH
 *
 */
public class HomeUI  extends BaseUI implements SwipeRefreshLayout.OnRefreshListener, OnRecyclerViewListener, OnRecyclerViewImageListener {
	
	//上拉下滑有关的
	@Bind(R.id.swiprefreshlayout_home)
	SwipeRefreshLayout swipeRefreshLayout;
	@Bind(R.id.recyclerview_home)
	RecyclerView recyclerView;

	private LinearLayoutManager layoutManager;

	private List<Goods> goodsList;
	private GoodsAdapter adapter;


	public HomeUI(Context context) {
		super(context);
		LogUtil.info(HomeUI.class, "HomeUI onCreate");

	}

	@Override
	public int getID() {
		return ConstantValue.VIEW_FIRST;
	}

	@Override
	public void init() {
		showInMiddle = (LinearLayout) View.inflate(context, R.layout.activity_home, null);
		ButterKnife.bind(this, showInMiddle);
		swipeRefreshLayout.setOnRefreshListener(this);
		initRecycleView();


	//	getDataByNet();
		
		LogUtil.info(HomeUI.class, "首次从服务器上获取数据");
	}

	private void initRecycleView() {
		goodsList = new ArrayList<>();
		layoutManager = new LinearLayoutManager(context);
		recyclerView.setLayoutManager(layoutManager);
		for (int i=0;i<10;i++){
			goodsList.add(getGoods());
		}
		adapter = new GoodsAdapter(context,goodsList);
		adapter.setOnRecyclerViewListener(this);
		adapter.setOnRecyclerViewImageListener(this);
		recyclerView.setAdapter(adapter);
	}

	private Goods getGoods(){
		Goods goods = new Goods();
		UserInfo userInfo = new UserInfo();
		userInfo.setNickname("张三");
		userInfo.setUsericon("https://gd2.alicdn.com/imgextra/i1/71460541/TB2H.g9gCBjpuFjy1XdXXaooVXa_!!71460541.jpg");
		goods.setUserInfo(userInfo);
		goods.setGoodsiconnumber(5);
		goods.setGoodsicon("https://gd2.alicdn.com/imgextra/i1/71460541/TB2H.g9gCBjpuFjy1XdXXaooVXa_!!71460541.jpg;https://gd1.alicdn.com/imgextra/i1/71460541/TB2xs4xh9VmpuFjSZFFXXcZApXa_!!71460541.jpg;https://gd4.alicdn.com/imgextra/i4/71460541/TB2XQUybNRDOuFjSZFzXXcIipXa_!!71460541.jpg;https://gd3.alicdn.com/imgextra/i3/71460541/TB2L_brgB0lpuFjSszdXXcdxFXa_!!71460541.jpg;https://gd2.alicdn.com/imgextra/i2/TB1iS85PVXXXXabaXXXXXXXXXXX_!!0-item_pic.jpg");
		goods.setGoodslikenumber(10);
		goods.setGoodsrepalynumber(28);
		goods.setGoodsoldmoney(8999);
		goods.setGoodstime(System.currentTimeMillis()+"");
		goods.setGoodstitle("春季新款性感荷叶边套装裙长袖网纱连衣裙中长款修身吊带裙两件套 ");
		goods.setGoodstext("这款透明波点网纱裙和吊带裙的两件套装备，很受女神们的喜爱呢，当柔软的吊带配合着网纱下美好的芳华，说性感撩人也不为过啊，透明的网纱透气轻盈，波点元素和吊带裙的组合，带你回归妙龄少女的年华，下摆的荷叶边设计空灵又飘逸，将女性的淑女俏皮一展无余，泥萌的衣橱可是缺了这款让你变美的利器不？");
		return goods;
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
				super.onPostExecute(result);
			}
		}.executeProxy("ll");
	}


	

	//		tvLastUpdateTime.setText("最后刷新时间："+getLastUpdateTime());
	private String getCurrentDataTime(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(new Date());
	}
	@Override
	public void setListener() {
		
	}

	@Override
	public void onRefresh() {
	}

	@Override
	public void onItemClick(int position) {

	}

	@Override
	public void onItemClick(int position, int id) {

	}

	@Override
	public boolean onItemLongClick(int position) {
		return false;
	}

	@Override
	public void onImageItemClick(int itemPosition, int imagePosition) {

	}
}
