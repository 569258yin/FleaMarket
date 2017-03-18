package com.agjsj.fleamarket.util;

import android.util.Log;

import com.agjsj.fleamarket.params.ConstantValue;
import com.orhanobut.logger.Logger;

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
				}
			} catch (Exception e) {
				e.printStackTrace();
				LogUtil.error("Http Connection",e);
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
			e.printStackTrace();
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
