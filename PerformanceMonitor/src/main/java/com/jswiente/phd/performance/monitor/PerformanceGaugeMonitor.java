package com.jswiente.phd.performance.monitor;

import javax.management.Notification;
import javax.management.ObjectName;
import javax.management.monitor.GaugeMonitor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jmx.export.annotation.ManagedResource;

@ManagedResource
public class PerformanceGaugeMonitor extends GaugeMonitor {
	
	@Value("${monitor.throughput.threshold.high}")
	private String highThreshold;
	
	@Value("${monitor.throughput.threshold.low}")
	private String lowThreshold;
	
	private String observedObjectName;

	private String observedAttributeName;
	
	private static final Logger logger = LoggerFactory
	.getLogger(PerformanceGaugeMonitor.class);
	
	public PerformanceGaugeMonitor() {
		
		super();
	}
	
	public void init() {
		try {
			this.addObservedObject(new ObjectName(observedObjectName));
			this.setObservedAttribute(observedAttributeName);
			this.setNotifyHigh(true);
			this.setNotifyLow(true);
			this.setDifferenceMode(false);
			this.setThresholds(Double.parseDouble(highThreshold), Double.parseDouble(lowThreshold));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void sendNotification(Notification notification) {
		super.sendNotification(notification);
	}
	
	public void setObservedObjectName(String observedObjectName) {
		this.observedObjectName = observedObjectName;
	}
	
	public void setObservedAttributeName(String observedAttributeName) {
		this.observedAttributeName = observedAttributeName;
	}
	
}
