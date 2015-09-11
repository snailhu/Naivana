package com.nirvana.domain.common;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

/**
 * �����ֹ����� <br>
 * ����ʲô���������ͺ��ж������ӡ�<br>
 * �����ֹ۾͸����ж��ˡ�<br>
 * Ȼ���Ҳ���֪��ʲô���ж�������ѯ�˹��ϰ壬���ϰ�����ȥ�ٶȣ�����������û�аٶ��ˡ�<br>
 * ��֮��ž��Ǻ܌ŵ���˼����<br>
 * 
 * 
 * @author �Է��ر�����
 * 
 * */
@Entity
@Table(name = "common_omnipotentlock")
public class OmnipotentLock {

	@Id
	@GeneratedValue
	private Integer id;

	/** �������͡� */
	@Enumerated(EnumType.STRING)
	@Column(length = 20)
	private LockType type;

	@Version
	private Integer version;

	public OmnipotentLock() {
	}

	public OmnipotentLock(LockType type) {
		this.type = type;
	}

	public Integer getId() {
		return id;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

}
