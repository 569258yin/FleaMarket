package com.agjsj.fleamarket.adapter.goods;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.agjsj.fleamarket.R;
import com.agjsj.fleamarket.adapter.base.OnRecyclerViewImageListener;
import com.agjsj.fleamarket.adapter.base.OnRecyclerViewListener;
import com.agjsj.fleamarket.bean.Goods;

import java.util.List;

/**
 * Created by YH on 2017/3/7.
 */

public class GoodsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private Context context;
    private List<Goods> goodsList;

    public GoodsAdapter(Context context, List<Goods> goodsList) {
        this.context = context;
        this.goodsList = goodsList;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new GoodsViewHolder(context,parent, onRecyclerViewListener,onRecyclerViewImageListener);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((GoodsViewHolder)holder).bindData(goodsList.get(position));
    }

    @Override
    public int getItemCount() {
        return goodsList.size();
    }

    private OnRecyclerViewListener onRecyclerViewListener;

    public void setOnRecyclerViewListener(OnRecyclerViewListener onRecyclerViewListener) {
        this.onRecyclerViewListener = onRecyclerViewListener;
    }

    private OnRecyclerViewImageListener onRecyclerViewImageListener;

    public void setOnRecyclerViewImageListener(OnRecyclerViewImageListener onRecyclerViewImageListener) {
        this.onRecyclerViewImageListener = onRecyclerViewImageListener;
    }

}
