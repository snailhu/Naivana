package com.nirvana.domain.backend;

import com.nirvana.domain.backend.usersys.ERole;

public enum PositionType {

	/** ҵ��Ա */
	AGENT {
		@Override
		public int getLevel() {
			return 0;
		}

		@Override
		public ERole getRole() {
			return ERole.AGENT;
		}

		@Override
		public String getName() {
			return "ҵ��Ա";
		}
	},

	/** ���������� */
	TDS {
		@Override
		public int getLevel() {
			return 1;
		}

		@Override
		public ERole getRole() {
			return ERole.TDS;
		}

		@Override
		public String getName() {
			return "����TDS";
		}
	},

	/** ��Ա */
	CLERK {
		@Override
		public int getLevel() {
			return 2;
		}

		@Override
		public ERole getRole() {
			return ERole.CLERK;
		}

		@Override
		public String getName() {
			return "��Ա";
		}
	},

	/** ���������� */
	TDM {
		@Override
		public int getLevel() {
			return 3;
		}

		@Override
		public ERole getRole() {
			return ERole.TDM;
		}

		@Override
		public String getName() {
			return "����TDM";
		}
	},

	/** �������� */
	UM {
		@Override
		public int getLevel() {
			return 4;
		}

		@Override
		public ERole getRole() {
			return ERole.UM;
		}

		@Override
		public String getName() {
			return "��������UM";
		}
	},

	/** ����ϵͳ���� */
	FSIS {

		@Override
		public int getLevel() {
			return 5;
		}

		@Override
		public ERole getRole() {
			return ERole.USER;
		}

		@Override
		public String getName() {
			return "����ϵͳ����FSIS";
		}
	},

	/** ϵͳά��Ա */
	SIS {
		@Override
		public int getLevel() {
			return 6;
		}

		@Override
		public ERole getRole() {
			return ERole.SIS;
		}

		@Override
		public String getName() {
			return "ϵͳά��ԱSIS";
		}
	},

	/** ��Ѷ�� */
	INFO_MINISTRY {
		@Override
		public int getLevel() {
			return 7;
		}

		@Override
		public ERole getRole() {
			return ERole.ADMIN;
		}

		@Override
		public String getName() {
			return "��Ѷ��";
		}
	},

	/** �������� */
	CDM {
		@Override
		public int getLevel() {
			return 8;
		}

		@Override
		public ERole getRole() {
			return ERole.CDM;
		}

		@Override
		public String getName() {
			return "��������CDM";
		}
	},

	/** ������������ */
	SISM {
		@Override
		public int getLevel() {
			return 9;
		}

		@Override
		public ERole getRole() {
			return ERole.SISM;
		}

		@Override
		public String getName() {
			return "������������SISM";
		}
	},

	/** �г���չ�ܼ� */
	MDM {
		@Override
		public int getLevel() {
			return 10;
		}

		@Override
		public ERole getRole() {
			return ERole.MDM;
		}

		@Override
		public String getName() {
			return "�г���չ�ܼ�MDM";
		}
	},

	/** �ܾ��� */
	GM {
		@Override
		public int getLevel() {
			return 11;
		}

		@Override
		public ERole getRole() {
			return ERole.GM;
		}

		@Override
		public String getName() {
			return "�ܾ���GM";
		}
	};

	public abstract int getLevel();

	public abstract ERole getRole();

	public abstract String getName();

}
