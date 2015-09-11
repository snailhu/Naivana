package com.nirvana.domain.backend;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * 第二陈列信息实体。
 * 
 * @deprecated 以后所有的拜访记录数据直接存在MongoDB中。
 * 
 * */
// @Entity
// @Table(name = "backend_secondshelvesinfo")
@Deprecated
public class SecondShelfInfo {

	/** ID */
	@Id
	@GeneratedValue
	private Long id;

	/** 位置 */
	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 20)
	private PlacedPosition position;

	/** 陈列方式 */
	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 20)
	private DisplayType type;

	/** 面积 */
	@Column(nullable = false)
	private Integer area;

	public SecondShelfInfo() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public PlacedPosition getPosition() {
		return position;
	}

	public void setPosition(PlacedPosition position) {
		this.position = position;
	}

	public DisplayType getType() {
		return type;
	}

	public void setType(DisplayType type) {
		this.type = type;
	}

	public Integer getArea() {
		return area;
	}

	public void setArea(Integer area) {
		this.area = area;
	}

}
