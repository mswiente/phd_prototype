package com.jswiente.phd.prototype.BillingRouter;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.cxf.message.MessageContentsList;

import com.jswiente.phd.prototype.domain.Costedevent;

public class ProcessCallDetailPostProcessor implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {
		MessageContentsList msgList = (MessageContentsList)exchange.getIn().getBody();
		Costedevent costedEvent = (Costedevent) msgList.get(0);
		exchange.getOut().setBody(costedEvent);
		exchange.getOut().setHeader("recordId", exchange.getIn().getHeader("recordId", String.class));
	}

}
