package com.agjsj.fleamarket.adapter.lostfound;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Bind;
import com.agjsj.fleamarket.R;
import com.agjsj.fleamarket.adapter.base.BaseViewHolder;
import com.agjsj.fleamarket.adapter.base.OnRecyclerViewListener;
import com.agjsj.fleamarket.bean.FoundCase;
import com.agjsj.fleamarket.bean.UserInfo;
import com.agjsj.fleamarket.util.PicassoUtils;
import com.agjsj.fleamarket.util.TimeUtil;
import com.agjsj.fleamarket.view.myview.CircleImageView;
import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.util.Date;

/**
 * Created by MyPC on 2017/4/29.
 */
public class LostFoundViewHolder extends BaseViewHolder{

    @Bind(R.id.iv_goodsitem_icon)
    CircleImageView user_icon;
    @Bind(R.id.tv_goodsitem_name)
    TextView user_name;
    @Bind(R.id.tv_goodsitem_time)
    TextView sendTime;
    @Bind(R.id.tv_lost_title)
    TextView title;
    @Bind(R.id.iv_lost_img)
    ImageView image;
    @Bind(R.id.tv_bottom_connection)
    TextView connect;


    public LostFoundViewHolder(Context context, ViewGroup root, OnRecyclerViewListener listener) {
        super(context, root, R.layout.item_lost, listener);

        connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onRecyclerViewListener != null){
                    onRecyclerViewListener.onItemClick(getAdapterPosition());
                }
            }
        });
    }

    @Override
    public void bindData(Object o) {
        if(o instanceof FoundCase) {
            FoundCase foundCase = (FoundCase) o;
            UserInfo userInfo = foundCase.getUserInfo();
            if(userInfo != null) {
                PicassoUtils.loadResizeImage(userInfo.getUsericon(), R.drawable.logo, R.drawable.logo, 100, 100,user_icon);
                user_name.setText(userInfo.getNickname());
            }
            try {
                sendTime.setText(TimeUtil.getChatTime(true,foundCase.getFdctime().getTime()));
            } catch (Exception e) {
            }
            title.setText(foundCase.getFdccontext());
            if (StringUtils.isNotEmpty(foundCase.getFdcimage())) {
                image.setVisibility(View.VISIBLE);
                PicassoUtils.loadResizeImage(foundCase.getFdcimage(),200,480,image);
            }else {
                image.setVisibility(View.GONE);
            }

        }
    }
}
