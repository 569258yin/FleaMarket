package com.agjsj.fleamarket.bean;

/**
 * Goodsrepaly entity. @author MyEclipse Persistence Tools
 */

public class Goodsrepaly implements java.io.Serializable {

	// Fields

	private Integer goodsreplayid;
	private Integer userid;
	private Integer goodsid;
	private String goodsreplaytime;
	private String goodsreplaycontent;

	// Constructors

	/** default constructor */
	public Goodsrepaly() {
	}

	/** full constructor */
	public Goodsrepaly(Integer userid, Integer goodsid, String goodsreplaytime,
			String goodsreplaycontent) {
		this.userid = userid;
		this.goodsid = goodsid;
		this.goodsreplaytime = goodsreplaytime;
		this.goodsreplaycontent = goodsreplaycontent;
	}

	// Property accessors

	public Integer getGoodsreplayid() {
		return this.goodsreplayid;
	}

	public void setGoodsreplayid(Integer goodsreplayid) {
		this.goodsreplayid = goodsreplayid;
	}

	public Integer getUserid() {
		return this.userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public Integer getGoodsid() {
		return this.goodsid;
	}

	public void setGoodsid(Integer goodsid) {
		this.goodsid = goodsid;
	}

	public String getGoodsreplaytime() {
		return this.goodsreplaytime;
	}

	public void setGoodsreplaytime(String goodsreplaytime) {
		this.goodsreplaytime = goodsreplaytime;
	}

	public String getGoodsreplaycontent() {
		return this.goodsreplaycontent;
	}

	public void setGoodsreplaycontent(String goodsreplaycontent) {
		this.goodsreplaycontent = goodsreplaycontent;
	}

}