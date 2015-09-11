package com.nirvana.domain.dealer;

import org.springframework.util.Assert;

public enum DealerOrderState {

	/** 已计划 */
	PLANNED,

	/** 已下达 */
	GIVEN,

	/** 已预留的 */
	RESERVED,

	/** 提货 */
	DELIVERING,

	/** 部分交货 */
	PART_DELIVERED,

	/** 已交货 */
	DELIVERED,

	/** 已开具发票已关闭。 */
	CLOSED,

	/** 已取消。 */
	CANCELED;

	public static DealerOrderState fromERP(String state) {
		if (state.equals("已计划")) {
			return DealerOrderState.PLANNED;
		} else if (state.equals("已下达")) {
			return DealerOrderState.GIVEN;
		} else if (state.equals("已预留的")) {
			return DealerOrderState.RESERVED;
		} else if (state.equals("提货")) {
			return DealerOrderState.DELIVERING;
		} else if (state.equals("部分交货")) {
			return DealerOrderState.PART_DELIVERED;
		} else if (state.equals("已交货")) {
			return DealerOrderState.DELIVERED;
		} else if (state.equals("已开具发票/已关闭")) {
			return DealerOrderState.CLOSED;
		} else if (state.equals("已取消")) {
			return DealerOrderState.CANCELED;
		} else {
			throw new IllegalArgumentException("DealerOrderState解析错误。");
		}

	}

	public static DealerOrderState parseDealerOrderState(String state) {
		Assert.hasLength(state, "参数不能为空。");
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
			throw new IllegalArgumentException("DealerOrderState解析错误。");
		}

	}

}
