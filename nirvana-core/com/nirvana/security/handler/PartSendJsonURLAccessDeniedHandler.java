package com.nirvana.security.handler;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.access.AccessDeniedHandler;
import com.nirvana.pojo4json.message.ErrorCode;
import com.nirvana.pojo4json.message.JsonResponseBean;

public class PartSendJsonURLAccessDeniedHandler implements AccessDeniedHandler {

	private String errorPage;

	// 设定API路径。
	private SendJsonDecider decider = null;

	public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
		if (!response.isCommitted()) {
			if ((decider != null) && (decider.decide(request))) {
				response.setContentType("application/json;charset=utf-8");
				PrintWriter writer = response.getWriter();
				String message = JsonResponseBean.getErrorResponse(ErrorCode.ACCESS_DENIED, accessDeniedException.getMessage()).toJson();
				writer.write(message);
				writer.flush();
				writer.close();
				return;
			}
			if (errorPage != null) {
				// Put exception into request scope (perhaps of use to a view)
				request.setAttribute(WebAttributes.ACCESS_DENIED_403, accessDeniedException);

				// Set the 403 status code.
				response.setStatus(HttpServletResponse.SC_FORBIDDEN);

				// forward to error page.
				RequestDispatcher dispatcher = request.getRequestDispatcher(errorPage);
				dispatcher.forward(request, response);
			} else {
				response.sendError(HttpServletResponse.SC_FORBIDDEN, accessDeniedException.getMessage());
			}
		}
	}

	/**
	 * The error page to use. Must begin with a "/" and is interpreted relative
	 * to the current context root.
	 * 
	 * @param errorPage
	 *            the dispatcher path to display
	 * 
	 * @throws IllegalArgumentException
	 *             if the argument doesn't comply with the above limitations
	 */
	public void setErrorPage(String errorPage) {
		if ((errorPage != null) && !errorPage.startsWith("/")) {
			throw new IllegalArgumentException("errorPage must begin with '/'");
		}

		this.errorPage = errorPage;
	}

	/** 如果不设置将不对路径进行sendjson */
	public void setDecider(SendJsonDecider decider) {
		this.decider = decider;
	}
}
