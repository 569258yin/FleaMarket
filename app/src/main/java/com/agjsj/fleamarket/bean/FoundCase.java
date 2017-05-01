package com.agjsj.fleamarket.bean;

import java.util.Date;

public class FoundCase {
    private String fdcid;

    private String userid;
    
    private UserInfo userInfo;
    
    private Integer fdctype;

    private String fdctitle;

    private Date fdctime;

    private String fdccontext;

    private String fdcimage;


    public String getFdcid() {
        return fdcid;
    }

    public void setFdcid(String fdcid) {
        this.fdcid = fdcid == null ? null : fdcid.trim();
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }

    public String getFdctitle() {
        return fdctitle;
    }

    public void setFdctitle(String fdctitle) {
        this.fdctitle = fdctitle == null ? null : fdctitle.trim();
    }

    public Date getFdctime() {
        return fdctime;
    }

    public void setFdctime(Date fdctime) {
        this.fdctime = fdctime;
    }

    public String getFdccontext() {
        return fdccontext;
    }

    public void setFdccontext(String fdccontext) {
        this.fdccontext = fdccontext == null ? null : fdccontext.trim();
    }

	public String getFdcimage() {
		return fdcimage;
	}

	public void setFdcimage(String fdcimage) {
		this.fdcimage = fdcimage;
	}

	public Integer getFdctype() {
		return fdctype;
	}

	public void setFdctype(Integer fdctype) {
		this.fdctype = fdctype;
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}
	
	

   
}