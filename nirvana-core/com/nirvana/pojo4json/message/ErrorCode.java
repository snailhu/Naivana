package com.nirvana.pojo4json.message;

/** 错误码。 */
public enum ErrorCode {

	NOT_LOGIN(12, "用户未登陆"),

	ILLEGAL_ARGUMENT(2, "非法参数"),

	USER_EXIST(3, "用户已存在"),

	DATA_NOT_FOUND(4, "找不到记录"),

	ACCESS_DENIED(5, "权限不够"),

	SERVER_ERROR(6, "服务器内部错误"),

	ERROR_STATE(7, "状态错误"),

	ALLREADY_EXISTS(8, "内容重复"),

	NOT_FOUND(9, "找不到内容"),

	AUTHENTICATE_FAILURE(10, "验证失败"),

	LOGIN_EXPIRED(11, "登陆过期"),

	LOGIN_INVALID(12, "登陆失效"),

	NOT_EMPTY(13, "");

	private final int code;

	private final String text;

	private ErrorCode(int code, String text) {
		this.text = text;
		this.code = code;
	}

	public String getText() {
		return text;
	}

	public int getCode() {
		return code;
	}
}
