/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.jswiente.phd.prototype.BillingRouter;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.spring.Main;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.jswiente.phd.prototype.camelutils.LogEndTimeProcessor;
import com.jswiente.phd.prototype.camelutils.LogStartTimeProcessor;
import com.jswiente.phd.prototype.utils.LogUtils.Event;

/**
 * Billing Camel Router
 */
public class BillingRouteBuilder extends RouteBuilder {

	@Autowired
	private CostedEventJpaProcessor costedEventProcessor;
	
	@Autowired
	private CostedEventsJpaProcessor costedEventsProcessor;

	@Value("${camel.aggregator}")
	private boolean useAggregator;

	@Value("${camel.aggregator.completionSize}")
	private int completionSize;
	
	@Value("${camel.aggregator.completionTimeout}")
	private long completionTimeout;

	/**
	 * A main() so we can easily run these routing rules in our IDE
	 */
	public static void main(String... args) throws Exception {
		Main.main(args);
	}

	/**
	 * Billing camel route
	 */
	public void configure() {

		errorHandler(deadLetterChannel("activemq:queue:BILLING.ERRORS"));

		if (useAggregator) {
			from("activemq:queue:BILLING.USAGE_EVENTS").id("JMS")
				.process(new LogStartTimeProcessor(Event.PROC_START)).id("LogTimestamp_PROCESSOR")
				.to("log:com.jswiente.phd?showAll=true&multiline=true").id("LOG")
				.unmarshal("jaxbContext").id("UNMARSHAL_PROCESSOR")
				.aggregate(constant(true), new UsageEventsAggrationStrategy()).completionSize(completionSize).completionTimeout(completionTimeout).parallelProcessing().id("AGGREGATE_PROCESSOR")
				.to("cxf:bean:mediationEndpoint?dataFormat=POJO&defaultOperationName=processEvents").id("MEDIATION_PROCESSOR")
				.to("log:com.jswiente.phd?showAll=true&multiline=true").id("LOG")
				.process(new ProcessEventsPostProcessor()).id("MEDIATION_RESPONSE_PROCESSOR")
				.to("cxf:bean:ratingEndpoint?dataFormat=POJO&defaultOperationName=processCallDetails").id("RATING_PROCESSOR")
				.process(new ProcessCallDetailsPostProcessor()).id("RATING_RESPONSE_PROCESSOR")
				.process(costedEventsProcessor).id("JPA_PROCESSOR")
				.process(new LogEndTimeProcessor(Event.PROC_END, useAggregator)).id("LogTimeStamp_PROCESSOR");

		} else {
			from("activemq:queue:BILLING.USAGE_EVENTS").id("JMS")
				.process(new LogStartTimeProcessor(Event.PROC_START)).id("LogTimestamp_PROCESSOR")
				.to("log:com.jswiente.phd?showAll=true&multiline=true").id("LOG")
				.unmarshal("jaxbContext").id("UNMARSHAL_PROCESSOR")
				.to("cxf:bean:mediationEndpoint?dataFormat=POJO&defaultOperationName=processEvent").id("MEDIATION_PROCESSOR")
				.to("log:com.jswiente.phd?showAll=true&multiline=true").id("LOG")
				.process(new ProcessEventPostProcessor()).id("MEDIATION_RESPONSE_PROCESSOR")
				.to("cxf:bean:ratingEndpoint?dataFormat=POJO&defaultOperationName=processCallDetail").id("RATING_PROCESSOR")
				.process(new ProcessCallDetailPostProcessor()).id("RATING_RESPONSE_PROCESSOR")
				.process(costedEventProcessor).id("JPA_PROCESSOR")
				.process(new LogEndTimeProcessor(Event.PROC_END, useAggregator)).id("LogTimeStamp_PROCESSOR");
		}

	}
}
