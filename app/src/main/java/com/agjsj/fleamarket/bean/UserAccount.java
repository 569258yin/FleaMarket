package com.agjsj.fleamarket.bean;

/**
 * 用于登录.
 */
public class UserAccount {
	
	private String useraccount;
	private String userpassword;

	private String phoneNum;

	private String code;

	public UserAccount() {
	}

	public UserAccount(String username, String userpassword) {
		this.useraccount = username;
		this.userpassword = userpassword;
	}

	public String getUseraccount() {
		return useraccount;
	}

	public void setUseraccount(String useraccount) {
		this.useraccount = useraccount;
	}

	public String getUserpassword() {
		return userpassword;
	}

	public void setUserpassword(String userpassword) {
		this.userpassword = userpassword;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
