package com.jswiente.phd.prototype.domain;

public enum SkipReason {
	FLAT (0),
	INVALID (1);
	
	private final Integer value;
	
	private SkipReason(Integer value) {
		this.value = value;
	}
	
	public Integer getValue() {
		return value;
	}
	
	public static SkipReason parse(Integer value) {
		for (SkipReason skipReason : SkipReason.values()) {
			if (skipReason.getValue().equals(value)) {
				return skipReason;
			}
		}
		return null;
	}
}
