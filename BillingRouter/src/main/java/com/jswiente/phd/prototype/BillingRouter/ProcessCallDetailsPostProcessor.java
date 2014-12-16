package com.jswiente.phd.prototype.BillingRouter;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.cxf.message.MessageContentsList;

import com.jswiente.phd.prototype.domain.Costedevents;

public class ProcessCallDetailsPostProcessor implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {
		MessageContentsList msgList = (MessageContentsList)exchange.getIn().getBody();
		Costedevents costedEvents = (Costedevents) msgList.get(0);
		exchange.getIn().setBody(costedEvents);
	}

}
