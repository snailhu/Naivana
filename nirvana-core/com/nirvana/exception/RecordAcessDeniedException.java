package com.nirvana.exception;

public class RecordAcessDeniedException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4334188686702751772L;
	public static final String message = "¼ÇÂ¼·ÃÎÊ±»¾Ü¾ø¡£";

	public RecordAcessDeniedException() {
		super(message);
	}

	public RecordAcessDeniedException(String message) {
		super(message);
	}

	public RecordAcessDeniedException(String message, Throwable cause) {
		super(message, cause);
	}

	public RecordAcessDeniedException(Throwable cause) {
		super(cause);
	}
}
