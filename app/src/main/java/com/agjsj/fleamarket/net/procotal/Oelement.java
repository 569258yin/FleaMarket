package com.agjsj.fleamarket.net.procotal;

import java.io.Serializable;

public class Oelement implements Serializable{
	private int errorcode;
	private String errormsg;
	public Oelement() {
	}
	public int getErrorcode() {
		return errorcode;
	}
	public void setErrorcode(int errorcode) {
		this.errorcode = errorcode;
	}
	public String getErrormsg() {
		return errormsg;
	}
	public void setErrormsg(String errormsg) {
		this.errormsg = errormsg;
	}
	@Override
	public String toString() {
		return "Oelement [errorcode=" + errorcode + ", errormsg=" + errormsg + "]";
	}
	
	
}
