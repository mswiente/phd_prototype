package com.jswiente.phd.prototype.BillingRouter;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jswiente.phd.prototype.domain.Costedevent;
import com.jswiente.phd.prototype.persistence.CostedeventDAO;
import com.jswiente.phd.prototype.utils.LogUtils;
import com.jswiente.phd.prototype.utils.Stopwatch;

@Component
public class CostedEventJpaProcessor implements Processor {
	
	
	@Autowired
	private CostedeventDAO costedEventDAO;

	@Override
	public void process(Exchange exchange) throws Exception {
		Costedevent costedEvent = exchange.getIn().getBody(Costedevent.class);
		Stopwatch stopwatch = Stopwatch.start(LogUtils.Event.DB.toString(), costedEvent.getRecordId());
		costedEventDAO.persist(costedEvent);
		LogUtils.logElapsedTime(stopwatch.stop());
	}

}
