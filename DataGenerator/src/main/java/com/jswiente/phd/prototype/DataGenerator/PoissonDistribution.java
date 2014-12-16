package com.jswiente.phd.prototype.DataGenerator;

/*
 * Code taken from http://stackoverflow.com/a/5615564/311134
 */
public class PoissonDistribution implements Distribution {

	public long getInterval(double lambda) {
		return (long)poissonRandomInterarrivalDelay(lambda);
	}
	
	private double poissonRandomInterarrivalDelay(double lambda) {
	    double u = Math.random();
		return -Math.log(1.0-u)/lambda;
	}
}
