package com.agjsj.fleamarket.adapter.goods;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.agjsj.fleamarket.adapter.base.OnRecyclerViewListener;

import java.util.List;

/**
 * Created by YH on 2017/3/11.
 */

public class GoodsImageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private List<String> goodImageLists;
    private Context context;
    private int width = -1;
    private int height = -1;

    public GoodsImageAdapter(Context context ,List<String> goodImageLists) {
        this.goodImageLists = goodImageLists;
        this.context = context;
    }


    public GoodsImageAdapter(Context context ,List<String> goodImageLists,int width,int height) {
        this.goodImageLists = goodImageLists;
        this.context = context;
        this.width = width;
        this.height = height;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(width < 0 || height < 0) {
            return new GoodsImageViewHolder(context, parent, onRecyclerViewListener);
        }else{
            return new GoodsImageViewHolder(context,parent,onRecyclerViewListener,width,height);
        }
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
