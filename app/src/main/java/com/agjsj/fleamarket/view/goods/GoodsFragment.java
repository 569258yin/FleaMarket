package com.agjsj.fleamarket.view.goods;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.agjsj.fleamarket.R;
import com.agjsj.fleamarket.adapter.base.OnRecyclerViewImageListener;
import com.agjsj.fleamarket.adapter.base.OnRecyclerViewListener;
import com.agjsj.fleamarket.adapter.goods.GoodsAdapter;
import com.agjsj.fleamarket.bean.Goods;
import com.agjsj.fleamarket.engine.BaseCallBack;
import com.agjsj.fleamarket.engine.GoodsEngine;
import com.agjsj.fleamarket.util.BeanFactory;
import com.agjsj.fleamarket.util.LogUtil;
import com.agjsj.fleamarket.view.HomeUI;
import com.agjsj.fleamarket.view.base.BaseFragment;
import com.agjsj.fleamarket.view.myview.PullToRefreshView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class GoodsFragment extends BaseFragment implements PullToRefreshView.OnFooterRefreshListener,
        PullToRefreshView.OnHeaderRefreshListener,OnRecyclerViewListener, OnRecyclerViewImageListener {

    @Bind(R.id.recyclerview_home)
    RecyclerView recyclerView;
    @Bind(R.id.ll_pullToRefresh_home)
    PullToRefreshView pullToRefresh;

    private List<Goods> goodsList;
    private int goodsTypeId;
    private LinearLayoutManager layoutManager;
    private GoodsAdapter adapter;
    private int currentPageNum = 1;
    private static final int PAGE_SIZE = 5;
    private boolean isLoading = false;
    private int eventMethod = 0 ;
    public static final int ON_REFRESH = 1;
    public static final int ON_LOADING = 2;

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    protected int setLayoutResouceId() {
        return R.layout.fragment_refresh_recyview;
    }

    @Override
    protected void initData(Bundle arguments) {
        goodsTypeId = arguments.getInt("goodsTypeId");
    }


    @Override
    protected void initView() {
        goodsList = new ArrayList<>();
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new GoodsAdapter(getContext(), goodsList);
        adapter.setOnRecyclerViewListener(this);
        adapter.setOnRecyclerViewImageListener(this);
        recyclerView.setAdapter(adapter);

        pullToRefresh.setOnFooterRefreshListener(this);
        pullToRefresh.setOnHeaderRefreshListener(this);
    }

    @Override
    protected void onFragmentVisibleChange(boolean isVisible) {
        if(isVisible) {

        }else {

        }
    }

    @Override
    public void onFragmentFirstVisible() {
        getGoodsFromServer();
    }

    private void getGoodsFromServer() {
        LogUtil.info("goodsTypeId="+goodsTypeId);
        if (!isLoading) {
            GoodsEngine goodsEngine = BeanFactory.getImpl(GoodsEngine.class);
            if (goodsEngine != null) {
                isLoading = true;
                goodsEngine.getAllGoodsByGoodsTypeId(currentPageNum, PAGE_SIZE, goodsTypeId, new BaseCallBack.GetAllListCallBack<Goods>() {
                    @Override
                    public void getAllResultCallBack(List<Goods> list) {
                        if (goodsList != null && list != null) {
                            goodsList.addAll(list);
                            adapter.notifyDataSetChanged();
                            currentPageNum++;
                        }else{
                            //没有更多数据了
                        }
                        if(eventMethod == ON_REFRESH){
                            pullToRefresh.onHeaderRefreshComplete("更新于："
                                    + getCurrentDataTime());
                        }else if(eventMethod == ON_LOADING){
                            pullToRefresh.onFooterRefreshComplete();
                        }
                        isLoading = false;
                    }
                });

            }
        }
    }

    //		tvLastUpdateTime.setText("最后刷新时间："+getLastUpdateTime());
    private String getCurrentDataTime() {
        return sdf.format(new Date());
    }

    public void onRefresh() {
        currentPageNum = 1;
        eventMethod = ON_REFRESH;
        if(goodsList == null) {
            goodsList = new ArrayList<>();
        }else {
            goodsList.clear();
        }
        getGoodsFromServer();
    }
    private void onLoad() {
        eventMethod = ON_LOADING;
        getGoodsFromServer();

    }

    @Override
    public void onFooterRefresh(PullToRefreshView view) {
        onLoad();
    }

    @Override
    public void onHeaderRefresh(PullToRefreshView view) {
        onRefresh();
    }

    @Override
    public void onItemClick(int position) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("goods",goodsList.get(position));
        startActivity(GoodsDetailActivity.class,bundle);
        //设置跳转动画
        mActivity.overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
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
