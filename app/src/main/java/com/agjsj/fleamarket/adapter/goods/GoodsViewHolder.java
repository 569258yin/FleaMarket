package com.agjsj.fleamarket.adapter.goods;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.agjsj.fleamarket.R;
import com.agjsj.fleamarket.adapter.base.BaseViewHolder;
import com.agjsj.fleamarket.adapter.base.OnRecyclerViewListener;
import com.agjsj.fleamarket.bean.Goods;
import com.agjsj.fleamarket.bean.UserInfo;
import com.agjsj.fleamarket.util.PicassoUtils;
import com.agjsj.fleamarket.view.myview.CircleImageView;

import butterknife.Bind;

/**
 * Created by MyPC on 2017/3/7.
 */

public class GoodsViewHolder extends BaseViewHolder {
    @Bind(R.id.ll_goods_item)
    LinearLayout goodsItem;
    @Bind(R.id.iv_goodsitem_icon)
    CircleImageView user_icon;
    @Bind(R.id.tv_goodsitem_name)
    TextView user_name;
    @Bind(R.id.tv_goodsitem_time)
    TextView goods_time;
    @Bind(R.id.tv_goodsitem_money)
    TextView goods_money;
    @Bind(R.id.recycleview_goods_item)
    RecyclerView recyclerView;
    @Bind(R.id.tv_goodsitem_content)
    TextView goods_content;
    @Bind(R.id.tv_goods_replay_number)
    TextView goods_replay_num;
    @Bind(R.id.tv_goods_zan_number)
    TextView goods_zan_num;



    public GoodsViewHolder(Context context, ViewGroup root, int layoutRes, OnRecyclerViewListener listener) {
        super(context, root, R.layout.goods_list_item, listener);
    }

    @Override
    public void bindData(Object o) {
        if(o instanceof  Goods) {
            Goods goods = (Goods) o;
            UserInfo userInfo = goods.getUserInfo();
            if(userInfo != null) {
                PicassoUtils.loadResizeImage(userInfo.getUsericon(), R.drawable.logo, R.drawable.logo, 100, 100,user_icon);
                user_name.setText(userInfo.getNickname());
            }
            goods_time.setText(goods.getGoodstime());
            goods_money.setText(goods.getGoodsoldmoney());
            goods_content.setText(goods.getGoodstext());
            goods_replay_num.setText(goods.getGoodsrepalynumber());
            goods_zan_num.setText(goods.getGoodslikenumber());
        }
        goodsItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }
}
