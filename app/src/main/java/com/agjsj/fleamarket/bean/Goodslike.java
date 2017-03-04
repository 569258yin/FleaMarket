package com.agjsj.fleamarket.bean;

/**
 * Goodslike entity. @author MyEclipse Persistence Tools
 */

public class Goodslike implements java.io.Serializable {

	// Fields

	private Integer goodslikeid;
	private Integer goodsid;
	private Integer userid;

	// Constructors

	/** default constructor */
	public Goodslike() {
	}

	/** full constructor */
	public Goodslike(Integer goodsid, Integer userid) {
		this.goodsid = goodsid;
		this.userid = userid;
	}

	// Property accessors

	public Integer getGoodslikeid() {
		return this.goodslikeid;
	}

	public void setGoodslikeid(Integer goodslikeid) {
		this.goodslikeid = goodslikeid;
	}

	public Integer getGoodsid() {
		return this.goodsid;
	}

	public void setGoodsid(Integer goodsid) {
		this.goodsid = goodsid;
	}

	public Integer getUserid() {
		return this.userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

}