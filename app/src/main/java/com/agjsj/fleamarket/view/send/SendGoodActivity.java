package com.agjsj.fleamarket.view.send;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.agjsj.fleamarket.BaseApplication;
import com.agjsj.fleamarket.R;
import com.agjsj.fleamarket.adapter.goods.GridImageAdapter;
import com.agjsj.fleamarket.bean.Goods;
import com.agjsj.fleamarket.bean.ImagePath;
import com.agjsj.fleamarket.engine.GoodsEngine;
import com.agjsj.fleamarket.util.BeanFactory;
import com.agjsj.fleamarket.util.GsonUtil;
import com.agjsj.fleamarket.view.base.BaseActivity;
import com.agjsj.fleamarket.view.myview.CircleImageView;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;

import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by YH on 2017/3/17.
 */

public class SendGoodActivity extends BaseActivity {

    public static final int IMAGE_PICKER = 1001;
    private GridImageAdapter gridImageAdapter;

    @Bind(R.id.tv_left)
    CircleImageView backBtn;
    @Bind(R.id.tv_title)
    TextView tv_title;
    @Bind(R.id.tv_right)
    TextView tv_right;
    @Bind(R.id.grid_sendgoods)
    GridView gridView;
    @Bind(R.id.et_content_sendgoods)
    EditText et_context;
    @Bind(R.id.et_title_sendgoods)
    EditText et_title;
    @Bind(R.id.tv_address_sendgoods)
    TextView tv_address;
    @Bind(R.id.et_price_sendgoods)
    EditText et_price;
    @Bind(R.id.et_catogory_sendgoods)
    EditText et_categoty;
    @Bind(R.id.spinner_qutity_sendgoods)
    Spinner spinner;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sendgoods);

        tv_title.setText("发布商品");


        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backClearn();
                finish();
            }
        });


        gridImageAdapter = new GridImageAdapter(SendGoodActivity.this);
        gridImageAdapter.setImageViewCallBack(new GridImageAdapter.ImageViewCallBack() {
            @Override
            public void onClickImage() {
                Intent intent = new Intent(SendGoodActivity.this, ImageGridActivity.class);
                startActivityForResult(intent, SendGoodActivity.IMAGE_PICKER);
            }
        });
        gridView.setAdapter(gridImageAdapter);


        tv_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = et_title.getText().toString();
                if (StringUtils.isEmpty(title)) {
                    toast("标题不能为空!");
                    return;
                }
                String context = et_context.getText().toString();
                if (StringUtils.isEmpty(context)) {
                    toast("宝贝描述不能为空!");
                    return;
                }
                String price = et_price.getText().toString();
                if (StringUtils.isEmpty(context)) {
                    toast("价格不能为空!");
                    return;
                }
                GoodsEngine goodsEngine = BeanFactory.getImpl(GoodsEngine.class);
                int imageCount = 0;
                String imagePath = "";
                List<ImageItem> imageItemList = gridImageAdapter.getImageItemList();
                if (imageItemList.size() > 1) {
                    List<File> fileList = new ArrayList<File>();
                    for (int i = 1; i < imageItemList.size(); i++) {
                        File file = new File(imageItemList.get(i).path);
                        if (file.exists()) {
                            imageCount++;
                            fileList.add(file);
                        }
                    }
                    if (imageCount > 0) {
                        imagePath = goodsEngine.uploadImage(fileList);
                        if (StringUtils.isEmpty(imagePath)) {
                            toast("图片上传失败!");
                            return;
                        }
                    }
                }
                Goods goods = new Goods();
                goods.setGoodstext(context);
                goods.setGoodstitle(title);
                goods.setGoodsoldmoney((int) (Double.parseDouble(price) * 100));
                goods.setGoodsrepalynumber(0);
                goods.setGoodstypeid(1);
                goods.setGoodsiconnumber(imageCount);
                ImagePath imageObj = (ImagePath) GsonUtil.stringToObjectByBean(imagePath, ImagePath.class);
                if(imageObj.getImageUrls() != null && imageObj.getImageUrls().size() > 0){
                    StringBuffer sb = new StringBuffer();
                    for(String imageP : imageObj.getImageUrls()){
                        sb.append(imageP);
                        sb.append(";");
                    }
                    goods.setGoodsicon(sb.toString().substring(0,sb.toString().length()-1));
                }
                goods.setUserid(BaseApplication.INSTANCE().getCurrentUser().getUserid());
                goodsEngine.sendGoods(goods);

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            if (data != null && requestCode == IMAGE_PICKER) {
                ArrayList<ImageItem> images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                gridImageAdapter.setImageItemList(images);
                gridImageAdapter.notifyDataSetChanged();
            } else {
                Toast.makeText(this, "没有数据", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void backClearn() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(et_context.getWindowToken(), 0);
    }


}
