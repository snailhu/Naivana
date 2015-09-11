package com.nirvana.security;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.util.AntPathRequestMatcher;
import org.springframework.security.web.util.RequestMatcher;

import com.nirvana.security.handler.SendJsonDecider;

public class NirvanaEntryPointSendJsonDecider implements SendJsonDecider {

	private static String DEFAULT_WEB_URL = "/backend/web/**";

	private static String DEFAULT_AJAX_URL = "/backend/web/api/**";

	private RequestMatcher webUrlMatcher = new AntPathRequestMatcher(DEFAULT_WEB_URL);

	private RequestMatcher ajaxUrlMatcher = new AntPathRequestMatcher(DEFAULT_AJAX_URL);

	public NirvanaEntryPointSendJsonDecider() {
	}

	@Override
	public boolean decide(HttpServletRequest request) {
		if (webUrlMatcher.matches(request) && !ajaxUrlMatcher.matches(request)) {
			return false;
		}
		return true;
	}

	public void setWebUrl(String webUrl) {
		webUrlMatcher = new AntPathRequestMatcher(webUrl);
	}

	public void setAjaxUrl(String ajaxUrl) {
		ajaxUrlMatcher = new AntPathRequestMatcher(ajaxUrl);
	}

}
