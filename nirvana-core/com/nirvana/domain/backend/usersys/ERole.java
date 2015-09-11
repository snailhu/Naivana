package com.nirvana.domain.backend.usersys;

import java.util.HashSet;
import java.util.Set;

/** ��ɫ���顣 Ԥ�ø��ӽ�ɫ��ϵ�� */
public enum ERole {

	/** �û� */
	USER {
		@Override
		public ERole getFather() {
			return null;
		}
	},

	/** ����Ա */
	ADMIN {
		@Override
		public ERole getFather() {
			return USER;
		}
	},

	/** �����ܲ�������Ա */
	PEPSI_MANAGER {
		@Override
		public ERole getFather() {
			return USER;
		}
	},

	/** �ܾ��� */
	GM {
		@Override
		public ERole getFather() {
			return PEPSI_MANAGER;
		}
	},

	/** �г���չ�ܼ� */
	MDM {
		@Override
		public ERole getFather() {
			return PEPSI_MANAGER;
		}
	},

	/** ������������ */
	SISM {
		@Override
		public ERole getFather() {
			return PEPSI_MANAGER;
		}
	},

	/** �������� */
	CDM {
		@Override
		public ERole getFather() {
			return USER;
		}
	},

	/** ϵͳά��Ա */
	SIS {
		@Override
		public ERole getFather() {
			return USER;
		}
	},

	/** ����ϵͳ���� */
	FSIS {
		@Override
		public ERole getFather() {
			return USER;
		}
	},

	/** ҵ��ϵͳ��Ա */
	AGENTSYS {
		@Override
		public ERole getFather() {
			return USER;
		}
	},

	/** �������� */
	UM {
		@Override
		public ERole getFather() {
			return AGENTSYS;
		}
	},

	/** ���������� */
	TDM {
		@Override
		public ERole getFather() {
			return AGENTSYS;
		}
	},

	/** ��Ա */
	CLERK {
		@Override
		public ERole getFather() {
			return AGENTSYS;
		}
	},

	/** ���������� */
	TDS {
		@Override
		public ERole getFather() {
			return AGENTSYS;
		}
	},

	/** ҵ��Ա */
	AGENT {
		@Override
		public ERole getFather() {
			return AGENTSYS;
		}
	};

	public abstract ERole getFather();

	/** ��ý�ɫ�� */
	public Set<ERole> getRoleSet() {
		Set<ERole> roles = new HashSet<ERole>();
		roles.add(this);
		ERole father = this.getFather();
		while (father != null) {
			roles.add(father);
			father = father.getFather();
		}
		return roles;
	}

	public String getRoleName() {
		return "BACKEND_" + this.toString();
	}

}
