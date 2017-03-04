package com.agjsj.fleamarket.bean;

import java.util.Set;

/**
 * Goodscollect entity. @author MyEclipse Persistence Tools
 */

public class Goodscollect implements java.io.Serializable {

	// Fields

	private Integer gcid;
	private Integer goodsid;
	private Integer userid;
	private Set<Goods> goods;
	

	// Constructors

	/** default constructor */
	public Goodscollect() {
	}

	/** full constructor */
	public Goodscollect(Integer goodsid, Integer userid) {
		this.goodsid = goodsid;
		this.userid = userid;
	}

	// Property accessors

	public Integer getGcid() {
		return this.gcid;
	}

	public void setGcid(Integer gcid) {
		this.gcid = gcid;
	}

	public Integer getGoodsid() {
		return this.goodsid;
	}

	public void setGoodsid(Integer goodsid) {
		this.goodsid = goodsid;
	}

	public Integer getUserid() {
		return this.userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public Set<Goods> getGoods() {
		return goods;
	}

	public void setGoods(Set<Goods> goods) {
		this.goods = goods;
	}
	

}