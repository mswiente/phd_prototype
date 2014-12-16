package com.jswiente.phd.prototype.batch.rating;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jswiente.phd.prototype.domain.Costedevent;
import com.jswiente.phd.prototype.persistence.CostedeventDAO;
import com.jswiente.phd.prototype.utils.LogUtils;
import com.jswiente.phd.prototype.utils.Stopwatch;

@Component
public class CostedEventWriter implements ItemWriter<Costedevent> {
	
	@Autowired
	private CostedeventDAO costedEventDAO;

	public void write(List<? extends Costedevent> items) throws Exception {
		long timestamp = System.currentTimeMillis();
		Stopwatch stopwatch = Stopwatch.start(LogUtils.Event.DB.toString(), "-1");
		costedEventDAO.persistAll(items);
		LogUtils.logElapsedTime(stopwatch.stop());
		LogUtils.logEvent(LogUtils.Event.PROC_END, items, timestamp);
	}

}
