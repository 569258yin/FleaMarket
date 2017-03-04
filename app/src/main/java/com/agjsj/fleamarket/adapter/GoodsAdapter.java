package com.agjsj.fleamarket.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.agjsj.fleamarket.R;
import com.agjsj.fleamarket.bean.Goods;
import com.agjsj.fleamarket.view.myview.CircleImageView;

import java.util.List;

public class GoodsAdapter extends HolderAdapter<Goods> {

	public GoodsAdapter(Context context, List<Goods> listData) {
		super(context, listData);
	}

	@Override
	public View buildConvertView(LayoutInflater layoutInflater) {
		return layoutInflater.inflate(R.layout.goods_list_item, null);
	}

	@Override
	public Object buildHolder(View convertView) {
		ViewHolder holder = new ViewHolder();
		holder.user_icon = (CircleImageView) convertView.findViewById(R.id.iv_goodsitem_icon);
		holder.user_name = (TextView) convertView.findViewById(R.id.tv_goodsitem_name);
		holder.goods_time = (TextView) convertView.findViewById(R.id.tv_goodsitem_time);
		holder.goods_money = (TextView) convertView.findViewById(R.id.tv_goodsitem_money);
		holder.goods_img_01 = (ImageView) convertView.findViewById(R.id.iv_goods_img1);
		holder.goods_img_02	= (ImageView) convertView.findViewById(R.id.iv_goods_img2);
		holder.goods_img_03 = (ImageView) convertView.findViewById(R.id.iv_goods_img3);
		holder.goods_content = (TextView) convertView.findViewById(R.id.tv_goodsitem_content);
		holder.goods_replay_num = (TextView) convertView.findViewById(R.id.tv_goods_replay_number);
		holder.goods_zan_num = (TextView) convertView.findViewById(R.id.tv_goods_zan_number);
		return holder;
	}

	@Override
	public void bindViewData(Object holder, Goods t, int position) {
		ViewHolder mholder = (ViewHolder) holder;
		mholder.goods_content.setText(listData.get(position).getGoodstext());
	}
	
	private class ViewHolder{
		CircleImageView user_icon;
		TextView user_name;
		TextView goods_time;
		TextView goods_money;
		ImageView goods_img_01;
		ImageView goods_img_02;
		ImageView goods_img_03;
		TextView goods_content;
		TextView goods_replay_num;
		TextView goods_zan_num;
		
	}

}
