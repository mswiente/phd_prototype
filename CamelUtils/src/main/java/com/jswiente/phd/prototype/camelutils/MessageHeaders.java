package com.jswiente.phd.prototype.camelutils;

public enum MessageHeaders {
	START ("startTime"),
	END ("endTime");
	
	private final String value;
	
	private MessageHeaders(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
}
