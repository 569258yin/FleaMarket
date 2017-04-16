package com.agjsj.fleamarket.view.goods;

import android.content.Context;
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
import com.agjsj.fleamarket.bean.GoodsType;
import com.agjsj.fleamarket.engine.GoodsEngine;
import com.agjsj.fleamarket.params.ConstantValue;
import com.agjsj.fleamarket.util.BeanFactory;
import com.agjsj.fleamarket.util.LogUtil;
import com.agjsj.fleamarket.view.HomeUI;
import com.agjsj.fleamarket.view.myview.PullToRefreshView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class GoodsFragment extends Fragment implements PullToRefreshView.OnFooterRefreshListener,
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
    public GoodsFragment() {
        Bundle bundle = getArguments();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        goodsTypeId = bundle.getInt("goodsTypeId");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_goodslist, container, false);
        ButterKnife.bind(this, view);
        initRecycleView();
        return view;
    }
    private void initRecycleView() {
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

    private void getGoodsFromServer() {
        if (!isLoading) {
            GoodsEngine goodsEngine = BeanFactory.getImpl(GoodsEngine.class);
            if (goodsEngine != null) {
                isLoading = true;
                goodsEngine.getAllGoodsByGoodsTypeId(currentPageNum, PAGE_SIZE, goodsTypeId, new GoodsEngine.GetAllGoodsCallBack() {
                    @Override
                    public void getAllGoodsCallback(List<Goods> list) {

                        if (goodsList != null && list != null) {
                            goodsList.addAll(list);
                            adapter.notifyDataSetChanged();
                            currentPageNum++;
                        }else{
                            //没有更多数据了
                        }
                        LogUtil.info(HomeUI.class, "从服务器上获取数据");
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
        Intent intent = new Intent(getContext(),GoodsDetailActivity.class);
        intent.putExtra("goods",goodsList.get(position));
        startActivity(intent);
        //设置跳转动画
        getActivity().overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
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
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
