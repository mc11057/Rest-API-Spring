package com.applaudo.restapi.utility;

public class RegisterExistException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 804815264063670692L;

	public RegisterExistException(String errorMessage) {
		super(errorMessage);
	}
}
