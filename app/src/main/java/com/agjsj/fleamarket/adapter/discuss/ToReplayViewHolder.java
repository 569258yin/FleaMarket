package com.agjsj.fleamarket.adapter.discuss;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.TextView;


import com.agjsj.fleamarket.R;
import com.agjsj.fleamarket.adapter.base.BaseViewHolder;
import com.agjsj.fleamarket.adapter.base.OnRecyclerViewListener;
import com.agjsj.fleamarket.bean.Torepaly;

import butterknife.Bind;

/**
 * Created by YH on 2017/04/03.
 */
public class ToReplayViewHolder extends BaseViewHolder {
    @Bind(R.id.tv_replayUserNickName)
    TextView tvReplayUserNickName;
    @Bind(R.id.tv_passiveUserNickName)
    TextView tvPassiveUserNickName;
    @Bind(R.id.tv_content)
    TextView tvContent;

    public ToReplayViewHolder(Context context, ViewGroup root, OnRecyclerViewListener listener) {
        super(context, root, R.layout.item_replay, listener);
    }

    @Override
    public void bindData(Object o) {
        Torepaly replay = (Torepaly) o;
        tvReplayUserNickName.setText(replay.getUsername());
        tvPassiveUserNickName.setText(replay.getTousername());
        tvContent.setText(":" + replay.getTorepalycontext());

    }
}
