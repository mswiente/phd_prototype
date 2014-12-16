package com.jswiente.phd.performance.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Value;

import com.jswiente.phd.performance.actuator.Actuator;

public class StaticTest implements Controller<Double, Double> {

	@Value("${statictest.maxValue}")
	private Double maxValue;
	@Value("${statictest.steps}")
	private int steps = 1;
	@Value("${statictest.repeats}")
	private int repeats;
	@Value("${statictest.values}")
	private String values;
	
	private ArrayList<Double> inputValues = new ArrayList<Double>();
	int i = 0;
	Double output = 1.0;

	private Actuator<Double> actuator;

	public void setInputValue(Double input, long seqNum) {
		
		output = inputValues.get(i);
		actuator.setValue(output);

		if (seqNum % repeats == 0) {
			if (i < inputValues.size()-1) {
				i++;
			}
		} 
		
	}
	
	public void init() {
		if (values != null && !values.isEmpty()) {
			
			for (String value: values.split(";")) {
				inputValues.add(Double.valueOf(value));
			}
			
		} else {
			for (int i = 1; i <= steps; i++) {
				double value = i * maxValue /steps;
				inputValues.add(value);
			}
		}
	}

	public Double getOutput() {
		return output;
	}

	public void setActuator(Actuator<Double> actuator) {
		this.actuator = actuator;
	}

}
