package com.nirvana.domain.statistics;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.nirvana.domain.store.Store;

@Entity
@Table(name = "statistics_dayvisitstatistics")
public class DayVisitStatistics {

	@Id
	@GeneratedValue
	private Long id;

	/** �������ŵ� */
	@ManyToOne
	@JoinColumn(name = "store_id")
	private Store store;

	/** �Ƿ��µ� */
	private Boolean isOrdered;

	/** �ݷ�ʱ�䣺�� */
	private Integer time;

	public DayVisitStatistics() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	public Boolean getIsOrdered() {
		return isOrdered;
	}

	public void setIsOrdered(Boolean isOrdered) {
		this.isOrdered = isOrdered;
	}

	public Integer getTime() {
		return time;
	}

	public void setTime(Integer time) {
		this.time = time;
	}

}
