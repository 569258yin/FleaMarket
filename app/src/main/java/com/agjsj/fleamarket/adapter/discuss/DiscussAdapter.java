package com.agjsj.fleamarket.adapter.discuss;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.agjsj.fleamarket.adapter.base.OnRecyclerViewListener;
import com.agjsj.fleamarket.bean.Goodsrepaly;

import java.util.List;

/**
 * Created by YH on 2017/4/3.
 */

public class DiscussAdapter extends RecyclerView.Adapter{

    private Context context;
    private List<Goodsrepaly> goodsrepalyList;

    public DiscussAdapter(Context context, List<Goodsrepaly> goodsrepalyList) {
        this.context = context;
        this.goodsrepalyList = goodsrepalyList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new DiscussViewHolder(context,null,onRecyclerViewListener);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((DiscussViewHolder)holder).bindData(goodsrepalyList.get(position));
    }

    @Override
    public int getItemCount() {
        return goodsrepalyList.size();
    }

    private OnRecyclerViewListener onRecyclerViewListener;

    public void setOnRecyclerViewListener(OnRecyclerViewListener onRecyclerViewListener) {
        this.onRecyclerViewListener = onRecyclerViewListener;
    }
}
