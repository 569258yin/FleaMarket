package com.agjsj.fleamarket.view.goods;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.*;

import butterknife.Bind;

import com.agjsj.fleamarket.R;
import com.agjsj.fleamarket.adapter.base.OnRecyclerViewListener;
import com.agjsj.fleamarket.adapter.discuss.DiscussAdapter;
import com.agjsj.fleamarket.adapter.goods.GoodsImageAdapter;
import com.agjsj.fleamarket.bean.Goods;
import com.agjsj.fleamarket.bean.Goodsrepaly;
import com.agjsj.fleamarket.bean.Torepaly;
import com.agjsj.fleamarket.bean.UserInfo;
import com.agjsj.fleamarket.engine.BaseCallBack;
import com.agjsj.fleamarket.engine.ReplayEngine;
import com.agjsj.fleamarket.params.GlobalParams;
import com.agjsj.fleamarket.util.BeanFactory;
import com.agjsj.fleamarket.util.PicassoUtils;
import com.agjsj.fleamarket.util.TimeUtil;
import com.agjsj.fleamarket.util.Utility;
import com.agjsj.fleamarket.view.MessageUI;
import com.agjsj.fleamarket.view.base.BaseActivity;
import com.agjsj.fleamarket.view.base.BaseApplication;
import com.agjsj.fleamarket.view.manager.MiddleManager;
import com.agjsj.fleamarket.view.message.ChatActivity;
import com.agjsj.fleamarket.view.myview.CircleImageView;
import com.hyphenate.chat.EMClient;

import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by YH on 2017/3/30.
 */

public class GoodsDetailActivity extends BaseActivity {
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
    @Bind(R.id.commom_title)
    RelativeLayout common_title;
    @Bind(R.id.tv_title_back)
    TextView tvTitleBack;
    @Bind(R.id.tv_title_text)
    TextView tvTitleText;
    @Bind(R.id.ll_user_info)
    LinearLayout llUserInfo;


    private LinearLayoutManager layoutManager;
    private List<String> imagePathList = new ArrayList<>();
    ;
    private GoodsImageAdapter adapter;
    private LinearLayoutManager linearLayoutManager_dis;
    private String currentUserName = "";
    //当前回复哪个人评论，默认为本宝贝发布在id
    private String currentToUserID = null;
    //当前回复的哪个评论
    private int currentPosition = -1;
    private Goods goods;
    private List<Goodsrepaly> goodsrepalyList = new ArrayList<Goodsrepaly>();
    private DiscussAdapter discussAdapter;

    private GoodsDetailActivity instance;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods);
        instance = this;
        common_title.setVisibility(View.VISIBLE);
        goods = (Goods) getBundle().getSerializable("goods");
        tvTitleBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideSoftKeyboard();
                finish();
            }
        });
        tvTitleText.setText("商品详情");
        initRecycleView();
        setListener();
        refreshView();
    }

    public void refreshView() {
        scrollView.scrollTo(0, 0);
        editText.setText("");
        currentToUserID = null;
        if (goods != null) {
            goodsrepalyList.clear();
            getGoodsReplay(goods);


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
            goods_context.setText(goods.getGoodstitle() +"  " + goods.getGoodstext() + "");
            goods_replay_num.setText(goods.getGoodsrepalynum() + "");
            goods_zan_num.setText(goods.getGoodslikenum() + "");
            goods_money.setText(Utility.getMoney(goods.getGoodsoldmoney()) + "");
            if (goods.getGoodsiconnumber() != null && goods.getGoodsiconnumber() > 0 && StringUtils.isNotEmpty(goods.getGoodsicon())) {
                imagePathList.clear();
                String[] strs = goods.getGoodsicon().split(GlobalParams.SPLIT_IMAGE_URL);
                if (strs != null && strs.length > 0) {
                    for (int i = 0; i < strs.length; i++) {
                        imagePathList.add(strs[i]);
                    }
                }
                adapter = new GoodsImageAdapter(getApplicationContext(), imagePathList, -1, BaseApplication.INSTANCE().phoneWidth - 20, BaseApplication.INSTANCE().phoneHeight / 5 * 4 - 50);
                recyclerView.setAdapter(adapter);
            }
            //加载评论
            discussAdapter = new DiscussAdapter(getApplicationContext(), goodsrepalyList);
            discussAdapter.setOnRecyclerViewListener(new OnRecyclerViewListener() {
                @Override
                public void onItemClick(int position) {
                    currentUserName = goods.getGoodsrepalyList().get(position).getUserInfo().getNickname();
                    currentToUserID = goods.getGoodsrepalyList().get(position).getUserInfo().getUserid();
                    currentPosition = position;
                    editText.setHint("@" + currentUserName);
                    InputMethodManager imm = (InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
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
        }
    }

    private void initRecycleView() {

        layoutManager = new LinearLayoutManager(getApplicationContext()) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);


        linearLayoutManager_dis = new LinearLayoutManager(getApplicationContext()) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        recyclerView_discuss.setLayoutManager(linearLayoutManager_dis);
    }

    private void getGoodsReplay(final Goods goods) {
        ReplayEngine replayEngine = BeanFactory.getImpl(ReplayEngine.class);
        if (replayEngine != null) {
            replayEngine.getAllReplayOfgoodsid(goods.getGoodsid(), new BaseCallBack.GetAllListCallBack<Goodsrepaly>() {
                @Override
                public void getAllResultCallBack(List<Goodsrepaly> lists) {
                    if (lists != null) {
                        goodsrepalyList.addAll(lists);
                        goods.setGoodsrepalyList(goodsrepalyList);
                        discussAdapter.notifyDataSetChanged();
                    }
                }
            });

        }
    }

    public void setListener() {
        llUserInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(GoodsDetailActivity.this, ChatActivity.class)
                        .putExtra("nickname", goods.getUserInfo().getNickname() + "")
                        .putExtra("username", Utility.getEAUserName(goods.getUserInfo().getUserid())));
            }
        });
        keyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
                editText.setText("");
                editText.setHint("输入文字信息");
            }
        });
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = editText.getText().toString();
                if (StringUtils.isNotEmpty(text)) {
                    //回复当前宝贝发布人
                    if (currentToUserID == null) {
                        ReplayEngine replayEngine = BeanFactory.getImpl(ReplayEngine.class);
                        if (replayEngine != null) {
                            final Goodsrepaly goodsrepaly = new Goodsrepaly(goods.getGoodsid(),
                                    BaseApplication.INSTANCE().getCurrentUser().getUserid(), text);
                            goodsrepaly.setUserInfo(BaseApplication.INSTANCE().getCurrentUser());
                            replayEngine.sendReplay(goodsrepaly, new BaseCallBack.SendCallBack() {
                                @Override
                                public void sendResultCallBack(int responseCode) {
                                    if (responseCode == BaseCallBack.SEND_OK) {
                                        List<Goodsrepaly> tempList = new ArrayList<Goodsrepaly>();
                                        tempList.addAll(goodsrepalyList);
                                        goodsrepalyList.clear();
                                        goodsrepalyList.add(goodsrepaly);
                                        goodsrepalyList.addAll(tempList);
                                        tempList = null;
                                        discussAdapter.notifyDataSetChanged();
                                        InputMethodManager imm = (InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                                        imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
                                        editText.setText("");
                                        editText.setHint("输入文字信息");
                                        toast("发送评论成功！");
                                    } else {
                                        toast("发送评论失败！");
                                    }
                                }
                            });
                        }
                    } else {//回复评论人
                        ReplayEngine replayEngine = BeanFactory.getImpl(ReplayEngine.class);
                        if (replayEngine != null) {
                            final Torepaly torepaly = new Torepaly(goodsrepalyList.get(currentPosition).getGoodsreplayid(), BaseApplication.INSTANCE().getCurrentUser().getUserid(), currentToUserID
                                    , text, null);
                            replayEngine.sendToReplay(torepaly, new BaseCallBack.SendCallBack() {
                                @Override
                                public void sendResultCallBack(int responseCode) {
                                    if (responseCode == BaseCallBack.SEND_OK) {
                                        UserInfo toUserInfo = new UserInfo();
                                        toUserInfo.setNickname(currentUserName);
                                        toUserInfo.setUserid(currentToUserID);
                                        torepaly.setToUserinfo(toUserInfo);
                                        UserInfo userInfo = new UserInfo();
                                        userInfo.setNickname(BaseApplication.INSTANCE().getCurrentUser().getNickname());
                                        userInfo.setUserid(BaseApplication.INSTANCE().getCurrentUser().getUserid());
                                        torepaly.setUserinfo(userInfo);
                                        if (currentPosition != -1) {
                                            Goodsrepaly goodsrepaly = goodsrepalyList.get(currentPosition);
                                            if (goodsrepaly.getTorepalyList() != null) {
                                                List<Torepaly> tempToLists = new ArrayList<Torepaly>();
                                                tempToLists.addAll(goodsrepaly.getTorepalyList());
                                                goodsrepaly.getTorepalyList().clear();
                                                goodsrepaly.getTorepalyList().add(torepaly);
                                                goodsrepaly.getTorepalyList().addAll(tempToLists);
                                                tempToLists = null;
                                            } else {
                                                List<Torepaly> tempToLists = new ArrayList<Torepaly>();
                                                tempToLists.add(torepaly);
                                                goodsrepaly.setTorepalyList(tempToLists);
                                            }
                                            discussAdapter.notifyItemChanged(currentPosition);

                                        }
                                        InputMethodManager imm = (InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                                        imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
                                        editText.setText("");
                                        editText.setHint("输入文字信息");
                                        toast("发送评论成功！");
                                    } else {
                                        toast("发送评论失败！");
                                    }
                                }
                            });
                        }
                    }
                } else {
                    toast("请输入内容！");
                }

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        instance = null;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            hideSoftKeyboard();
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        // make sure only one chat activity is opened
        startActivity(intent);
    }
}
