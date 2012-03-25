package com.jswiente.phd.prototype.core;

import org.springframework.stereotype.Service;

import com.jswiente.phd.prototype.domain.Record;

@Service
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
