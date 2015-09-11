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
 * ����ҵ��Ա����������������ͳ�����ݡ�
 * 
 * �˱�ʹ�ö�ʱ����ÿ��ҹ���Զ�ͳ�ơ��˱�ֻ����һ���µ����ݡ�
 * 
 * */
@Entity
@Table(name = "statistics_dayagentstatistics")
public class DayAgentStatistics {

	/** ID */
	@Id
	@GeneratedValue
	private Long id;

	/** ��������ʵ�� */
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "area_id")
	private AgentArea area;

	/** ��ʽΪ20150713 */
	private Integer date;

	/** ��Ȼ���� */
	private Integer totalBox;

	/** ���ڶ�����Ȼ���� */
	private Integer inlineBox;

	/** ���ⶩ����Ȼ�� */
	private Integer outlineBox;

	/** Ԥ�ÿͻ��� */
	private Integer preVisitCount;

	/** ʵ�¿ͻ��� */
	private Integer visitCount;

	/** �µ��ͻ��� */
	private Integer orderedCount;

	/** ��SKU�� */
	private Integer sku;

	/** �ܰݷ�ʱ������� */
	private Integer totalTime;

	/** ��ͷ�������� */
	private Integer storeHeadPN;

	/** �������������� */
	private Integer mainShelfPN;

	/** �豸�������� */
	private Integer devicePN;

	/** �ڶ������������� */
	private Integer secondShelfPN;

	/** ��Ʒ�������������� */
	private Integer vividPN;

	public DayAgentStatistics() {
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
