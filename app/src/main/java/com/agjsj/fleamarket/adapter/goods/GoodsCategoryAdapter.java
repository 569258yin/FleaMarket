package com.agjsj.fleamarket.adapter.goods;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.agjsj.fleamarket.R;
import com.agjsj.fleamarket.bean.GoodsType;

import java.util.List;

/**
 * Created by YH on 2017/4/15.
 */

public class GoodsCategoryAdapter extends BaseAdapter {

    private List<GoodsType> goodsTypeList;
    private Context context;
    private LayoutInflater mInfalater;

    public GoodsCategoryAdapter(List<GoodsType> goodsTypeList, Context context) {
        this.goodsTypeList = goodsTypeList;
        this.context = context;
        mInfalater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return goodsTypeList.size();
    }

    @Override
    public Object getItem(int position) {
        return goodsTypeList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if(convertView == null) {
            convertView = mInfalater.inflate(R.layout.item_textview,null);
            viewHolder = new ViewHolder();
            viewHolder.textView = (TextView) convertView.findViewById(R.id.tv_item);
            viewHolder.textView.setTextSize(20);
            viewHolder.textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(textViewCallBack != null) {
                        textViewCallBack.OnTextViewClick(goodsTypeList.get(position), position);
                    }
                }
            });
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.textView.setText(goodsTypeList.get(position).getGoodstypename());
        return convertView;
    }

    public interface TextViewCallBack{
        public void OnTextViewClick(GoodsType goodsType, int position);
    }

    private TextViewCallBack textViewCallBack;

    public void setTextViewCallBack(TextViewCallBack textViewCallBack) {
        this.textViewCallBack = textViewCallBack;
    }

    class ViewHolder{
        TextView textView;
    }

}
