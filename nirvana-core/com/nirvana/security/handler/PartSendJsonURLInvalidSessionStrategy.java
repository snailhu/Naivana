package com.nirvana.security.handler;

import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.security.web.util.UrlUtils;
import org.springframework.util.Assert;

import com.nirvana.pojo4json.message.ErrorCode;
import com.nirvana.pojo4json.message.JsonResponseBean;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public final class PartSendJsonURLInvalidSessionStrategy implements InvalidSessionStrategy {
	private final String destinationUrl;
	private final RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	private boolean createNewSession = true;

	// 设定API路径。
	private SendJsonDecider decider = null;

	public PartSendJsonURLInvalidSessionStrategy(String invalidSessionUrl) {
		Assert.isTrue(UrlUtils.isValidRedirectUrl(invalidSessionUrl), "url must start with '/' or with 'http(s)'");
		this.destinationUrl = invalidSessionUrl;
	}

	@Override
	public void onInvalidSessionDetected(HttpServletRequest request, HttpServletResponse response) throws IOException {
		if (createNewSession) {
			request.getSession();
		}
		if ((decider != null) && (decider.decide(request))) {
			response.setContentType("application/json;charset=utf-8");
			PrintWriter writer = response.getWriter();
			String message = JsonResponseBean.getErrorResponse(ErrorCode.ACCESS_DENIED, "登陆失效，请重新登陆。").toJson();
			writer.write(message);
			writer.flush();
			writer.close();
			return;
		}
		redirectStrategy.sendRedirect(request, response, destinationUrl);
	}

	public void setCreateNewSession(boolean createNewSession) {
		this.createNewSession = createNewSession;
	}

	/** 如果不设置将不对路径进行sendjson */
	public void setDecider(SendJsonDecider decider) {
		this.decider = decider;
	}
}
