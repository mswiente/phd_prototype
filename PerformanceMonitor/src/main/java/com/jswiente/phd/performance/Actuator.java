package com.jswiente.phd.performance;

public interface Actuator {

	public void onLowThreshold();
	
	public void onHighThreshold();
	
	public void setValue(Double value);
}
