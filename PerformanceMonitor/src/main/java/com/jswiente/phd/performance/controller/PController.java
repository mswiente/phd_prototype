package com.jswiente.phd.performance.controller;

import org.springframework.beans.factory.annotation.Value;

public class PController implements ControllerStrategy {

	@Value("${controller.kp}")
	private Double kp;

	public Double getOutput(Double error) {
		Double output = kp * error;
		return output;
	}

	public void setKp(Double kp) {
		this.kp = kp;
	}
}
