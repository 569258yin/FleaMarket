package com.agjsj.fleamarket.bean;

/**
 * Goodstype entity. @author MyEclipse Persistence Tools
 */

public class Goodstype implements java.io.Serializable {

	// Fields

	private Integer goodstypeid;
	private String goodstypename;

	// Constructors

	/** default constructor */
	public Goodstype() {
	}

	/** full constructor */
	public Goodstype(String goodstypename) {
		this.goodstypename = goodstypename;
	}

	// Property accessors

	public Integer getGoodstypeid() {
		return this.goodstypeid;
	}

	public void setGoodstypeid(Integer goodstypeid) {
		this.goodstypeid = goodstypeid;
	}

	public String getGoodstypename() {
		return this.goodstypename;
	}

	public void setGoodstypename(String goodstypename) {
		this.goodstypename = goodstypename;
	}

}