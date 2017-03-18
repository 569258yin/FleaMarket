package com.agjsj.fleamarket.net;

import com.agjsj.fleamarket.util.HttpConnectionUtils;

import java.io.File;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * 文件上传任务
 * Created by YH on 2017/3/18.
 */

public class HttpFileReuqest implements Callable<String>{

    private List<File> fileList;

    private int timeout = 3000;

    public HttpFileReuqest(List<File> fileList) {
        this.fileList = fileList;
    }


    @Override
    public String call() throws Exception {
        return HttpConnectionUtils.uploadImage(fileList);
    }
}
