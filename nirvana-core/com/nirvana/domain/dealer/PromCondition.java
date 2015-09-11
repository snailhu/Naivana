package com.nirvana.domain.dealer;

/**
 * �Ż�������
 * */
public enum PromCondition {

	/** ���� */
	ACHIEVECASE,

	/** ����� */
	ACHIEVEMONEY;

	public static PromCondition parsePromCondition(String condition) {
		if (condition.equals("ACHIEVECASE")) {
			return PromCondition.ACHIEVECASE;
		} else if (condition.equals("ACHIEVEMONEY")) {
			return PromCondition.ACHIEVEMONEY;
		} else {
			throw new IllegalArgumentException("PromCondition����ʧ�ܡ�������ƥ�䡣");
		}
	};
}
