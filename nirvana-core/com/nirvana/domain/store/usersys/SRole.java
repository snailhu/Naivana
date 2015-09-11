package com.nirvana.domain.store.usersys;

public enum SRole {

	USER;

	public String getRoleName() {
		return "STORE_" + this.toString();
	}

}
