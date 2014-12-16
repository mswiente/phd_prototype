package com.jswiente.phd.prototype.BillingRouter;

import java.util.ArrayList;
import java.util.List;

import org.apache.camel.Exchange;
import org.apache.camel.processor.aggregate.AggregationStrategy;

import com.jswiente.phd.prototype.camelutils.MessageHeaders;
import com.jswiente.phd.prototype.domain.RawUsageEvent;
import com.jswiente.phd.prototype.domain.RawUsageEvents;

public class UsageEventsAggrationStrategy implements AggregationStrategy {

	@Override
	public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
		if (oldExchange == null) {
			RawUsageEvent rawUsageEvent = newExchange.getIn().getBody(RawUsageEvent.class);
			RawUsageEvents rawUsageEvents = new RawUsageEvents();
			List<RawUsageEvent> usageEventList = new ArrayList<RawUsageEvent>();
			rawUsageEvents.setUsageEvents(usageEventList);
			usageEventList.add(rawUsageEvent);
			newExchange.getIn().setBody(rawUsageEvents);
			increaseAggregateSize(newExchange);
			
			Long startTime = getStartTime(newExchange);
			addStartTime(newExchange, startTime);
			
			return newExchange;
		}
		else {
			RawUsageEvents rawUsageEvents = oldExchange.getIn().getBody(RawUsageEvents.class);
			RawUsageEvent rawUsageEvent = newExchange.getIn().getBody(RawUsageEvent.class);
			rawUsageEvents.getUsageEvents().add(rawUsageEvent);
			increaseAggregateSize(oldExchange);
			
			Long startTime = getStartTime(newExchange);
			addStartTime(oldExchange, startTime);
			
			return oldExchange;
		}
	}
	
	private void increaseAggregateSize(Exchange exchange) {
		Integer oldSize = exchange.getIn().getHeader("aggregateSize", Integer.class);
		exchange.getIn().setHeader("aggregateSize", (oldSize == null) ? 1 : ++oldSize);
	}
	
	private Long getStartTime(Exchange exchange) {
		return exchange.getIn().getHeader(MessageHeaders.START.getValue(), Long.class);
	}
	
	private void addStartTime(Exchange exchange, Long startTime) {
		@SuppressWarnings("unchecked")
		List<String> startTimes = exchange.getIn().getHeader("startTimes", List.class);
		if (startTimes == null) {
			startTimes = new ArrayList<String>();
		}
		
		startTimes.add(startTime.toString());
		exchange.getIn().setHeader("startTimes", startTimes);
	}

}
