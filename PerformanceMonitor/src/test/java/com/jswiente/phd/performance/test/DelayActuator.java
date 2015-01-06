package com.jswiente.phd.performance.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.jswiente.phd.feedbackcontrol.actuator.Actuator;

public class DelayActuator implements Actuator<Double> {
	
	protected static final Logger logger = LoggerFactory
	.getLogger(DelayActuator.class);
	
	@Autowired
	protected MonitoredService monitoredService;

	public void setMonitoredService(MonitoredService monitoredService) {
		this.monitoredService = monitoredService;
	}

	public void setValue(Double value) {
		value = value * -1;
		int currentDelay = monitoredService.getDelay();
		int newDelay = currentDelay + value.intValue();
		logger.debug("Setting value: delay=" + newDelay);
		monitoredService.setDelay(newDelay);
	}

}
