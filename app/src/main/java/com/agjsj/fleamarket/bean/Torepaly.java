package com.agjsj.fleamarket.bean;

/**
 * Torepaly entity. @author MyEclipse Persistence Tools
 */

public class Torepaly implements java.io.Serializable {

	// Fields

	private Integer torepalyid;
	private Integer goodsreplayid;
	private Integer userid;
	private String torepalycontext;
	private String torepalytime;

	// Constructors

	/** default constructor */
	public Torepaly() {
	}

	/** full constructor */
	public Torepaly(Integer goodsreplayid, Integer userid,
			String torepalycontext, String torepalytime) {
		this.goodsreplayid = goodsreplayid;
		this.userid = userid;
		this.torepalycontext = torepalycontext;
		this.torepalytime = torepalytime;
	}

	// Property accessors

	public Integer getTorepalyid() {
		return this.torepalyid;
	}

	public void setTorepalyid(Integer torepalyid) {
		this.torepalyid = torepalyid;
	}

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

	public String getTorepalycontext() {
		return this.torepalycontext;
	}

	public void setTorepalycontext(String torepalycontext) {
		this.torepalycontext = torepalycontext;
	}

	public String getTorepalytime() {
		return this.torepalytime;
	}

	public void setTorepalytime(String torepalytime) {
		this.torepalytime = torepalytime;
	}

}