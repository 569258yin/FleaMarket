package com.agjsj.fleamarket.view;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.util.Pair;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;

import com.agjsj.fleamarket.R;
import com.agjsj.fleamarket.adapter.message.EaseConversationAdapater;
import com.agjsj.fleamarket.dialog.ShowMegDialog;
import com.agjsj.fleamarket.params.GlobalParams;
import com.agjsj.fleamarket.util.Utility;
import com.agjsj.fleamarket.view.base.BaseApplication;
import com.agjsj.fleamarket.view.manager.BaseUI;
import com.agjsj.fleamarket.view.message.ChatActivity;
import com.agjsj.fleamarket.view.myview.PullToRefreshView;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMConversation;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 消息界面
 *
 * @author YH
 */
public class MessageUI extends BaseUI implements PullToRefreshView.OnHeaderRefreshListener{
    @Bind(R.id.listView_coversation)
    ListView listView;
    @Bind(R.id.ll_pullToRefresh_home)
    PullToRefreshView pullToRefreshView;

    private List<EMConversation> conversationList;
    private EaseConversationAdapater adapter;
    private ShowMegDialog dialog;

    public MessageUI(Context context, FragmentManager fragmentManager) {
        super(context, fragmentManager);
    }

    // 第一步：加载layout（布局参数设置）
    // 第二步：初始化layout中控件
    // 第三步：设置监听

    @Override
    public void init() {
        showInMiddle = (LinearLayout) View.inflate(context,
                R.layout.activity_message, null);
        ButterKnife.bind(this, showInMiddle);

        conversationList = new ArrayList<EMConversation>();
        adapter = new EaseConversationAdapater(context, 1, conversationList);

        adapter.setOnItemClickListener(new EaseConversationAdapater.OnItemClickListener() {
            @Override
            public void onClick(final int position) {
                final String st2 = context.getResources().getString(R.string.Cant_chat_with_yourself);
                EMConversation conversation = adapter.getItem(position);
                String username = conversation.conversationId();
                if (username.equals(BaseApplication.INSTANCE().getCurrentUserName()))
                    toast(st2);
                else {
                    // 进入聊天页面
                    Intent intent = new Intent(context, ChatActivity.class);
                    intent.putExtra("username", username);
                    intent.putExtra("nickname", "");
                    context.startActivity(intent);
                }
            }

            @Override
            public void onLongClick(final int position) {
                dialog = new ShowMegDialog(context, "提示", "是否删除当前聊天信息");
                dialog.setOnResultListener(new ShowMegDialog.OnResultListener() {
                    @Override
                    public void onOk() {
                        //删除和某个user会话，如果需要保留聊天记录，传false
                        EMClient.getInstance().chatManager().deleteConversation(conversationList.get(position).conversationId(), true);
                        conversationList.remove(position);
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancel() {

                    }
                });
                dialog.show();
            }
        });
        listView.setAdapter(adapter);
        Utility.setListViewHeightBasedOnChildren(listView);
        pullToRefreshView.setOnHeaderRefreshListener(this);
    }

    @Override
    public void refreshView() {
        conversationList.clear();
        conversationList.addAll(loadConversationList());
        Utility.setListViewHeightBasedOnChildren(listView);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void setListener() {
        final String st2 = context.getResources().getString(R.string.Cant_chat_with_yourself);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                EMConversation conversation = adapter.getItem(position);
                String username = conversation.conversationId();
                if (username.equals(BaseApplication.INSTANCE().getCurrentUserName()))
                    toast(st2);
                else {
                    // 进入聊天页面
                    Intent intent = new Intent(context, ChatActivity.class);
                    intent.putExtra("username", username);
                    intent.putExtra("nickname", "");
                    context.startActivity(intent);
                }
            }
        });

    }

    /**
     * 获取会话列表
     *
     * @return +
     */
    protected List<EMConversation> loadConversationList() {
        // 获取所有会话，包括陌生人
        Map<String, EMConversation> conversations = EMClient.getInstance().chatManager().getAllConversations();
        // 过滤掉messages size为0的conversation
        /**
         * 如果在排序过程中有新消息收到，lastMsgTime会发生变化 影响排序过程，Collection.sort会产生异常
         * 保证Conversation在Sort过程中最后一条消息的时间不变 避免并发问题
         */
        List<Pair<Long, EMConversation>> sortList = new ArrayList<Pair<Long, EMConversation>>();
        synchronized (conversations) {
            for (EMConversation conversation : conversations.values()) {
                if (conversation.getAllMessages().size() != 0) {
                    sortList.add(
                            new Pair<Long, EMConversation>(conversation.getLastMessage().getMsgTime(), conversation));

                }
            }
        }
        try {
            sortConversationByLastChatTime(sortList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<EMConversation> list = new ArrayList<EMConversation>();
        for (Pair<Long, EMConversation> sortItem : sortList) {
            list.add(sortItem.second);
        }
        return list;
    }

    /**
     * 根据最后一条消息的时间排序
     *
     * @param conversationList
     */
    private void sortConversationByLastChatTime(List<Pair<Long, EMConversation>> conversationList) {
        Collections.sort(conversationList, new Comparator<Pair<Long, EMConversation>>() {
            @Override
            public int compare(final Pair<Long, EMConversation> con1, final Pair<Long, EMConversation> con2) {

                if (con1.first == con2.first) {
                    return 0;
                } else if (con2.first > con1.first) {
                    return 1;
                } else {
                    return -1;
                }
            }

        });
    }

    @Override
    public int getID() {
        return GlobalParams.VIEW_MESSSAGE;
    }


    @Override
    public void onHeaderRefresh(PullToRefreshView view) {
        refreshView();
        pullToRefreshView.onHeaderRefreshComplete();
    }
}