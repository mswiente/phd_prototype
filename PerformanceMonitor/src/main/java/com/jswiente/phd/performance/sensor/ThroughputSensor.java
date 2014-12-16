package com.jswiente.phd.performance.sensor;
import java.util.concurrent.ConcurrentHashMap;

import com.jswiente.phd.performance.monitor.statistics.Sample;


public class ThroughputSensor {
	
	private ConcurrentHashMap<String, Sample> buffer;
	
	public ThroughputSensor() {
		this.buffer = new ConcurrentHashMap<String, Sample>(10000);
	}
	
	public void registerStart(String id, Long startTime) {
		Sample sample = new Sample();
		sample.setStart(startTime);
		buffer.put(id, sample);
	}
	
	public Sample registerEnd(String id, Long endTime) {
		Sample sample = buffer.remove(id);
		if (sample != null) {
			sample.setEnd(endTime);
		}
		return sample;
	}
	
	public long getSize() {
		return buffer.size();
	}

}
