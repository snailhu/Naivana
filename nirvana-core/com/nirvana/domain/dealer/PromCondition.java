package com.nirvana.domain.dealer;

/**
 * 优惠条件。
 * */
public enum PromCondition {

	/** 满箱 */
	ACHIEVECASE,

	/** 满金额 */
	ACHIEVEMONEY;

	public static PromCondition parsePromCondition(String condition) {
		if (condition.equals("ACHIEVECASE")) {
			return PromCondition.ACHIEVECASE;
		} else if (condition.equals("ACHIEVEMONEY")) {
			return PromCondition.ACHIEVEMONEY;
		} else {
			throw new IllegalArgumentException("PromCondition解析失败。参数不匹配。");
		}
	};
}
