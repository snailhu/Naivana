package com.nirvana.exception;

public class InventoryException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4334188686702751772L;
	public static final String message = "ø‚¥Ê“Ï≥£°£";

	public InventoryException() {
		super(message);
	}

	public InventoryException(String message) {
		super(message);
	}

	public InventoryException(String message, Throwable cause) {
		super(message, cause);
	}

	public InventoryException(Throwable cause) {
		super(cause);
	}

}
