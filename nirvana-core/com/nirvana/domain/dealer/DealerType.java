package com.nirvana.domain.dealer;


public enum DealerType {

	/** ������ */
	DEALER,

	/** ֱӪ�� */
	DIRECTSALE_STORE;

	public static DealerType parseDealerType(String type) {
		if (type.equals("DEALER")) {
			return DealerType.DEALER;
		} else if (type.equals("DIRECTSALE_STORE")) {
			return DealerType.DIRECTSALE_STORE;
		} else {
			throw new IllegalArgumentException("IdentiType����ʧ�ܡ�������ƥ�䡣");
		}
	}

}
