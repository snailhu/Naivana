package com.nirvana.domain.backend;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * �ڶ�������Ϣʵ�塣
 * 
 * @deprecated �Ժ����еİݷü�¼����ֱ�Ӵ���MongoDB�С�
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

	/** λ�� */
	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 20)
	private PlacedPosition position;

	/** ���з�ʽ */
	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 20)
	private DisplayType type;

	/** ��� */
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
