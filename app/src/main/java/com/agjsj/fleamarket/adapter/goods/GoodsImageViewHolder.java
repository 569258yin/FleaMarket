package com.agjsj.fleamarket.adapter.goods;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.agjsj.fleamarket.R;
import com.agjsj.fleamarket.adapter.base.BaseViewHolder;
import com.agjsj.fleamarket.adapter.base.OnRecyclerViewListener;
import com.agjsj.fleamarket.util.DensityTool;
import com.agjsj.fleamarket.util.PicassoUtils;
import com.agjsj.fleamarket.view.base.BaseApplication;

import butterknife.Bind;

/**
 * Created by MyPC on 2017/3/11.
 */

public class GoodsImageViewHolder extends BaseViewHolder {

    @Bind(R.id.iv_goods_item)
    ImageView iv_goods;
    private int _width = BaseApplication.INSTANCE().phoneWidth / 4 * 3;
    private int _height = BaseApplication.INSTANCE().phoneHeight / 4 ;

    public GoodsImageViewHolder(Context context, ViewGroup root, final OnRecyclerViewListener listener) {
        super(context, root, R.layout.item_goods_image, listener);
        iv_goods.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(getAdapterPosition());
            }
        });
    }

    public GoodsImageViewHolder(Context context, ViewGroup root, OnRecyclerViewListener listener,int width,int height) {
        super(context, root, R.layout.item_goods_image, listener);
        this._width = width;
        this._height = height;
    }

    @Override
    public void bindData(Object o) {
        ViewGroup.LayoutParams layoutParams = iv_goods.getLayoutParams();
        layoutParams.width = _width;
        layoutParams.height = _height ;
        iv_goods.setLayoutParams(layoutParams);
        String url = (String) o;
        PicassoUtils.loadResizeImage(url,_width,_height,iv_goods);
    }
}
