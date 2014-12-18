package com.jswiente.phd.feedbackcontrol.controller;

import org.springframework.beans.factory.annotation.Value;

public class PIController implements ControllerStrategy {

	@Value("${controller.kp}")
	private Double kp;
	
	@Value("${controller.ki}")
	private Double ki;
	
	@Value("${controller.ta}")
	private Double ta;
	
	private Double errorSum = 0.0;
	
	public Double getOutput(Double error) {
		errorSum = errorSum + error;
		Double output = kp * error + ki * ta * errorSum;
		return output;
	}

	public void setKp(Double kp) {
		this.kp = kp;
	}

	public void setKi(Double ki) {
		this.ki = ki;
	}

	public void setTa(Double ta) {
		this.ta = ta;
	}

}
