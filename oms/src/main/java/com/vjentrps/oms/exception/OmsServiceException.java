package com.vjentrps.oms.exception;

public class OmsServiceException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public OmsServiceException() {

	}

	public OmsServiceException(String message) {
		super(message);
	}

	public OmsServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public OmsServiceException(Throwable cause) {
		super(cause);
	}
}

