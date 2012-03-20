package com.jswiente.phd.prototype.core;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jswiente.phd.prototype.domain.RawUsageEvent;
import com.jswiente.phd.prototype.domain.SimpleCDR;
import com.jswiente.phd.prototype.domain.SkipReason;

public class MediationProcessor implements DataProcessor<RawUsageEvent, SimpleCDR> {
	
	private static final Logger logger = LoggerFactory.getLogger(MediationProcessor.class);
	private static final long MIN_EVENT_DURATION = 5000;

	@Override
	public SimpleCDR process(RawUsageEvent usageEvent) {
		
		logger.debug("processing usageEvent with id: " + usageEvent.getRecordId());
		SimpleCDR output = new SimpleCDR(usageEvent);
			
		if (usageEvent.isFlatEvent()) {
			logger.debug("skipping flat usageEvent");
			output.setSkip(true);
			output.setSkipReason(SkipReason.FLAT);
			return output;
		}
		
		if (getDuration(usageEvent) < MIN_EVENT_DURATION) {
			logger.debug("skipping invalid usageEvent");
			output.setSkip(true);
			output.setSkipReason(SkipReason.INVALID);
			return output;
		}
		
		return output;
	}
	
	private long getDuration(RawUsageEvent usageEvent) {
		return usageEvent.getEndDate().getTime() - usageEvent.getStartDate().getTime();
	}

}
