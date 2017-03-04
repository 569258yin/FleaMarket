package com.agjsj.fleamarket.util;

import com.orhanobut.logger.Logger;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class HttpConnectionUtils {

	private static HttpURLConnection getConnection(String path) {

		URL url;
		try {
			url = new URL(path);
			// url = new URL(
			// "http://wthrcdn.etouch.cn/weather_mini?citykey=101010100");
			// 打开链接
			URLConnection connection = url.openConnection();
			HttpURLConnection httpURLConnection = (HttpURLConnection) connection;
			httpURLConnection.setRequestMethod("POST");
			httpURLConnection.setReadTimeout(15000);
			httpURLConnection.setRequestProperty("Content-type","application/json");
			httpURLConnection.setRequestProperty("charset","utf-8");
			// httpURLConnection.setRequestMethod("GET");
			httpURLConnection.setUseCaches(false);
			httpURLConnection.setDoInput(true);
			httpURLConnection.setDoOutput(true);
			return httpURLConnection;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 向服务器发送一个json字符串，并接受一个服务器返回的json字符串
	 * 
	 * @param jsonString
	 *            要发送的json字符串
	 * @return 服务器返回的json字符串
	 */
	public static String sendJsonString(String jsonString, String path) {
		HttpURLConnection httpURLConnection = getConnection(path);
		if(httpURLConnection != null){
			try {
				BufferedWriter bufferedWriter = new BufferedWriter(
						new OutputStreamWriter(httpURLConnection.getOutputStream(),"UTF-8"));
				bufferedWriter.write(jsonString);
				bufferedWriter.flush();

				BufferedReader bufferedReader = new BufferedReader(
						new InputStreamReader(httpURLConnection.getInputStream(),"UTF-8"));
				String jsonString2 = bufferedReader.readLine();
				Logger.d(jsonString2);
				return jsonString2;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

}
