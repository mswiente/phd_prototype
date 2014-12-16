package com.jswiente.phd.prototype.BillingRouter;

import java.util.ArrayList;
import java.util.List;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import com.jswiente.phd.prototype.domain.RawUsageEvent;
import com.jswiente.phd.prototype.domain.RawUsageEvents;

public class MediationRequestProcessor implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {
		RawUsageEvent rawUsageEvent = (RawUsageEvent) exchange.getIn().getBody(RawUsageEvent.class);
		RawUsageEvents rawUsageEvents = new RawUsageEvents();
		List<RawUsageEvent> usageEventList = new ArrayList<RawUsageEvent>();
		rawUsageEvents.setUsageEvents(usageEventList);
		usageEventList.add(rawUsageEvent);
		exchange.getOut().setBody(rawUsageEvents);
		exchange.getOut().setHeader("aggregateSize", 1);
	}

}
