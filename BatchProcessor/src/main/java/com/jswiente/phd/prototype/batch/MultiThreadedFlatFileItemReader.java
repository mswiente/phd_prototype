package com.jswiente.phd.prototype.batch;

import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemStream;
import org.springframework.batch.item.ItemStreamException;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

import com.jswiente.phd.prototype.utils.LogUtils;
import com.jswiente.phd.prototype.utils.Stopwatch;

public class MultiThreadedFlatFileItemReader<T> implements ItemReader<T>, ItemStream {
	
	private ItemReader<T> delegate;

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

	public synchronized T read() throws Exception, UnexpectedInputException, ParseException,
			NonTransientResourceException {
		Stopwatch stopwatch = Stopwatch.start(LogUtils.Event.FILE_READ.toString(), "-1");
		T retValue =  delegate.read();
		LogUtils.logElapsedTime(stopwatch.stop());
		return retValue;
	}
	
	public void setDelegate(ItemReader<T> delegate) {
		this.delegate = delegate;
	}
}
