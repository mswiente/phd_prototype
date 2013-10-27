package com.jswiente.phd.prototype.camelutils;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import com.jswiente.phd.prototype.utils.LogUtils;
import com.jswiente.phd.prototype.utils.LogUtils.Event;

public class LogStartTimeProcessor implements Processor {

	private Event event;
	
	public LogStartTimeProcessor(Event event) {
		super();
		this.event = event;
	}
	
	@Override
	public void process(Exchange exchange) throws Exception {
		LogUtils.logEvent(event.name(), exchange.getIn().getHeader("recordId", String.class));
	}
}
