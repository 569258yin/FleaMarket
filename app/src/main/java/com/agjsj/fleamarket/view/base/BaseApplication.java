package com.agjsj.fleamarket.view.base;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.agjsj.fleamarket.bean.GoodsType;
import com.agjsj.fleamarket.bean.UserInfo;
import com.agjsj.fleamarket.util.PicassoImageLoader;
import com.agjsj.fleamarket.util.PicassoUtils;
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
    private List<GoodsType> goodstypes;

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

            try {
                InputStream is = getResources().getAssets().open("bean.properties");
                properties.load(is);
            } catch (IOException e) {
                e.printStackTrace();
            }
            this.token = getLocalToken();
            Logger.d("Token:" + token);

            currentUser = getLocalUser();

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
            myUser.setUsername(sharedPreferences.getString("userNickName", ""));
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
        SharedPreferences sharedPreferences = BaseApplication.INSTANCE().getSharedPreferences("currentUser", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("id", myUser.getUserid());
        editor.putInt("userSex", myUser.getUsersex());
        editor.putString("userAddress", myUser.getUseraddress());
        editor.putString("userNickName", myUser.getUsername());
        editor.putString("qqNumber", myUser.getQqnumber());
        editor.putString("wxNumber", myUser.getWxnumber());
        editor.putString("colleage", myUser.getColleage());
        editor.putString("school", myUser.getSchool());
        editor.putString("userIcon", myUser.getUsericon());
        editor.putString("userPhone", myUser.getUserphone());
        editor.commit();

    }

    //------------------------------------退出登录--------------------------------
    public void deleteCurrentUser() {
        SharedPreferences sharedPreferences = BaseApplication.INSTANCE().getSharedPreferences("currentUser", Context.MODE_PRIVATE);
        sharedPreferences.edit().clear().commit();
    }

    public List<GoodsType> getGoodstypes() {
        return goodstypes;
    }

    public void setGoodstypes(List<GoodsType> goodstypes) {
        this.goodstypes = goodstypes;
    }
}
