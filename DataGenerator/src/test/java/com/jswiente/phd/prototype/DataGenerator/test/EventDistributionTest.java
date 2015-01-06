package com.jswiente.phd.prototype.DataGenerator.test;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jswiente.phd.prototype.DataGenerator.PoissonDistribution;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

public class EventDistributionTest {
	
	private static final Logger logger = LoggerFactory
			.getLogger(EventDistributionTest.class);
	
	@Test
	public void testExponentialDistribution() {
		
		PoissonDistribution expDistribution = new PoissonDistribution();
		double lambda = 100.0;
		
		DescriptiveStatistics stats = new DescriptiveStatistics();
		
		for (int i = 0; i < 1000; i++) {
			Double interval = expDistribution.getInterval(lambda);
			stats.addValue(interval);
			
			//convert to millis
			interval = interval * 1000.0;
			logger.info(i+1 + ";" + interval.longValue() + ";" + lambda);
		}
		
		logger.info("Statistics: " + " Mean: " + stats.getMean());
	}
}
