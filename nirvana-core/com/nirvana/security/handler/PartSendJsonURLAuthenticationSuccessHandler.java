package com.nirvana.security.handler;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AbstractAuthenticationTargetUrlRequestHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import com.nirvana.pojo4json.message.JsonResponseBean;

public class PartSendJsonURLAuthenticationSuccessHandler extends AbstractAuthenticationTargetUrlRequestHandler implements AuthenticationSuccessHandler {

	// 设定API路径。
	private SendJsonDecider decider = null;

	public PartSendJsonURLAuthenticationSuccessHandler() {
	}

	/**
	 * Constructor which sets the <tt>defaultTargetUrl</tt> property of the base
	 * class.
	 * 
	 * @param defaultTargetUrl
	 *            the URL to which the user should be redirected on successful
	 *            authentication.
	 */
	public PartSendJsonURLAuthenticationSuccessHandler(String defaultTargetUrl) {
		setDefaultTargetUrl(defaultTargetUrl);
	}

	/**
	 * Calls the parent class {@code handle()} method to forward or redirect to
	 * the target URL, and then calls {@code clearAuthenticationAttributes()} to
	 * remove any leftover session data.
	 */
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
		if ((decider != null) && (decider.decide(request))) {
			response.setContentType("application/json;charset=utf-8");
			PrintWriter writer = response.getWriter();
			String message = JsonResponseBean.getSuccessResponse().toJson();
			writer.write(message);
			writer.flush();
			writer.close();
			clearAuthenticationAttributes(request);
			return;
		}
		handle(request, response, authentication);
		clearAuthenticationAttributes(request);
	}

	/**
	 * Removes temporary authentication-related data which may have been stored
	 * in the session during the authentication process.
	 */
	protected final void clearAuthenticationAttributes(HttpServletRequest request) {
		HttpSession session = request.getSession(false);

		if (session == null) {
			return;
		}

		session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
	}

	/** 如果不设置将不对路径进行sendjson */
	public void setDecider(SendJsonDecider decider) {
		this.decider = decider;
	}
}
