package com.agjsj.fleamarket.view.school;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import butterknife.Bind;
import com.agjsj.fleamarket.R;
import com.agjsj.fleamarket.adapter.base.OnRecyclerViewImageListener;
import com.agjsj.fleamarket.adapter.base.OnRecyclerViewListener;
import com.agjsj.fleamarket.adapter.goods.GoodsAdapter;
import com.agjsj.fleamarket.adapter.lostfound.LostFoundAdapter;
import com.agjsj.fleamarket.bean.FoundCase;
import com.agjsj.fleamarket.bean.Goods;
import com.agjsj.fleamarket.engine.BaseCallBack;
import com.agjsj.fleamarket.engine.FoundEngine;
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


public class LostFoundFragment extends BaseFragment implements PullToRefreshView.OnFooterRefreshListener,
        PullToRefreshView.OnHeaderRefreshListener,OnRecyclerViewListener{

    @Bind(R.id.recyclerview_home)
    RecyclerView recyclerView;
    @Bind(R.id.ll_pullToRefresh_home)
    PullToRefreshView pullToRefresh;

    private List<FoundCase> foundCaseList;
    private int type;
    private LinearLayoutManager layoutManager;
    private LostFoundAdapter adapter;
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
    protected void initView() {
        foundCaseList = new ArrayList<>();
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new LostFoundAdapter(getContext(), foundCaseList);
        adapter.setOnRecyclerViewListener(this);
        recyclerView.setAdapter(adapter);

        pullToRefresh.setOnFooterRefreshListener(this);
        pullToRefresh.setOnHeaderRefreshListener(this);
    }

    @Override
    protected void initData(Bundle arguments) {
        if (arguments == null){
            return;
        }
        type = arguments.getInt("Type");
    }

    @Override
    protected void onFragmentVisibleChange(boolean isVisible) {
    }

    @Override
    protected void onFragmentFirstVisible() {
        getGoodsFromServer();
    }

    private void getGoodsFromServer() {
        if (!isLoading) {
            FoundEngine foundEngine = BeanFactory.getImpl(FoundEngine.class);
            if (foundEngine != null) {
                isLoading = true;
                foundEngine.getAllFoundCaseByType(currentPageNum, PAGE_SIZE, type, new BaseCallBack.GetAllListCallBack<FoundCase>() {
                    @Override
                    public void getAllResultCallBack(List<FoundCase> list) {
                        if(list != null) {
                            foundCaseList.addAll(list);
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
        if(foundCaseList == null) {
            foundCaseList = new ArrayList<>();
        }else {
            foundCaseList.clear();
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
    }

    @Override
    public void onItemClick(int position, int id) {

    }

    @Override
    public boolean onItemLongClick(int position) {
        return false;
    }



}
