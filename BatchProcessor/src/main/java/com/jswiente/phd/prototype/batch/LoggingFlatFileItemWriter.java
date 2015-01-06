package com.jswiente.phd.prototype.batch;

import java.util.List;

import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemStream;
import org.springframework.batch.item.ItemStreamException;
import org.springframework.batch.item.ItemWriter;

import com.jswiente.phd.prototype.utils.LogUtils;
import com.jswiente.phd.prototype.utils.Stopwatch;

public class LoggingFlatFileItemWriter<T> implements ItemStream, ItemWriter<T> {
	
	private ItemWriter<T> delegate;
	
	public void write(List<? extends T> items) throws Exception {
		Stopwatch stopwatch = Stopwatch.start(LogUtils.Event.FILE_WRITE.toString(), "-1");
		delegate.write(items);
		LogUtils.logElapsedTime(stopwatch.stop());
	}

	public void open(ExecutionContext executionContext)
			throws ItemStreamException {
		if (this.delegate instanceof ItemStream) {
			Stopwatch stopwatch = Stopwatch.start(LogUtils.Event.FILE_OPEN.toString(), "-1");
			((ItemStream)this.delegate).open(executionContext);
			LogUtils.logElapsedTime(stopwatch.stop());
		}
	}

	public void update(ExecutionContext executionContext)
			throws ItemStreamException {
		if (this.delegate instanceof ItemStream) {
			((ItemStream)this.delegate).update(executionContext);
		}
	}

	public void close() throws ItemStreamException {
		if (this.delegate instanceof ItemStream) {
			Stopwatch stopwatch = Stopwatch.start(LogUtils.Event.FILE_CLOSE.toString(), "-1");
			((ItemStream)this.delegate).close();
			LogUtils.logElapsedTime(stopwatch.stop());
		}
	}

	public void setDelegate(ItemWriter<T> delegate) {
		this.delegate = delegate;
	}

}
