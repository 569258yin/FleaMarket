package com.agjsj.fleamarket.bean;

import java.util.Date;
import java.util.List;

/**
 * Goodsrepaly entity. @author MyEclipse Persistence Tools
 */

public class Goodsrepaly implements java.io.Serializable {

	// Fields
	private String goodsreplayid;

	private String goodsid;

	private String userid;

	private UserInfo userInfo;

	private Date goodsreplaytime;

	private String goodsreplaycontent;

	private List<Torepaly> torepalyList;

	// Constructors

	/** default constructor */
	public Goodsrepaly() {
	}

	public Goodsrepaly(String goodsid, String userid, String goodsreplaycontent) {
		this.goodsid = goodsid;
		this.userid = userid;
		this.goodsreplaycontent = goodsreplaycontent;
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	public Date getGoodsreplaytime() {
		return goodsreplaytime;
	}

	public void setGoodsreplaytime(Date goodsreplaytime) {
		this.goodsreplaytime = goodsreplaytime;
	}

	public String getGoodsreplayid() {
		return goodsreplayid;
	}

	public void setGoodsreplayid(String goodsreplayid) {
		this.goodsreplayid = goodsreplayid;
	}

	public String getGoodsid() {
		return goodsid;
	}

	public void setGoodsid(String goodsid) {
		this.goodsid = goodsid;
	}

	public String getGoodsreplaycontent() {
		return goodsreplaycontent;
	}

	public void setGoodsreplaycontent(String goodsreplaycontent) {
		this.goodsreplaycontent = goodsreplaycontent;
	}

	public List<Torepaly> getTorepalyList() {
		return torepalyList;
	}

	public void setTorepalyList(List<Torepaly> torepalyList) {
		this.torepalyList = torepalyList;
	}
}