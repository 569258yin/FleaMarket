package com.agjsj.fleamarket.bean;

/**
 * Foundcase entity. @author MyEclipse Persistence Tools
 */

public class Foundcase implements java.io.Serializable {

	// Fields

	private Integer fdcid;
	private Integer userid;
	private UserInfo userInfo;
	private String fdctitle;
	private String fdctime;
	private String fdccontext;
	private String fdcimage;
	private String fdcfindtime;
	private String fdcfindaddress;
	private String fdcuserphone;
	private String fdcuseraddress;
	private int fdctag;

	// Constructors

	public int getFdctag() {
		return fdctag;
	}

	public void setFdctag(int fdctag) {
		this.fdctag = fdctag;
	}

	/** default constructor */
	public Foundcase() {
	}

	/** minimal constructor */
	public Foundcase(Integer userid, String fdctitle, String fdctime) {
		this.userid = userid;
		this.fdctitle = fdctitle;
		this.fdctime = fdctime;
	}

	/** full constructor */
	public Foundcase(Integer userid, String fdctitle, String fdctime,
			String fdccontext, String fdcimage, String fdcfindtime,
			String fdcfindaddress, String fdcuserphone, String fdcuseraddress) {
		this.userid = userid;
		this.fdctitle = fdctitle;
		this.fdctime = fdctime;
		this.fdccontext = fdccontext;
		this.fdcimage = fdcimage;
		this.fdcfindtime = fdcfindtime;
		this.fdcfindaddress = fdcfindaddress;
		this.fdcuserphone = fdcuserphone;
		this.fdcuseraddress = fdcuseraddress;
	}

	// Property accessors

	public Integer getFdcid() {
		return this.fdcid;
	}

	public void setFdcid(Integer fdcid) {
		this.fdcid = fdcid;
	}

	public Integer getUserid() {
		return this.userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public String getFdctitle() {
		return this.fdctitle;
	}

	public void setFdctitle(String fdctitle) {
		this.fdctitle = fdctitle;
	}

	public String getFdctime() {
		return this.fdctime;
	}

	public void setFdctime(String fdctime) {
		this.fdctime = fdctime;
	}

	public String getFdccontext() {
		return this.fdccontext;
	}

	public void setFdccontext(String fdccontext) {
		this.fdccontext = fdccontext;
	}

	public String getFdcimage() {
		return this.fdcimage;
	}

	public void setFdcimage(String fdcimage) {
		this.fdcimage = fdcimage;
	}

	public String getFdcfindtime() {
		return this.fdcfindtime;
	}

	public void setFdcfindtime(String fdcfindtime) {
		this.fdcfindtime = fdcfindtime;
	}

	public String getFdcfindaddress() {
		return this.fdcfindaddress;
	}

	public void setFdcfindaddress(String fdcfindaddress) {
		this.fdcfindaddress = fdcfindaddress;
	}

	public String getFdcuserphone() {
		return this.fdcuserphone;
	}

	public void setFdcuserphone(String fdcuserphone) {
		this.fdcuserphone = fdcuserphone;
	}

	public String getFdcuseraddress() {
		return this.fdcuseraddress;
	}

	public void setFdcuseraddress(String fdcuseraddress) {
		this.fdcuseraddress = fdcuseraddress;
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

}