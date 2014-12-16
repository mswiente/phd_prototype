package com.jswiente.phd.performance.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import com.jswiente.phd.performance.actuator.Actuator;

@Deprecated
public class StepFunctionController implements Controller<Double, Double> {
	
	@Value("${stepfunction.interval}")
	private int interval;
	
	@Value("${stepfunction.highValue}")
	private Double highValue;
	
	private Double output = 0.0;
	
	private Actuator<Double> actuator;
	
	private static final Logger logger = LoggerFactory
	.getLogger(StepFunctionController.class);
	
	private Double[] inputValues = {800.0, 400.0, 600.0, 100.0, 0.0, 200.0};
	//private Double[] inputValues = {800.0};
	private int i = 0;
	

	public void setInputValue(Double input, long seqNum) {
		
		output = inputValues[i];
		actuator.setValue(output);
		
		if (seqNum % interval == 0) {
			
			if (i < inputValues.length-1 ) {
				i++;
			} else {
				i = 0;
			}
		}
	}

	public void setActuator(Actuator<Double> actuator) {
		this.actuator = actuator;
	}

	public Double getOutput() {
		return output;
	}
}
