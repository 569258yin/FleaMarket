package com.agjsj.fleamarket.util;

import android.app.Activity;
import android.widget.ImageView;

import com.agjsj.fleamarket.R;
import com.lzy.imagepicker.loader.ImageLoader;

/**
 * Created by MyPC on 2017/3/17.
 */

public class PicassoImageLoader implements ImageLoader {
    @Override
    public void displayImage(Activity activity, String path, ImageView imageView, int width, int height) {
        PicassoUtils.loadResizeImage(path, R.drawable.logo,R.drawable.logo,width,height,imageView);
    }

    @Override
    public void clearMemoryCache() {

    }
}
