package com.jswiente.phd.prototype.DataGenerator;

import java.io.IOException;

import com.jswiente.phd.prototype.domain.Record;

public interface Writer<T extends Record> {
	public void writeRecord(T record) throws IOException;
	public void open();
	public void close() throws IOException;
}
