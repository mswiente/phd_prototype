package com.jswiente.phd.prototype.core;

import java.util.List;

import com.jswiente.phd.prototype.domain.Record;

public class DummyDataProcessor implements DataProcessor<Record, Record> {

	@Override
	public List<Record> process(List<Record> input) {
		
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		return input;
	}

}
