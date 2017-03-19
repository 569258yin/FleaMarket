package com.agjsj.fleamarket.adapter.goods;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.agjsj.fleamarket.R;
import com.agjsj.fleamarket.util.PicassoUtils;
import com.agjsj.fleamarket.view.send.SendGoodActivity;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MyPC on 2017/3/17.
 */

public class GridImageAdapter extends BaseAdapter {

    private Context context;
    private List<ImageItem> imageItemList = new ArrayList<>();

    private LayoutInflater mInfalater;

    public GridImageAdapter(Context context) {
        this.context = context;
        imageItemList.add(new ImageItem());
        mInfalater = LayoutInflater.from(context);
    }

    public void setImageItemList(List<ImageItem> lists) {
        imageItemList.clear();
        imageItemList.add(new ImageItem());
        imageItemList.addAll(lists);
    }

    public List<ImageItem> getImageItemList() {
        return imageItemList;
    }

    @Override
    public int getCount() {
        return imageItemList.size();
    }

    @Override
    public Object getItem(int position) {
        return imageItemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = mInfalater.inflate(R.layout.item_image, null);
            viewHolder = new ViewHolder();
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.iv_item);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        if (position == 0) {
            Resources resources = context.getResources();
            Drawable drawable = resources.getDrawable(R.mipmap.chat_add_picture_normal);
            viewHolder.imageView.setBackground(drawable);
            viewHolder.imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(imageViewCallBack != null){
                        imageViewCallBack.onClickImage();
                    }

                }
            });
        } else {
            viewHolder.imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   return;
                }
            });
            ImageItem imageItem = imageItemList.get(position);
            PicassoUtils.loadResourceImage(imageItem.path,100, 100, viewHolder.imageView);
        }
        return convertView;
    }

    class ViewHolder {
        ImageView imageView;
    }

    private ImageViewCallBack imageViewCallBack;

    public void setImageViewCallBack(ImageViewCallBack imageViewCallBack) {
        this.imageViewCallBack = imageViewCallBack;
    }
    public interface ImageViewCallBack{
        public void onClickImage();
    }

}
