package com.nirvana.domain.backend;

/**
 * ͼƬ���͡�
 * 
 * */
public enum PhotoType {

	/** ��ͷ */
	STOREHEAD {
		@Override
		public String getName() {
			return "��ͷ";
		}
	},
	/** ������ */
	MAINSHELF {
		@Override
		public String getName() {
			return "������";
		}
	},
	/** �ڶ����� */
	SECONDSHELF {
		@Override
		public String getName() {
			return "�ڶ�����";
		}
	},
	/** �豸 */
	DEVICE {
		@Override
		public String getName() {
			return "�豸";
		}
	},
	/** ΧĤ */
	FILM {
		@Override
		public String getName() {
			return "ΧĤ";
		}
	},
	/** ���� */
	POSTER {
		@Override
		public String getName() {
			return "����";
		}
	},
	/** ���� */
	LIGHT {
		@Override
		public String getName() {
			return "����";
		}
	},
	/** ���� */
	OTHER {
		@Override
		public String getName() {
			return "����";
		}
	},
	/** ��Ʒ������������ΧĤ�����������֣������� */
	VIVID {
		@Override
		public String getName() {
			return "������";
		}
	};

	public abstract String getName();

}
