package com.agjsj.fleamarket.adapter.goods;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.agjsj.fleamarket.R;
import com.agjsj.fleamarket.adapter.base.BaseViewHolder;
import com.agjsj.fleamarket.adapter.base.OnRecyclerViewImageListener;
import com.agjsj.fleamarket.adapter.base.OnRecyclerViewListener;
import com.agjsj.fleamarket.bean.Goods;
import com.agjsj.fleamarket.bean.UserInfo;
import com.agjsj.fleamarket.params.ConstantValue;
import com.agjsj.fleamarket.params.GlobalParams;
import com.agjsj.fleamarket.util.PicassoUtils;
import com.agjsj.fleamarket.util.TimeUtil;
import com.agjsj.fleamarket.view.myview.CircleImageView;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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
    private GoodsImageAdapter goodsImageAdapter;
    private OnRecyclerViewImageListener onRecyclerViewImageListener;
    private Context context;
    private LinearLayoutManager layoutManager;

    public GoodsViewHolder(Context context, ViewGroup root,OnRecyclerViewListener listener,OnRecyclerViewImageListener onRecyclerViewImageListener) {
        super(context, root, R.layout.goods_list_item, listener);
        this.context = context;
        this.onRecyclerViewImageListener = onRecyclerViewImageListener;
        layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);

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
            SimpleDateFormat sdf = new SimpleDateFormat("MMM d, yyyy K:m:s a", Locale.ENGLISH);
            try {
              Date date = sdf.parse(goods.getGoodstime());
                goods_time.setText(TimeUtil.getChatTime(true,date.getTime()));
            } catch (ParseException e) {
            }
            goods_content.setText(goods.getGoodstext()+"");
            goods_replay_num.setText(goods.getGoodsrepalynum()+"");
            goods_zan_num.setText(goods.getGoodslikenum()+"");
            goods_money.setText(getMoney(goods.getGoodsoldmoney())+"");

            if(goods.getGoodsiconnumber() != null && goods.getGoodsiconnumber() > 0){
                List<String> urls = new ArrayList<>(6);
                String[] strs = goods.getGoodsicon().split(GlobalParams.SPLIT_IMAGE_URL);
                if(strs != null && strs.length > 0){
                    for (int i=0; i< strs.length;i++){
                        urls.add(strs[i]);
                    }

                    goodsImageAdapter = new GoodsImageAdapter(context,urls);
                    recyclerView.setAdapter(goodsImageAdapter);
                }
            }
        }
        goodsItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRecyclerViewListener.onItemClick(getAdapterPosition());
            }
        });
    }

    private String getMoney(int money){
        return new BigDecimal(money).divide(new BigDecimal(100)).setScale(2,BigDecimal.ROUND_CEILING).toString();
    }
}
