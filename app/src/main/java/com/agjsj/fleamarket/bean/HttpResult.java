package com.agjsj.fleamarket.bean;

public class HttpResult {
	private String code;
	private String message;
	
	public HttpResult() {
	}
	
	public HttpResult(String code, String message) {
		super();
		this.code = code;
		this.message = message;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
