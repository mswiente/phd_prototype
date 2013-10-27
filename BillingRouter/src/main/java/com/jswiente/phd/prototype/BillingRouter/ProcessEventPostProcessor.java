package com.jswiente.phd.prototype.BillingRouter;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.cxf.message.MessageContentsList;

import com.jswiente.phd.prototype.domain.SimpleCDR;

public class ProcessEventPostProcessor implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {
		MessageContentsList msgList = (MessageContentsList)exchange.getIn().getBody();
		SimpleCDR simpleCDR = (SimpleCDR) msgList.get(0);
		exchange.getOut().setBody(simpleCDR);
		exchange.getOut().setHeader("recordId", exchange.getIn().getHeader("recordId", String.class));
	}

}
