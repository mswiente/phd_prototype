package com.jswiente.phd.prototype.BillingRouter;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jswiente.phd.prototype.domain.Costedevents;
import com.jswiente.phd.prototype.persistence.CostedeventDAO;
import com.jswiente.phd.prototype.utils.LogUtils;
import com.jswiente.phd.prototype.utils.Stopwatch;

@Component
public class CostedEventsJpaProcessor implements Processor {
	
	@Autowired
	private CostedeventDAO costedEventDAO;

	@Override
	public void process(Exchange exchange) throws Exception {
		Costedevents costedEvents = exchange.getIn().getBody(Costedevents.class);
		Stopwatch stopwatch = Stopwatch.start(LogUtils.Event.DB.toString(), exchange.getIn().getMessageId());
		costedEventDAO.persistAll(costedEvents.getCostedEvents());
		LogUtils.logElapsedTime(stopwatch.stop());
	}
}
