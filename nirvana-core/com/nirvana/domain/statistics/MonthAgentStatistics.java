package com.nirvana.domain.statistics;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.nirvana.domain.backend.area.AgentArea;

/**
 * 关于业务员的生产力销量的月统计数据。
 * 
 * 此表使用定时任务每月一号自动统计。
 * 
 * */
@Entity
@Table(name = "statistics_monthagentstatistics")
public class MonthAgentStatistics {
	/** ID */
	@Id
	@GeneratedValue
	private Long id;

	/** 关联的区实体 */
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "arra_id")
	private AgentArea area;

	/** int表示的月份的日期，例如201409，表示2014年09月 */
	private Integer date;

	/** 累计工作日 */
	private Integer workDay;

	/** 自然箱数 */
	private Integer totalBox;

	/** 线内订单自然箱数 */
	private Integer inlineBox;

	/** 线外订单自然箱 */
	private Integer outlineBox;

	/** 预访客户数 */
	private Integer preVisitCount;

	/** 实仿客户数 */
	private Integer visitCount;

	/** 下单客户数 */
	private Integer orderedCount;

	/** 总SKU数 */
	private Integer sku;

	/** 总拜访时间的秒数 */
	private Integer totalTime;

	/** 门头拍照总数 */
	private Integer storeHeadPN;

	/** 主货架拍照总数 */
	private Integer mainShelfPN;

	/** 设备拍照总数 */
	private Integer devicePN;

	/** 第二陈列拍照总数 */
	private Integer secondShelfPN;

	/** 产品生动化拍照总数 */
	private Integer vividPN;

	public MonthAgentStatistics() {
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

	public Integer getDate() {
		return date;
	}

	public void setDate(Integer date) {
		this.date = date;
	}

	public Integer getTotalBox() {
		return totalBox;
	}

	public void setTotalBox(Integer totalBox) {
		this.totalBox = totalBox;
	}

	public Integer getInlineBox() {
		return inlineBox;
	}

	public void setInlineBox(Integer inlineBox) {
		this.inlineBox = inlineBox;
	}

	public Integer getOutlineBox() {
		return outlineBox;
	}

	public void setOutlineBox(Integer outlineBox) {
		this.outlineBox = outlineBox;
	}

	public Integer getWorkDay() {
		return workDay;
	}

	public void setWorkDay(Integer workDay) {
		this.workDay = workDay;
	}

	public Integer getPreVisitCount() {
		return preVisitCount;
	}

	public void setPreVisitCount(Integer preVisitCount) {
		this.preVisitCount = preVisitCount;
	}

	public Integer getVisitCount() {
		return visitCount;
	}

	public void setVisitCount(Integer visitCount) {
		this.visitCount = visitCount;
	}

	public Integer getOrderedCount() {
		return orderedCount;
	}

	public void setOrderedCount(Integer orderedCount) {
		this.orderedCount = orderedCount;
	}

	public Integer getSku() {
		return sku;
	}

	public void setSku(Integer sku) {
		this.sku = sku;
	}

	public Integer getTotalTime() {
		return totalTime;
	}

	public void setTotalTime(Integer totalTime) {
		this.totalTime = totalTime;
	}

	public Integer getStoreHeadPN() {
		return storeHeadPN;
	}

	public void setStoreHeadPN(Integer storeHeadPN) {
		this.storeHeadPN = storeHeadPN;
	}

	public Integer getMainShelfPN() {
		return mainShelfPN;
	}

	public void setMainShelfPN(Integer mainShelfPN) {
		this.mainShelfPN = mainShelfPN;
	}

	public Integer getDevicePN() {
		return devicePN;
	}

	public void setDevicePN(Integer devicePN) {
		this.devicePN = devicePN;
	}

	public Integer getSecondShelfPN() {
		return secondShelfPN;
	}

	public void setSecondShelfPN(Integer secondShelfPN) {
		this.secondShelfPN = secondShelfPN;
	}

	public Integer getVividPN() {
		return vividPN;
	}

	public void setVividPN(Integer vividPN) {
		this.vividPN = vividPN;
	}

}
