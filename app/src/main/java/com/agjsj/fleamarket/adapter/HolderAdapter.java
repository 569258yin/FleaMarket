package com.agjsj.fleamarket.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * 进一步实现了BaseAdapter中的getView方法
 * 下一步只需实现抽象的三种方法即可
 * @author yh
 *
 * @param <T>
 */
public abstract class HolderAdapter<T> extends AbstractAdapter<T> {

	public HolderAdapter(Context context, List<T> listData) {
		super(context, listData);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Object holder = null;
		if (convertView ==null) {
			convertView = buildConvertView(layoutInflater);
			holder = buildHolder(convertView);
			convertView.setTag(holder);
		}else{
			holder = convertView.getTag();
		}
		
		bindViewData(holder,listData.get(position),position);
		
		
		return null;
	}
	
	/**
	 * 建立convertView,绑定视图
	 * @param layoutInflater
	 * @return
	 */
	public abstract View buildConvertView(LayoutInflater layoutInflater);
	
	/**
	 * 建立视图，绑定ID
	 * @param convertView
	 * @return
	 */
	public abstract Object buildHolder(View convertView);
	
	/**
	 * 绑定数据
	 * @param holder
	 * @param t
	 * @param position
	 */
	public abstract void bindViewData(Object holder, T t, int position);


}
