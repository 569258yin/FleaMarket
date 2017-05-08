package com.agjsj.fleamarket.view.school;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.*;
import butterknife.Bind;
import com.agjsj.fleamarket.R;
import com.agjsj.fleamarket.adapter.goods.GridImageAdapter;
import com.agjsj.fleamarket.bean.FoundCase;
import com.agjsj.fleamarket.bean.ImagePath;
import com.agjsj.fleamarket.engine.BaseCallBack;
import com.agjsj.fleamarket.engine.FileEngin;
import com.agjsj.fleamarket.engine.FoundEngine;
import com.agjsj.fleamarket.util.BeanFactory;
import com.agjsj.fleamarket.util.CompressHelperUtil;
import com.agjsj.fleamarket.util.GsonUtil;
import com.agjsj.fleamarket.view.base.BaseActivity;
import com.agjsj.fleamarket.view.base.BaseApplication;
import com.agjsj.fleamarket.view.myview.CircleImageView;
import com.agjsj.fleamarket.view.send.SendGoodActivity;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.nanchen.compresshelper.CompressHelper;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by MyPC on 2017/4/29.
 */
public class SendLostFoundActivity extends BaseActivity{

    @Bind(R.id.tv_left)
    CircleImageView backBtn;
    @Bind(R.id.tv_title)
    TextView tv_title;
    @Bind(R.id.tv_right)
    TextView tv_right;
    @Bind(R.id.grid_send_foundcase)
    GridView gridView;
    @Bind(R.id.et_content_send_foundcase)
    EditText et_context;
    @Bind(R.id.spinner_category_send_foundcase)
    Spinner spinner;


    private String[] typeValue = new String[]{"物品招领","遗失物品"};
    public static final int IMAGE_PICKER = 1001;
    private GridImageAdapter gridImageAdapter;
    private boolean isSend = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_foundcase);

        initData();
        setListener();
    }
    private void initData() {
        tv_title.setText("发布失物招领");

        //质量选择
        ArrayAdapter<String> typeAdapter =  new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, typeValue);
        //设置下拉列表的风格
        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(typeAdapter);

        //初始化图片选择器参数
        ImagePicker imagePicker = ImagePicker.getInstance();
        imagePicker.setSelectLimit(1);

    }

    private void setListener() {
//返回
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backClearn();
                finish();
            }
        });

        //添加图片监听事件
        gridImageAdapter = new GridImageAdapter(SendLostFoundActivity.this);
        gridImageAdapter.setImageViewCallBack(new GridImageAdapter.ImageViewCallBack() {
            @Override
            public void onClickImage() {
                Intent intent = new Intent(SendLostFoundActivity.this, ImageGridActivity.class);
                startActivityForResult(intent, SendGoodActivity.IMAGE_PICKER);
            }
        });
        gridView.setAdapter(gridImageAdapter);

        tv_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isSend){
                    return;
                }
                isSend = true;
                String context = et_context.getText().toString();
                if (StringUtils.isEmpty(context)) {
                    toast("物品丢失描述不能为空!");
                    return;
                }
                FoundEngine foundEngine = BeanFactory.getImpl(FoundEngine.class);
                FileEngin fileEngin = BeanFactory.getImpl(FileEngin.class);
                if (fileEngin == null || foundEngine == null){
                    toast("系统异常");
                    return;
                }
                int imageCount = 0;
                String imagePath = "";
                List<ImageItem> imageItemList = gridImageAdapter.getImageItemList();
                if (imageItemList.size() > 1) {
                    List<File> fileList = new ArrayList<File>();
                    for (int i = 1; i < imageItemList.size(); i++) {
                        File file = new File(imageItemList.get(i).path);
                        if (file.exists()) {
                            imageCount++;
                            File newFile = CompressHelperUtil.compressToFile(file);
                            fileList.add(newFile);
                        }
                    }
                    if (imageCount > 0) {
                        imagePath = fileEngin.uploadImage(fileList);
                        if (StringUtils.isEmpty(imagePath)) {
                            toast("图片上传失败!");
                            return;
                        }
                    }
                }
                FoundCase foundCase = new FoundCase();
                if(!imagePath.equals("")) {
                    ImagePath imageObj = (ImagePath) GsonUtil.stringToObjectByBean(imagePath, ImagePath.class);
                    if (imageObj.getImageUrls() != null && imageObj.getImageUrls().size() > 0) {
                        StringBuffer sb = new StringBuffer();
                        for (String imageP : imageObj.getImageUrls()) {
                            sb.append(imageP);
                            sb.append(";");
                        }
                        foundCase.setFdcimage(sb.toString().substring(0, sb.toString().length() - 1));
                    }
                }

                foundCase.setFdccontext(context);
                foundCase.setFdcimage(imagePath);
                foundCase.setFdctype(spinner.getSelectedItemPosition());
                foundCase.setUserid(BaseApplication.INSTANCE().getCurrentUser().getUserid());
                foundEngine.sendFoundcase(foundCase, new BaseCallBack.SendCallBack() {
                    @Override
                    public void sendResultCallBack(int responseCode) {
                        if(responseCode == BaseCallBack.SEND_OK){
                            backClearn();
                            toast("发布成功");
                            finish();
                        } else {
                            isSend = false;
                            toast("发布失败");
                        }
                    }
                });
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
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }

    private void backClearn() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(et_context.getWindowToken(), 0);
    }

}
