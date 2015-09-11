package com.nirvana.domain.dealer;


public enum DealerType {

	/** 经销商 */
	DEALER,

	/** 直营店 */
	DIRECTSALE_STORE;

	public static DealerType parseDealerType(String type) {
		if (type.equals("DEALER")) {
			return DealerType.DEALER;
		} else if (type.equals("DIRECTSALE_STORE")) {
			return DealerType.DIRECTSALE_STORE;
		} else {
			throw new IllegalArgumentException("IdentiType解析失败。参数不匹配。");
		}
	}

}
