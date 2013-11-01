package com.jswiente.phd.performance;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Controller {

	private ControllerStrategy strategy;

	private Actuator actuator;

	private static final Logger logger = LoggerFactory
			.getLogger(PerformanceMonitor.class);

	private static final Logger perf = LoggerFactory.getLogger("perf");

	public void setInputValue(Double input, Double reference, long seqNum) {

		Double error = reference - input;
		Double output = this.strategy.getOutput(error);
		
		logger.debug("Controller: seqNum=" + seqNum + ", input=" + input
				+ ", reference=" + reference + ", error=" + error + ", output="
				+ output);

		actuator.setValue(output);

		logState(seqNum, input, reference, error, output);
	}

	public void setStrategy(ControllerStrategy strategy) {
		this.strategy = strategy;
	}

	public void setActuator(Actuator actuator) {
		this.actuator = actuator;
	}

	private void logState(long seqNum, Double input, Double reference, Double error, Double output) {
		perf.info(String.format("CTRL;%d;%s;%s;%s;%s", seqNum, input, reference, error, output));
	}

}
