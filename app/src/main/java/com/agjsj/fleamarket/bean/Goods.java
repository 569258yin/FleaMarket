package com.agjsj.fleamarket.bean;


import android.content.Intent;

import java.util.List;

/**
 * Goods entity. @author MyEclipse Persistence Tools
 */

public class Goods implements java.io.Serializable {

    private String goodsid;

    private String userid;

    private Integer goodstypeid;

    private String goodstitle;

    private String goodstime;

    private String goodstext;

    private Integer goodsmoney;

    private Integer goodsoldmoney;

    private String goodsquality;

    private Integer goodsiconnumber;

    private String useraddress;

    private String userphone;

    private String coonecttime;

    private Integer goodslikenum;

    private Integer goodsrepalynum;

    private String goodsicon;

    private UserInfo userInfo;

    private List<Goodsrepaly> goodsrepalyList;

    /**
     * default constructor
     */
    public Goods() {
    }

    public String getGoodsid() {
        return goodsid;
    }

    public void setGoodsid(String goodsid) {
        this.goodsid = goodsid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public Integer getGoodstypeid() {
        return goodstypeid;
    }

    public void setGoodstypeid(Integer goodstypeid) {
        this.goodstypeid = goodstypeid;
    }

    public String getGoodstitle() {
        return goodstitle;
    }

    public void setGoodstitle(String goodstitle) {
        this.goodstitle = goodstitle;
    }

    public String getGoodstime() {
        return goodstime;
    }

    public void setGoodstime(String goodstime) {
        this.goodstime = goodstime;
    }

    public String getGoodstext() {
        return goodstext;
    }

    public void setGoodstext(String goodstext) {
        this.goodstext = goodstext;
    }

    public Integer getGoodsmoney() {
        return goodsmoney;
    }

    public void setGoodsmoney(Integer goodsmoney) {
        this.goodsmoney = goodsmoney;
    }

    public Integer getGoodsoldmoney() {
        return goodsoldmoney;
    }

    public void setGoodsoldmoney(Integer goodsoldmoney) {
        this.goodsoldmoney = goodsoldmoney;
    }

    public String getGoodsquality() {
        return goodsquality;
    }

    public void setGoodsquality(String goodsquality) {
        this.goodsquality = goodsquality;
    }

    public Integer getGoodsiconnumber() {
        return goodsiconnumber;
    }

    public void setGoodsiconnumber(Integer goodsiconnumber) {
        this.goodsiconnumber = goodsiconnumber;
    }

    public String getUseraddress() {
        return useraddress;
    }

    public void setUseraddress(String useraddress) {
        this.useraddress = useraddress;
    }

    public String getUserphone() {
        return userphone;
    }

    public void setUserphone(String userphone) {
        this.userphone = userphone;
    }

    public String getCoonecttime() {
        return coonecttime;
    }

    public void setCoonecttime(String coonecttime) {
        this.coonecttime = coonecttime;
    }

    public Integer getGoodslikenum() {
        return goodslikenum;
    }

    public void setGoodslikenum(Integer goodslikenum) {
        this.goodslikenum = goodslikenum;
    }

    public Integer getGoodsrepalynum() {
        return goodsrepalynum;
    }

    public void setGoodsrepalynum(Integer goodsrepalynum) {
        this.goodsrepalynum = goodsrepalynum;
    }

    public String getGoodsicon() {
        return goodsicon;
    }

    public void setGoodsicon(String goodsicon) {
        this.goodsicon = goodsicon;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public List<Goodsrepaly> getGoodsrepalyList() {
        return goodsrepalyList;
    }

    public void setGoodsrepalyList(List<Goodsrepaly> goodsrepalyList) {
        this.goodsrepalyList = goodsrepalyList;
    }
}