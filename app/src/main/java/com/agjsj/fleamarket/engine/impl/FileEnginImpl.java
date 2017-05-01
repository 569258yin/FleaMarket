package com.agjsj.fleamarket.engine.impl;

import com.agjsj.fleamarket.engine.BaseEngine;
import com.agjsj.fleamarket.engine.FileEngin;
import com.agjsj.fleamarket.net.HttpClient;
import com.agjsj.fleamarket.net.HttpFileReuqest;

import java.io.File;
import java.util.List;

/**
 * Created by YH on 2017/4/30.
 */
public class FileEnginImpl extends BaseEngine implements FileEngin{


    @Override
    public String uploadImage(List<File> fileList) {
        if(fileList == null || fileList.size() <= 0){
            return null;
        }
        return HttpClient.getInstance().submitRequestBlock(new HttpFileReuqest(fileList));
    }
}
