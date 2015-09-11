package com.nirvana.domain.backend;

import org.springframework.util.Assert;

public enum PromotionState {

	/** ����� */
	WAITVERIFY,

	/** ����� */
	REJECTED,

	/** ��ͨ�� */
	ADOPTED;

	public static PromotionState parsePromotionState(String state) {
		Assert.notNull(state, "��������Ϊ�ա�");
		if (state.equals(WAITVERIFY.name())) {
			return WAITVERIFY;
		} else if (state.equals(REJECTED.name())) {
			return REJECTED;
		} else if (state.equals(ADOPTED.name())) {
			return ADOPTED;
		} else {
			throw new IllegalArgumentException("state������������");
		}
	}
}
