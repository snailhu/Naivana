package com.nirvana.exception;

public class EditTooManyTimesException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4334188686702751772L;
	public static final String message = "编辑次数超过上限。";

	public EditTooManyTimesException() {
		super(message);
	}

	public EditTooManyTimesException(String message) {
		super(message);
	}

	public EditTooManyTimesException(String message, Throwable cause) {
		super(message, cause);
	}

	public EditTooManyTimesException(Throwable cause) {
		super(cause);
	}

}
