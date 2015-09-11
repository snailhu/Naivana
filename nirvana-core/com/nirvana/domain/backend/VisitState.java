package com.nirvana.domain.backend;

/**
 * �ݷü�¼��ö���͡����ڱ�ʾ�ݷü�¼�ĸ����׶Ρ�
 * 
 * */
public enum VisitState {

	/** ��ʼ */
	START {
		@Override
		public String getName() {
			return "��ʼ";
		}
	},
	/** ��ͷ��Ƭ�ѻ�ȡ */
	HEADPHOTOGOTTEN {
		@Override
		public String getName() {
			return "��ͷ��Ƭ�ѻ�ȡ";
		}
	},
	/** ��������Ϣ�ѻ�ȡ */
	MAINSHELFINFOGOTTEN {
		@Override
		public String getName() {
			return "��������Ϣ�ѻ�ȡ";
		}
	},
	/** �豸��Ϣ�ѻ�ȡ */
	DEVICEINFOGOTTEN {
		@Override
		public String getName() {
			return "�豸��Ϣ�ѻ�ȡ";
		}
	},
	/** �ڶ�������Ϣ�ѻ�ȡ */
	SECONDSHELFINFOGOTTEN {
		@Override
		public String getName() {
			return "�ڶ�������Ϣ�ѻ�ȡ";
		}
	},
	/** ��Ʒ��������Ϣ�ѻ�ȡ */
	PRODUCTVIVIDPICGOTTEN {
		@Override
		public String getName() {
			return "��Ʒ��������Ϣ�ѻ�ȡ";
		}
	},
	/** ���µ� */
	ORDERED {
		@Override
		public String getName() {
			return "���µ�";
		}
	},
	/** �ݷ���� */
	FINISHED {
		@Override
		public String getName() {
			return "�ݷ����";
		}
	};

	public abstract String getName();

	public static VisitState parseVisitState(VisitState state) {
		if (state.equals("START")) {
			return VisitState.START;
		} else if (state.equals("HEADPHOTOGOTTEN")) {
			return VisitState.HEADPHOTOGOTTEN;
		} else if (state.equals("MAINSHELFINFOGOTTEN")) {
			return VisitState.MAINSHELFINFOGOTTEN;
		} else if (state.equals("DEVICEINFOGOTTEN")) {
			return VisitState.DEVICEINFOGOTTEN;
		} else if (state.equals("SECONDSHELFINFOGOTTEN")) {
			return VisitState.SECONDSHELFINFOGOTTEN;
		} else if (state.equals("PRODUCTVIVIDPICGOTTEN")) {
			return VisitState.PRODUCTVIVIDPICGOTTEN;
		} else if (state.equals("ORDERED")) {
			return VisitState.ORDERED;
		} else if (state.equals("FINISHED")) {
			return VisitState.FINISHED;
		} else {
			throw new IllegalArgumentException("VisitState����ʧ�ܡ�������ƥ�䡣");
		}
	}

}
