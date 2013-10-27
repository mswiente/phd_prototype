package com.jswiente.phd.prototype.camelutils;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import com.jswiente.phd.prototype.domain.Costedevents;
import com.jswiente.phd.prototype.utils.LogUtils;
import com.jswiente.phd.prototype.utils.LogUtils.Event;

public class LogEndTimeProcessor implements Processor {

	private Event event;
	private boolean isAggregate;

	public LogEndTimeProcessor(Event event, boolean isAggregate) {
		super();
		this.event = event;
		this.isAggregate = isAggregate;
	}

	@Override
	public void process(Exchange exchange) throws Exception {
		if (isAggregate) {
			Costedevents costedEvents = exchange.getIn().getBody(Costedevents.class);
			LogUtils.logEvent(LogUtils.Event.PROC_END, costedEvents.getCostedEvents());
		} else {
			LogUtils.logEvent(event.name(), exchange.getIn().getHeader("recordId", String.class));
		}
	}

}
