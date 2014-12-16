package com.jswiente.phd.performance.sensor;


public class QueueLengthSensor extends JmxSensor {

	private String attribute = "QueueSize";
	

	public QueueLengthSensor() {
		
	}

	public Long getQueueSize() {

		return getLongAttribute(attribute);
	}

}
