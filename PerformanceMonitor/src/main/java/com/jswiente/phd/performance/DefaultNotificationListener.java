package com.jswiente.phd.performance;

import javax.management.Notification;
import javax.management.NotificationFilter;
import javax.management.NotificationListener;
import javax.management.monitor.MonitorNotification;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultNotificationListener implements NotificationListener, NotificationFilter {

	private static final long serialVersionUID = 6351424621350941740L;
	
	private static final Logger logger = LoggerFactory
	.getLogger(DefaultNotificationListener.class);
	
	private Controller controller;
	
	@Override
	public void handleNotification(Notification notification, Object handback) {
		
		logger.debug("Received notification: " + notification.getType());
		if (notification.getType().equals(PerformanceNotification.Type.HIGH_THRESHOLD_EXCEEDED.toString())) {
			controller.onHighThreshold();
		}
		else if (notification.getType().equals(PerformanceNotification.Type.LOW_THRESHOLD_BELOW.toString())) {
			controller.onLowThreshold();
		}
	}
	
	@Override
	public boolean isNotificationEnabled(Notification notification) {
		return MonitorNotification.class.isAssignableFrom(notification.getClass());
	}

	public void setController(Controller controller) {
		this.controller = controller;
	}
}
