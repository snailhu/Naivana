package com.nirvana.security.handler;

import javax.servlet.http.HttpServletRequest;

public interface SendJsonDecider {

	/**
	 * ���request��������send Json ���� send redirect.
	 * 
	 * */
	public boolean decide(HttpServletRequest request);

}
