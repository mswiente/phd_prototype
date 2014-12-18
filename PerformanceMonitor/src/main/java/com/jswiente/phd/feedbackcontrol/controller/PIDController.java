package com.jswiente.phd.feedbackcontrol.controller;

import org.springframework.beans.factory.annotation.Value;

public class PIDController implements ControllerStrategy {
	
	@Value("${controller.kp}")
	private Double kp;
	
	@Value("${controller.ki}")
	private Double ki;
	
	@Value("${controller.kd}")
	private Double kd;
	
	@Value("${controller.ta}")
	private Double ta;
	
	private Double errorSum = 0.0;
	private Double previousError = 0.0;

	public Double getOutput(Double error) {
		errorSum = errorSum + error;
		Double output = kp * error + ki * ta * errorSum + (kd * (error - previousError)/ta);
		previousError = error;
		return output;
	}

	public void setKp(Double kp) {
		this.kp = kp;
	}

	public void setKi(Double ki) {
		this.ki = ki;
	}

	public void setKd(Double kd) {
		this.kd = kd;
	}

	public void setTa(Double ta) {
		this.ta = ta;
	}

}
