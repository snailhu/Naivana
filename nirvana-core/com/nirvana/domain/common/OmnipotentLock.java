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
 * 万能乐观锁。 <br>
 * 万能什么的听起来就很中二的样子。<br>
 * 加上乐观就更加中二了。<br>
 * 然而我并不知道什么是中二，我咨询了顾老板，顾老板让我去百度，我忘记我有没有百度了。<br>
 * 总之大概就是很屌的意思啦。<br>
 * 
 * 
 * @author 吃饭特别快的人
 * 
 * */
@Entity
@Table(name = "common_omnipotentlock")
public class OmnipotentLock {

	@Id
	@GeneratedValue
	private Integer id;

	/** 锁的类型。 */
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
