package com.nirvana.security;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import com.nirvana.pojo4json.message.ErrorCode;
import com.nirvana.pojo4json.message.JsonResponseBean;

public class SendJsonAuthenticationEntryPoint implements AuthenticationEntryPoint {

	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
		response.setContentType("application/json;charset=utf-8");
		PrintWriter writer = response.getWriter();
		String message = JsonResponseBean.getErrorResponse(ErrorCode.NOT_LOGIN, "ÓÃ»§Î´µÇÂ½¡£").toJson();
		writer.write(message);
		writer.flush();
		writer.close();
		return;
	}

}
