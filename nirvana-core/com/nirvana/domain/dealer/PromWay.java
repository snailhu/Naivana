package com.nirvana.domain.dealer;

/**
 * 优惠方式。
 * 
 * */
public enum PromWay {

	/** 现金减免 */
	MONEYREDUCTION,

	/** 价格折扣 */
	PRICEDISCOUNT,

	/** 享受赠品 */
	GIVEAWAY;

	public static PromWay parsePromWay(String way) {
		if (way.equals("MONEYREDUCTION")) {
			return PromWay.MONEYREDUCTION;
		} else if (way.equals("PRICEDISCOUNT")) {
			return PromWay.PRICEDISCOUNT;
		} else if (way.equals("GIVEAWAY")) {
			return PromWay.GIVEAWAY;
		} else {
			throw new IllegalArgumentException("PromWay解析失败。参数不匹配。");
		}
	};

}
