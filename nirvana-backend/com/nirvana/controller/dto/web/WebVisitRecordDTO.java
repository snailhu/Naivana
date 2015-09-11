package com.nirvana.controller.dto.web;

import java.util.Date;

import com.nirvana.domain.backend.VisitRecord;
import com.nirvana.domain.store.Store;
import com.nirvana.domain.store.StoreStorefrontInfo;
import com.nirvana.pojo4json.BaseDTO;
import com.nirvana.utils.DateUtil;

public class WebVisitRecordDTO extends BaseDTO<VisitRecord> {

	private Long id;
	private Long storeId;
	private String storeName = "";
	private String storeAddr = "";
	private String visitTime = "0";
	private String visitDate = "";

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getStoreId() {
		return storeId;
	}

	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getStoreAddr() {
		return storeAddr;
	}

	public void setStoreAddr(String storeAddr) {
		this.storeAddr = storeAddr;
	}

	public String getVisitTime() {
		return visitTime;
	}

	public void setVisitTime(String visitTime) {
		this.visitTime = visitTime;
	}

	public String getVisitDate() {
		return visitDate;
	}

	public void setVisitDate(String visitDate) {
		this.visitDate = visitDate;
	}

	@Override
	public BaseDTO<VisitRecord> convert(VisitRecord domain) {
		if (domain == null)
			return null;
		WebVisitRecordDTO dto = new WebVisitRecordDTO();
		dto.setId(domain.getId());
		Store store = domain.getStore();
		Date start = domain.getStartTime();
		Date end = domain.getFinishTime();
		if (start != null && end != null) {
			dto.setVisitDate(DateUtil.minus(end, start));
			dto.setVisitTime(DateUtil.dateToString(start) + "~"
					+ DateUtil.dateToString(end, "HH:mm:ss"));
		} else if (start != null) {
			dto.setVisitTime(DateUtil.dateToString(start) + "~");
		}
		if (store != null) {
			dto.setStoreId(store.getId());
			StoreStorefrontInfo info = store.getStorefrontInfo();
			if (info != null) {
				dto.setStoreAddr(info.getDeliveryAddr());
				dto.setStoreName(info.getName());
			}
		}
		return dto;
	}

}
