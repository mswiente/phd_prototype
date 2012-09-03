package com.jswiente.phd.prototype.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.joda.time.DateMidnight;
import org.joda.time.MutableDateTime;

import com.jswiente.phd.prototype.domain.RawUsageEvent;

public class DataUtils {
	
	private static DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
	
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
	 * Calculates the next bill date
	 * @param lastDate
	 * @return
	 */
	public static Date getNextBillDate(Date lastDate) {
		MutableDateTime nextDate = new MutableDateTime(lastDate);
		nextDate.addDays(30);
		return nextDate.toDateTime().toDateMidnight().toDate();
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
	
	public static int getRandomInteger(int maxInt) {
		return new Random().nextInt(maxInt);
	}
	
	public static boolean getRandomBoolean() {
		return new Random().nextBoolean();
	}
	
	/**
	 * Returns a random element from given list
	 * @param <T>
	 * @param list
	 * @return
	 */
	public static <T> T getRandomElement(List<T> list) {
		return list.get(new Random().nextInt(list.size()));
	}
	
	public static <T> List<T> getRandomElements(List<T> list, int count) {
		
		List<T> result = new ArrayList<T>();
		for (int i = 0; i < count; i++) {
			result.add(getRandomElement(list));
		}
		return result;
	}
	
	public static long getEventDuration(RawUsageEvent usageEvent) {
		return usageEvent.getEndDate().getTime() - usageEvent.getStartDate().getTime();
	}
}
