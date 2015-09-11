package com.nirvana.domain.statistics;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.nirvana.domain.store.Store;

@Entity
@Table(name = "statistics_monthvisitstatistics")
public class MonthVisitStatistics {

	@Id
	@GeneratedValue
	private Long id;

	/** ��ʽΪ198803�����ڡ� */
	private Integer date;

	/** �������ŵ� */
	@ManyToOne
	@JoinColumn(name = "store_id")
	private Store store;

	/** �µ����� */
	private Integer orderedCount;

	/** �ݷ����� */
	private Integer visitCount;

	/** �ݷ�ʱ�䣺�� */
	private Integer visitTime;

	public MonthVisitStatistics() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getDate() {
		return date;
	}

	public void setDate(Integer date) {
		this.date = date;
	}

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	public Integer getOrderedCount() {
		return orderedCount;
	}

	public void setOrderedCount(Integer orderedCount) {
		this.orderedCount = orderedCount;
	}

	public Integer getVisitCount() {
		return visitCount;
	}

	public void setVisitCount(Integer visitCount) {
		this.visitCount = visitCount;
	}

	public Integer getVisitTime() {
		return visitTime;
	}

	public void setVisitTime(Integer visitTime) {
		this.visitTime = visitTime;
	}

}
