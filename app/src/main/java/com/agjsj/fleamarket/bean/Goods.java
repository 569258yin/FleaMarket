package com.agjsj.fleamarket.bean;


/**
 * Goods entity. @author MyEclipse Persistence Tools
 */

public class Goods implements java.io.Serializable {

	// Fields

	private String goodsid;

	private String userid;
	private String userName;
	private String userIcon;
	private Integer goodstypeid;
	private GoodsType goodstype;
	private String goodstitle;
	private String goodstime;
	private String goodstext;
	private Integer goodsoldmoney;
	private Integer goodsmoney;
	private String goodsquality;
	private Integer goodsiconnumber;
	private String goodsicon;
	private String useraddress;
	private String userphone;
	private String coonecttime;
	private Integer goodslikenumber;
	// 单向一对多
	private Integer goodsrepalynumber;

	/** default constructor */
	public Goods() {
	}

	public String getGoodsid() {
		return this.goodsid;
	}

	public void setGoodsid(String goodsid) {
		this.goodsid = goodsid;
	}

	public String getUserid() {
		return this.userid;
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
		return this.goodstitle;
	}

	public void setGoodstitle(String goodstitle) {
		this.goodstitle = goodstitle;
	}

	public String getGoodstime() {
		return this.goodstime;
	}

	public void setGoodstime(String goodstime) {
		this.goodstime = goodstime;
	}

	public String getGoodstext() {
		return this.goodstext;
	}

	public void setGoodstext(String goodstext) {
		this.goodstext = goodstext;
	}

	public Integer getGoodsoldmoney() {
		return this.goodsoldmoney;
	}

	public void setGoodsoldmoney(Integer goodsoldmoney) {
		this.goodsoldmoney = goodsoldmoney;
	}

	public Integer getGoodsmoney() {
		return this.goodsmoney;
	}

	public void setGoodsmoney(Integer goodsmoney) {
		this.goodsmoney = goodsmoney;
	}

	public String getGoodsquality() {
		return this.goodsquality;
	}

	public void setGoodsquality(String goodsquality) {
		this.goodsquality = goodsquality;
	}

	public Integer getGoodsiconnumber() {
		return this.goodsiconnumber;
	}

	public void setGoodsiconnumber(Integer goodsiconnumber) {
		this.goodsiconnumber = goodsiconnumber;
	}

	public String getGoodsicon() {
		return this.goodsicon;
	}

	public void setGoodsicon(String goodsicon) {
		this.goodsicon = goodsicon;
	}

	public String getUseraddress() {
		return this.useraddress;
	}

	public void setUseraddress(String useraddress) {
		this.useraddress = useraddress;
	}

	public String getUserphone() {
		return this.userphone;
	}

	public void setUserphone(String userphone) {
		this.userphone = userphone;
	}

	public String getCoonecttime() {
		return this.coonecttime;
	}

	public void setCoonecttime(String coonecttime) {
		this.coonecttime = coonecttime;
	}

	public Integer getGoodslikenumber() {
		return this.goodslikenumber;
	}

	public void setGoodslikenumber(Integer goodslikenumber) {
		this.goodslikenumber = goodslikenumber;
	}

	public Integer getGoodsrepalynumber() {
		return this.goodsrepalynumber;
	}

	public void setGoodsrepalynumber(Integer goodsrepalynumber) {
		this.goodsrepalynumber = goodsrepalynumber;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserIcon() {
		return userIcon;
	}
	public void setUserIcon(String userIcon) {
		this.userIcon = userIcon;
	}

	public GoodsType getGoodstype() {
		return goodstype;
	}

	public void setGoodsType(GoodsType goodstype) {
		this.goodstype = goodstype;
	}

}