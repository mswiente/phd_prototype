package com.jswiente.phd.performance.monitor.statistics;

public class Sample {
	private Long start;
	private Long end;
	
	public Sample() {
		
	}
	
	public Sample(Long start, Long end) {
		this.start = start;
		this.end = end;
	}
	
	public Long getStart() {
		return start;
	}
	
	public void setStart(Long start) {
		this.start = start;
	}
	
	public Long getEnd() {
		return end;
	}
	
	public void setEnd(Long end) {
		this.end = end;
	}
}
