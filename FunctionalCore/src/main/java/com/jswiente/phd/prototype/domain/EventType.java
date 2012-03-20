package com.jswiente.phd.prototype.domain;

public enum EventType {
	DATA (0),
	VOICE (1);
	
	private final Integer value;
	
	private EventType(Integer value) {
		this.value = value;
	}
	
	public Integer getValue() {
		return value;
	}
	
	public static EventType parse(Integer value) {
		for (EventType eventType : EventType.values()) {
			if (eventType.getValue().equals(value)) {
				return eventType;
			}
		}
		return null;
	}
}
