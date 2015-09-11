package com.nirvana.exception;

public class BindNumExitedException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4334188686702751772L;
	public static final String message = "用户绑定的手机号已经存在";

	public BindNumExitedException() {
		super(message);
	}

	public BindNumExitedException(String message) {
		super(message);
	}

	public BindNumExitedException(String message, Throwable cause) {
		super(message, cause);
	}

	public BindNumExitedException(Throwable cause) {
		super(cause);
	}

}
