package com.jswiente.phd.performance.statistics;

import java.util.List;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

import com.jswiente.phd.performance.Sample;

public class PerformanceStatistics {

	private List<Sample> samples;
	private DescriptiveStatistics latencyStats;

	public PerformanceStatistics() {

	}

	public PerformanceStatistics(List<Sample> samples) {
		this.samples = samples;
		calculateLatencies();
	}

	public Double getThroughput() {
		
		Double throughput = 0.0;

		if (samples != null && !samples.isEmpty()) {

			Integer size = samples.size();
			Sample startSample = samples.get(0);
			Sample endSample = samples.get(size - 1);

			Long startTime = startSample.getStart();
			Long endTime = endSample.getEnd();

			Long duration = (endTime - startTime) / 1000;

			throughput = (double) (size / duration);
		}

		return throughput;
	}

	public Double getLatencyMin() {
		return latencyStats.getMin();
	}

	public Double getLatencyMax() {
		return latencyStats.getMax();
	}

	public Double getLatenyPercentage95() {
		return latencyStats.getPercentile(95);
	}

	public Double getLatencyMean() {
		return latencyStats.getMean();
	}

	public void setSamples(List<Sample> samples) {
		reset();
		this.samples = samples;
		calculateLatencies();
	}

	public void reset() {
		if (samples != null) samples.clear();
		if (latencyStats != null) latencyStats.clear();
	}

	private void calculateLatencies() {
		latencyStats = new DescriptiveStatistics();
		for (Sample sample : samples) {
			long duration = sample.getEnd() - sample.getStart();
			latencyStats.addValue(duration/1000.0D);
		}
	}
}
