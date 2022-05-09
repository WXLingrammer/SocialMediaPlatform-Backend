package com.dxc.MyDigitalHub.entity;

public class ResponseMessages {
	private String message;
	public ResponseMessages(String message) {
		this.message = message;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
