package com.agjsj.fleamarket;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.agjsj.fleamarket.util.PicassoUtils;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.orhanobut.logger.Logger;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class BaseApplication extends Application {

	private static BaseApplication INSTANCE;
	//依据配置文件加载实例
	private Properties properties = new Properties();

	public static BaseApplication INSTANCE() {
		return INSTANCE;
	}

	private void setInstance(BaseApplication app) {
		setBmobIMApplication(app);
	}

	private static void setBmobIMApplication(BaseApplication a) {
		BaseApplication.INSTANCE = a;
	}

	private String token = "";

	@Override
	public void onCreate() {
		super.onCreate();
		setInstance(this);
		//初始化
		Logger.init("FleaMarket");
		//只有主进程运行的时候才需要初始化
		if (getApplicationInfo().packageName.equals(getMyProcessName())) {
			//Picasso初始化
			PicassoUtils.initPicasso(this);

			try {
				InputStream is = getResources().getAssets().open("bean.properties");
				properties.load(is);
			} catch (IOException e) {
				e.printStackTrace();
			}
			getLocalToken();
		}
	}

	/**
	 * 获取当前运行的进程名
	 *
	 * @return
	 */
	public static String getMyProcessName() {
		try {
			File file = new File("/proc/" + android.os.Process.myPid() + "/" + "cmdline");
			BufferedReader mBufferedReader = new BufferedReader(new FileReader(file));
			String processName = mBufferedReader.readLine().trim();
			mBufferedReader.close();
			return processName;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 配置ImageLoder 从服务器加载图片
	 */
	private void configImageLoader() {
		// 初始化ImageLoader
		DisplayImageOptions options = new DisplayImageOptions.Builder()
		.showImageOnLoading(R.drawable.icon_stub) // 设置图片下载期间显示的图片
		.showImageForEmptyUri(R.drawable.icon_empty) // 设置图片Uri为空或是错误的时候显示的图片
		.showImageOnFail(R.drawable.icon_error) // 设置图片加载或解码过程中发生错误显示的图片
		.cacheInMemory(true) // 设置下载的图片是否缓存在内存中
		.cacheOnDisc(true) // 设置下载的图片是否缓存在SD卡中
		// .displayer(new RoundedBitmapDisplayer(20)) // 设置成圆角图片
		.build(); // 创建配置过得DisplayImageOption对象

		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
				getApplicationContext()).defaultDisplayImageOptions(options)
				.threadPriority(Thread.NORM_PRIORITY - 2)
				.denyCacheImageMultipleSizesInMemory()
				.discCacheFileNameGenerator(new Md5FileNameGenerator())
				.tasksProcessingOrder(QueueProcessingType.LIFO).build();
		ImageLoader.getInstance().init(config);
	}

	public Properties getProperties() {
		return properties;
	}

	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
		saveToken(token);
	}


	private void saveToken(String token){
		SharedPreferences sharedPreferences = BaseApplication.INSTANCE().getSharedPreferences("currentUser", Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = sharedPreferences.edit();
		editor.putString("token",token);
	}

	private String getLocalToken(){
		SharedPreferences sharedPreferences = BaseApplication.INSTANCE().getSharedPreferences("currentUser", Context.MODE_PRIVATE);
		if(sharedPreferences != null){
			return sharedPreferences.getString("token","");
		}
		return "";
	}
}
