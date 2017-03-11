package com.agjsj.fleamarket.adapter.goods;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.agjsj.fleamarket.R;
import com.agjsj.fleamarket.adapter.base.BaseViewHolder;
import com.agjsj.fleamarket.adapter.base.OnRecyclerViewListener;
import com.agjsj.fleamarket.util.PicassoUtils;

import butterknife.Bind;

/**
 * Created by MyPC on 2017/3/11.
 */

public class GoodsImageViewHolder extends BaseViewHolder {

    @Bind(R.id.iv_goods_item)
    ImageView iv_goods;

    public GoodsImageViewHolder(Context context, ViewGroup root, OnRecyclerViewListener listener) {
        super(context, root, R.layout.item_goods_image, listener);
    }

    @Override
    public void bindData(Object o) {
        String url = (String) o;
        PicassoUtils.loadResizeImage(url,R.drawable.logo, R.drawable.logo,210,150,iv_goods);
    }
}
