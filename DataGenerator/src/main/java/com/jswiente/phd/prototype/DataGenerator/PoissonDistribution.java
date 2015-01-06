package com.jswiente.phd.prototype.DataGenerator;

/*
 * Code taken from http://stackoverflow.com/a/5615564/311134
 */
public class PoissonDistribution implements Distribution {

	public double getInterval(double lambda) {
		return exponentialRandomInterarrivalDelay(lambda);
	}
	
	private double exponentialRandomInterarrivalDelay(double lambda) {
	    double u = Math.random();
		return Math.log(1.0-u)/-lambda;
	}
}
