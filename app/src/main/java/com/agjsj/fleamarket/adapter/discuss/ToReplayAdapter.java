package com.agjsj.fleamarket.adapter.discuss;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.agjsj.fleamarket.adapter.base.BaseViewHolder;
import com.agjsj.fleamarket.adapter.base.OnRecyclerViewListener;
import com.agjsj.fleamarket.bean.Torepaly;


import java.util.List;

/**
 * Created by YH on 2017/4/3.
 */
public class ToReplayAdapter extends RecyclerView.Adapter {
    private Context context;
    private List<Torepaly> replays;

    public ToReplayAdapter(Context context, List<Torepaly> replays) {
        this.context = context;
        this.replays = replays;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ToReplayViewHolder(context, parent, onRecyclerViewListener);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        ((BaseViewHolder) holder).bindData(replays.get(position));
    }

    @Override
    public int getItemCount() {
        return replays.size();
    }

    private OnRecyclerViewListener onRecyclerViewListener;

    public void setOnRecyclerViewListener(OnRecyclerViewListener onRecyclerViewListener) {
        this.onRecyclerViewListener = onRecyclerViewListener;
    }

}
