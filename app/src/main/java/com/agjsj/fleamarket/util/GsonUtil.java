package com.agjsj.fleamarket.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.orhanobut.logger.Logger;

import java.lang.reflect.Type;

/**
 * Created by YH on 2016/12/23.
 */

public class GsonUtil {
    private static Gson gson;

    static {
        gson = new Gson();
    }

    public static Gson getGson() {
        return gson;
    }

    public static String objectToString(Object obj){
        String str = null;
        try{
            str = gson.toJson(obj);
        }catch (Exception e){
            LogUtil.error("objectToString",e);
        }
        return str;
    }


    /**
     * 根据javaBean类型将字符串转换为对象，适用于一个bean对象
     * @param str
     * @param clazz
     * @return
     */
    public static Object stringToObjectByBean(String str,Class clazz){
        Object obj = null;
        try{
            obj = gson.fromJson(str, clazz);
        }catch (Exception e){
            LogUtil.error("stringToObjectByBean",e);
        }
        return obj;
    }



    /**
     * 集合json转换
     * @param str
     * @param type new TypeToken<Collection<Foo>>(){}.getType();
     * @return
     */
    public static Object stringToObjectByType(String str,Type type){
        Object obj = null;
        try{
            obj = gson.fromJson(str, type);
        }catch (Exception e){
            LogUtil.error("stringToObjectByType",e);
        }
        return obj;
    }

}
