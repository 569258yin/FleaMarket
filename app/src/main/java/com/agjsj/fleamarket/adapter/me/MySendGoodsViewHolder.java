package com.agjsj.fleamarket.adapter.me;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Bind;
import com.agjsj.fleamarket.R;
import com.agjsj.fleamarket.adapter.base.BaseViewHolder;
import com.agjsj.fleamarket.adapter.base.OnRecyclerViewImageListener;
import com.agjsj.fleamarket.adapter.base.OnRecyclerViewListener;
import com.agjsj.fleamarket.adapter.goods.GoodsImageAdapter;
import com.agjsj.fleamarket.bean.Goods;
import com.agjsj.fleamarket.bean.UserInfo;
import com.agjsj.fleamarket.params.GlobalParams;
import com.agjsj.fleamarket.util.PicassoUtils;
import com.agjsj.fleamarket.util.TimeUtil;
import com.agjsj.fleamarket.util.Utility;
import com.agjsj.fleamarket.view.myview.CircleImageView;
import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by YH on 2017/4/7.
 */

public class MySendGoodsViewHolder extends BaseViewHolder {

    @Bind(R.id.iv_me_goods)
    ImageView imageView;
    @Bind(R.id.tv_me_goods_context)
    TextView goodsTitle;
    @Bind(R.id.tv_me_goods_price)
    TextView goodsPrice;
    @Bind(R.id.tv_me_goods_refresh)
    TextView refresh;
    @Bind(R.id.tv_me_goods_update)
    TextView update;
    @Bind(R.id.tv_me_goods_delete)
    TextView deleter;
    @Bind(R.id.ll_me_goods)
    LinearLayout item;

    private Context context;

    public MySendGoodsViewHolder(Context context, ViewGroup root, HashMap<String,OnRecyclerViewListener> listenerHashMap) {
        super(context, root, R.layout.item_me_goods, null);
        this.context = context;
        if(listenerHashMap != null) {
            final OnRecyclerViewListener updateListen = listenerHashMap.get("update");
            if(updateListen != null) {
                update.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        updateListen.onItemClick(getAdapterPosition());
                    }
                });
            }

            final OnRecyclerViewListener refreshListen = listenerHashMap.get("refersh");
            if(refreshListen != null) {
                refresh.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        refreshListen.onItemClick(getAdapterPosition());
                    }
                });
            }

            final OnRecyclerViewListener deleteListen = listenerHashMap.get("delete");
            if(deleteListen != null) {
                deleter.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        deleteListen.onItemClick(getAdapterPosition());
                    }
                });
            }
            final OnRecyclerViewListener itemListen = listenerHashMap.get("item");
            if(itemListen != null) {
                item.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        itemListen.onItemClick(getAdapterPosition());
                    }
                });
            }
        }
    }

    @Override
    public void bindData(Object o) {
        if(o instanceof  Goods) {
            Goods goods = (Goods) o;
            if(o!= null) {
                goodsTitle.setText(goods.getGoodstitle() + "");
                goodsPrice.setText(Utility.getMoney(goods.getGoodsoldmoney()) + "");
                //加载图片
                if (goods.getGoodsiconnumber() != null && goods.getGoodsiconnumber() > 0 && StringUtils.isNotEmpty(goods.getGoodsicon())) {
                    String[] strs = goods.getGoodsicon().split(GlobalParams.SPLIT_IMAGE_URL);
                    if (strs != null && strs.length > 0) {
                        imageView.setVisibility(View.VISIBLE);
                        PicassoUtils.loadResizeImage(strs[0], 120, 120, imageView);
                    }
                } else {
                    imageView.setVisibility(View.GONE);
                }
            }
        }
    }
}
