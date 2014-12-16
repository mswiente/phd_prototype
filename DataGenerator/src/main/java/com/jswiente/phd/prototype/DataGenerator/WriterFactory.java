package com.jswiente.phd.prototype.DataGenerator;

import com.jswiente.phd.prototype.domain.RawUsageEvent;

public abstract class WriterFactory {
	
	public static enum Type {
		JMS,
		FILE
	}

	protected abstract JMSWriter createJMSWriter();
	protected abstract FileWriter createFileWriter();

	public Writer<RawUsageEvent> createWriter(Configuration config) {
		Writer<RawUsageEvent> writer;
		switch (config.getWriterType()) {
			case JMS: {
				writer = createJMSWriter();
				break;
			}
			default:
			case FILE: {
				writer = createFileWriter();
			}
		}
		writer.setConfig(config);
		return writer;
	}
}
