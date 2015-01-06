package com.jswiente.phd.performance.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.jswiente.phd.feedbackcontrol.monitor.statistics.CircularBuffer;

public class CircularBufferTest {
	
	@Test
	public void testInsert() {
		CircularBuffer<Long> circularBuffer = new CircularBuffer<Long>(5, Long.class);
		for (int i = 0; i < 5; i++) {
			circularBuffer.insert(System.currentTimeMillis());
		}
		
		assertTrue(circularBuffer.size() == 5);
		
		circularBuffer.insert(System.currentTimeMillis());
		circularBuffer.insert(System.currentTimeMillis());
		
		assertTrue(circularBuffer.size() == 5);
		
	}
	
	@Test
	public void testGetFirst() {
		CircularBuffer<Integer> circularBuffer = new CircularBuffer<Integer>(5, Integer.class);
		for (int i = 0; i < 5; i++) {
			circularBuffer.insert(i);
		}
		
		assertTrue(circularBuffer.getFirst() == 0);
		assertTrue(circularBuffer.getLast() == 4);
		
		circularBuffer.insert(5);
		
		assertTrue(circularBuffer.getFirst() == 1);
		assertTrue(circularBuffer.getLast() == 5);
	}
	
	@Test
	public void testGetSnapshot() throws InterruptedException {
		CircularBuffer<Integer> circularBuffer = new CircularBuffer<Integer>(5, Integer.class);
		for (int i = 0; i < 9; i++) {
			circularBuffer.insert(i);
			Thread.sleep(100);
		}
		
		Object[] snapshot = circularBuffer.getSnapshot();

		assertNotNull(snapshot);
		assertTrue(snapshot.length == 5);
		
		for (int i = 0; i < snapshot.length; i++) {
			System.out.println(snapshot[i]);
		}
	}

}
