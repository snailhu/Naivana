package com.nirvana.controller.dto.app;

import java.text.DateFormat;

import com.nirvana.domain.backend.VisitRecord;
import com.nirvana.pojo4json.BaseDTO;
import com.nirvana.utils.DateUtil;

public class VisitRecordDTO extends BaseDTO<VisitRecord> {
	private Long store_id;
	private String store_name;
	private String start_time;
	private String finish_time;
	private int order_sku;
	private int store_sku;
	private int order_products;
	private String use_time;

	@Override
	public BaseDTO<VisitRecord> convert(VisitRecord visitRecord) {
		VisitRecordDTO dto = new VisitRecordDTO();
		if (visitRecord == null)
			return dto;
		if (visitRecord.getStore() != null) {
			dto.setStore_id(visitRecord.getStore().getId());
			if (visitRecord.getStore().getStorefrontInfo() != null)
				dto.setStore_name(visitRecord.getStore().getStorefrontInfo().getName());
		}

		DateFormat dateFormat = DateFormat.getInstance();
		if (visitRecord.getFinishTime() != null)
			dto.setFinish_time(dateFormat.format(visitRecord.getFinishTime()));
		if (visitRecord.getStartTime() != null)
			dto.setStart_time(dateFormat.format(visitRecord.getStartTime()));
		if (visitRecord.getStartTime() != null && visitRecord.getFinishTime() != null) {
			dto.setUse_time(DateUtil.minus(visitRecord.getFinishTime(), visitRecord.getStartTime()));
		}

		// dto.setOrder_products(visitRecord.getOrder().getItems().size());
		return dto;
	}

	public Long getStore_id() {
		return store_id;
	}

	public void setStore_id(Long store_id) {
		this.store_id = store_id;
	}

	public String getStore_name() {
		return store_name;
	}

	public void setStore_name(String store_name) {
		this.store_name = store_name;
	}

	public String getStart_time() {
		return start_time;
	}

	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}

	public String getFinish_time() {
		return finish_time;
	}

	public void setFinish_time(String finish_time) {
		this.finish_time = finish_time;
	}

	public int getOrder_sku() {
		return order_sku;
	}

	public void setOrder_sku(int order_sku) {
		this.order_sku = order_sku;
	}

	public int getStore_sku() {
		return store_sku;
	}

	public void setStore_sku(int store_sku) {
		this.store_sku = store_sku;
	}

	public int getOrder_products() {
		return order_products;
	}

	public void setOrder_products(int order_products) {
		this.order_products = order_products;
	}

	public String getUse_time() {
		return use_time;
	}

	public void setUse_time(String use_time) {
		this.use_time = use_time;
	}

}
