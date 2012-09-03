package com.jswiente.phd.prototype.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.jswiente.phd.prototype.domain.BillRecord;
import com.jswiente.phd.prototype.domain.Costedevent;

@Service
public class BillingProcessor implements DataProcessor<Costedevent, BillRecord> {
	
	private static final Logger logger = LoggerFactory.getLogger(BillingProcessor.class);

	@Override
	public BillRecord process(Costedevent costedEvent) {
		// TODO Auto-generated method stub
		return null;
	}
}
