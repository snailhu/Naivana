package com.nirvana.domain.backend;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.nirvana.domain.backend.area.AgentArea;
import com.nirvana.domain.dealer.Dealer;

/**
 * ֱӪ�����ʵ�塣
 * 
 * */
@Entity
@Table(name = "backend_agentdealercategory")
public class AgentDealerCategory {

	public static final String UNDEFINED = "δ����";

	/** ID */
	@Id
	@GeneratedValue
	private Long id;

	/** ������CR�� */
	@ManyToOne(optional = false)
	@JoinColumn(name = "area_id")
	private AgentArea area;

	/** ������ */
	@Column(length = 30, nullable = false)
	private String categoryName;

	/** �����а�����ֱӪ�� */
	@OneToMany
	@JoinColumn(name = "category_id")
	private Set<Dealer> dealers;

	public AgentDealerCategory() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public AgentArea getArea() {
		return area;
	}

	public void setArea(AgentArea area) {
		this.area = area;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Set<Dealer> getDealers() {
		return dealers;
	}

	public void setDealers(Set<Dealer> dealers) {
		this.dealers = dealers;
	}

}
