package com.jswiente.phd.prototype.core.test;

import static org.junit.Assert.*;

import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jswiente.phd.prototype.core.MediationProcessor;
import com.jswiente.phd.prototype.domain.EventType;
import com.jswiente.phd.prototype.domain.RawUsageEvent;
import com.jswiente.phd.prototype.domain.SimpleCDR;
import com.jswiente.phd.prototype.domain.SkipReason;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-jpa.xml")
public class MediationProcessorTest {
	
	@Autowired
	private MediationProcessor mediationProcessor;
	
	@Test
	public void testProcess() {
		
		RawUsageEvent usageEvent = getUsageEvent();
		SimpleCDR simpleCDR = mediationProcessor.process(usageEvent);
		
		assertNotNull(simpleCDR);
		assertFalse(simpleCDR.isSkip());
	}
	
	@Test
	public void testProcessFlatEvent() {
		RawUsageEvent usageEvent = getUsageEvent();
		usageEvent.setFlatEvent(true);
		SimpleCDR simpleCDR = mediationProcessor.process(usageEvent);
		
		assertNotNull(simpleCDR);
		assertTrue(simpleCDR.isSkip());
		assertEquals(simpleCDR.getSkipReason(), SkipReason.FLAT);
	}
	
	@Test
	public void testProcessInvalidEvent() {
		RawUsageEvent usageEvent = getUsageEvent();
		DateTime startDate = new DateTime(usageEvent.getEndDate());
		startDate = startDate.minusSeconds(1);
		usageEvent.setStartDate(startDate.toDate());
		SimpleCDR simpleCDR = mediationProcessor.process(usageEvent);
		
		assertNotNull(simpleCDR);
		assertTrue(simpleCDR.isSkip());
		assertEquals(simpleCDR.getSkipReason(), SkipReason.INVALID);
	}
	
	private RawUsageEvent getUsageEvent() {
		RawUsageEvent usageEvent = new RawUsageEvent();
		
		usageEvent.setRecordId(1L);
		usageEvent.setSequenceNum(1L);
		usageEvent.setFlatEvent(false);
		usageEvent.setCalledParty("+4901703097135");
		usageEvent.setCallingParty("+4901703097135");
		
		DateTime endDate = new DateTime();
		DateTime startDate = endDate.minusSeconds(180);
		
		usageEvent.setStartDate(startDate.toDate());
		usageEvent.setEndDate(endDate.toDate());
		usageEvent.setEventType(EventType.VOICE);
		
		return usageEvent;
	}
}
