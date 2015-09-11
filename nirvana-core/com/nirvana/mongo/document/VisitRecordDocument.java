package com.nirvana.mongo.document;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

import org.springframework.data.mongodb.core.mapping.Document;

import com.nirvana.domain.backend.DisplayType;
import com.nirvana.domain.backend.PlacedPosition;
import com.nirvana.domain.backend.VisitNodeType;
import com.nirvana.utils.Assert;

/**
 * 存储客户拜访记录。
 * 
 * ID设计为6位日期+4位业务员编号+1位客户标志位+6位客户编号。
 * 
 * 如：2015080503251050000 = 20150805 + 0325 + 1 + 050000<br>
 * 表示这个拜访记录是20150805开始的，业务员的ID为：8325,客户为小店客户（0表示经销商，1表示小店），客户的ID为：050000
 * 
 * */
@Document(collection = "VISIT_RECORD")
public class VisitRecordDocument {
	/** ID */
	private Long id;

	/** 拜访客户的的业务代表 */
	private Integer agentAreaId;

	private VisitNodeType nodeType;

	/** 关联的门店 */
	private Long storeId;

	/** 关联的直营店 */
	private Long dealerId;

	/** 拜访是否完成 */
	private Boolean isFinished;

	/** 是否已下单 */
	private Boolean ordered;

	/** 关联的门店订单编号 */
	private Long storeOrderNo;

	/** 关联的直营店订单编号 */
	private Long dealerOrderNo;

	/** 货架信息 */
	private MainShelfInfoDocument mainShelfInfo;

	/** 设备信息 */
	private Set<DeviceInfoDocument> deviceInfos;

	/** 第二陈列信息 */
	private SecondShelfInfoDocument secondShelfInfo;

	/** 开始时间 */
	private Date startTime;

	/** 开始时间（形式为20100510表示2010年5月10日） */
	private Integer startDate;

	/** 结束时间 */
	private Date finishTime;

	public VisitRecordDocument() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getAgentAreaId() {
		return agentAreaId;
	}

	public void setAgentAreaId(Integer agentAreaId) {
		this.agentAreaId = agentAreaId;
	}

	public VisitNodeType getNodeType() {
		return nodeType;
	}

	public void setNodeType(VisitNodeType nodeType) {
		this.nodeType = nodeType;
	}

	public Long getStoreId() {
		return storeId;
	}

	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}

	public Long getDealerId() {
		return dealerId;
	}

	public void setDealerId(Long dealerId) {
		this.dealerId = dealerId;
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

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Integer getStartDate() {
		return startDate;
	}

	public void setStartDate(Integer startDate) {
		this.startDate = startDate;
	}

	public Date getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(Date finishTime) {
		this.finishTime = finishTime;
	}

	public MainShelfInfoDocument getMainShelfInfo() {
		return mainShelfInfo;
	}

	public void setMainShelfInfo(MainShelfInfoDocument mainShelfInfo) {
		this.mainShelfInfo = mainShelfInfo;
	}

	public Set<DeviceInfoDocument> getDeviceInfos() {
		return deviceInfos;
	}

	public void setDeviceInfos(Set<DeviceInfoDocument> deviceInfos) {
		this.deviceInfos = deviceInfos;
	}

	public SecondShelfInfoDocument getSecondShelfInfo() {
		return secondShelfInfo;
	}

	public void setSecondShelfInfo(SecondShelfInfoDocument secondShelfInfo) {
		this.secondShelfInfo = secondShelfInfo;
	}

	public static long generateId(Date date, long areaId, VisitNodeType type, long storeId) {
		Assert.notNull(date, type);
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		String dateStr = format.format(date);

		long dateLong = Long.parseLong(dateStr);
		long tagLong = 1l;
		if (type == VisitNodeType.DIRECT_STORE) {
			tagLong = 0l;
		}

		long id = dateLong * 10000 * 10000 * 1000 + areaId * 10000 * 1000 + tagLong * 10000 * 100 + storeId;
		return id;
	}

	public static class MainShelfInfoDocument {

		/** 是否为人流方向第一顺位 */
		private Boolean isFirst;

		/** 分销：CSD */
		private Integer distrCSD;

		/** 分销：NCB */
		private Integer distrNCB;

		/** 陈列:CSD */
		private Integer displCSD;

		/** 陈列:NCB */
		private Integer displNCB;

		public Boolean getIsFirst() {
			return isFirst;
		}

		public void setIsFirst(Boolean isFirst) {
			this.isFirst = isFirst;
		}

		public Integer getDistrCSD() {
			return distrCSD;
		}

		public void setDistrCSD(Integer distrCSD) {
			this.distrCSD = distrCSD;
		}

		public Integer getDistrNCB() {
			return distrNCB;
		}

		public void setDistrNCB(Integer distrNCB) {
			this.distrNCB = distrNCB;
		}

		public Integer getDisplCSD() {
			return displCSD;
		}

		public void setDisplCSD(Integer displCSD) {
			this.displCSD = displCSD;
		}

		public Integer getDisplNCB() {
			return displNCB;
		}

		public void setDisplNCB(Integer displNCB) {
			this.displNCB = displNCB;
		}
	}

	public static class DeviceInfoDocument {

		/** 设备码 */
		private String code;

		/** 位置 */
		private PlacedPosition position;

		/** 纯度 */
		private String purity;

		/** 是否异常 */
		private Boolean isAbnormal;

		/** 异常描述 */
		private String note;

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

		public PlacedPosition getPosition() {
			return position;
		}

		public void setPosition(PlacedPosition position) {
			this.position = position;
		}

		public String getPurity() {
			return purity;
		}

		public void setPurity(String purity) {
			this.purity = purity;
		}

		public Boolean getIsAbnormal() {
			return isAbnormal;
		}

		public void setIsAbnormal(Boolean isAbnormal) {
			this.isAbnormal = isAbnormal;
		}

		public String getNote() {
			return note;
		}

		public void setNote(String note) {
			this.note = note;
		}
	}

	public static class SecondShelfInfoDocument {

		/** 位置 */
		private PlacedPosition position;

		/** 陈列方式 */
		private DisplayType type;

		/** 面积 */
		private Integer area;

		public PlacedPosition getPosition() {
			return position;
		}

		public void setPosition(PlacedPosition position) {
			this.position = position;
		}

		public DisplayType getType() {
			return type;
		}

		public void setType(DisplayType type) {
			this.type = type;
		}

		public Integer getArea() {
			return area;
		}

		public void setArea(Integer area) {
			this.area = area;
		}
	}
}
