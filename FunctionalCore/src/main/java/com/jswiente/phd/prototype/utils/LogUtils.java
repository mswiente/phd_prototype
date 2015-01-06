package com.jswiente.phd.prototype.utils;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jswiente.phd.prototype.domain.Record;

public class LogUtils {
	
	private static final Logger logger = LoggerFactory.getLogger("perf");
	
	public enum Event {
		RECORD_GEN,
		PROC_START,
		PROC_END,
		DB_START,
		DB_END,
		DB,
		FTP_START,
		FTP_END,
		FTP,
		MEDIATION_START,
		MEDIATION_END,
		MEDIATION,
		RATING_START,
		RATING_END,
		RATING,
		FILE_READ,
		FILE_WRITE,
		FILE_OPEN,
		FILE_CLOSE,
		JMS_CONSUMER,
		JMS_CONSUMER_DONE
	}
	
	public enum Type {
		TIMESTAMP,
		TIMER
	}
	
	public static void logEvent(String event, String id, long timestamp) {
		logEvent(event, id, timestamp, Type.TIMESTAMP);
	}
	
	public static void logEvent(Event event, Long id, long timestamp) {
		logEvent(event.toString(), Long.toString(id), timestamp);
	}
	
	@Deprecated
	public static void logEvent(Event event, long timestamp) {
		logEvent(event.toString(), "-1", timestamp);
	}
	
	public static void logEvent(Event event, List<? extends Record> records, long timestamp) {
		for (Record record : records) {
			logEvent(event.name(), Long.toString(record.getId()), timestamp, Type.TIMESTAMP);
		}
	}
	
	public static <T extends Record> void logEvent(Event event, T record) {
		long timestamp = System.currentTimeMillis();
		logEvent(event.name(), Long.toString(record.getId()), timestamp, Type.TIMESTAMP);
	}
	
	public static void logElapsedTime(Stopwatch stopwatch) {
		logEvent(stopwatch.getName(), stopwatch.getId(), stopwatch.elapsedMillis(), Type.TIMER);
	}
	
	private static void logEvent(String event, String id, long timestamp, Type type) {
		logger.info(id + ";" + type + ";" + event + ";" + timestamp);
	}
	
}
