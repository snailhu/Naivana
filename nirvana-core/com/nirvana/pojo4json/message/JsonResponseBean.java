package com.nirvana.pojo4json.message;

import com.nirvana.pojo4json.json.IgnoreClassRenderer;
import com.nirvana.pojo4json.json.Renderer;

/**
 * 返回json的统一Bean.
 * 
 * */
public class JsonResponseBean {

	private String response = "success";

	private Object data = null;

	private static final JsonResponseBean SUCCESS = new JsonResponseBean();

	private static final Renderer RENDERER = new IgnoreClassRenderer();

	public JsonResponseBean() {
	}

	public JsonResponseBean(Object data) {
		if (data != null) {
			this.data = data;
		}else{
			this.data = "";
		}
	}

	public JsonResponseBean(String response, Object data) {
		this.response = response;
		this.data = data;
	}

	public String toJson() {
		return RENDERER.render(this);
	}

	public static JsonResponseBean getSuccessResponse() {
		return SUCCESS;
	}

	public static JsonResponseBean getErrorResponse(ErrorCode code, String text) {
		JsonResponseBean errorBean = new JsonResponseBean();
		Error error = new Error(code.getCode(), text);
		errorBean.setResponse("error");
		errorBean.setData(error);
		return errorBean;
	}

	public String getResponse() {
		return response;
	}

	private void setResponse(String response) {
		this.response = response;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	private static class Error {

		@SuppressWarnings("unused")
		private int errorcode;

		@SuppressWarnings("unused")
		private String text;

		public Error(int errorcode, String text) {
			this.errorcode = errorcode;
			this.text = text;
		}

	}

}
