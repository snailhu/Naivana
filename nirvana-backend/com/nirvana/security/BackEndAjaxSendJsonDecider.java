package com.nirvana.security;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.util.AntPathRequestMatcher;
import org.springframework.security.web.util.RequestMatcher;

import com.nirvana.security.handler.SendJsonDecider;

public class BackEndAjaxSendJsonDecider implements SendJsonDecider {

	private static String DEFAULT_AJAX_RUL = "/backend/web/api/**";

	private RequestMatcher pathMatcher = new AntPathRequestMatcher(DEFAULT_AJAX_RUL);

	@Override
	public boolean decide(HttpServletRequest request) {
		return pathMatcher.matches(request);
	}

	public void setAjaxUrl(String ajaxUrl) {
		pathMatcher = new AntPathRequestMatcher(ajaxUrl);
	}

}
