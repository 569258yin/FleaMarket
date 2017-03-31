package com.agjsj.fleamarket.util;

import android.content.Context;
import android.content.res.Resources;

/**
 * 像素处理适配工具类
 * Created by YH on 2017/3/30.
 */

public class DensityTool {
    //根据手机的分辨率从 dp 的单位 转成为 px(像素)
    public static float dp2px(Resources resources, float dpValue) {
        final float scale = resources.getDisplayMetrics().density;
        return (dpValue * scale + 0.5f);
    }
    //根据手机的分辨率从 dp 的单位 转成为 px(像素)
    public static int dp2px(Context context, float dpValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int)(dpValue * scale + 0.5f);
    }
    //根据手机的分辨率从 px(像素) 的单位 转成为 dp
    public static float px2dp(Resources resources, float pxValue) {
        final float scale = resources.getDisplayMetrics().density;
        return (pxValue / scale + 0.5f);
    }
    //根据手机的分辨率从 px(像素) 的单位 转成为 dp
    public static float px2dp(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (pxValue / scale + 0.5f);
    }
    //获取屏幕dpi
    public static int getDpi(Context context) {
        return context.getResources().getDisplayMetrics().densityDpi;
    }
}
