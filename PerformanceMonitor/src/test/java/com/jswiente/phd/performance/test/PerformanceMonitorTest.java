package com.jswiente.phd.performance.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.jswiente.phd.performance.PerformanceMonitor;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-config-test.xml")
public class PerformanceMonitorTest {
	
	@Autowired
	private MonitoredService monitoredService;
	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws Exception {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] { "/spring-config-test.xml"});
		
		MonitoredService monitoredService = context.getBean(MonitoredService.class);
		monitoredService.start();
		
		PerformanceMonitor monitor = context.getBean(PerformanceMonitor.class);
		monitor.start();
		
		Thread.sleep(Long.MAX_VALUE);
		
		((ClassPathXmlApplicationContext) context).close();
	}
	
	@Test
	public void testPerformanceMonitor() throws Exception {
		monitoredService.start();
		Thread.sleep(Long.MAX_VALUE);
	}
}
