package com.agjsj.fleamarket.view.send;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.agjsj.fleamarket.R;
import com.agjsj.fleamarket.adapter.goods.GridImageAdapter;
import com.agjsj.fleamarket.bean.Goods;
import com.agjsj.fleamarket.bean.GoodsType;
import com.agjsj.fleamarket.bean.ImagePath;
import com.agjsj.fleamarket.engine.GoodsEngine;
import com.agjsj.fleamarket.util.BeanFactory;
import com.agjsj.fleamarket.util.GsonUtil;
import com.agjsj.fleamarket.view.base.BaseActivity;
import com.agjsj.fleamarket.view.base.BaseApplication;
import com.agjsj.fleamarket.view.myview.CircleImageView;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.nanchen.compresshelper.CompressHelper;

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
    private CompressHelper compressHelper;
    //spinner
    private ArrayAdapter<String> adapter;
    private String[] texts;
    private int[] values;
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
        
        initData();
        setListener();
    }


    private void initData() {
        tv_title.setText("发布商品");

        List<GoodsType> GoodsTypeList = BaseApplication.INSTANCE().getGoodstypes();
        if(GoodsTypeList == null){
            GoodsEngine goodsEngine = BeanFactory.getImpl(GoodsEngine.class);
            GoodsTypeList = goodsEngine.getAllGoodsType();
            BaseApplication.INSTANCE().setGoodstypes(GoodsTypeList);
        }
        if(GoodsTypeList == null){
            toast("商品类别为null");
            finish();
        }else {
            texts = new String[GoodsTypeList.size()];
            values = new int[GoodsTypeList.size()];
            for (int i = 0; i < GoodsTypeList.size(); i++) {
                GoodsType GoodsType = GoodsTypeList.get(i);
                texts[i] = GoodsType.getGoodstypename();
                values[i] = GoodsType.getGoodstypeid();
            }
            //绑定要显示的texts
            adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, texts);
            //设置下拉列表的风格
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adapter);
            spinner.setOnItemSelectedListener(new SpinnerSelectedListener());
        }

        compressHelper = new CompressHelper.Builder(SendGoodActivity.this)
                .setMaxWidth(720)  // 默认最大宽度为720
                .setMaxHeight(960) // 默认最大高度为960
                .setQuality(80)    // 默认压缩质量为80
                .setCompressFormat(Bitmap.CompressFormat.JPEG) // 设置默认压缩为jpg格式
                .setDestinationDirectoryPath(Environment.getExternalStoragePublicDirectory(
                        Environment.DIRECTORY_PICTURES).getAbsolutePath())
                .build();
    }

    private void setListener() {
        //返回
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //添加图片监听事件
        gridImageAdapter = new GridImageAdapter(SendGoodActivity.this);
        gridImageAdapter.setImageViewCallBack(new GridImageAdapter.ImageViewCallBack() {
            @Override
            public void onClickImage() {
                Intent intent = new Intent(SendGoodActivity.this, ImageGridActivity.class);
                startActivityForResult(intent, SendGoodActivity.IMAGE_PICKER);
            }
        });
        gridView.setAdapter(gridImageAdapter);

        // 点击发布
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
                            File newFile = compressHelper.compressToFile(file);
                            fileList.add(newFile);
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
                goods.setGoodslikenumber(0);
                goods.setGoodstypeid(values[spinner.getSelectedItemPosition()]);
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
                boolean result = goodsEngine.sendGoods(goods);
                if(result){
                    toast("发布成功");
                    finish();
                }

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

    @Override
    protected void onDestroy() {
        backClearn();
        super.onDestroy();
    }

    private void backClearn() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(et_context.getWindowToken(), 0);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
           finish();
        }
        return super.onKeyDown(keyCode, event);
    }


    class SpinnerSelectedListener implements AdapterView.OnItemSelectedListener {
        public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
            //利用数组中的对应位置取得values中需要的值
            int Id = values[arg2];
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }

}
