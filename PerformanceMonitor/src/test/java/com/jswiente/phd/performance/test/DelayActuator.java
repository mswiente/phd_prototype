package com.jswiente.phd.performance.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.jswiente.phd.performance.Actuator;
import com.jswiente.phd.performance.DefaultNotificationListener;

public class DelayActuator implements Actuator {
	
	private static final Logger logger = LoggerFactory
	.getLogger(DefaultNotificationListener.class);
	
	@Autowired
	private MonitoredService monitoredService;
	@Value("${controller.interval}")
	private int interval;

	@Override
	public void onLowThreshold() {
		int oldValue = monitoredService.getDelay();
		int newValue = oldValue - interval;
		monitoredService.setDelay(newValue);
		logger.debug("Setting delay: oldValue: " + oldValue + ", newValue: " + newValue);
	}

	@Override
	public void onHighThreshold() {
		int oldValue = monitoredService.getDelay();
		int newValue = oldValue + interval;
		monitoredService.setDelay(newValue);
		logger.debug("Setting delay: oldValue: " + oldValue + ", newValue: " + newValue);
	}

	public void setMonitoredService(MonitoredService monitoredService) {
		this.monitoredService = monitoredService;
	}

	public void setInterval(int interval) {
		this.interval = interval;
	}

	@Override
	public void setValue(Double value) {
		logger.debug("Setting value: value=" + value.intValue());
		monitoredService.setDelay(value.intValue());
	}

}
