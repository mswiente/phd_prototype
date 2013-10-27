package com.jswiente.phd.performance;

import javax.management.Notification;

public class PerformanceNotification extends Notification {

	private static final long serialVersionUID = 1L;

	public static enum Type {
		HIGH_THRESHOLD_EXCEEDED,
		LOW_THRESHOLD_BELOW
	}
	
	public PerformanceNotification(Type type, Object source,
			long sequenceNumber) {
		super(type.toString(), source, sequenceNumber);
	}
}
