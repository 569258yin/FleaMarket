package com.agjsj.fleamarket.bean.json;

/**
 * 用于请求参数json转换
 * Created by YH on 2017/3/21.
 */

public class PageJsonData
{
    private String id;
    private int pageNum;
    private int pageSize;
    private int type;

    public PageJsonData() {
    }

    public PageJsonData(String id, int pageNum, int pageSize) {
        this.id = id;
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }

    public PageJsonData(String id, int pageNum, int pageSize, int type) {
        this.id = id;
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
