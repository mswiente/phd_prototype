package com.jswiente.phd.performance;

import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.jmx.export.notification.NotificationPublisher;
import org.springframework.jmx.export.notification.NotificationPublisherAware;

import com.jswiente.phd.performance.statistics.PerformanceStatistics;

@ManagedResource
public class PerformanceMonitor implements NotificationPublisherAware {

	private static final Logger logger = LoggerFactory
			.getLogger(PerformanceMonitor.class);

	private static final Logger perf = LoggerFactory.getLogger("perf");

	private ArrayList<Sample> samples;

	@Value("${monitor.delay}")
	private Integer delay;

	@Value("${monitor.threshold.high}")
	private Double highThreshold;

	@Value("${monitor.threshold.low}")
	private Double lowThreshold;

	private ScheduledExecutorService scheduler;
	private Future<?> monitoringTask;
	private PerformanceStatistics statistics;
	private NotificationPublisher notificationPublisher;
	private int notificationSeqNum = 0;

	private class MonitoringTask implements Runnable {
		
		private long count = 1;
		
		@Override
		public void run() {
			synchronized (samples) {
				logger.debug("Running MonitoringTask...");
				ArrayList<Sample> currentSamples = new ArrayList<Sample>(
						samples);
				statistics.setSamples(currentSamples);
				samples = new ArrayList<Sample>();
				updateNotifications(count++);
			}
		}
	}

	public PerformanceMonitor() {
		this.samples = new ArrayList<Sample>();
		this.scheduler = Executors.newSingleThreadScheduledExecutor();
		this.statistics = new PerformanceStatistics();
	}

	public void addSample(Sample sample) {
		synchronized (samples) {
			this.samples.add(sample);
		}
	}

	private void updateNotifications(long count) {
		logState(count);
		logger.debug("update notifications...");
		Double currentThroughput = getThroughput();
		if (currentThroughput <= lowThreshold) {
			logger.debug("Throughput below lowThreshold: " + currentThroughput
					+ "(" + lowThreshold + ")");
			sendNotification(PerformanceNotification.Type.LOW_THRESHOLD_BELOW);
		} else if (currentThroughput >= highThreshold) {
			logger.debug("Throughput above highTreshold: " + currentThroughput
					+ "(" + highThreshold + ")");
			sendNotification(PerformanceNotification.Type.HIGH_THRESHOLD_EXCEEDED);
		} else {
			logger.debug("Throughput within limit: " + currentThroughput + "["
					+ lowThreshold + ";" + highThreshold + "]");
		}
	}

	private void sendNotification(PerformanceNotification.Type type) {
		logger.debug("sending notification: " + type.toString());
		this.notificationPublisher
				.sendNotification(new PerformanceNotification(type, this,
						notificationSeqNum++));
	}

	private void logState(long count) {
		long timestamp = System.currentTimeMillis();
		Double throughput = getThroughput();
		Double meanLatency = getMeanLatency();
		Double pLatency = get95PercentageLatency();
		Double minLatency = getMinimumLatency();
		Double maxLatency = getMaximumLatency();
		perf.info(String.format("%d;%d;%s;%s;%s;%s;%s", count, timestamp,
				throughput, meanLatency, pLatency, minLatency, maxLatency));
	}

	@ManagedOperation
	public void start() {
		if (monitoringTask == null) {
			logger.debug("Starting monitoringTask...");
			monitoringTask = scheduler.scheduleWithFixedDelay(
					new MonitoringTask(), delay, delay, TimeUnit.SECONDS);
		}
	}

	@ManagedOperation
	public void stop() {
		logger.debug("Stopping monitoringTask...");
		monitoringTask.cancel(true);
		monitoringTask = null;
	}

	@ManagedOperation
	public void shutDown() {
		logger.debug("Shutting down...");
		scheduler.shutdownNow();
	}

	@ManagedOperation
	public void reset() {
		this.samples.clear();
	}

	@ManagedAttribute
	public Integer getSampleSize() {
		return samples.size();
	}

	@ManagedAttribute
	public Integer getDelay() {
		return delay;
	}

	@ManagedAttribute(defaultValue = "5")
	public void setDelay(Integer delay) {
		this.delay = delay;
	}

	@ManagedAttribute
	public Double getThroughput() {
		return statistics.getThroughput();
	}

	@ManagedOperation
	public Double getMaximumLatency() {
		return statistics.getLatencyMax();
	}

	@ManagedOperation
	public Double getMinimumLatency() {
		return statistics.getLatencyMin();
	}

	@ManagedOperation
	public Double getMeanLatency() {
		return statistics.getLatencyMean();
	}

	@ManagedOperation
	public Double get95PercentageLatency() {
		return statistics.getLatenyPercentage95();
	}

	@Override
	public void setNotificationPublisher(
			NotificationPublisher notificationPublisher) {
		this.notificationPublisher = notificationPublisher;
	}
}
