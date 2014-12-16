package com.jswiente.phd.performance.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import com.jswiente.phd.performance.actuator.Actuator;
import com.jswiente.phd.performance.monitor.PerformanceMonitor;

public class BasicController implements Controller<Double, Double> {

	@Value("${controller.setPoint}")
	private Double setPoint;
	private Double output;
	
	@Value("${controller.inverted}")
	private boolean inverted = false;
	
	private ControllerStrategy strategy;
	private Actuator<Double> actuator;

	private static final Logger logger = LoggerFactory
			.getLogger(PerformanceMonitor.class);

	private static final Logger perf = LoggerFactory.getLogger("perf");

	public void setInputValue(Double input, long seqNum) {

		Double error = setPoint - input;
		if (inverted) {
			error = -error;
		}
		output = this.strategy.getOutput(error);
		
		logger.debug("Controller: seqNum=" + seqNum + ", input=" + input
				+ ", reference=" + setPoint + ", error=" + error + ", output="
				+ output);

		if (actuator != null) {
			actuator.setValue(output);
		}

		logState(seqNum, input, setPoint, error, output);
	}

	public void setStrategy(ControllerStrategy strategy) {
		this.strategy = strategy;
	}

	public void setActuator(Actuator<Double> actuator) {
		this.actuator = actuator;
	}

	private void logState(long seqNum, Double input, Double reference, Double error, Double output) {
		perf.info(String.format("CTRL;%d;%s;%s;%s;%s", seqNum, input, reference, error, output));
	}

	public Double getOutput() {
		return output;
	}

}
