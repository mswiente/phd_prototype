package com.jswiente.phd.prototype.core;

public class DummyDataProcessor implements DataProcessor {

	@Override
	public WorkUnit process(WorkUnit input) {
		
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		return input;
	}

}
