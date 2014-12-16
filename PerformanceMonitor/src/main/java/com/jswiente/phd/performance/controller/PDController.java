package com.jswiente.phd.performance.controller;

import org.springframework.beans.factory.annotation.Value;

public class PDController implements ControllerStrategy {
	
	@Value("${controller.kp}")
	private Double kp;
	
	@Value("${controller.kd}")
	private Double kd;
	
	@Value("${controller.ta}")
	private Double ta;
	
	private Double previousError;

	public Double getOutput(Double error) {
		Double output = (kp * error) + (kd * (error - previousError)/ta);
		previousError = error;
		return output;
	}

	public void setKp(Double kp) {
		this.kp = kp;
	}

	public void setKd(Double kd) {
		this.kd = kd;
	}

	public void setTa(Double ta) {
		this.ta = ta;
	}
}
