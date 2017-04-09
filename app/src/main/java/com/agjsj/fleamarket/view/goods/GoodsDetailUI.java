package com.agjsj.fleamarket.view.goods;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.agjsj.fleamarket.R;
import com.agjsj.fleamarket.adapter.base.OnRecyclerViewListener;
import com.agjsj.fleamarket.adapter.discuss.DiscussAdapter;
import com.agjsj.fleamarket.adapter.goods.GoodsImageAdapter;
import com.agjsj.fleamarket.bean.Goods;
import com.agjsj.fleamarket.bean.Goodsrepaly;
import com.agjsj.fleamarket.bean.Torepaly;
import com.agjsj.fleamarket.bean.UserInfo;
import com.agjsj.fleamarket.engine.ReplayEngine;
import com.agjsj.fleamarket.params.GlobalParams;
import com.agjsj.fleamarket.util.BeanFactory;
import com.agjsj.fleamarket.util.PicassoUtils;
import com.agjsj.fleamarket.util.TimeUtil;
import com.agjsj.fleamarket.util.Utility;
import com.agjsj.fleamarket.view.MainActivity;
import com.agjsj.fleamarket.view.base.BaseApplication;
import com.agjsj.fleamarket.view.manager.BaseUI;
import com.agjsj.fleamarket.view.myview.CircleImageView;

import org.apache.commons.lang3.StringUtils;

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
    @Bind(R.id.scrollView_goodsinfo)
    ScrollView scrollView;
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
    @Bind(R.id.recyclerview_discuss)
    RecyclerView recyclerView_discuss;
    @Bind(R.id.btn_chat_keyboard)
    Button keyBtn;
    @Bind(R.id.edit_msg)
    EditText editText;
    @Bind(R.id.btn_chat_send)
    Button sendBtn;
    
    private LinearLayoutManager layoutManager;
    private List<String> imagePathList;
    private GoodsImageAdapter adapter;
    private LinearLayoutManager linearLayoutManager_dis;
    //当前回复哪个人评论，默认为本宝贝发布在id
    private String currentToUserID = null;
    //当前回复的哪个评论
    private int currentPosition = -1;
    private Goods goods;
    private List<Goodsrepaly> goodsrepalyList = new ArrayList<Goodsrepaly>();
    private DiscussAdapter discussAdapter;


    public GoodsDetailUI(Context context) {
        super(context);
    }

    @Override
    public void init() {
        this.showInMiddle = (RelativeLayout) View.inflate(context, R.layout.activity_goods, null);
        ButterKnife.bind(this, showInMiddle);
        initRecycleView();
    }
    @Override
    public void refreshView() {
        scrollView.scrollTo(0,0);
        editText.setText("");
        currentToUserID = null;
        goods = (Goods) ((MainActivity)context).getMiddleObj();
        if(goods != null) {
            goodsrepalyList.clear();
            getGoodsReplay(goods);
            imagePathList.clear();
            UserInfo userInfo = goods.getUserInfo();
            if (userInfo != null) {
                PicassoUtils.loadResizeImage(userInfo.getUsericon(), R.drawable.logo, R.drawable.logo, 100, 100, user_icon);
                user_name.setText(userInfo.getNickname());
            }
            SimpleDateFormat sdf = new SimpleDateFormat("MMM d, yyyy K:m:s a", Locale.ENGLISH);
            try {
                Date date = sdf.parse(goods.getGoodstime());
                goods_time.setText(TimeUtil.getChatTime(true, date.getTime()));
            } catch (ParseException e) {
            }
            goods_context.setText(goods.getGoodstext() + "");
            goods_replay_num.setText(goods.getGoodsrepalynum() + "");
            goods_zan_num.setText(goods.getGoodslikenum() + "");
            goods_money.setText(Utility.getMoney(goods.getGoodsoldmoney()) + "");
            if (goods.getGoodsiconnumber() != null && goods.getGoodsiconnumber() > 0) {
                String[] strs = goods.getGoodsicon().split(GlobalParams.SPLIT_IMAGE_URL);
                if (strs != null && strs.length > 0) {
                    for (int i = 0; i < strs.length; i++) {
                        imagePathList.add(strs[i]);
                    }
                }
            }
            //加载评论
                discussAdapter = new DiscussAdapter(context, goodsrepalyList);
                discussAdapter.setOnRecyclerViewListener(new OnRecyclerViewListener() {
                    @Override
                    public void onItemClick(int position) {
                        String toUserName = goods.getGoodsrepalyList().get(position).getUserInfo().getNickname();
                        currentToUserID = goods.getGoodsrepalyList().get(position).getUserInfo().getUserid();
                        currentPosition = position;
                        editText.setHint("@"+toUserName);
                        InputMethodManager imm = (InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.toggleSoftInput(0,InputMethodManager.HIDE_NOT_ALWAYS);
                    }

                    @Override
                    public void onItemClick(int position, int id) {

                    }

                    @Override
                    public boolean onItemLongClick(int position) {
                        return false;
                    }
                });
                recyclerView_discuss.setAdapter(discussAdapter);
            adapter.notifyDataSetChanged();
        }
    }

    private void initRecycleView() {
        imagePathList = new ArrayList<>();
        layoutManager = new LinearLayoutManager(context){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new GoodsImageAdapter(context,imagePathList,-1,BaseApplication.INSTANCE().phoneWidth - 20,BaseApplication.INSTANCE().phoneHeight / 5 * 4 - 20);
        recyclerView.setAdapter(adapter);


        linearLayoutManager_dis = new LinearLayoutManager(context){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        recyclerView_discuss.setLayoutManager(linearLayoutManager_dis);
    }

    private void getGoodsReplay(final Goods goods){
        ReplayEngine replayEngine = BeanFactory.getImpl(ReplayEngine.class);
        if (replayEngine != null){
            replayEngine.getAllReplayOfgoodsid(goods.getGoodsid(), new ReplayEngine.GetAllReplayCallBack() {
                @Override
                public void getAllReplayCallback(List<Goodsrepaly> lists) {
                    if(lists != null){
                        goodsrepalyList.addAll(lists);
                        goods.setGoodsrepalyList(goodsrepalyList);
                        discussAdapter.notifyDataSetChanged();
                    }
                }
            });

        }
    }

    @Override
    public void setListener() {
        keyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(editText.getWindowToken(),0);
                editText.setText("");
                editText.setHint("输入文字信息");
            }
        });
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = editText.getText().toString();
                if(StringUtils.isNotEmpty(text)){
                    //回复当前宝贝发布人
                    if(currentToUserID == null){
                        ReplayEngine replayEngine = BeanFactory.getImpl(ReplayEngine.class);
                        if (replayEngine != null){
                            final Goodsrepaly goodsrepaly; goodsrepaly = new Goodsrepaly(goods.getGoodsid(),
                                    BaseApplication.INSTANCE().getCurrentUser().getUserid(),text);
                            goodsrepaly.setGoodsreplaytime(new Date());
                            goodsrepaly.setUserInfo(BaseApplication.INSTANCE().getCurrentUser());
                            replayEngine.sendReplay(goodsrepaly, new ReplayEngine.SendReplayCallBack(){
                                @Override
                                public void sendReplayCallback(int responseCode) {
                                    if(responseCode == ReplayEngine.SEND_OK) {
                                        List<Goodsrepaly> tempList = new ArrayList<Goodsrepaly>();
                                        tempList.addAll(goodsrepalyList);
                                        goodsrepalyList.clear();
                                        goodsrepalyList.add(goodsrepaly);
                                        goodsrepalyList.addAll(tempList);
                                        tempList = null;
                                        discussAdapter.notifyDataSetChanged();
                                        InputMethodManager imm = (InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE);
                                        imm.hideSoftInputFromWindow(editText.getWindowToken(),0);
                                        editText.setText("");
                                        editText.setHint("输入文字信息");
                                        ((MainActivity)context).toast("发送评论成功！");
                                    } else {
                                        ((MainActivity)context).toast("发送评论失败！");
                                    }
                                }
                            });
                        }
                    }else{//回复评论人
                        ReplayEngine replayEngine = BeanFactory.getImpl(ReplayEngine.class);
                        if (replayEngine != null){
                            final Torepaly torepaly = new Torepaly(goods.getGoodsid(),BaseApplication.INSTANCE().getCurrentUser().getUserid(),currentToUserID
                            ,text,new Date());
                            replayEngine.sendToReplay(torepaly, new ReplayEngine.SendReplayCallBack() {
                                @Override
                                public void sendReplayCallback(int responseCode) {
                                    if(responseCode == ReplayEngine.SEND_OK) {
                                        if(currentPosition != -1){
                                            Goodsrepaly goodsrepaly = goodsrepalyList.get(currentPosition);
                                            if(goodsrepaly.getTorepalyList() != null) {
                                                List<Torepaly> tempToLists = new ArrayList<Torepaly>();
                                                tempToLists.addAll(goodsrepaly.getTorepalyList());
                                                goodsrepaly.getTorepalyList().clear();
                                                goodsrepaly.getTorepalyList().add(torepaly);
                                                goodsrepaly.getTorepalyList().addAll(tempToLists);
                                                tempToLists = null;
                                            }else {
                                                List<Torepaly> tempToLists = new ArrayList<Torepaly>();
                                                tempToLists.add(torepaly);
                                                goodsrepaly.setTorepalyList(tempToLists);
                                            }
                                            discussAdapter.notifyItemChanged(currentPosition);

                                        }
                                        InputMethodManager imm = (InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE);
                                        imm.hideSoftInputFromWindow(editText.getWindowToken(),0);
                                        editText.setText("");
                                        editText.setHint("输入文字信息");
                                        ((MainActivity)context).toast("发送评论成功！");
                                    }else {
                                        ((MainActivity)context).toast("发送评论失败！");
                                    }
                                }
                            });
                        }
                    }
                }else{
                    ((MainActivity)context).toast("请输入内容！");
                }

            }
        });
    }

    @Override
    public int getID() {
        return GlobalParams.VIEW_GOODSDETAIL;
    }
}
