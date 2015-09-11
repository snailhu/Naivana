package com.nirvana.security.handler;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.web.session.InvalidSessionStrategy;

import com.nirvana.pojo4json.message.ErrorCode;
import com.nirvana.pojo4json.message.JsonResponseBean;

public class JsonInvalidSessionStrategy implements InvalidSessionStrategy {

	@Override
	public void onInvalidSessionDetected(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		response.setContentType("application/json;charset=utf-8");
		PrintWriter writer = response.getWriter();
		String message = JsonResponseBean.getErrorResponse(ErrorCode.LOGIN_INVALID, "µÇÂ½Ê§Ð§£¬ÇëÖØÐÂµÇÂ½¡£").toJson();
		writer.write(message);
		writer.flush();
		writer.close();
	}

}
