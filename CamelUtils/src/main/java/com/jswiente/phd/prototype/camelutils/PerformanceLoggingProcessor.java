package com.jswiente.phd.prototype.camelutils;

import org.apache.camel.AsyncCallback;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.model.ProcessorDefinition;
import org.apache.camel.processor.DelegateAsyncProcessor;

import com.jswiente.phd.prototype.utils.LogUtils;
import com.jswiente.phd.prototype.utils.Stopwatch;

public class PerformanceLoggingProcessor extends DelegateAsyncProcessor {

	private final ProcessorDefinition<?> _node;

	public PerformanceLoggingProcessor(ProcessorDefinition<?> definition,
			Processor target) {
		super(target);
		this._node = definition;
	}

	@Override
	public boolean process(Exchange exchange, final AsyncCallback callback) {
		final Stopwatch stopwatch = Stopwatch.start(this._node.getId(), exchange.getIn().getHeader("recordId", String.class));
		return super.process(exchange, new AsyncCallback() {
			public void done(boolean doneSync) {
				try {
					LogUtils.logElapsedTime(stopwatch.stop());
				} finally {
					callback.done(doneSync);
				}
			}
		});
	}
}
