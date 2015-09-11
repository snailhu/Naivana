package com.nirvana.domain.backend;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 百事促销活动图片。
 * 
 * */
@Entity
@Table(name = "backend_pepsipromotionpic")
public class PepsiPromotionPic {

	/** ID */
	@Id
	@GeneratedValue
	private Integer id;

	/** 图片URL */
	@Column(length = 200, nullable = false)
	private String url;

	public PepsiPromotionPic() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
