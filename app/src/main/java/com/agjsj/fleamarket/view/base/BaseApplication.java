package com.agjsj.fleamarket.view.base;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.text.TextUtils;
import android.view.WindowManager;

import com.agjsj.fleamarket.bean.Goodstype;
import com.agjsj.fleamarket.bean.UserInfo;
import com.agjsj.fleamarket.util.CompressHelperUtil;
import com.agjsj.fleamarket.util.PicassoImageLoader;
import com.agjsj.fleamarket.util.PicassoUtils;
import com.agjsj.fleamarket.view.send.MyLocationListener;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.view.CropImageView;
import com.orhanobut.logger.Logger;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
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
    private UserInfo currentUser;
    private List<Goodstype> goodstypes;

    public LocationClient mLocationClient = null;
    public BDLocationListener myListener = new MyLocationListener();
    public int phoneWidth;
    public int phoneHeight;


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
            initImagePicker();
            CompressHelperUtil.initCompress(this);

//            initBaiDuMap();

            try {
                InputStream is = getResources().getAssets().open("bean.properties");
                properties.load(is);
            } catch (IOException e) {
                e.printStackTrace();
            }
            this.token = getLocalToken();
            Logger.d("Token:" + token);

            currentUser = getLocalUser();

            WindowManager wm = (WindowManager) getApplicationContext()
                    .getSystemService(Context.WINDOW_SERVICE);
            Point point = new Point();
            wm.getDefaultDisplay().getSize(point);
            phoneWidth = point.x;
            phoneHeight = point.y;

        }
    }



    /**
     * 初始化图片选择器参数
     */
    private void initImagePicker() {
        //初始化图片选择器参数
        ImagePicker imagePicker = ImagePicker.getInstance();
        imagePicker.setImageLoader(new PicassoImageLoader());   //设置图片加载器
        imagePicker.setShowCamera(true);  //显示拍照按钮
        imagePicker.setCrop(true);        //允许裁剪（单选才有效）
        imagePicker.setSaveRectangle(true); //是否按矩形区域保存
        imagePicker.setSelectLimit(7);    //选中数量限制
        imagePicker.setStyle(CropImageView.Style.RECTANGLE);  //裁剪框的形状
        imagePicker.setFocusWidth(800);   //裁剪框的宽度。单位像素（圆形自动取宽高最小值）
        imagePicker.setFocusHeight(800);  //裁剪框的高度。单位像素（圆形自动取宽高最小值）
        imagePicker.setOutPutX(1000);//保存文件的宽度。单位像素
        imagePicker.setOutPutY(1000);//保存文件的高度。单位像素
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


    private void saveToken(String token) {
        SharedPreferences sharedPreferences = BaseApplication.INSTANCE().getSharedPreferences("currentUser", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Logger.d("Get TOken From Server" + token);
        editor.putString("token", token);
        editor.commit();
    }

    private String getLocalToken() {
        SharedPreferences sharedPreferences = BaseApplication.INSTANCE().getSharedPreferences("currentUser", Context.MODE_PRIVATE);
        if (sharedPreferences != null) {
            return sharedPreferences.getString("token", "");
        }
        return "";
    }

    public UserInfo getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(UserInfo currentUser) {
        this.currentUser = currentUser;
    }

    //-------------------------------------获取当前用户------------------------------
    public UserInfo getLocalUser() {
        SharedPreferences sharedPreferences = BaseApplication.INSTANCE().getSharedPreferences("currentUser", Context.MODE_PRIVATE);
        if (TextUtils.isEmpty(sharedPreferences.getString("id", ""))) {
            return null;
        }else {
            //获取用户，并返回
            UserInfo myUser = new UserInfo();
            myUser.setUserid(sharedPreferences.getString("id", ""));
            myUser.setUsersex(sharedPreferences.getInt("userSex", -1));
            myUser.setUseraddress(sharedPreferences.getString("userAddress", ""));
            myUser.setUsername(sharedPreferences.getString("userName", ""));
            myUser.setNickname(sharedPreferences.getString("userNickName", ""));
            myUser.setQqnumber(sharedPreferences.getString("qqNumber", ""));
            myUser.setWxnumber(sharedPreferences.getString("wxNumber", ""));
            myUser.setColleage(sharedPreferences.getString("colleage", ""));
            myUser.setSchool(sharedPreferences.getString("school", ""));
            myUser.setUsericon(sharedPreferences.getString("userIcon", ""));
            myUser.setUserphone(sharedPreferences.getString("userPhone", ""));
            return myUser;
        }
    }


    //------------------------------------更新当前用户信息----------------------------
    public void updateLocalUser(UserInfo myUser) {
        currentUser = myUser;
        SharedPreferences sharedPreferences = BaseApplication.INSTANCE().getSharedPreferences("currentUser", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("id", myUser.getUserid()+"");
        editor.putInt("userSex", myUser.getUsersex());
        editor.putString("userAddress", myUser.getUseraddress()+"");
        editor.putString("userName", myUser.getUsername()+"");
        editor.putString("userNickName", myUser.getNickname()+"");
        editor.putString("qqNumber", myUser.getQqnumber()+"");
        editor.putString("wxNumber", myUser.getWxnumber()+"");
        editor.putString("colleage", myUser.getColleage()+"");
        editor.putString("school", myUser.getSchool()+"");
        editor.putString("userIcon", myUser.getUsericon()+"");
        editor.putString("userPhone", myUser.getUserphone()+"");
        editor.commit();

    }

    //------------------------------------退出登录--------------------------------
    public void deleteCurrentUser() {
        currentUser = null;
        SharedPreferences sharedPreferences = BaseApplication.INSTANCE().getSharedPreferences("currentUser", Context.MODE_PRIVATE);
        sharedPreferences.edit().clear().commit();
    }

    /**
     * 初始化百度地图参数配置
     */
    private void initBaiDuMap() {
        mLocationClient = new LocationClient(getApplicationContext());
        //声明LocationClient类
        mLocationClient.registerLocationListener(myListener);
        //注册监听函数

        initLocation();

    }

    private void initLocation(){
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        //可选，默认高精度，设置定位模式，高精度，低功耗，仅设备

        option.setCoorType("bd09ll");
        //可选，默认gcj02，设置返回的定位结果坐标系

        int span=1000;
        option.setScanSpan(span);
        //可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的

        option.setIsNeedAddress(true);
        //可选，设置是否需要地址信息，默认不需要

        option.setOpenGps(true);
        //可选，默认false,设置是否使用gps

        option.setLocationNotify(true);
        //可选，默认false，设置是否当GPS有效时按照1S/1次频率输出GPS结果

        option.setIsNeedLocationDescribe(true);
        //可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”

        option.setIsNeedLocationPoiList(true);
        //可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到

        option.setIgnoreKillProcess(false);
        //可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死

        option.SetIgnoreCacheException(false);
        //可选，默认false，设置是否收集CRASH信息，默认收集

        option.setEnableSimulateGps(false);
        //可选，默认false，设置是否需要过滤GPS仿真结果，默认需要

        mLocationClient.setLocOption(option);
    }


    public List<Goodstype> getGoodstypes() {
        return goodstypes;
    }

    public void setGoodstypes(List<Goodstype> goodstypes) {
        this.goodstypes = goodstypes;
    }

    public LocationClient getmLocationClient() {
        return mLocationClient;
    }
}
