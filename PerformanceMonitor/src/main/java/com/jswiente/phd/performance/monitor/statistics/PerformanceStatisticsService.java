package com.jswiente.phd.performance.monitor.statistics;

import java.util.concurrent.TimeUnit;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

public class PerformanceStatisticsService {

	private static final Logger logger = LoggerFactory
			.getLogger(PerformanceStatisticsService.class);

	private CircularBuffer<Sample> samples;
	private Long sampleCount = 0L;

	public PerformanceStatisticsService(int sampleSize) {
		this.samples = new CircularBuffer<Sample>(sampleSize, Sample.class);
	}

	public PerformanceStatistics calculateStatistics(long startTime,
			long endTime) {

		Sample[] snapshot = this.samples.getSnapshot();
		long currentSampleCount = sampleCount;
		sampleCount = 0L;
		
		PerformanceStatistics statistics = new PerformanceStatistics();

		statistics.setThroughput(getThroughput(startTime, endTime, currentSampleCount));

		DescriptiveStatistics latencies = getLatencies(snapshot);
		statistics.setLatencyMax(latencies.getMax());
		statistics.setLatencyMin(latencies.getMin());
		statistics.setLatencyMean(latencies.getMean());
		statistics.setLatenyPercentage95(latencies.getPercentile(95));

		return statistics;
	}

	private Double getThroughput(long startTime, long endTime, long count) {

		Double throughput = 0.0;

		Long duration = (endTime - startTime);
		Long stateSize = TimeUnit.MILLISECONDS.convert(count, TimeUnit.SECONDS);
		throughput = (stateSize.doubleValue() / duration);
		logger.debug("stateSize: " + stateSize + ", duration: " + duration);

		return throughput;
	}

	private DescriptiveStatistics getLatencies(Sample[] samples) {
		DescriptiveStatistics latencies = new DescriptiveStatistics();
		for (int i = 0; i < samples.length; i++) {
			long duration = samples[i].getEnd() - samples[i].getStart();
			latencies.addValue(duration / 1000.0D);
		}
		return latencies;
	}

	public void addSample(Sample sample) {

		samples.insert(sample);
		sampleCount++;
	}
}
