package com.agjsj.fleamarket.adapter.me;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import com.agjsj.fleamarket.adapter.base.OnRecyclerViewImageListener;
import com.agjsj.fleamarket.adapter.base.OnRecyclerViewListener;
import com.agjsj.fleamarket.bean.Goods;

import java.util.HashMap;
import java.util.List;

/**
 * Created by YH on 2017/3/7.
 */

public class MySendGoodsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private Context context;
    private List<Goods> goodsList;
    private HashMap<String,OnRecyclerViewListener> listenerHashMap;

    public MySendGoodsAdapter(Context context, List<Goods> goodsList) {
        this.context = context;
        this.goodsList = goodsList;
    }

    public void setListenerHashMap(HashMap<String, OnRecyclerViewListener> listenerHashMap) {
        this.listenerHashMap = listenerHashMap;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MySendGoodsViewHolder(context,parent, listenerHashMap);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((MySendGoodsViewHolder)holder).bindData(goodsList.get(position));
    }

    @Override
    public int getItemCount() {
        return goodsList.size();
    }

}
