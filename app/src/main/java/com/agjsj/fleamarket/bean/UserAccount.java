package com.agjsj.fleamarket.bean;

/**
 * 用于登录.
 */
public class UserAccount {
	
	private String username;
	
	private String userpassword;

	public UserAccount(String username, String userpassword) {
		this.username = username;
		this.userpassword = userpassword;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserpassword() {
		return userpassword;
	}

	public void setUserpassword(String userpassword) {
		this.userpassword = userpassword;
	}
	
	
	
}
