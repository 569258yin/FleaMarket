package com.agjsj.fleamarket.view.goods;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

import com.agjsj.fleamarket.R;
import com.agjsj.fleamarket.adapter.goods.GoodsImageAdapter;
import com.agjsj.fleamarket.bean.Goods;
import com.agjsj.fleamarket.bean.UserInfo;
import com.agjsj.fleamarket.params.GlobalParams;
import com.agjsj.fleamarket.util.PicassoUtils;
import com.agjsj.fleamarket.util.TimeUtil;
import com.agjsj.fleamarket.util.Utility;
import com.agjsj.fleamarket.view.MainActivity;
import com.agjsj.fleamarket.view.base.BaseApplication;
import com.agjsj.fleamarket.view.manager.BaseUI;
import com.agjsj.fleamarket.view.myview.CircleImageView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by YH on 2017/3/30.
 */

public class GoodsDetailUI extends BaseUI {
    @Bind(R.id.ll_goodsdetail_img)
    RecyclerView recyclerView;
//    LinearLayout ll_img;
    @Bind(R.id.iv_goodsitem_icon)
    CircleImageView user_icon;
    @Bind(R.id.tv_goodsitem_name)
    TextView user_name;
    @Bind(R.id.tv_goodsitem_time)
    TextView goods_time;
    @Bind(R.id.tv_goodsitem_money)
    TextView goods_money;
    @Bind(R.id.tv_goods_replay_number)
    TextView goods_replay_num;
    @Bind(R.id.tv_goods_zan_number)
    TextView goods_zan_num;
    @Bind(R.id.tv_goodsdetail_context)
    TextView goods_context;
    
    private LinearLayoutManager layoutManager;
    private List<String> imagePathList;
    private GoodsImageAdapter adapter;

    public GoodsDetailUI(Context context) {
        super(context);
    }

    @Override
    public void init() {
        this.showInMiddle = (ScrollView) View.inflate(context, R.layout.activity_goods, null);
        ButterKnife.bind(this, showInMiddle);
        initRecycleView();
    }
    @Override
    public void refreshView() {
        this.showInMiddle.scrollTo(0,0);
        Goods goods = (Goods) ((MainActivity)context).getMiddleObj();
        imagePathList.clear();
        UserInfo userInfo = goods.getUserInfo();
        if(userInfo != null) {
            PicassoUtils.loadResizeImage(userInfo.getUsericon(), R.drawable.logo, R.drawable.logo, 100, 100,user_icon);
            user_name.setText(userInfo.getNickname());
        }
        SimpleDateFormat sdf = new SimpleDateFormat("MMM d, yyyy K:m:s a", Locale.ENGLISH);
        try {
            Date date = sdf.parse(goods.getGoodstime());
            goods_time.setText(TimeUtil.getChatTime(true,date.getTime()));
        } catch (ParseException e) {
        }
        goods_context.setText(goods.getGoodstext()+"");
        goods_replay_num.setText(goods.getGoodsrepalynum()+"");
        goods_zan_num.setText(goods.getGoodslikenum()+"");
        goods_money.setText(Utility.getMoney(goods.getGoodsoldmoney())+"");
        if(goods.getGoodsiconnumber() != null && goods.getGoodsiconnumber() > 0) {
            String[] strs = goods.getGoodsicon().split(GlobalParams.SPLIT_IMAGE_URL);
            if (strs != null && strs.length > 0) {
                for (int i = 0; i < strs.length; i++) {
                    imagePathList.add(strs[i]);
                }
            }
//            for (String imgPath :
//                    imagePathList) {
//                ImageView iv = new ImageView(context);
//                ll_img.addView(iv);
//                ViewGroup.LayoutParams layoutParams = iv.getLayoutParams();
//                int width = BaseApplication.INSTANCE().phoneWidth - 20;
//                int height = BaseApplication.INSTANCE().phoneHeight / 5 * 4;
//                layoutParams.width = width;
//                layoutParams.height = height ;
//                iv.setLayoutParams(layoutParams);
//                PicassoUtils.loadResizeImage(imgPath, R.drawable.logo, R.drawable.logo, width, height,iv);
//
//            }
        }
        adapter.notifyDataSetChanged();
    }

    private void initRecycleView() {
        imagePathList = new ArrayList<>();
        layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new GoodsImageAdapter(context,imagePathList,BaseApplication.INSTANCE().phoneWidth - 20,BaseApplication.INSTANCE().phoneHeight / 5 * 4 - 20);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void setListener() {

    }

    @Override
    public int getID() {
        return GlobalParams.VIEW_GOODSDETAIL;
    }
}
