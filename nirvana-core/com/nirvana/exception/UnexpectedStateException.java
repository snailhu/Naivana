package com.nirvana.exception;

public class UnexpectedStateException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4334188686702751772L;
	public static final String message = "��������״̬����";

	public UnexpectedStateException() {
		super(message);
	}

	public UnexpectedStateException(String message) {
		super(message);
	}

	public UnexpectedStateException(String message, Throwable cause) {
		super(message, cause);
	}

	public UnexpectedStateException(Throwable cause) {
		super(cause);
	}

}
