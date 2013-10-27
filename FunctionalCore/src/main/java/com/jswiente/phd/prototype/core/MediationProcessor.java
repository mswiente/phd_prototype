package com.jswiente.phd.prototype.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.jswiente.phd.prototype.domain.RawUsageEvent;
import com.jswiente.phd.prototype.domain.SimpleCDR;
import com.jswiente.phd.prototype.domain.SkipReason;
import com.jswiente.phd.prototype.utils.DataUtils;
import com.jswiente.phd.prototype.utils.LogUtils;
import com.jswiente.phd.prototype.utils.Stopwatch;

@Service
public class MediationProcessor implements
		DataProcessor<RawUsageEvent, SimpleCDR> {

	private static final Logger logger = LoggerFactory
			.getLogger(MediationProcessor.class);
	private static final long MIN_EVENT_DURATION = 5000;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.jswiente.phd.prototype.core.MediationProcessInterface#process(com
	 * .jswiente.phd.prototype.domain.RawUsageEvent)
	 */
	@Override
	public SimpleCDR process(RawUsageEvent usageEvent) throws Exception {

		Stopwatch stopwatch = Stopwatch.start(LogUtils.Event.MEDIATION_START.toString(), usageEvent.getRecordId());
		SimpleCDR output = new SimpleCDR(usageEvent);

		if (usageEvent.isFlatEvent()) {
			logger.debug("skipping flat usageEvent");
			output.setSkip(true);
			output.setSkipReason(SkipReason.FLAT);
		}

		if (DataUtils.getEventDuration(usageEvent) < MIN_EVENT_DURATION) {
			logger.debug("skipping invalid usageEvent");
			output.setSkip(true);
			output.setSkipReason(SkipReason.INVALID);
		}
		
		//Simulating some more processing time...
		Thread.sleep(10);

		LogUtils.logElapsedTime(stopwatch.stop());

		return output;
	}
}
