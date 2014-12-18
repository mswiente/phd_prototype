package com.jswiente.phd.performance.component;


public class Identity implements Component<Double, Double> {

	public Double process(Double u) {
		return u;
	}
	
}
