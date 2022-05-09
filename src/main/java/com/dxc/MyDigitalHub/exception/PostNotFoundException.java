package com.dxc.MyDigitalHub.exception;

public class PostNotFoundException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 202204250201L;

	public PostNotFoundException(String errMsg) {
		super(errMsg);
	}
}
