package com.jswiente.phd.feedbackcontrol.monitor;

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

import com.jswiente.phd.feedbackcontrol.controller.Controller;
import com.jswiente.phd.feedbackcontrol.monitor.statistics.PerformanceStatistics;
import com.jswiente.phd.feedbackcontrol.monitor.statistics.PerformanceStatisticsService;
import com.jswiente.phd.feedbackcontrol.monitor.statistics.Sample;
import com.jswiente.phd.feedbackcontrol.sensor.JmxSensor;
import com.jswiente.phd.feedbackcontrol.sensor.QueueLengthSensor;

@ManagedResource
public class PerformanceMonitor implements NotificationPublisherAware {

	private static final Logger logger = LoggerFactory
			.getLogger(PerformanceMonitor.class);

	private static final Logger perf = LoggerFactory.getLogger("mon");

	@Value("${monitor.interval}")
	private Integer interval;

	@Value("${monitor.sampleSize}")
	private int sampleSize;
	
	@Value("${monitor.maxLogCount}")
	private int maxLogCount;

	private ScheduledExecutorService scheduler;
	private Future<?> monitoringTask;
	private PerformanceStatisticsService statisticsService;
	private PerformanceStatistics statistics;
	private Long queueSize = 0L;
	private Long queueSizeChange;
	private Integer inFlightExchanges = 0;
	private NotificationPublisher notificationPublisher;
	private int notificationSeqNum = 0;
	private boolean isStarted = false;
	
	private long lastRunTime;

	private QueueLengthSensor queueLengthSensor;
	private JmxSensor jmxSensor;
	private Controller<Double, Double> controller;

	private class MonitoringTask implements Runnable {

		private long count = 1;

		public void run() {
			try {
				long currentRunTime = System.currentTimeMillis();
				setStatistics(statisticsService.calculateStatistics(lastRunTime, currentRunTime));
				Long prevQueueSize = queueSize;
				queueSize = queueLengthSensor.getQueueSize();
				queueSizeChange = queueSize - prevQueueSize;
				inFlightExchanges = jmxSensor.getIntegerAttribute("InflightExchanges");
				updateController(count);
				logState(count, currentRunTime);
				count++;
				lastRunTime = currentRunTime;
				
			} catch (Exception e) {
				logger.error("Exception occured");
				e.printStackTrace();
			}

		}
	}

	public PerformanceMonitor() {
		
	}
	
	public void init() {
		this.scheduler = Executors.newSingleThreadScheduledExecutor();
		this.statisticsService = new PerformanceStatisticsService(sampleSize);
		this.statistics = new PerformanceStatistics();
	}

	public void addSample(Sample sample) {

		if (!isStarted) {
			return;
		}

		statisticsService.addSample(sample);
	}

	//TODO: input for the controller should be configurable...
	private void updateController(long count) {
		if (controller == null) return;
		
		controller.setInputValue(queueSizeChange.doubleValue(), count);
	}

	@SuppressWarnings("unused")
	private void sendNotification(PerformanceNotification.Type type) {
		logger.debug("sending notification: " + type.toString());
		this.notificationPublisher
				.sendNotification(new PerformanceNotification(type, this,
						notificationSeqNum++));
	}

	private void logState(long count, long timestamp) {

		Double output = null;
		if (controller != null) {
			
			output = controller.getOutput();
		}
		Double throughput = getThroughput();
		Double meanLatency = getMeanLatency();
		Double pLatency = get95PercentageLatency();
		Double minLatency = getMinimumLatency();
		Double maxLatency = getMaximumLatency();
		
		logger.debug(String.format("Monitor: step=%d, u=%s, y=%s", count,
				output, throughput));
		
		logger.debug(String.format("Queue: queueSize=%s, queueSizeChange=%s", getQueueSize(), getQueueSizeChange()));
		
		logger.debug(String.format("InflightExchanges=%s", getInflightExchanges()));
		
		if (count <= maxLogCount) {
			perf.info(String.format("MON;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s", count, timestamp, output,
					throughput, meanLatency, pLatency, minLatency, maxLatency, getQueueSize(), getQueueSizeChange(), getInflightExchanges()));
		}
	}

	private void setStatistics(PerformanceStatistics statistics) {
		this.statistics = statistics;
	}

	@ManagedOperation
	public void start() {
		if (monitoringTask == null) {
			logger.debug("Starting monitoringTask...");
			monitoringTask = scheduler.scheduleWithFixedDelay(
					new MonitoringTask(), interval, interval, TimeUnit.MILLISECONDS);
			isStarted = true;
		}
	}

	@ManagedOperation
	public void stop() {
		logger.debug("Stopping monitoringTask...");
		monitoringTask.cancel(true);
		monitoringTask = null;
		isStarted = false;
	}

	@ManagedOperation
	public void shutDown() {
		logger.debug("Shutting down...");
		scheduler.shutdownNow();
	}

	@ManagedAttribute
	public Integer getInterval() {
		return interval;
	}

	@ManagedAttribute(defaultValue = "5")
	public void setInterval(Integer interval) {
		this.interval = interval;
	}

	@ManagedAttribute
	public Double getThroughput() {
		return statistics.getThroughput();
	}
	
	@ManagedAttribute
	public Integer getInflightExchanges() {
		return inFlightExchanges;
	}
	
	@ManagedAttribute
	public Long getQueueSize() {
		return queueSize;
	}
	
	@ManagedAttribute
	public Long getQueueSizeChange() {
		return queueSizeChange;
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

	public void setNotificationPublisher(
			NotificationPublisher notificationPublisher) {
		this.notificationPublisher = notificationPublisher;
	}

	public void setController(Controller<Double, Double> controller) {
		this.controller = controller;
	}

	public void setQueueLengthSensor(QueueLengthSensor queueLengthSensor) {
		this.queueLengthSensor = queueLengthSensor;
	}
	
	public void setJmxSensor(JmxSensor jmxSensor) {
		this.jmxSensor = jmxSensor;
	}
}
