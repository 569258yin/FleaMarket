package com.agjsj.fleamarket.adapter.message;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.agjsj.fleamarket.R;
import com.agjsj.fleamarket.util.PicassoUtils;
import com.agjsj.fleamarket.view.base.BaseApplication;
import com.agjsj.fleamarket.view.message.ChatActivity;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.chat.EMTextMessageBody;

import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * Created by YH on 2017/5/17.
 */

public class MessageAdapter extends BaseAdapter {
    private List<EMMessage> msgs;
    private Context context;
    private LayoutInflater inflater;

    public MessageAdapter(List<EMMessage> msgs, Context context_) {
        this.msgs = msgs;
        this.context = context_;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return msgs.size();
    }

    @Override
    public EMMessage getItem(int position) {
        return msgs.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        EMMessage message = getItem(position);
        return message.direct() == EMMessage.Direct.RECEIVE ? 0 : 1;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        EMMessage message = getItem(position);
        int viewType = getItemViewType(position);
        if (convertView == null) {
            if (viewType == 0) {
                convertView = inflater.inflate(R.layout.item_message_received, parent, false);
            } else {
                convertView = inflater.inflate(R.layout.item_message_sent, parent, false);
            }
        }
        ViewHolder holder = (ViewHolder) convertView.getTag();
        if (holder == null) {
            holder = new ViewHolder();
            holder.tv = (TextView) convertView.findViewById(R.id.tv_chatcontent);
            holder.userIcon = (ImageView) convertView.findViewById(R.id.iv_userhead);
            holder.nickName = (TextView) convertView.findViewById(R.id.tv_userid);
            convertView.setTag(holder);
        }
        if (viewType == 0) {
            String nickName = (String) message.getStringAttribute("userName","用户名不存在");
            if(StringUtils.isNotEmpty(nickName)) {
                holder.nickName.setText(nickName);
            }
            String userIcon = (String)  message.getStringAttribute("userIcon","");
            if(StringUtils.isNotEmpty(userIcon)){
                PicassoUtils.loadResizeImage(userIcon,100,100,holder.userIcon);
            }
        }else {
            PicassoUtils.loadResizeImage(BaseApplication.INSTANCE().getCurrentUser().getUsericon(),100,100,holder.userIcon);
        }
        EMTextMessageBody txtBody = (EMTextMessageBody) message.getBody();
        holder.tv.setText(txtBody.getMessage());

        return convertView;
    }

     class ViewHolder {
        ImageView userIcon;
        TextView nickName;
        TextView tv;
    }

}
