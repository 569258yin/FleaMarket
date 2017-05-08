package com.agjsj.fleamarket.adapter.me;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import com.agjsj.fleamarket.adapter.base.OnRecyclerViewListener;
import com.agjsj.fleamarket.bean.FoundCase;
import com.agjsj.fleamarket.bean.Goods;

import java.util.HashMap;
import java.util.List;

/**
 * Created by YH on 2017/5/1.
 */

public class MySendFoundCaseAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private Context context;
    private List<FoundCase> foundCaseList;
    private HashMap<String,OnRecyclerViewListener> listenerHashMap;

    public MySendFoundCaseAdapter(Context context, List<FoundCase> goodsList) {
        this.context = context;
        this.foundCaseList = goodsList;
    }

    public void setListenerHashMap(HashMap<String, OnRecyclerViewListener> listenerHashMap) {
        this.listenerHashMap = listenerHashMap;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MySendFoundCaseViewHolder(context,parent, listenerHashMap);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((MySendFoundCaseViewHolder)holder).bindData(foundCaseList.get(position));
    }

    @Override
    public int getItemCount() {
        return foundCaseList.size();
    }

}
