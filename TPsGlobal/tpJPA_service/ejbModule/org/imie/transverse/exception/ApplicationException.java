package org.imie.transverse.exception;

public class ApplicationException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4263198743267149893L;

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
