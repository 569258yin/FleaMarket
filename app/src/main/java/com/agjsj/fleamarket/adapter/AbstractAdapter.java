package com.agjsj.fleamarket.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * 抽象adapter将其中一些通用的属性进行初步实现
 * @author yh
 *
 * @param <T>
 */

public abstract class AbstractAdapter<T> extends BaseAdapter {
	
	protected Context context;
	
	protected List<T> listData;
	
	protected LayoutInflater layoutInflater;
	
	public AbstractAdapter(Context context, List<T> listData) {
		this.context = context;
		this.listData = listData;
		layoutInflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		if (listData != null) {
			return listData.size();
		}
		return 0;
	}

	@Override
	public Object getItem(int position) {
		if (listData != null) {
			return listData.get(position);
		}
		return null;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	public List<T> getListData() {
		return listData;
	}

	public void setListData(List<T> listData) {
		this.listData = listData;
	}
	
}
