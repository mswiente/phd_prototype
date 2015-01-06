package com.jswiente.phd.feedbackcontrol.controller;

import java.util.ArrayList;

import com.jswiente.phd.feedbackcontrol.actuator.Actuator;

public class TestController implements Controller<Double, Double> {

	private Double maxValue;
	private int steps = 1;
	private int repeats;
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

	public void setMaxValue(Double maxValue) {
		this.maxValue = maxValue;
	}

	public void setSteps(int steps) {
		this.steps = steps;
	}

	public void setRepeats(int repeats) {
		this.repeats = repeats;
	}

	public void setValues(String values) {
		this.values = values;
	}

}
