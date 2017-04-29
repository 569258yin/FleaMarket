package com.agjsj.fleamarket.view.search;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.agjsj.fleamarket.R;
import com.agjsj.fleamarket.params.ConstantValue;
import com.agjsj.fleamarket.params.GlobalParams;
import com.agjsj.fleamarket.util.LogUtil;
import com.agjsj.fleamarket.util.SearchALG;
import com.agjsj.fleamarket.view.HomeUI;
import com.agjsj.fleamarket.view.manager.BaseUI;
import com.agjsj.fleamarket.view.myview.SearchView;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by YH on 2017/3/4.
 */

public class SearchUI extends BaseUI {

    private SearchView searchView;
    private SearchALG searchALG;
    private List<String> changedHintDatas;
    //热搜数据
    private List<String> hot_datas;
    //提示列表数据
    private List<String> hint_datas;

    public SearchUI(Context context, FragmentManager fragmentManager) {
        super(context,fragmentManager);
    }
    @Override
    public void init() {
        showInMiddle = (LinearLayout)View.inflate(context, R.layout.activity_search, null);
        searchView = (SearchView) findViewById(R.id.searchView);


    }
    @Override
    public void setListener() {
        searchView.setOnSearchListener(new MyOnSearchListener());
    }
    @Override
    public void refreshView() {
        initData();
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
        for (int i = 0; i < 10; i++) {
            hot_datas.add("Android Hot " + i);
        }
        //设置热搜数据显示的列数
        searchView.setHotNumColumns(2);
        //设置热搜数据
        searchView.setHotSearchDatas(hot_datas);

        /**
         * 设置提示数据的集合
         */
        for (int i = 0; i < 10; i++) {
            hint_datas.add("ts"+"安卓学习" + "Android Hint " + i + " 你好");
        }
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
