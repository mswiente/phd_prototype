package com.jswiente.phd.performance.test;

public class SimpleDelayActuator extends DelayActuator {
	@Override
	public void setValue(Double value) {
		monitoredService.setDelay(value.intValue());
	}
}
