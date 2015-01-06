package com.jswiente.phd.feedbackcontrol.component;

import org.springframework.beans.factory.annotation.Value;

public class Integrator implements Component<Double, Double> {

	private Double data = 0.0;
	@Value("${integrator.dt}")
	private int dt;
	
	public Double process(Double u) {
		data = data + u;
		
		return dt*data;
	}

}
