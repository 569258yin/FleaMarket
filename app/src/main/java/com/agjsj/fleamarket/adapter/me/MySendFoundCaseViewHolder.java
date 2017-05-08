package com.agjsj.fleamarket.adapter.me;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Bind;
import com.agjsj.fleamarket.R;
import com.agjsj.fleamarket.adapter.base.BaseViewHolder;
import com.agjsj.fleamarket.adapter.base.OnRecyclerViewListener;
import com.agjsj.fleamarket.bean.FoundCase;
import com.agjsj.fleamarket.bean.Goods;
import com.agjsj.fleamarket.params.GlobalParams;
import com.agjsj.fleamarket.util.PicassoUtils;
import com.agjsj.fleamarket.util.Utility;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;

/**
 * Created by YH on 2017/4/7.
 */

public class MySendFoundCaseViewHolder extends BaseViewHolder {

    @Bind(R.id.iv_me_goods)
    ImageView imageView;
    @Bind(R.id.tv_me_goods_context)
    TextView goodsTitle;
    @Bind(R.id.tv_me_goods_refresh)
    TextView refresh;
    @Bind(R.id.tv_me_goods_delete)
    TextView deleter;
    @Bind(R.id.ll_me_foundcase_img)
    LinearLayout llImg;

    private Context context;

    public MySendFoundCaseViewHolder(Context context, ViewGroup root, HashMap<String,OnRecyclerViewListener> listenerHashMap) {
        super(context, root, R.layout.item_me_foundcase, null);
        this.context = context;
        if(listenerHashMap != null) {
            final OnRecyclerViewListener refreshListen = listenerHashMap.get("refersh");
            if(refreshListen != null) {
                refresh.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        refreshListen.onItemClick(getAdapterPosition());
                    }
                });
            }

            final OnRecyclerViewListener deleteListen = listenerHashMap.get("delete");
            if(deleteListen != null) {
                deleter.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        deleteListen.onItemClick(getAdapterPosition());
                    }
                });
            }
        }
    }

    @Override
    public void bindData(Object o) {
        if(o instanceof FoundCase) {
            FoundCase foundCase = (FoundCase) o;
            if(foundCase != null) {
                goodsTitle.setText(foundCase.getFdccontext() + "");
                //加载图片
                if (StringUtils.isNotEmpty(foundCase.getFdcimage())) {
                    String[] strs = foundCase.getFdcimage().split(GlobalParams.SPLIT_IMAGE_URL);
                    if (strs != null && strs.length > 0) {
                        llImg.setVisibility(View.VISIBLE);
                        PicassoUtils.loadResizeImage(strs[0], 120, 120, imageView);
                    }
                } else {
                    llImg.setVisibility(View.GONE);
                }
            }
        }
    }
}
