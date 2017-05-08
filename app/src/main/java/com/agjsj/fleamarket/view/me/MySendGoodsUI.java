package com.agjsj.fleamarket.view.me;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.agjsj.fleamarket.R;
import com.agjsj.fleamarket.adapter.base.OnRecyclerViewListener;
import com.agjsj.fleamarket.adapter.goods.GoodsAdapter;
import com.agjsj.fleamarket.adapter.me.MySendGoodsAdapter;
import com.agjsj.fleamarket.bean.Goods;
import com.agjsj.fleamarket.dialog.ShowMegDialog;
import com.agjsj.fleamarket.engine.BaseCallBack;
import com.agjsj.fleamarket.engine.GoodsEngine;
import com.agjsj.fleamarket.params.ConstantValue;
import com.agjsj.fleamarket.params.GlobalParams;
import com.agjsj.fleamarket.util.BeanFactory;
import com.agjsj.fleamarket.util.LogUtil;
import com.agjsj.fleamarket.view.base.BaseApplication;
import com.agjsj.fleamarket.view.goods.GoodsDetailUI;
import com.agjsj.fleamarket.view.manager.BaseUI;
import com.agjsj.fleamarket.view.manager.MiddleManager;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by YH on 2017/5/1.
 */
public class MySendGoodsUI extends BaseUI{

    @Bind(R.id.recyclerview_me)
    RecyclerView recyclerView;

    private MySendGoodsAdapter adapter;
    private  boolean isLoading = false;
    private ArrayList<Goods> goodsList;

    private LinearLayoutManager layoutManager;

    public MySendGoodsUI(Context context, FragmentManager mFragmentManager) {
        super(context, mFragmentManager);
    }


    @Override
    public void init() {
        showInMiddle = (LinearLayout) View.inflate(context,R.layout.activity_me_goods,null);
        ButterKnife.bind(this, showInMiddle);

        initRecycleView();

//        getGoodsFromServer();

    }

    private void initRecycleView() {
        goodsList = new ArrayList<>();
        layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new MySendGoodsAdapter(context, goodsList);
        HashMap<String,OnRecyclerViewListener> listenerHashMap = new HashMap<>(3);
        listenerHashMap.put("update", new OnRecyclerViewListener() {
            @Override
            public void onItemClick(int position) {
                LogUtil.info("update");

            }

            @Override
            public void onItemClick(int position, int id) {

            }

            @Override
            public boolean onItemLongClick(int position) {
                return false;
            }
        });
        listenerHashMap.put("delete", new OnRecyclerViewListener() {
            @Override
            public void onItemClick(final int position) {
                ShowMegDialog dialog = new ShowMegDialog(context,"提示","确认删除此条物品信息吗？");
                dialog.show();
                dialog.setOnResultListener(new ShowMegDialog.OnResultListener() {
                    @Override
                    public void onOk() {
                        GoodsEngine goodsEngine = BeanFactory.getImpl(GoodsEngine.class);
                        if(goodsEngine != null) {
                            goodsEngine.deleteGoods(goodsList.get(position).getGoodsid(), new BaseCallBack.SendCallBack() {
                                @Override
                                public void sendResultCallBack(int responseCode) {
                                    if(responseCode == BaseCallBack.SEND_OK) {
                                        toast("删除成功");
                                        goodsList.remove(position);
                                        adapter.notifyDataSetChanged();
                                    } else {
                                        toast("删除失败");
                                    }
                                }
                            });
                        }
                    }
                    @Override
                    public void onCancel() {

                    }
                });

            }

            @Override
            public void onItemClick(int position, int id) {
            }

            @Override
            public boolean onItemLongClick(int position) {
                return false;
            }
        });
        listenerHashMap.put("refersh", new OnRecyclerViewListener() {
            @Override
            public void onItemClick(final int position) {
                long hour = (new Date().getTime() - goodsList.get(position).getModifyTime().getTime()) / 1000 / 60 / 60;
                if (hour > 24) {
                    GoodsEngine goodsEngine = BeanFactory.getImpl(GoodsEngine.class);
                    if(goodsEngine != null) {
                        goodsEngine.refreshGoods(goodsList.get(position).getGoodsid(), new BaseCallBack.SendCallBack() {
                            @Override
                            public void sendResultCallBack(int responseCode) {
                                if(responseCode == BaseCallBack.SEND_OK) {
                                    goodsList.get(position).setModifyTime(new Date());
                                    toast("置顶成功！");
                                } else {
                                    toast("本次置顶失败，请稍后重试");
                                }
                            }
                        });
                    }
                } else {
                    toast("24小时内只允许置顶一次");
                    return;
                }
            }
            @Override
            public void onItemClick(int position, int id) {
            }
            @Override
            public boolean onItemLongClick(int position) {
                return false;
            }
        });

        listenerHashMap.put("item", new OnRecyclerViewListener() {
            @Override
            public void onItemClick(final int position) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("goods",goodsList.get(position));
                MiddleManager.getInstance().changeUI(GoodsDetailUI.class,bundle);
            }
            @Override
            public void onItemClick(int position, int id) {
            }
            @Override
            public boolean onItemLongClick(int position) {
                return false;
            }
        });



        adapter.setListenerHashMap(listenerHashMap);
        recyclerView.setAdapter(adapter);
    }

    private void getGoodsFromServer() {
        if (!isLoading) {
            GoodsEngine goodsEngine = BeanFactory.getImpl(GoodsEngine.class);
            if (goodsEngine != null) {
                isLoading = true;
                goodsEngine.getGoodsOfUser(BaseApplication.INSTANCE().getCurrentUser().getUserid(),new BaseCallBack.GetAllListCallBack<Goods>() {
                    @Override
                    public void getAllResultCallBack(List<Goods> list) {
                        if (goodsList != null && list != null) {
                            goodsList.addAll(list);
                            adapter.notifyDataSetChanged();
                        }else{
                            //没有更多数据了
                        }
                        isLoading = false;
                    }
                });
            }
        }
    }

    @Override
    public void setListener() {
    }


    @Override
    public void refreshView() {
        goodsList.clear();
        getGoodsFromServer();
    }

    @Override
    public int getID() {
        return GlobalParams.VIEW_MY_SEND_GOODS;
    }
}
