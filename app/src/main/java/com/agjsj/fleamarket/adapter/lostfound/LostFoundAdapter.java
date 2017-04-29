package com.agjsj.fleamarket.adapter.lostfound;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import com.agjsj.fleamarket.adapter.base.OnRecyclerViewImageListener;
import com.agjsj.fleamarket.adapter.base.OnRecyclerViewListener;
import com.agjsj.fleamarket.bean.FoundCase;

import java.util.List;

/**
 * Created by MyPC on 2017/4/29.
 */
public class LostFoundAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private Context context;
    private List<FoundCase> foundCaseList;

    public LostFoundAdapter(Context context, List<FoundCase> foundCaseList) {
        this.context = context;
        this.foundCaseList = foundCaseList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new LostFoundViewHolder(context,parent,onRecyclerViewListener);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((LostFoundViewHolder)holder).bindData(foundCaseList.get(position));
    }

    @Override
    public int getItemCount() {
        return foundCaseList.size();
    }

    private OnRecyclerViewListener onRecyclerViewListener;

    public void setOnRecyclerViewListener(OnRecyclerViewListener onRecyclerViewListener) {
        this.onRecyclerViewListener = onRecyclerViewListener;
    }
}
