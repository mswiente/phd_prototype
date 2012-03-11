package com.jswiente.phd.prototype.core;

import java.util.List;

import com.jswiente.phd.prototype.domain.Record;

public interface DataProcessor<T extends Record, S extends Record> {
	
	public List<S> process(List<T> input);
}
