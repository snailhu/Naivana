package com.nirvana.domain.backend;

public enum ComplaintType {
	DELIVERY {
		@Override
		public String getName() {
			return "��������";
		}
	},
	SERVICE {
		@Override
		public String getName() {
			return "��������";
		}
	},
	LOGISTICS {
		@Override
		public String getName() {
			return "��������";
		}
	},
	ACTIVITY {
		@Override
		public String getName() {
			return "�����";
		}
	},
	OTHER {
		@Override
		public String getName() {
			return "��������";
		}
	};
	public abstract String getName();

	public static ComplaintType parseComplaintType(String type) {
		for (ComplaintType t : values()) {
			if (t.name().equalsIgnoreCase(type)) {
				return t;
			}
		}
		throw new IllegalArgumentException("ComplaintType����ʧ�ܡ�������ƥ�䡣");
	}
}
