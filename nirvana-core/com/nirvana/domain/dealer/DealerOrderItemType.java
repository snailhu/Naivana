package com.nirvana.domain.dealer;

public enum DealerOrderItemType {

	/** 销售订单 */
	NO,

	/** 折扣订单 */
	DO;

	public static DealerOrderItemType parseDealerOrderItemType(String type) {

		if (type.equals("NO")) {
			return NO;
		} else if (type.equals("DO")) {
			return DO;
		} else {
			throw new IllegalArgumentException("parseDealerOrderItemType参数解析错误。");
		}
	}

}
