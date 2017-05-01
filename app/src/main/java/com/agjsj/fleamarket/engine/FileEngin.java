package com.agjsj.fleamarket.engine;

import java.io.File;
import java.util.List;

/**
 * Created by MyPC on 2017/4/30.
 */
public interface FileEngin {

    /**
     * 上传图片
     * @param fileList
     * @return
     */
    public String uploadImage(List<File> fileList);
}
