package com.jswiente.phd.prototype.core;

import com.jswiente.phd.prototype.domain.Record;

public class DummyDataProcessor implements DataProcessor<Record, Record> {

	@Override
	public Record process(Record input) {
		
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		return input;
	}

}
