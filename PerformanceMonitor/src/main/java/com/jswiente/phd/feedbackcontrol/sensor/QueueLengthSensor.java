package com.jswiente.phd.feedbackcontrol.sensor;


@Deprecated
public class QueueLengthSensor extends JmxSensor<Long> {

	private String attribute = "QueueSize";
	

	public QueueLengthSensor() {
		
	}

	public Long getQueueSize() {

		return getLongAttribute(attribute);
	}
}
