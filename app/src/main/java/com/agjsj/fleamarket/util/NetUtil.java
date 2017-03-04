package com.agjsj.fleamarket.util;


import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.util.Log;

import com.agjsj.fleamarket.params.GlobalParams;

public class NetUtil {
	/**
	 * 检查用户的网络
	 * @return
	 */
	public static boolean checkNet(Context context){
		//判断是否连接网络
		//判断WIFI连接
		boolean isWIFI = isWIFIConnection(context);
		//判断mobile连接
		boolean isMOBILE = isMOBILEConnection(context);
		/*
		 *如果Mobile被连接，判断是哪个APN被选中，
		*/
		if (isMOBILE) {
			/*
			 *APN被选中后，判断代理信息是否有内容，有为WAP连接
			 */
			readAPN(context);
			
		}
		
	if(!isWIFI && !isMOBILE){
		return false;
	}
	return true;
	}

	/**
	 * APN被选中后，判断代理信息是否有内容，有为WAP连接
	 * @param context
	 */
	private static void readAPN(Context context) {
		Uri uri = Uri.parse("content://telephony/carriers/preferapn");
		ContentResolver resolver = context.getContentResolver();
		//判断是哪个APN被选中
		Cursor cursor =  resolver.query(uri, null, null, null, null);
		if (cursor != null && cursor.moveToFirst()) {
			String proxy = cursor.getString(cursor.getColumnIndex("proxy"));
			int port = cursor.getInt(cursor.getColumnIndex("port"));
			Log.i("NET", "proxy:"+proxy+"port:"+port);
			GlobalParams.PROXY = proxy;
			GlobalParams.PORT = port;
		}
	}
	
	/**
	 * 判断mobile连接
	 * @param context
	 * @return
	 */
	private static boolean isMOBILEConnection(Context context) {
		ConnectivityManager manager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = manager
				.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
		if (networkInfo != null) {
			return networkInfo.isConnected();
		}
		return false;
	}
	
	/**
	 *  * 判断WIFI连接
	 * @param context
	 * @return
	 */
	private static boolean isWIFIConnection(Context context) {
		ConnectivityManager manager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = manager
				.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
		if (networkInfo != null) {
			return networkInfo.isConnected();
		}
		return false;
	}


}
