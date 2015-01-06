package com.jswiente.phd.feedbackcontrol.component;

public class Identity implements Component<Double, Double> {

	public Double process(Double u) {
		return u;
	}
	
}
