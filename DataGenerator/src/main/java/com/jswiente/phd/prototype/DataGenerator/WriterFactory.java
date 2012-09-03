package com.jswiente.phd.prototype.DataGenerator;

import com.jswiente.phd.prototype.domain.Record;

public abstract class WriterFactory {
	
	public static enum Type {
		JMS,
		FILE
	}

	protected abstract JMSWriter createJMSWriter();
	protected abstract FileWriter createFileWriter();

	public Writer<Record> createWriter(Type type) {
		switch (type) {
		case JMS:
			return createJMSWriter();
		default:
		case FILE:
			return createFileWriter();
		}
	}
}
