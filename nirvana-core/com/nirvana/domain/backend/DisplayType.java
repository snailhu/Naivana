package com.nirvana.domain.backend;

import com.nirvana.utils.Assert;

/**
 * ���з�ʽ��ö���͡����ڱ�ʾ�ڶ�������Ϣ�еĳ��з�ʽ��
 * 
 * */
public enum DisplayType {

	/** �˼� */
	ONSHELF {
		@Override
		public String getName() {
			return "�˼�";
		}
	},
	/** �ض� */
	ABOVEGROUND {
		@Override
		public String getName() {
			return "�ض�";
		}
	},
	/** ר�û��� */
	SPECIALSHELF {
		@Override
		public String getName() {
			return "ר�û���";
		}
	},
	/** ���� */
	OTHER {
		@Override
		public String getName() {
			return "����";
		}
	};

	public abstract String getName();

	public static DisplayType parseDisplayType(String type) {
		Assert.notNull(type);
		if (type.equals("ONSHELF")) {
			return DisplayType.ONSHELF;
		} else if (type.equals("ABOVEGROUND")) {
			return DisplayType.ABOVEGROUND;
		} else if (type.equals("SPECIALSHELF")) {
			return DisplayType.SPECIALSHELF;
		} else if (type.equals("OTHER")) {
			return DisplayType.OTHER;
		} else {
			throw new IllegalArgumentException("DisplayType����ʧ�ܡ�������ƥ�䡣");
		}
	};

}
