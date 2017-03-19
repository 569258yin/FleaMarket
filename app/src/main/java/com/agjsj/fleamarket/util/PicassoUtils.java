package com.agjsj.fleamarket.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.agjsj.fleamarket.R;
import com.squareup.picasso.LruCache;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.io.File;

/**
 * 对picasso的封装
 *
 * @author chx
 * @date 2016/10/29
 */
public class PicassoUtils {

    private static Picasso picasso;


    /**
     * 初始化Picasso
     *
     * @param context
     */
    public static void initPicasso(Context context) {

        Picasso.Builder builder = new Picasso.Builder(context);
        picasso = builder.memoryCache(new LruCache(10 << 20)).defaultBitmapConfig(Bitmap.Config.RGB_565)
                .indicatorsEnabled(false).build();

        Picasso.setSingletonInstance(picasso);

    }

    /**
     * 简单的加载图片
     */
    public static void loadImage(String path, ImageView imageView) {
        picasso.load(path).into(imageView);
    }

    /**
     * 指定加载失败显示的图片
     */
    public static void loadImage(String path, int errorPicId, ImageView imageView) {
        picasso.load(path).error(errorPicId).into(imageView);
    }

    /**
     * 指定加载失败显示的图片
     */
    public static void loadImage(String path, int errorPicId, int placeholderPicId, ImageView imageView) {
        picasso.load(path).error(errorPicId).placeholder(placeholderPicId).into(imageView);
    }

    /**
     * 加载本地文件图片
     * @param path
     * @param resizeWidth
     * @param resizeHeight
     * @param imageView
     */
    public static void loadResizeImage(String path, int resizeWidth, int resizeHeight, ImageView imageView) {
        picasso.load(path).error(R.drawable.icon_error).placeholder(R.drawable.icon_empty).resize(resizeWidth, resizeHeight).into(imageView);
    }

    /**
     * 加载指定大小的图片
     * 并制定加载错我的图片和默认的图片
     *
     * @param path
     * @param resizeWidth
     * @param resizeHeight
     * @param imageView
     */
    public static void loadResizeImage(String path, int errorPicId, int placeholderPicId, int resizeWidth, int resizeHeight, ImageView imageView) {
        picasso.load(path).error(errorPicId).placeholder(placeholderPicId).resize(resizeWidth, resizeHeight).into(imageView);
    }


    /**
     * 加载本地资源  会转化为new File(path)
     * @param path
     * @param resizeWidth
     * @param resizeHeight
     * @param imageView
     */
    public static void loadResourceImage(String path, int resizeWidth, int resizeHeight, ImageView imageView) {
        picasso.load(new File(path)).error(R.drawable.icon_error).placeholder(R.drawable.icon_empty).resize(resizeWidth, resizeHeight).into(imageView);
    }


    /**
     * 裁剪图片
     *
     * @param mContext   上下文
     * @param path       图片路径
     * @param mImageView 控件
     */
    public static void loadImageViewCrop(Context mContext, String path, ImageView mImageView) {
        Picasso.with(mContext).load(path).transform(new CropImageView()).into(mImageView);
    }

    /**
     * 自定义图片裁剪
     */
    public static class CropImageView implements Transformation {

        @Override
        public Bitmap transform(Bitmap source) {
            int size = Math.min(source.getWidth(), source.getHeight());
            int x = (source.getWidth() - size) / 2;
            int y = (source.getHeight() - size) / 2;

            Bitmap newBitmap = Bitmap.createBitmap(source, x, y, size, size);

            if (newBitmap != null) {
                //内存回收
                source.recycle();
            }
            return newBitmap;
        }

        @Override
        public String key() {

            return "lgl";
        }
    }
}

