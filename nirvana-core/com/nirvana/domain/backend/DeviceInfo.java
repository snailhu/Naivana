package com.nirvana.domain.backend;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * �豸���ݡ�
 * 
 * @deprecated �Ժ����еİݷü�¼����ֱ�Ӵ���MongoDB�С�
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

	/** �豸�� */
	@Column(length = 20, nullable = false)
	private String code;

	/** λ�� */
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private PlacedPosition position;

	/** ���� */
	@Column(length = 10, nullable = false)
	private String purity;

	/** �Ƿ��쳣 */
	@Column(nullable = false)
	private Boolean isAbnormal = false;

	/** �쳣���� */
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
