package com.nirvana.domain.dealer;

/**
 * �Żݷ�ʽ��
 * 
 * */
public enum PromWay {

	/** �ֽ���� */
	MONEYREDUCTION,

	/** �۸��ۿ� */
	PRICEDISCOUNT,

	/** ������Ʒ */
	GIVEAWAY;

	public static PromWay parsePromWay(String way) {
		if (way.equals("MONEYREDUCTION")) {
			return PromWay.MONEYREDUCTION;
		} else if (way.equals("PRICEDISCOUNT")) {
			return PromWay.PRICEDISCOUNT;
		} else if (way.equals("GIVEAWAY")) {
			return PromWay.GIVEAWAY;
		} else {
			throw new IllegalArgumentException("PromWay����ʧ�ܡ�������ƥ�䡣");
		}
	};

}
