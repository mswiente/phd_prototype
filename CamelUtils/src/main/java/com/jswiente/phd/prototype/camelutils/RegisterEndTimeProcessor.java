package com.jswiente.phd.prototype.camelutils;

import java.util.List;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jswiente.phd.prototype.domain.Costedevent;
import com.jswiente.phd.prototype.domain.Costedevents;
import com.jswiente.phd.prototype.utils.LogUtils;
import com.jswiente.phd.prototype.utils.LogUtils.Event;
import com.jswiente.phd.performance.monitor.PerformanceMonitor;
import com.jswiente.phd.performance.monitor.statistics.Sample;
import com.jswiente.phd.performance.sensor.ThroughputSensor;

@Component
public class RegisterEndTimeProcessor implements Processor {

	private Event event;
	private boolean isAggregate = false;
	
//	@Autowired
//	private ThroughputSensor throughputSensor;
	
	@Autowired
	private PerformanceMonitor performanceMonitor;

	public RegisterEndTimeProcessor() {
		super();
		this.event = Event.PROC_END;
	}

	public void process(Exchange exchange) throws Exception {
		Long timestamp = System.currentTimeMillis();
		
		if (isAggregate) {
			Costedevents costedEvents = exchange.getIn().getBody(Costedevents.class);
			for (Costedevent costedEvent : costedEvents.getCostedEvents()) {
				String recordId = Long.toString(costedEvent.getId());
//				Sample sample = throughputSensor.registerEnd(recordId, timestamp);
//				performanceMonitor.addSample(sample);
			}
			
			@SuppressWarnings("unchecked")
			List<String> startTimes = exchange.getIn().getHeader("startTimes", List.class);
			for (String startTime : startTimes) {
				Sample sample = new Sample(Long.valueOf(startTime), timestamp);
				performanceMonitor.addSample(sample);
			}
			
			LogUtils.logEvent(LogUtils.Event.PROC_END, costedEvents.getCostedEvents(), timestamp);
		} else {
			String recordId = exchange.getIn().getHeader("recordId", String.class);
			Long startTime = exchange.getIn().getHeader(MessageHeaders.START.getValue(), Long.class);
			Sample sample = new Sample(startTime, timestamp);
			performanceMonitor.addSample(sample);
			LogUtils.logEvent(event.name(), recordId, timestamp);
		}
	}
	
	public void setAggregate(boolean isAggregate) {
		this.isAggregate = isAggregate;
	}

}
