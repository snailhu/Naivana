package com.nirvana.domain.backend;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * 设备数据。
 * 
 * @deprecated 以后所有的拜访记录数据直接存在MongoDB中。
 * 
 * */
// @Entity
// @Table(name = "backend_secondshelvesinfo")
@Deprecated
public class DeviceInfo {

	/** ID */
	@Id
	@GeneratedValue
	private Long id;

	/** 设备码 */
	@Column(length = 20, nullable = false)
	private String code;

	/** 位置 */
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private PlacedPosition position;

	/** 纯度 */
	@Column(length = 10, nullable = false)
	private String purity;

	/** 是否异常 */
	@Column(nullable = false)
	private Boolean isAbnormal = false;

	/** 异常描述 */
	@Column(length = 30)
	private String note;

	public DeviceInfo() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public PlacedPosition getPosition() {
		return position;
	}

	public void setPosition(PlacedPosition position) {
		this.position = position;
	}

	public String getPurity() {
		return purity;
	}

	public void setPurity(String purity) {
		this.purity = purity;
	}

	public Boolean getIsAbnormal() {
		return isAbnormal;
	}

	public void setIsAbnormal(Boolean isAbnormal) {
		this.isAbnormal = isAbnormal;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

}
