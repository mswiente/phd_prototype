package com.jswiente.phd.prototype.BillingRouter;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import com.jswiente.phd.prototype.utils.LogUtils;
import com.jswiente.phd.prototype.utils.Stopwatch;

@Aspect
public class LoggingAspect {
	
	@Around("execution(* org.apache.camel.component.jms.EndpointMessageListener.onMessage(..))")
	public Object logOnMessage(ProceedingJoinPoint pjp) throws Throwable {
		Stopwatch stopwatch = new Stopwatch(LogUtils.Event.JMS_CONSUMER.toString(), "-1");
		try {
			stopwatch.start();
			Object retVal = pjp.proceed();
			return retVal;
		} finally {
			LogUtils.logElapsedTime(stopwatch.stop());
		}
	}
	
	@Around("execution(* org.apache.camel.component.jms.EndpointMessageListener.EndpointMessageListenerAsyncCallback.done(..))")
	public Object logOnDone(ProceedingJoinPoint pjp) throws Throwable {
		Stopwatch stopwatch = new Stopwatch(LogUtils.Event.JMS_CONSUMER_DONE.toString(), "-1");
		try {
			stopwatch.start();
			Object retVal = pjp.proceed();
			return retVal;
		} finally {
			LogUtils.logElapsedTime(stopwatch.stop());
		}
	}
	
}
