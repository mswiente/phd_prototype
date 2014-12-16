package com.jswiente.phd.performance.monitor.statistics;

public class PerformanceStatistics {
	private Double throughput;
	private Double latencyMin;
	private Double latencyMax;
	private Double latenyPercentage95;
	private Double latencyMean;

	public PerformanceStatistics() {

	}

	public PerformanceStatistics(Double throughput, Double latencyMin,
			Double latencyMax, Double latenyPercentage95, Double latencyMean) {
		this.throughput = throughput;
		this.latencyMin = latencyMin;
		this.latencyMax = latencyMax;
		this.latenyPercentage95 = latenyPercentage95;
		this.latencyMean = latencyMean;
	}

	public Double getThroughput() {
		return throughput;
	}

	public void setThroughput(Double throughput) {
		this.throughput = throughput;
	}

	public Double getLatencyMin() {
		return latencyMin;
	}

	public void setLatencyMin(Double latencyMin) {
		this.latencyMin = latencyMin;
	}

	public Double getLatencyMax() {
		return latencyMax;
	}

	public void setLatencyMax(Double latencyMax) {
		this.latencyMax = latencyMax;
	}

	public Double getLatenyPercentage95() {
		return latenyPercentage95;
	}

	public void setLatenyPercentage95(Double latenyPercentage95) {
		this.latenyPercentage95 = latenyPercentage95;
	}

	public Double getLatencyMean() {
		return latencyMean;
	}

	public void setLatencyMean(Double latencyMean) {
		this.latencyMean = latencyMean;
	}
}
