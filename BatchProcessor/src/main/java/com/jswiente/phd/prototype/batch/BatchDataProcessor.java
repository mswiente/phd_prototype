package com.jswiente.phd.prototype.batch;

import org.springframework.batch.item.ItemProcessor;

import com.jswiente.phd.prototype.core.DataProcessor;
import com.jswiente.phd.prototype.domain.Record;

public class BatchDataProcessor<T extends Record, S extends Record> implements ItemProcessor<T, S> {
	
	private DataProcessor<T,S> dataProcessor;

	public S process(T item) throws Exception {
		
		return dataProcessor.process(item);
	}
	
	public void setDataProcessor(DataProcessor<T,S> dataProcessor) {
		this.dataProcessor = dataProcessor;
	}

}
