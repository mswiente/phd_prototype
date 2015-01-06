package com.jswiente.phd.performance.test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedResource;

import com.jswiente.phd.feedbackcontrol.monitor.PerformanceMonitor;
import com.jswiente.phd.feedbackcontrol.monitor.statistics.Sample;

@ManagedResource
public class MonitoredService {
	
	private Integer delay;
	private Integer workTime = 100;
	@Autowired
	private PerformanceMonitor performanceMonitor;
	private ExecutorService executorService;
	private Future<?> task;
	
	private static final Logger logger = LoggerFactory
	.getLogger(MonitoredService.class);
	
	public MonitoredService() {
		this.delay = 0;
		this.executorService = Executors.newSingleThreadExecutor();
	}
	
	public void start() throws Exception {
		task = executorService.submit(new Runnable() {

			public void run() {
				while (true) {
					Long start = System.currentTimeMillis();
					try {
						Thread.sleep(workTime + delay);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					Long end = System.currentTimeMillis();
					Sample sample = new Sample(start, end);
					
					performanceMonitor.addSample(sample);
				}
			}
			
		});
	}
	
	public void stop() {
		task.cancel(true);
	}

	@ManagedAttribute
	public Integer getDelay() {
		return delay;
	}

	@ManagedAttribute(defaultValue="1000")
	public void setDelay(Integer delay) {
		this.delay = delay;
	}
	
	public void setPerformanceMonitor(PerformanceMonitor performanceMonitor) {
		this.performanceMonitor = performanceMonitor;
	}
	
}
