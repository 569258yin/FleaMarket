package com.agjsj.fleamarket.view.goods;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import butterknife.Bind;
import com.agjsj.fleamarket.R;
import com.agjsj.fleamarket.adapter.goods.GoodsCategoryAdapter;
import com.agjsj.fleamarket.bean.GoodsType;
import com.agjsj.fleamarket.engine.BaseCallBack;
import com.agjsj.fleamarket.engine.GoodsEngine;
import com.agjsj.fleamarket.util.BeanFactory;
import com.agjsj.fleamarket.view.base.BaseActivity;
import com.agjsj.fleamarket.view.base.BaseApplication;
import com.agjsj.fleamarket.view.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by YH on 2017/4/15.
 */

public class GoodsCategoryActivity extends BaseActivity {

    @Bind(R.id.list_goods_category)
    ListView listView;
    @Bind(R.id.vp_goods_category)
    ViewPager viewPager;
    @Bind(R.id.tv_title_back)
    TextView tvTitleBack;
    @Bind(R.id.tv_title_text)
    TextView tvTitleText;

    private List<GoodsType> goodsTypeList;
    private GoodsCategoryAdapter adapter;
    private GoodsViewPagerAdapter viewPagerAdapter;
    private List<BaseFragment> viewList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_category);
    }

    @Override
    protected void initView() {
        super.initView();
        goodsTypeList = BaseApplication.INSTANCE().getGoodstypes();
        if (goodsTypeList == null) {
            goodsTypeList = new ArrayList<>();
            GoodsEngine goodsEngine = BeanFactory.getImpl(GoodsEngine.class);
            goodsEngine.getAllGoodsType(new BaseCallBack.GetAllListCallBack<GoodsType>() {
                @Override
                public void getAllResultCallBack(List<GoodsType> _goodsTypeList) {
                    if (_goodsTypeList != null) {
                        BaseApplication.INSTANCE().setGoodstypes(_goodsTypeList);
                        goodsTypeList.addAll(_goodsTypeList);
                        initListViewAndViewPager();
                    } else {
                        toast("商品类别为null");
                        finish();
                    }
                }
            });
        } else {
            initListViewAndViewPager();
        }

        tvTitleBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        tvTitleText.setText("分类浏览");
    }

    private void initListViewAndViewPager() {
        adapter = new GoodsCategoryAdapter(goodsTypeList, getApplicationContext());
        adapter.setTextViewCallBack(new GoodsCategoryAdapter.TextViewCallBack() {
            @Override
            public void OnTextViewClick(GoodsType goodsType, int position) {
                viewList.get(position).setUserVisibleHint(true);
                viewPager.setCurrentItem(position);
            }
        });
        listView.setAdapter(adapter);
        for (int i = 0; i < goodsTypeList.size(); i++) {
            GoodsType goodsType = goodsTypeList.get(i);
            GoodsFragment fragment = new GoodsFragment();
            Bundle args = new Bundle();
            args.putInt("goodsTypeId", goodsType.getGoodstypeid());
            fragment.setArguments(args);
            viewList.add(fragment);
        }
        viewPagerAdapter = new GoodsViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.setOffscreenPageLimit(goodsTypeList.size() - 1);
        viewPager.addOnPageChangeListener(new MyOnPageChangeListener());
        viewPager.setCurrentItem(0);
        viewList.get(0).setUserVisibleHint(true);
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    Thread.sleep(200);
//                } catch (InterruptedException e) {
//                }
//                viewList.get(0).onRefresh();
//            }
//        }).start();
    }


    class GoodsViewPagerAdapter extends FragmentPagerAdapter {

        public GoodsViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return viewList.get(position);
        }

        @Override
        public int getCount() {
            return viewList.size();
        }


    }

    public class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onPageScrollStateChanged(int arg0) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onPageSelected(int arg0) {
            viewList.get(arg0).setUserVisibleHint(true);
        }
    }


}
