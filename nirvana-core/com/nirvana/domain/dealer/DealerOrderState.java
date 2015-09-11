package com.nirvana.domain.dealer;

import org.springframework.util.Assert;

public enum DealerOrderState {

	/** �Ѽƻ� */
	PLANNED,

	/** ���´� */
	GIVEN,

	/** ��Ԥ���� */
	RESERVED,

	/** ��� */
	DELIVERING,

	/** ���ֽ��� */
	PART_DELIVERED,

	/** �ѽ��� */
	DELIVERED,

	/** �ѿ��߷�Ʊ�ѹرա� */
	CLOSED,

	/** ��ȡ���� */
	CANCELED;

	public static DealerOrderState fromERP(String state) {
		if (state.equals("�Ѽƻ�")) {
			return DealerOrderState.PLANNED;
		} else if (state.equals("���´�")) {
			return DealerOrderState.GIVEN;
		} else if (state.equals("��Ԥ����")) {
			return DealerOrderState.RESERVED;
		} else if (state.equals("���")) {
			return DealerOrderState.DELIVERING;
		} else if (state.equals("���ֽ���")) {
			return DealerOrderState.PART_DELIVERED;
		} else if (state.equals("�ѽ���")) {
			return DealerOrderState.DELIVERED;
		} else if (state.equals("�ѿ��߷�Ʊ/�ѹر�")) {
			return DealerOrderState.CLOSED;
		} else if (state.equals("��ȡ��")) {
			return DealerOrderState.CANCELED;
		} else {
			throw new IllegalArgumentException("DealerOrderState��������");
		}

	}

	public static DealerOrderState parseDealerOrderState(String state) {
		Assert.hasLength(state, "��������Ϊ�ա�");
		if (state.equals(PLANNED.name())) {
			return DealerOrderState.PLANNED;
		} else if (state.equals(GIVEN.name())) {
			return DealerOrderState.GIVEN;
		} else if (state.equals(RESERVED.name())) {
			return DealerOrderState.RESERVED;
		} else if (state.equals(DELIVERING.name())) {
			return DealerOrderState.DELIVERING;
		} else if (state.equals(PART_DELIVERED.name())) {
			return DealerOrderState.PART_DELIVERED;
		} else if (state.equals(DELIVERED.name())) {
			return DealerOrderState.DELIVERED;
		} else if (state.equals(CLOSED.name())) {
			return DealerOrderState.CLOSED;
		} else if (state.equals(CANCELED.name())) {
			return DealerOrderState.CANCELED;
		} else {
			throw new IllegalArgumentException("DealerOrderState��������");
		}

	}

}
