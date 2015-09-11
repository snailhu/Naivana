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

	/** 形式为198803的日期。 */
	private Integer date;

	/** 关联的门店 */
	@ManyToOne
	@JoinColumn(name = "store_id")
	private Store store;

	/** 下单总数 */
	private Integer orderedCount;

	/** 拜访总数 */
	private Integer visitCount;

	/** 拜访时间：秒 */
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
