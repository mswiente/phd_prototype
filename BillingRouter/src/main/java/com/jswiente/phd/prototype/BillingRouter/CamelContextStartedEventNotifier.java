package com.jswiente.phd.prototype.BillingRouter;

import java.util.EventObject;

import org.apache.camel.management.event.CamelContextStartedEvent;
import org.apache.camel.support.EventNotifierSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jswiente.phd.performance.monitor.PerformanceMonitor;

@Component
public class CamelContextStartedEventNotifier extends EventNotifierSupport {

	@Autowired
	private PerformanceMonitor performanceMonitor;

	@Override
	public void notify(EventObject event) throws Exception {
		if (event instanceof CamelContextStartedEvent) {
			//performanceMonitor.start();
		}
	}

	@Override
	public boolean isEnabled(EventObject event) {

		return event instanceof CamelContextStartedEvent;
	}

	@Override
	protected void doStart() throws Exception {
		// noop
	}

	@Override
	protected void doStop() throws Exception {
		// noop
	}

}
