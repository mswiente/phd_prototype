package com.jswiente.phd.prototype.BillingRouter;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.cxf.message.MessageContentsList;

import com.jswiente.phd.prototype.domain.SimpleCDRs;

public class ProcessEventsPostProcessor implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {
		MessageContentsList msgList = (MessageContentsList)exchange.getIn().getBody();
		SimpleCDRs simpleCDRs = (SimpleCDRs) msgList.get(0);
		exchange.getIn().setBody(simpleCDRs);
	}

}
