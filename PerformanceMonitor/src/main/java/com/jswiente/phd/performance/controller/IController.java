package com.jswiente.phd.performance.controller;

import org.springframework.beans.factory.annotation.Value;


public class IController implements ControllerStrategy {
	
	@Value("${controller.ki}")
	private Double ki;
	
	@Value("${controller.ta}")
	private Double ta;
	
	private Double errorSum = 0.0;

	public Double getOutput(Double error) {
		errorSum = errorSum + error;
		return ki * ta * errorSum;
	}
	
	public void setKi(Double ki) {
		this.ki = ki;
	}
	
	public void setTa(Double ta) {
		this.ta = ta;
	}
}
