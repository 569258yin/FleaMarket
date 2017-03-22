package com.agjsj.fleamarket.view;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.agjsj.fleamarket.R;
import com.agjsj.fleamarket.adapter.base.OnRecyclerViewImageListener;
import com.agjsj.fleamarket.adapter.base.OnRecyclerViewListener;
import com.agjsj.fleamarket.adapter.goods.GoodsAdapter;
import com.agjsj.fleamarket.bean.Goods;
import com.agjsj.fleamarket.engine.GoodsEngine;
import com.agjsj.fleamarket.net.procotal.IMessage;
import com.agjsj.fleamarket.params.ConstantValue;
import com.agjsj.fleamarket.util.BeanFactory;
import com.agjsj.fleamarket.util.LogUtil;
import com.agjsj.fleamarket.view.manager.BaseUI;
import com.agjsj.fleamarket.view.myview.PullToRefreshView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * 第一个界面，为商品展示界面
 *
 * @author YH
 */
public class HomeUI extends BaseUI implements OnRecyclerViewListener, OnRecyclerViewImageListener
        , PullToRefreshView.OnFooterRefreshListener, PullToRefreshView.OnHeaderRefreshListener {

    @Bind(R.id.recyclerview_home)
    RecyclerView recyclerView;
    // 上拉下滑有关的
    @Bind(R.id.ll_pullToRefresh_home)
    PullToRefreshView pullToRefresh;

    private LinearLayoutManager layoutManager;

    private List<Goods> goodsList;
    private GoodsAdapter adapter;

    private int currentPageNum = 1;
    private static final int PAGE_SIZE = 2;
    private boolean isLoading = false;

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

        initRecycleView();
        getGoodsFromServer();

        pullToRefresh.setOnFooterRefreshListener(this);
        pullToRefresh.setOnHeaderRefreshListener(this);
    }


    private void initRecycleView() {
        goodsList = new ArrayList<>();
        layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new GoodsAdapter(context, goodsList);
        adapter.setOnRecyclerViewListener(this);
        adapter.setOnRecyclerViewImageListener(this);
        recyclerView.setAdapter(adapter);
    }

    private void getGoodsFromServer() {
        if (!isLoading) {
            GoodsEngine goodsEngine = BeanFactory.getImpl(GoodsEngine.class);
            if (goodsEngine != null) {
                isLoading = true;
                List<Goods> lists = goodsEngine.getAllGoodsByPage(currentPageNum, PAGE_SIZE, 0);
                LogUtil.info(HomeUI.class, "从服务器上获取数据");
                if (lists != null) {
                    goodsList.addAll(lists);
                    adapter.notifyDataSetChanged();
                    currentPageNum++;
                }
                isLoading = false;
            }
        }
    }

    /**
     * 从服务器上获取数据
     */
    private void getDataByNet() {
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
    private String getCurrentDataTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(new Date());
    }

    @Override
    public void setListener() {

    }



    private void onRefresh() {
        currentPageNum = 1;
        goodsList.clear();
        getGoodsFromServer();
        pullToRefresh.onHeaderRefreshComplete("更新于："
                + getCurrentDataTime());
    }

    private void onLoad() {
        getGoodsFromServer();
        pullToRefresh.onFooterRefreshComplete();
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


    @Override
    public void onFooterRefresh(PullToRefreshView view) {
        onLoad();
    }

    @Override
    public void onHeaderRefresh(PullToRefreshView view) {
        onRefresh();
    }
}
