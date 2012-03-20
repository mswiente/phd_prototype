package com.jswiente.phd.prototype.core;

import com.jswiente.phd.prototype.domain.Record;

public interface DataProcessor<T extends Record, S extends Record> {
	
	public S process(T input);
}
