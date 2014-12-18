package com.jswiente.phd.feedbackcontrol.controller;

public interface ControllerStrategy {
	public Double getOutput(Double error);
}
