package com.agjsj.fleamarket.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;
import com.agjsj.fleamarket.view.send.SendGoodActivity;
import com.nanchen.compresshelper.CompressHelper;

import java.io.File;

/**
 * Created by YH on 2017/5/7.
 */
public class CompressHelperUtil {

    private static CompressHelper compressHelper;


    public static void initCompress(Context context){
        compressHelper = new CompressHelper.Builder(context)
                .setMaxWidth(720)  // 默认最大宽度为720
                .setMaxHeight(960) // 默认最大高度为960
                .setQuality(80)    // 默认压缩质量为80
                .setCompressFormat(Bitmap.CompressFormat.JPEG) // 设置默认压缩为jpg格式
                .setDestinationDirectoryPath(Environment.getExternalStoragePublicDirectory(
                        Environment.DIRECTORY_PICTURES).getAbsolutePath())
                .build();
    }


    public static File compressToFile(File file){
        if(file.exists()) {
            File newFile = compressHelper.compressToFile(file);
            return newFile;
        }else {
            return null;
        }
    }


}
