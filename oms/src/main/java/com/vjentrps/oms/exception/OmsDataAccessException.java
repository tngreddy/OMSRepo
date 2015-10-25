package com.vjentrps.oms.exception;

public class OmsDataAccessException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public OmsDataAccessException() {

	}

	public OmsDataAccessException(String message) {
		super(message);
	}

	public OmsDataAccessException(String message, Throwable cause) {
		super(message, cause);
	}

	public OmsDataAccessException(Throwable cause) {
		super(cause);
	}
}

