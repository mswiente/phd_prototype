package com.jswiente.phd.prototype.BillingRouter;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.api.management.ManagedAttribute;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.jswiente.phd.performance.actuator.Actuator;

@Component
public class AggregateSizeActuator implements Processor, Actuator<Double> {

	@Value("${camel.aggregator.completionSize}")
	private long aggregateSize;
	
	@Value("${camel.aggregator.completionSizeHeader}")
	private String completionSizeHeader;
	
	private static final Logger logger = LoggerFactory
			.getLogger(AggregateSizeActuator.class);
	
	@Override
	public void process(Exchange exchange) throws Exception {
		exchange.getIn().setHeader(completionSizeHeader, aggregateSize);
	}

	@ManagedAttribute
	public long getAggregateSize() {
		return aggregateSize;
	}

	@ManagedAttribute
	public void setAggregateSize(long aggregateSize) {
		logger.debug("Setting aggregateSize to: " + aggregateSize);
		this.aggregateSize = aggregateSize;
	}

	@Override
	public void setValue(Double value) {
		logger.debug("Actuator: Setting aggregateSize to: " + value);
		long aggregateSize = Math.round(value);
		this.setAggregateSize(aggregateSize);
	}

}
