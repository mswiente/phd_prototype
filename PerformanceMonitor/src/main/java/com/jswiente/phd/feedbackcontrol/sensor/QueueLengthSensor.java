package com.jswiente.phd.feedbackcontrol.sensor;


public class QueueLengthSensor extends JmxSensor {

	private String attribute = "QueueSize";
	

	public QueueLengthSensor() {
		
	}

	public Long getQueueSize() {

		return getLongAttribute(attribute);
	}

}
