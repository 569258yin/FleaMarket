package com.agjsj.fleamarket.bean;

import java.util.Date;

/**
 * UserInfo entity. @author MyEclipse Persistence Tools
 */

public class UserInfo implements java.io.Serializable {

	// Fields

	private String userid;
	private String username;
	private String userpassword;
	private Integer usersex;
	private Integer userage;
	private String useraddress;
	private String nickname;
	private String signature;
	private String qqnumber;
	private String wxnumber;
	private String colleage;
	private String school;
	private String usericon;
	private String userphone;
	private String validatenumber;
	private String uuid;
	private Date createTime;
	private Date modifyTime;

	// Constructors

	/** default constructor */
	public UserInfo() {
	}

	public UserInfo(String username, String userpassword) {
		this.username = username;
		this.userpassword = userpassword;
	}

	/** minimal constructor */
	public UserInfo(String username, String userpassword, Integer usersex) {
		this.username = username;
		this.userpassword = userpassword;
		this.usersex = usersex;
	}

	/** full constructor */
	

	// Property accessors



	public UserInfo(String userid, String username, String userpassword,
			Integer usersex, Integer userage, String useraddress,
			String nickname, String signature, String qqnumber,
			String wxnumber, String colleage, String school, String usericon,
			String userphone, String validatenumber) {
		super();
		this.userid = userid;
		this.username = username;
		this.userpassword = userpassword;
		this.usersex = usersex;
		this.userage = userage;
		this.useraddress = useraddress;
		this.nickname = nickname;
		this.signature = signature;
		this.qqnumber = qqnumber;
		this.wxnumber = wxnumber;
		this.colleage = colleage;
		this.school = school;
		this.usericon = usericon;
		this.userphone = userphone;
		this.validatenumber = validatenumber;
	}


	public String getUserid() {
		return this.userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserpassword() {
		return this.userpassword;
	}

	public void setUserpassword(String userpassword) {
		this.userpassword = userpassword;
	}

	public Integer getUsersex() {
		return this.usersex;
	}

	public void setUsersex(Integer usersex) {
		this.usersex = usersex;
	}

	public Integer getUserage() {
		return this.userage;
	}

	public void setUserage(Integer userage) {
		this.userage = userage;
	}

	public String getUseraddress() {
		return this.useraddress;
	}

	public void setUseraddress(String useraddress) {
		this.useraddress = useraddress;
	}

	public String getNickname() {
		return this.nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getSignature() {
		return this.signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getQqnumber() {
		return this.qqnumber;
	}

	public void setQqnumber(String qqnumber) {
		this.qqnumber = qqnumber;
	}

	public String getWxnumber() {
		return this.wxnumber;
	}

	public void setWxnumber(String wxnumber) {
		this.wxnumber = wxnumber;
	}

	public String getColleage() {
		return this.colleage;
	}

	public void setColleage(String colleage) {
		this.colleage = colleage;
	}

	public String getSchool() {
		return this.school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public String getUsericon() {
		return this.usericon;
	}

	public void setUsericon(String usericon) {
		this.usericon = usericon;
	}

	public String getUserphone() {
		return this.userphone;
	}

	public void setUserphone(String userphone) {
		this.userphone = userphone;
	}

	public String getValidatenumber() {
		return this.validatenumber;
	}

	public void setValidatenumber(String validatenumber) {
		this.validatenumber = validatenumber;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	@Override
	public String toString() {
		return "UserInfo [userid=" + userid + ", username=" + username + ", userpassword=" + userpassword + ", usersex="
				+ usersex + ", userage=" + userage + ", useraddress=" + useraddress + ", nickname=" + nickname
				+ ", signature=" + signature + ", qqnumber=" + qqnumber + ", wxnumber=" + wxnumber + ", colleage="
				+ colleage + ", school=" + school + ", usericon=" + usericon + ", userphone=" + userphone
				+ ", validatenumber=" + validatenumber + ", uuid=" + uuid + "]";
	}


	

}