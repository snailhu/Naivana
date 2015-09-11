package com.nirvana.erp.exception;

public class WebServiceException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4334188686702751772L;
	public static final String message = "����WEB�����쳣��";

	public WebServiceException() {
		super(message);
	}

	public WebServiceException(String message) {
		super(message);
	}

	public WebServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public WebServiceException(Throwable cause) {
		super(cause);
	}

}
