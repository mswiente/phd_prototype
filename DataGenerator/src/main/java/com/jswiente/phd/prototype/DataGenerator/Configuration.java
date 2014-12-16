package com.jswiente.phd.prototype.DataGenerator;

public class Configuration {
	
	private WriterFactory.Type writerType;
	private String outFileName;
	private Integer partSize;
	private Integer noOfRecords;
	private Double arrivalRate;
	private boolean continuous;
	private boolean startMonitoring;
	private boolean loadTest;
	
	public Configuration() {
		this.partSize = 0;
		this.noOfRecords = 0;
		this.writerType = WriterFactory.Type.FILE;
		this.continuous = false;
		this.startMonitoring = false;
		this.loadTest = false;
	}
	
	public WriterFactory.Type getWriterType() {
		return writerType;
	}
	
	public void setWriterType(WriterFactory.Type writerType) {
		this.writerType = writerType;
	}
	
	public String getOutFileName() {
		return outFileName;
	}
	
	public void setOutFileName(String fileName) {
		this.outFileName = fileName;
	}
	
	public Integer getPartSize() {
		return partSize;
	}
	
	public void setPartSize(Integer partSize) {
		this.partSize = partSize;
	}
	
	public Integer getNoOfRecords() {
		return noOfRecords;
	}
	
	public void setNoOfRecords(Integer recordNo) {
		this.noOfRecords = recordNo;
	}
	
	public Double getArrivalRate() {
		return arrivalRate;
	}
	
	public void setArrivalRate(Double arrivalRate) {
		this.arrivalRate = arrivalRate;
	}

	public boolean isContinuous() {
		return continuous;
	}

	public void setContinuous(boolean continuous) {
		this.continuous = continuous;
	}

	public boolean isStartMonitoring() {
		return startMonitoring;
	}

	public void setStartMonitoring(boolean startMonitoring) {
		this.startMonitoring = startMonitoring;
	}

	public boolean isLoadTest() {
		return loadTest;
	}

	public void setLoadTest(boolean loadTest) {
		this.loadTest = loadTest;
	}
}
