package com.jswiente.phd.performance.component;

import org.springframework.beans.factory.annotation.Value;

public class Integrator implements Component<Double, Double> {

	private Double data = 0.0;
	@Value("${actuator.dt}")
	private int dt;
	
	public Double process(Double u) {
		
		data = data + u;
		return dt*data;
	}

}
