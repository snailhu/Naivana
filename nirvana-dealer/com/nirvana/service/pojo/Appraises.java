package com.nirvana.service.pojo;

public class Appraises {

	// 商品描述相符
	private Integer product;

	// 物流
	private Integer delivery;

	// 服务态度
	private Integer manner;

	// 留言
	private String note;

	public Appraises() {
	}

	public Integer getProduct() {
		return product;
	}

	public void setProduct(Integer product) {
		this.product = product;
	}

	public Integer getDelivery() {
		return delivery;
	}

	public void setDelivery(Integer delivery) {
		this.delivery = delivery;
	}

	public Integer getManner() {
		return manner;
	}

	public void setManner(Integer manner) {
		this.manner = manner;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

}
