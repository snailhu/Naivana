package com.nirvana.domain.backend;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * 主货架信息实体。每次拜访客户，取得的货架信息。
 * 
 * @deprecated 以后所有的拜访记录数据直接存在MongoDB中。
 * 
 * */
// @Entity
// @Table(name = "backend_secondshelvesinfo")
@Deprecated
public class MainShelfInfo {

	/** ID */
	@Id
	@GeneratedValue
	private Long id;

	/** 是否为人流方向第一顺位 */
	@Column(nullable = false)
	private Boolean isFirst;

	/** 分销：CSD */
	@Column(nullable = false)
	private Integer distrCSD;

	/** 分销：NCB */
	@Column(nullable = false)
	private Integer distrNCB;

	/** 陈列:CSD */
	@Column(nullable = false)
	private Integer displCSD;

	/** 陈列:NCB */
	@Column(nullable = false)
	private Integer displNCB;

	public MainShelfInfo() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getIsFirst() {
		return isFirst;
	}

	public void setIsFirst(Boolean isFirst) {
		this.isFirst = isFirst;
	}

	public Integer getDistrCSD() {
		return distrCSD;
	}

	public void setDistrCSD(Integer distrCSD) {
		this.distrCSD = distrCSD;
	}

	public Integer getDistrNCB() {
		return distrNCB;
	}

	public void setDistrNCB(Integer distrNCB) {
		this.distrNCB = distrNCB;
	}

	public Integer getDisplCSD() {
		return displCSD;
	}

	public void setDisplCSD(Integer displCSD) {
		this.displCSD = displCSD;
	}

	public Integer getDisplNCB() {
		return displNCB;
	}

	public void setDisplNCB(Integer displNCB) {
		this.displNCB = displNCB;
	}

}
