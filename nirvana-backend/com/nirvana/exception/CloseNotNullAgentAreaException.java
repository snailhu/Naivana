package com.nirvana.exception;

public class CloseNotNullAgentAreaException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4334188686702751772L;
	public static final String message = "无法关闭非空的CR区。";

	public CloseNotNullAgentAreaException() {
		super(message);
	}

	public CloseNotNullAgentAreaException(String message) {
		super(message);
	}

	public CloseNotNullAgentAreaException(String message, Throwable cause) {
		super(message, cause);
	}

	public CloseNotNullAgentAreaException(Throwable cause) {
		super(cause);
	}

}
