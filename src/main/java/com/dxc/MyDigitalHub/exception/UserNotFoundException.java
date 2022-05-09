package com.dxc.MyDigitalHub.exception;

public class UserNotFoundException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 202204230201L;

	public UserNotFoundException(String errMsg) {
		super(errMsg);
	}
}
