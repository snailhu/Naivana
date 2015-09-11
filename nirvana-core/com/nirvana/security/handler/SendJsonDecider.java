package com.nirvana.security.handler;

import javax.servlet.http.HttpServletRequest;

public interface SendJsonDecider {

	/**
	 * 检测request，决定是send Json 还是 send redirect.
	 * 
	 * */
	public boolean decide(HttpServletRequest request);

}
