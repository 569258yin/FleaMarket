package com.agjsj.fleamarket.adapter.discuss;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.agjsj.fleamarket.R;
import com.agjsj.fleamarket.adapter.base.BaseViewHolder;
import com.agjsj.fleamarket.adapter.base.OnRecyclerViewListener;
import com.agjsj.fleamarket.bean.Goodsrepaly;
import com.agjsj.fleamarket.util.PicassoUtils;
import com.agjsj.fleamarket.util.TimeUtil;
import com.agjsj.fleamarket.view.myview.CircleImageView;

import butterknife.Bind;

/**
 * Created by YH on 2017/04/03.
 */
public class DiscussViewHolder extends BaseViewHolder {
    @Bind(R.id.iv_user_icon)
    CircleImageView ivUserIcon;
    @Bind(R.id.tv_nickname)
    TextView tvNickname;
    @Bind(R.id.tv_time)
    TextView tvTime;
    @Bind(R.id.tv_replay)
    TextView tvReplay;
    @Bind(R.id.tv_content)
    TextView tvContent;
    @Bind(R.id.recyclerview)
    RecyclerView recyclerview;

    public DiscussViewHolder(Context context, ViewGroup root, final OnRecyclerViewListener listener) {
        super(context, root, R.layout.item_discuss, listener);
        tvReplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener != null){
                    listener.onItemClick(getAdapterPosition());
                }
            }
        });

    }

    @Override
    public void bindData(Object o) {
        Goodsrepaly discuss = (Goodsrepaly) o;
        if(discuss.getUserInfo() != null) {
            if(discuss.getUserInfo().getUsericon() != null && !discuss.getUserInfo().getUsericon().equals("")) {
                PicassoUtils.loadResizeImage(discuss.getUserInfo().getUsericon(), R.drawable.logo, R.drawable.logo, 50, 50, ivUserIcon);
            }
            tvNickname.setText(discuss.getUserInfo().getNickname());
        }

        try {
            tvTime.setText(TimeUtil.getChatTime(false, discuss.getGoodsreplaytime().getTime()));
        }catch (Exception e){}
        tvContent.setText(discuss.getGoodsreplaycontent());

        recyclerview.setLayoutManager(new LinearLayoutManager(context));
        if(discuss.getTorepalyList() != null) {
            ToReplayAdapter adapter = new ToReplayAdapter(context, discuss.getTorepalyList());
            recyclerview.setAdapter(adapter);
        }
    }
}
