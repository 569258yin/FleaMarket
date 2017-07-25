package com.agjsj.fleamarket.util;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.math.BigDecimal;

public class Utility {
	public static void setListViewHeightBasedOnChildren(ListView listView) {
		// listView Adapter 设置显示的高度，根据获取的数据值
		ListAdapter listAdapter = listView.getAdapter();
		if (listAdapter == null) {
			// pre-condition
			return;
		}

		int totalHeight = 0;
		for (int i = 0, len = listAdapter.getCount(); i < len; i++) { // listAdapter.getCount()
			View listItem = listAdapter.getView(i, null, listView);
			listItem.measure(0, 0);
			totalHeight += listItem.getMeasuredHeight(); //
		}

		ViewGroup.LayoutParams params = listView.getLayoutParams();
		params.height = totalHeight
				+ (listView.getDividerHeight() * (listAdapter.getCount() - 1));
		// listView.getDividerHeight()��ȡ�����ָ���ռ�õĸ߶�
		// params.height���õ�����ListView������ʾ��Ҫ�ĸ߶�
		listView.setLayoutParams(params);
	}

	/**
	 * 检查SD卡是否存在
	 * @return
     */
	public static boolean checkSdCard() {
		if (android.os.Environment.getExternalStorageState().equals(
				android.os.Environment.MEDIA_MOUNTED))
			return true;
		else
			return false;
	}

	public static String getMoney(int money){
		return new BigDecimal(money).divide(new BigDecimal(100)).setScale(2,BigDecimal.ROUND_CEILING).toString();
	}

	public static String getEAUserName(String userId){
		return "EA_"+userId;
	}
}
