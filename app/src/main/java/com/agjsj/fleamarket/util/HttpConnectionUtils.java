package com.agjsj.fleamarket.util;

import android.util.Log;

import com.agjsj.fleamarket.bean.MessageEvent;
import com.agjsj.fleamarket.params.ConstantValue;
import com.agjsj.fleamarket.params.OelementType;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.List;

public class HttpConnectionUtils {

	private static HttpURLConnection getConnection(String path) {
		URL url;
		try {
//			path = URLEncoder.encode(path, "UTF-8");
			url = new URL(path);
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
			LogUtil.error("与服务器联网失败\r\n当前连接地址为："+path,e);
			EventBus.getDefault().post(new MessageEvent(OelementType.NET_ERROR));
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
			BufferedWriter bufferedWriter = null;
			BufferedReader bufferedReader = null;
			try {
				bufferedWriter = new BufferedWriter(
						new OutputStreamWriter(httpURLConnection.getOutputStream(),"UTF-8"));
				bufferedWriter.write(jsonString);
				bufferedWriter.flush();

				int status = httpURLConnection.getResponseCode();
				LogUtil.info("Http Status:"+status);
				if(status == HttpURLConnection.HTTP_OK) {
					bufferedReader = new BufferedReader(
							new InputStreamReader(httpURLConnection.getInputStream(), "UTF-8"));
					String jsonString2 = bufferedReader.readLine();
					Logger.d(jsonString2);
					return jsonString2;
				}else{
					LogUtil.error("服务器返回码为:"+status+"\r\n请求url="+path);
					EventBus.getDefault().post(new MessageEvent(OelementType.NET_ERROR));
				}
			} catch (Exception e) {
				LogUtil.error("Http Connection post error!\r\njson = "+jsonString,e);
				EventBus.getDefault().post(new MessageEvent(OelementType.NET_ERROR));
			}finally {
				if(bufferedWriter != null){
					try {
						bufferedWriter.close();
					} catch (Exception e) {
					}
				}
				if(bufferedReader != null){
					try {
						bufferedReader.close();
					} catch (Exception e) {
					}
				}
			}
		}
		return null;
	}

	/**
	 * 上传图片
	 * @param files
	 * @return
	 */
	public static String uploadImage(List<File> files) {
		URL url;
		DataOutputStream ds = null;
		BufferedReader bufferedReader = null;
		String end = "\r\n";
		String Hyphens = "--";
		String boundary = "WUm4580jbtwfJhNp7zi1djFEO3wNNm";
		try {
			if (files != null && files.size() > 0) {
				url = new URL(ConstantValue.URL_ROOT + ConstantValue.IMAGE_UPLOAD);
				// 打开链接
				URLConnection connection = url.openConnection();
				HttpURLConnection httpURLConnection = (HttpURLConnection) connection;
				httpURLConnection.setRequestMethod("POST");
				httpURLConnection.setReadTimeout(30000);
				httpURLConnection.setRequestProperty("Connection", "Keep-Alive");
				httpURLConnection.setRequestProperty("Content-type", "multipart/form-data;boundary=" + boundary);
				httpURLConnection.setRequestProperty("charset", "utf-8");
				// httpURLConnection.setRequestMethod("GET");
				httpURLConnection.setUseCaches(false);
				httpURLConnection.setDoInput(true);
				httpURLConnection.setDoOutput(true);
				ds = new DataOutputStream(httpURLConnection.getOutputStream());
				for (int i = 0;i<files.size();i++) {
					File file = files.get(i);
					ds.writeBytes(Hyphens + boundary + end);
					ds.writeBytes("Content-Disposition: form-data; "
							+ "name=\"file"+i+"\";filename=\"" + file.getName() + "\"" + end);
					ds.writeBytes(end);
					FileInputStream fStream = new FileInputStream(file);
					byte[] buff = new byte[1024];
					int length = -1;
					while((length = fStream.read(buff)) != -1){
						ds.write(buff,0,length);
					}
					ds.writeBytes(end);
					fStream.close();
				}
				ds.writeBytes(Hyphens + boundary + Hyphens+ end);
				ds.flush();
				bufferedReader = new BufferedReader(
						new InputStreamReader(httpURLConnection.getInputStream(),"UTF-8"));
				String jsonString2 = bufferedReader.readLine();
				Logger.d(jsonString2);
				return jsonString2;
			}
		} catch (Exception e) {
			LogUtil.error("上传文件失败!\r\n上传的文件路径为："+files.toString(),e);
			EventBus.getDefault().post(new MessageEvent(OelementType.NET_ERROR));
		}finally {
			if(ds != null){
				try {
					ds.close();
				} catch (Exception e) {
				}
			}
			if(bufferedReader != null){
				try {
					bufferedReader.close();
				} catch (Exception e) {
				}
			}
		}
		return null;
	}

}
