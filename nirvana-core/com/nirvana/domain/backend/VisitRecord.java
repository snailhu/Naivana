package com.nirvana.domain.backend;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.nirvana.domain.backend.area.AgentArea;
import com.nirvana.domain.dealer.Dealer;
import com.nirvana.domain.store.Store;

/**
 * 拜访记录实体。
 * 
 * @deprecated 以后所有的拜访记录数据直接存在MongoDB中。
 * 
 * */
// @Entity
// @Table(name = "backend_secondshelvesinfo")
@Deprecated
public class VisitRecord {

	/** ID */
	@Id
	@GeneratedValue
	private Long id;

	/** 拜访客户的的业务代表 */
	@ManyToOne(optional = false)
	@JoinColumn(name = "area_id")
	private AgentArea area;

	@Enumerated(EnumType.STRING)
	@Column(length = 20)
	private VisitNodeType nodeType;

	/** 关联的门店 */
	@ManyToOne
	@JoinColumn(name = "store_id")
	private Store store;

	/** 关联的直营店 */
	@ManyToOne
	@JoinColumn(name = "dealer_id")
	private Dealer dealer;

	/** 拜访是否完成 */
	@Enumerated(EnumType.STRING)
	private Boolean isFinished = false;

	/** 是否已下单 */
	private Boolean ordered;

	/** 关联的门店订单编号 */
	private Long storeOrderNo;

	/** 关联的直营店订单编号 */
	private Long dealerOrderNo;

	/** 货架信息 */
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "mainshelfinfo_id")
	private MainShelfInfo mainShelfInfo;

	/** 设备信息 */
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "deviceinfo_id")
	private Set<DeviceInfo> deviceInfos;

	/** 第二陈列信息 */
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "secondshelfinfo_id")
	private SecondShelfInfo secondShelfInfo;

	/** 开始时间 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date startTime;

	/** 开始时间（形式为20100510表示2010年5月10日） */
	private Integer startDate;

	/** 结束时间 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date finishTime;

	/** 关联的照片 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "record_id")
	private Set<VisitPhoto> photos;

	public VisitRecord() {
	}

	public Integer getStartDate() {
		return startDate;
	}

	public void setStartDate(Integer startDate) {
		this.startDate = startDate;
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

	public Dealer getDealer() {
		return dealer;
	}

	public void setDealer(Dealer dealer) {
		this.dealer = dealer;
	}

	public Long getStoreOrderNo() {
		return storeOrderNo;
	}

	public void setStoreOrderNo(Long storeOrderNo) {
		this.storeOrderNo = storeOrderNo;
	}

	public Long getDealerOrderNo() {
		return dealerOrderNo;
	}

	public void setDealerOrderNo(Long dealerOrderNo) {
		this.dealerOrderNo = dealerOrderNo;
	}

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	public Boolean getIsFinished() {
		return isFinished;
	}

	public void setIsFinished(Boolean isFinished) {
		this.isFinished = isFinished;
	}

	public Boolean getOrdered() {
		return ordered;
	}

	public void setOrdered(Boolean ordered) {
		this.ordered = ordered;
	}

	public MainShelfInfo getMainShelfInfo() {
		return mainShelfInfo;
	}

	public void setMainShelfInfo(MainShelfInfo mainShelfInfo) {
		this.mainShelfInfo = mainShelfInfo;
	}

	public SecondShelfInfo getSecondShelfInfo() {
		return secondShelfInfo;
	}

	public void setSecondShelfInfo(SecondShelfInfo secondShelfInfo) {
		this.secondShelfInfo = secondShelfInfo;
	}

	public Set<DeviceInfo> getDeviceInfos() {
		return deviceInfos;
	}

	public void setDeviceInfos(Set<DeviceInfo> deviceInfos) {
		this.deviceInfos = deviceInfos;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(Date finishTime) {
		this.finishTime = finishTime;
	}

	public Set<VisitPhoto> getPhotos() {
		return photos;
	}

	public void setPhotos(Set<VisitPhoto> photos) {
		this.photos = photos;
	}

	public VisitNodeType getNodeType() {
		return nodeType;
	}

	public void setNodeType(VisitNodeType nodeType) {
		this.nodeType = nodeType;
	}

}
