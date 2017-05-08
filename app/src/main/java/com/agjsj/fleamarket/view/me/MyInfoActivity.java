package com.agjsj.fleamarket.view.me;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.*;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.agjsj.fleamarket.R;
import com.agjsj.fleamarket.bean.UserInfo;
import com.agjsj.fleamarket.engine.BaseCallBack;
import com.agjsj.fleamarket.engine.FileEngin;
import com.agjsj.fleamarket.engine.UserEngine;
import com.agjsj.fleamarket.params.GlobalParams;
import com.agjsj.fleamarket.util.BeanFactory;
import com.agjsj.fleamarket.util.CompressHelperUtil;
import com.agjsj.fleamarket.util.PicassoUtils;
import com.agjsj.fleamarket.view.MainActivity;
import com.agjsj.fleamarket.view.MeUI;
import com.agjsj.fleamarket.view.base.BaseActivity;
import com.agjsj.fleamarket.view.base.BaseApplication;
import com.agjsj.fleamarket.view.manager.BaseUI;
import com.agjsj.fleamarket.view.manager.MiddleManager;
import com.agjsj.fleamarket.view.manager.TitleManager;
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
 * Created by YH on 2017/5/1.
 */
public class MyInfoActivity extends BaseActivity {
    @Bind(R.id.iv_user_icon)
    ImageView userIcon;
    @Bind(R.id.et_me_info_name)
    EditText etName;
    @Bind(R.id.et_me_info_age)
    EditText etAge;
    @Bind(R.id.spinner_me_info_sex)
    Spinner spinner;
    @Bind(R.id.et_me_info_address)
    EditText etAddress;
    @Bind(R.id.et_me_info_school)
    EditText etSchool;
    @Bind(R.id.et_me_info_colleage)
    EditText etColleage;
    @Bind(R.id.et_me_info_qqNumber)
    EditText etQqNum;
    @Bind(R.id.et_me_info_wxNumber)
    EditText etWxNum;
    @Bind(R.id.et_me_info_phone)
    EditText etPhone;

    @Bind(R.id.tv_title_back)
    TextView tvBack;
    @Bind(R.id.tv_title_text)
    TextView tvTitle;
    @Bind(R.id.tv_title_right)
    TextView tvRight;

    private String[] ages = new String[]{"男", "女"};
    private UserInfo userInfo;
    private boolean isLoad = false;
    private boolean isUploadImage = false;
    public static final int IMAGE_PICKER = 1001;
    private ArrayList<ImageItem> images;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_me_info);
        userInfo = BaseApplication.INSTANCE().getCurrentUser();
        if (userInfo == null) {
            finish();
        }
        initMyView();

        setListener();
    }

    private void initMyView() {
        //质量选择
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(MyInfoActivity.this, android.R.layout.simple_spinner_item, ages);
        //设置下拉列表的风格
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);

        PicassoUtils.loadResizeImage(userInfo.getUsericon(), 70, 70, userIcon);

        etName.setText(userInfo.getNickname() + "");
        try {
            if (userInfo.getUserage() == null) {
                etAge.setText(0 + "");
            } else {
                etAge.setText(userInfo.getUserage()+"");
            }
        } catch (Exception e) {
        }
        spinner.setSelection(userInfo.getUsersex(), true);
        etAddress.setText(userInfo.getUseraddress() + "");
        etSchool.setText(userInfo.getSchool() + "");
        etColleage.setText(userInfo.getColleage() + "");
        etQqNum.setText(userInfo.getQqnumber() + "");
        etWxNum.setText(userInfo.getWxnumber() + "");
        etPhone.setText(userInfo.getUserphone() + "");

        tvTitle.setText("个人资料");
        tvRight.setVisibility(View.VISIBLE);
        tvRight.setText("更新");

        //初始化图片选择器参数
        ImagePicker imagePicker = ImagePicker.getInstance();
        imagePicker.setSelectLimit(1);


    }

    public void setListener() {
        tvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideSoftInputView();
                finish();
            }
        });


        userIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyInfoActivity.this, ImageGridActivity.class);
                startActivityForResult(intent, SendGoodActivity.IMAGE_PICKER);
            }
        });
        tvRight.setVisibility(View.VISIBLE);
        tvRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isLoad) {
                    return;
                }
                isLoad = true;


                String nickName = etName.getText().toString();
                String age = etAge.getText().toString();
                int sex = spinner.getSelectedItemPosition();
                String address = etAddress.getText().toString();
                String school = etSchool.getText().toString();
                String colleage = etColleage.getText().toString();
                String qqNum = etQqNum.getText().toString();
                String wxNum = etWxNum.getText().toString();
                boolean bool = true;
                if (StringUtils.isEmpty(nickName)) {
                    toast("用户名不能为空");
                    bool = false;
                }
                if (StringUtils.isEmpty(age)) {
                    toast("年龄不能为空");
                    bool = false;
                }
                if (StringUtils.isEmpty(address)) {
                    toast("地址不能为空");
                    bool = false;
                }
                if (!bool) {
                    return;
                }
                String userIconPath = userInfo.getUsericon();
                if (isUploadImage) {
                    FileEngin fileEngin = BeanFactory.getImpl(FileEngin.class);
                    List<File> files = new ArrayList<>(1);
                    File file = new File(images.get(0).path);
                    if (file.exists()) {
                        File newFile = CompressHelperUtil.compressToFile(file);
                        files.add(newFile);
                        String result = fileEngin.uploadImage(files);
                        if (StringUtils.isNotEmpty(result)) {
                            userIconPath = result;
                        }
                    }
                }
                UserEngine userEngine = BeanFactory.getImpl(UserEngine.class);
                if (userEngine != null) {
                    final UserInfo user = new UserInfo(userInfo.getUserid(), nickName,
                            sex, Integer.parseInt(age), address, qqNum, wxNum, colleage, school, userIconPath);
                    userEngine.updateUserInfo(user, new BaseCallBack.SendCallBack() {
                        @Override
                        public void sendResultCallBack(int responseCode) {
                            if (responseCode == BaseCallBack.SEND_OK) {
                                BaseApplication.INSTANCE().updateLocalUser(userInfo);
                                isLoad = false;
                                toast("上传成功！");
                            } else {
                                toast("上传失败！");
                                isLoad = false;
                            }
                        }
                    });
                } else {
                    isLoad = false;
                    toast("系统异常");
                }
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            if (data != null && requestCode == IMAGE_PICKER) {
                images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                if (images != null && images.size() > 0) {
                    PicassoUtils.loadResizeImage(images.get(0).path, 70, 70, userIcon);
                    isUploadImage = true;
                }
            } else {
                Toast.makeText(this, "没有数据", Toast.LENGTH_SHORT).show();
            }
        }
    }

}
