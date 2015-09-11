package com.nirvana.domain.common;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * 地址表，表示现实中的地址。
 * 
 * */
@Entity
@Table(name = "common_address")
public class Address {
	/** 地址ID */
	@Id
	@GeneratedValue
	private Integer id;

	/** 地址名称内容 */
	@Column(length = 20)
	private String content;

	/** 子地址 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "father_id")
	private Set<Address> childs;

	/** 父地址 */
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "father_id")
	private Address parent;

	public Address() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Set<Address> getChilds() {
		return childs;
	}

	public void setChilds(Set<Address> childs) {
		this.childs = childs;
	}

	public Address getParent() {
		return parent;
	}

	public void setParent(Address parent) {
		this.parent = parent;
	}

}
