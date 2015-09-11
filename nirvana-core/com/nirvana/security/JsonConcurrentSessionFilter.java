package com.nirvana.security;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.util.Assert;
import org.springframework.web.filter.GenericFilterBean;

import com.nirvana.pojo4json.message.ErrorCode;
import com.nirvana.pojo4json.message.JsonResponseBean;

/**
 * 自定义ConcurrentSessionFilter 实现SendJson
 * 
 * @author zhang
 */
public class JsonConcurrentSessionFilter extends GenericFilterBean {
	// ~ Instance fields
	// ================================================================================================

	private SessionRegistry sessionRegistry = new SessionRegistryImpl();
	private LogoutHandler[] handlers = new LogoutHandler[] { new SecurityContextLogoutHandler() };

	// ~ Methods
	// ========================================================================================================

	public JsonConcurrentSessionFilter() {
	}

	@Override
	public void afterPropertiesSet() {
		Assert.notNull(sessionRegistry, "SessionRegistry required");
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;

		HttpSession session = request.getSession(false);

		if (session != null) {
			SessionInformation info = sessionRegistry.getSessionInformation(session.getId());

			if (info != null) {
				if (info.isExpired()) {
					// Expired - abort processing
					doLogout(request, response);
					// TODO
					PrintWriter writer = response.getWriter();
					String message = JsonResponseBean.getErrorResponse(ErrorCode.LOGIN_EXPIRED, "登陆过期，请重新登陆。").toJson();
					writer.write(message);
					writer.flush();
					writer.close();
					return;
				} else {
					// Non-expired - update last request date/time
					sessionRegistry.refreshLastRequest(info.getSessionId());
				}
			}
		}

		chain.doFilter(request, response);
	}

	private void doLogout(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		for (LogoutHandler handler : handlers) {
			handler.logout(request, response, auth);
		}
	}

	public void setLogoutHandlers(LogoutHandler[] handlers) {
		Assert.notNull(handlers);
		this.handlers = handlers;
	}

}
