package com.nirvana.domain.dealer;

public enum DealerOrderItemType {

	/** ���۶��� */
	NO,

	/** �ۿ۶��� */
	DO;

	public static DealerOrderItemType parseDealerOrderItemType(String type) {

		if (type.equals("NO")) {
			return NO;
		} else if (type.equals("DO")) {
			return DO;
		} else {
			throw new IllegalArgumentException("parseDealerOrderItemType������������");
		}
	}

}
