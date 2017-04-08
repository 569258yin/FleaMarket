package com.agjsj.fleamarket.bean;

/**
 * Torepaly entity. @author MyEclipse Persistence Tools
 */

public class Torepaly implements java.io.Serializable {

	// Fields

	private String torepalyid;
	private String goodsreplayid;
	private String userid;
	private String username;
	private String touserid;
	private String tousername;
	private String torepalycontext;
	private String torepalytime;

	// Constructors

	/** default constructor */
	public Torepaly() {
	}

	public Torepaly(String torepalyid, String goodsreplayid, String userid, String username, String touserid, String tousername, String torepalycontext, String torepalytime) {
		this.torepalyid = torepalyid;
		this.goodsreplayid = goodsreplayid;
		this.userid = userid;
		this.username = username;
		this.touserid = touserid;
		this.tousername = tousername;
		this.torepalycontext = torepalycontext;
		this.torepalytime = torepalytime;
	}

	// Property accessors


	public String getTorepalyid() {
		return torepalyid;
	}

	public void setTorepalyid(String torepalyid) {
		this.torepalyid = torepalyid;
	}

	public String getGoodsreplayid() {
		return goodsreplayid;
	}

	public void setGoodsreplayid(String goodsreplayid) {
		this.goodsreplayid = goodsreplayid;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getTouserid() {
		return touserid;
	}

	public void setTouserid(String touserid) {
		this.touserid = touserid;
	}

	public String getTousername() {
		return tousername;
	}

	public void setTousername(String tousername) {
		this.tousername = tousername;
	}

	public String getTorepalycontext() {
		return torepalycontext;
	}

	public void setTorepalycontext(String torepalycontext) {
		this.torepalycontext = torepalycontext;
	}

	public String getTorepalytime() {
		return torepalytime;
	}

	public void setTorepalytime(String torepalytime) {
		this.torepalytime = torepalytime;
	}
}