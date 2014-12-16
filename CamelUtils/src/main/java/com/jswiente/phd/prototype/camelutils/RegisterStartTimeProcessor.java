package com.jswiente.phd.prototype.camelutils;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

import com.jswiente.phd.prototype.utils.LogUtils;
import com.jswiente.phd.prototype.utils.LogUtils.Event;

@Component
public class RegisterStartTimeProcessor implements Processor {

	private Event event;
	
	public RegisterStartTimeProcessor() {
		super();
		this.event = Event.PROC_START;
	}
	
	public void process(Exchange exchange) throws Exception {
		Long timestamp = System.currentTimeMillis();
		
		String recordId = exchange.getIn().getHeader("recordId", String.class);
		exchange.getIn().setHeader(MessageHeaders.START.getValue(), timestamp);
		
		LogUtils.logEvent(event.name(), recordId, timestamp);
	}
}
