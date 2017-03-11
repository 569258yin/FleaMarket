package com.agjsj.fleamarket.adapter.goods;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.agjsj.fleamarket.adapter.base.OnRecyclerViewListener;

import java.util.List;

/**
 * Created by MyPC on 2017/3/11.
 */

public class GoodsImageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private List<String> goodImageLists;
    private Context context;

    public GoodsImageAdapter(Context context ,List<String> goodImageLists) {
        this.goodImageLists = goodImageLists;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new GoodsImageViewHolder(context,parent,onRecyclerViewListener);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((GoodsImageViewHolder)holder).bindData(goodImageLists.get(position));
    }

    @Override
    public int getItemCount() {
        return goodImageLists.size();
    }

    private OnRecyclerViewListener onRecyclerViewListener;

    public void setOnRecyclerViewListener(OnRecyclerViewListener onRecyclerViewListener) {
        this.onRecyclerViewListener = onRecyclerViewListener;
    }
}
