package com.jswiente.phd.feedbackcontrol.controller;

import org.springframework.beans.factory.annotation.Value;

public class SimpleControllerStrategy implements ControllerStrategy {

	@Value("${simpleController.period1}")
	private int period1;
	@Value("${simpleController.period2}")
	private int period2;
	private int timer = 0;
	
	public Double getOutput(Double error) {
		if (error > 0) {
			timer = period1;
			return +1.0;
		}

		timer--;
		
		if (timer == 0) {
			timer = period2;
			return -1.0;
		}
		
		return 0.0;
	}

}
