package com.dxc.MyDigitalHub.exception;

public class PasswordMismatchException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 202205010201L;

	public PasswordMismatchException(String errMsg) {
		super(errMsg);
	}
}
