package com.jswiente.phd.prototype.DataGenerator.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.joda.time.DateMidnight;
import org.joda.time.MutableDateTime;

public class DataUtils {
	
	private static DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mmZ");
	
	/**
	 * Returns a pseudo random date between start and end date
	 * @param start
	 * @param end
	 */
	public static Date getRandomDate(Date start, Date end) {
		long interval = end.getTime() - start.getTime();
		Random r = new Random();
		long randomTime = (long)(r.nextDouble() * interval) + start.getTime();
		return new Date(randomTime);
	}
	
	/**
	 * Returns a pseudo random date string between start and end date
	 * @param start
	 * @param end
	 */
	public static String getRandomDateString(Date start, Date end) {
		return formatDate(getRandomDate(start, end));
	}
	
	/**
	 * Returns a DateMidnight object set to the first day of the current month
	 * @return
	 */
	public static DateMidnight getFirstDayOfMonth() {
		
		MutableDateTime now = new MutableDateTime();
		now.dayOfMonth().set(1);

		return now.toDateTime().toDateMidnight();
	}
	
	/**
	 * Formats the given date as a string
	 * @param date
	 */
	public static String formatDate(Date date) {
		
		return df.format(date);
	}
	
	/**
	 * Returns a pseudo random long <= maxNumber
	 * @param maxNumber
	 */
	public static long getRandomLong(long maxNumber) {
		Random r = new Random();
		return (long)(r.nextDouble() * maxNumber);
	}
}
