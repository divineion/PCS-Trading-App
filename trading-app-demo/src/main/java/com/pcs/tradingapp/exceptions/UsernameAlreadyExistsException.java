package com.pcs.tradingapp.exceptions;

public class UsernameAlreadyExistsException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8L;
	
	public UsernameAlreadyExistsException(String message) {
		super(message);
	}

}
