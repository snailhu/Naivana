package com.nirvana.domain.backend;

import org.springframework.util.Assert;

public enum PromotionState {

	/** 待审核 */
	WAITVERIFY,

	/** 被打回 */
	REJECTED,

	/** 已通过 */
	ADOPTED;

	public static PromotionState parsePromotionState(String state) {
		Assert.notNull(state, "参数不能为空。");
		if (state.equals(WAITVERIFY.name())) {
			return WAITVERIFY;
		} else if (state.equals(REJECTED.name())) {
			return REJECTED;
		} else if (state.equals(ADOPTED.name())) {
			return ADOPTED;
		} else {
			throw new IllegalArgumentException("state参数解析错误。");
		}
	}
}
