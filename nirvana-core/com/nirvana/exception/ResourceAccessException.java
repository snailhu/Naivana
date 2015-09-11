package com.nirvana.exception;

public class ResourceAccessException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4334188686702751772L;
	public static final String message = "你无权获取此资源。";

	public ResourceAccessException() {
		super(message);
	}

	public ResourceAccessException(String message) {
		super(message);
	}

	public ResourceAccessException(String message, Throwable cause) {
		super(message, cause);
	}

	public ResourceAccessException(Throwable cause) {
		super(cause);
	}

}
