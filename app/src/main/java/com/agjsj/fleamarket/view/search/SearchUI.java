package com.agjsj.fleamarket.view.search;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import com.agjsj.fleamarket.R;
import com.agjsj.fleamarket.adapter.base.OnRecyclerViewImageListener;
import com.agjsj.fleamarket.adapter.base.OnRecyclerViewListener;
import com.agjsj.fleamarket.adapter.goods.GoodsAdapter;
import com.agjsj.fleamarket.bean.Goods;
import com.agjsj.fleamarket.engine.BaseCallBack;
import com.agjsj.fleamarket.engine.GoodsEngine;
import com.agjsj.fleamarket.params.ConstantValue;
import com.agjsj.fleamarket.params.GlobalParams;
import com.agjsj.fleamarket.util.BeanFactory;
import com.agjsj.fleamarket.util.LogUtil;
import com.agjsj.fleamarket.util.SearchALG;
import com.agjsj.fleamarket.view.HomeUI;
import com.agjsj.fleamarket.view.goods.GoodsDetailUI;
import com.agjsj.fleamarket.view.manager.BaseUI;
import com.agjsj.fleamarket.view.manager.MiddleManager;
import com.agjsj.fleamarket.view.myview.SearchView;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by YH on 2017/3/4.
 */

public class SearchUI extends BaseUI implements OnRecyclerViewListener, OnRecyclerViewImageListener {

    private SearchALG searchALG;
    private List<String> changedHintDatas;
    //热搜数据
    private List<String> hot_datas;
    //提示列表数据
    private List<String> hint_datas;

    @Bind(R.id.recyclerview_home)
    RecyclerView recyclerView;
    @Bind(R.id.searchView)
    SearchView searchView;

    private LinearLayoutManager layoutManager;

    private List<Goods> goodsList;
    private GoodsAdapter adapter;


    public SearchUI(Context context, FragmentManager fragmentManager) {
        super(context,fragmentManager);
    }
    @Override
    public void init() {
        showInMiddle = (LinearLayout)View.inflate(context, R.layout.activity_search, null);
        ButterKnife.bind(this, showInMiddle);
        initRecycleView();
        initData();
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

    @Override
    public void setListener() {
        searchView.setOnSearchListener(new MyOnSearchListener());


    }
    @Override
    public void refreshView() {
        GoodsEngine goodsEngine = BeanFactory.getImpl(GoodsEngine.class);
        if(goodsEngine != null){
            goodsEngine.getSearchHot(10, new BaseCallBack.GetAllListCallBack<String>() {
                @Override
                public void getAllResultCallBack(List<String> list) {
                    if(list != null){
                        searchView.updateHotSearchDatas(list);
                    }
                }
            });
        }
    }

    @Override
    public void onItemClick(int position) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("goods",goodsList.get(position));
        MiddleManager.getInstance().changeUI(GoodsDetailUI.class,bundle);
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
        Bundle bundle = new Bundle();
        bundle.putSerializable("goods",goodsList.get(itemPosition));
        MiddleManager.getInstance().changeUI(GoodsDetailUI.class,bundle);
    }

    /**
     * 设置searview的监听
     */
    class MyOnSearchListener implements SearchView.OnSearchListener {
        /**
         * 搜索回调
         * @param searchText 进行搜索的文本
         */
        @Override
        public void onSearch(String searchText) {
            if (!TextUtils.isEmpty(searchText)) {
                GoodsEngine goodsEngine = BeanFactory.getImpl(GoodsEngine.class);
                if(goodsEngine != null) {
                    goodsList.clear();
                    adapter.notifyDataSetChanged();
                    goodsEngine.searchGoods(searchText, new BaseCallBack.GetAllListCallBack<Goods>() {
                        @Override
                        public void getAllResultCallBack(List<Goods> list) {
                            if(list != null){
                                LogUtil.info(list.size()+"");
                                LogUtil.info(list.toString());
                                goodsList.addAll(list);
                                adapter.notifyDataSetChanged();
                            }
                        }
                    });
                }
                Toast.makeText(context, "完成搜索" + searchText, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "搜索内容不能为空！", Toast.LENGTH_SHORT).show();
            }
        }

        /**
         * 刷新提示列表
         * @param changedText 改变后的文本
         */
        @Override
        public void onRefreshHintList(String changedText) {
            if (changedHintDatas == null) {
                changedHintDatas = new ArrayList<>();
            } else {
                changedHintDatas.clear();
            }
            if (TextUtils.isEmpty(changedText)) {
                return;
            }
            for (int i = 0; i < hint_datas.size(); i++) {
                String hint_data = hint_datas.get(i);
                boolean isAdd = searchALG.isAddToHintList(hint_data, changedText);
                if (isAdd) {
                    changedHintDatas.add(hint_datas.get(i));
                }
            }

            /**
             * 根据搜索框文本的变化，动态的改变提示的listView
             */
            searchView.updateHintList(changedHintDatas);

        }
    }

    private void initData() {
        hot_datas = new ArrayList<>();
        hint_datas = new ArrayList<>();
        searchALG = new SearchALG(context);

        //设置热搜数据显示的列数
        searchView.setHotNumColumns(2);
        //设置热搜数据
        searchView.setHotSearchDatas(hot_datas);

        /**
         * 设置自动保存搜索记录
         */
        searchView.keepSearchHistory(true);

        //设置提示列表的最大显示列数
        searchView.setMaxHintLines(8);
        //设置保存搜索记录的个数
        searchView.setMaxHistoryRecordCount(6);

    }
    @Override
    public int getID() {
        return GlobalParams.VIEW_SEARCH;
    }
}
