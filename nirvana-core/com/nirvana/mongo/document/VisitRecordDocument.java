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
 * �洢�ͻ��ݷü�¼��
 * 
 * ID���Ϊ6λ����+4λҵ��Ա���+1λ�ͻ���־λ+6λ�ͻ���š�
 * 
 * �磺2015080503251050000 = 20150805 + 0325 + 1 + 050000<br>
 * ��ʾ����ݷü�¼��20150805��ʼ�ģ�ҵ��Ա��IDΪ��8325,�ͻ�ΪС��ͻ���0��ʾ�����̣�1��ʾС�꣩���ͻ���IDΪ��050000
 * 
 * */
@Document(collection = "VISIT_RECORD")
public class VisitRecordDocument {
	/** ID */
	private Long id;

	/** �ݷÿͻ��ĵ�ҵ����� */
	private Integer agentAreaId;

	private VisitNodeType nodeType;

	/** �������ŵ� */
	private Long storeId;

	/** ������ֱӪ�� */
	private Long dealerId;

	/** �ݷ��Ƿ���� */
	private Boolean isFinished;

	/** �Ƿ����µ� */
	private Boolean ordered;

	/** �������ŵ궩����� */
	private Long storeOrderNo;

	/** ������ֱӪ�궩����� */
	private Long dealerOrderNo;

	/** ������Ϣ */
	private MainShelfInfoDocument mainShelfInfo;

	/** �豸��Ϣ */
	private Set<DeviceInfoDocument> deviceInfos;

	/** �ڶ�������Ϣ */
	private SecondShelfInfoDocument secondShelfInfo;

	/** ��ʼʱ�� */
	private Date startTime;

	/** ��ʼʱ�䣨��ʽΪ20100510��ʾ2010��5��10�գ� */
	private Integer startDate;

	/** ����ʱ�� */
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

		/** �Ƿ�Ϊ���������һ˳λ */
		private Boolean isFirst;

		/** ������CSD */
		private Integer distrCSD;

		/** ������NCB */
		private Integer distrNCB;

		/** ����:CSD */
		private Integer displCSD;

		/** ����:NCB */
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

		/** �豸�� */
		private String code;

		/** λ�� */
		private PlacedPosition position;

		/** ���� */
		private String purity;

		/** �Ƿ��쳣 */
		private Boolean isAbnormal;

		/** �쳣���� */
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

		/** λ�� */
		private PlacedPosition position;

		/** ���з�ʽ */
		private DisplayType type;

		/** ��� */
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
