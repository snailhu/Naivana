package com.nirvana.domain.backend;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * ��������Ϣʵ�塣ÿ�ΰݷÿͻ���ȡ�õĻ�����Ϣ��
 * 
 * @deprecated �Ժ����еİݷü�¼����ֱ�Ӵ���MongoDB�С�
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

	/** �Ƿ�Ϊ���������һ˳λ */
	@Column(nullable = false)
	private Boolean isFirst;

	/** ������CSD */
	@Column(nullable = false)
	private Integer distrCSD;

	/** ������NCB */
	@Column(nullable = false)
	private Integer distrNCB;

	/** ����:CSD */
	@Column(nullable = false)
	private Integer displCSD;

	/** ����:NCB */
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
