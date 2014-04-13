package org.imie.transverse.exception;

public class ApplicationException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2959628274842455271L;

	/**
	 * @param cause
	 */
	public ApplicationException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 */
	public ApplicationException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	

}
