package com.agjsj.fleamarket.view.me;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.agjsj.fleamarket.R;
import com.agjsj.fleamarket.adapter.base.OnRecyclerViewListener;
import com.agjsj.fleamarket.adapter.me.MySendFoundCaseAdapter;
import com.agjsj.fleamarket.bean.FoundCase;
import com.agjsj.fleamarket.dialog.ShowMegDialog;
import com.agjsj.fleamarket.engine.BaseCallBack;
import com.agjsj.fleamarket.engine.FoundEngine;
import com.agjsj.fleamarket.engine.GoodsEngine;
import com.agjsj.fleamarket.params.GlobalParams;
import com.agjsj.fleamarket.util.BeanFactory;
import com.agjsj.fleamarket.util.LogUtil;
import com.agjsj.fleamarket.util.TimeUtil;
import com.agjsj.fleamarket.view.base.BaseApplication;
import com.agjsj.fleamarket.view.manager.BaseUI;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by YH on 2017/5/1.
 */
public class MySendFoundcaseUI extends BaseUI {

    @Bind(R.id.recyclerview_me)
    RecyclerView recyclerView;

    private MySendFoundCaseAdapter adapter;
    private LinearLayoutManager layoutManager;
    private List<FoundCase> foundCaseList;
    private boolean isLoading = false;

    public MySendFoundcaseUI(Context context, FragmentManager mFragmentManager) {
        super(context, mFragmentManager);
    }


    @Override
    public void init() {
        showInMiddle = (LinearLayout) View.inflate(context, R.layout.activity_me_goods, null);
        ButterKnife.bind(this, showInMiddle);

        initRecycleView();

//        getFoundCaseFromServer();
    }

    private void initRecycleView() {
        foundCaseList = new ArrayList<>();
        layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new MySendFoundCaseAdapter(context, foundCaseList);
        HashMap<String, OnRecyclerViewListener> listenerHashMap = new HashMap<>(2);
        listenerHashMap.put("delete", new OnRecyclerViewListener() {
            @Override
            public void onItemClick(final int position) {
                LogUtil.info("delete");
                ShowMegDialog dialog = new ShowMegDialog(context, "提示", "确认删除此条物品信息吗？");
                dialog.show();
                dialog.setOnResultListener(new ShowMegDialog.OnResultListener() {
                    @Override
                    public void onOk() {
                        FoundEngine foundEngine = BeanFactory.getImpl(FoundEngine.class);
                        if (foundEngine != null) {
                            foundEngine.deleteFoundcase(foundCaseList.get(position).getFdcid(), new BaseCallBack.SendCallBack() {
                                @Override
                                public void sendResultCallBack(int responseCode) {
                                    if (responseCode == BaseCallBack.SEND_OK) {
                                        toast("删除成功");
                                        foundCaseList.remove(position);
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
                LogUtil.info("refersh");
                LogUtil.info("Now:"+TimeUtil.getFormatToday(TimeUtil.FORMAT_DATE_TIME_SECOND));
                LogUtil.info("modigyTime:"+ new SimpleDateFormat(TimeUtil.FORMAT_DATE_TIME_SECOND).format(foundCaseList.get(position).getModifyTime()));
                long hour = (new Date().getTime() - foundCaseList.get(position).getModifyTime().getTime()) / 1000 / 60 / 60 ;
                LogUtil.info("hour:"+hour);
                if (hour > 24) {
                    FoundEngine foundEngine = BeanFactory.getImpl(FoundEngine.class);
                    if(foundEngine != null) {
                        foundEngine.refreshFoundCase(foundCaseList.get(position).getFdcid(), new BaseCallBack.SendCallBack() {
                            @Override
                            public void sendResultCallBack(int responseCode) {
                                if(responseCode == BaseCallBack.SEND_OK) {
                                    foundCaseList.get(position).setModifyTime(new Date());
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

        adapter.setListenerHashMap(listenerHashMap);
        recyclerView.setAdapter(adapter);

    }

    private void getFoundCaseFromServer() {
        if (!isLoading) {
            final FoundEngine foundEngine = BeanFactory.getImpl(FoundEngine.class);
            if (foundEngine != null) {
                isLoading = true;
                foundEngine.getFoundcaseOfuser(BaseApplication.INSTANCE().getCurrentUser().getUserid(), new BaseCallBack.GetAllListCallBack<FoundCase>() {
                    @Override
                    public void getAllResultCallBack(List<FoundCase> list) {
                        if (foundCaseList != null && list != null) {
                            foundCaseList.addAll(list);
                            adapter.notifyDataSetChanged();
                        } else {
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
        foundCaseList.clear();
        getFoundCaseFromServer();
    }

    @Override
    public int getID() {
        return GlobalParams.VIEW_MY_SEND_FOUNDCASE;
    }
}
