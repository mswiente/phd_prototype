package com.jswiente.phd.performance;

public class PControllerStrategy implements ControllerStrategy {

	private int kp;

	@Override
	public Double getOutput(Double error) {
		Double output = kp * error;
		return output;
	}

	public void setKp(int kp) {
		this.kp = kp;
	}
}
