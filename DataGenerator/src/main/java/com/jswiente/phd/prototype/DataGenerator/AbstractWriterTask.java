package com.jswiente.phd.prototype.DataGenerator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jswiente.phd.prototype.domain.RawUsageEvent;

public abstract class AbstractWriterTask implements Runnable {

	@SuppressWarnings("unused")
	private Writer<RawUsageEvent> writer;
	
	@SuppressWarnings("unused")
	private Generator<RawUsageEvent> generator;
	
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory
	.getLogger(AbstractWriterTask.class);
	
	public AbstractWriterTask(Writer<RawUsageEvent> writer, Generator<RawUsageEvent> generator) {
		this.writer = writer;
		this.generator = generator;
	}
	
	public void setWriter(Writer<RawUsageEvent> writer) {
		this.writer = writer;
	}

	public void setGenerator(Generator<RawUsageEvent> generator) {
		this.generator = generator;
	}

	public void run() {
		
	}

}
