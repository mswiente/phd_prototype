package com.jswiente.phd.prototype.core.test;

import static org.junit.Assert.*;

import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jswiente.phd.prototype.core.RatingProcessor;
import com.jswiente.phd.prototype.domain.Costedevent;
import com.jswiente.phd.prototype.domain.EventType;
import com.jswiente.phd.prototype.domain.SimpleCDR;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-jpa.xml")
public class RatingProcessorTest {
	
	@Autowired
	private RatingProcessor ratingProcessor;

	@Test
	public void testProcess() {
		SimpleCDR simpleCDR = getSimpleCDR();
		Costedevent costedEvent = ratingProcessor.process(simpleCDR);
		
		assertNotNull(costedEvent);
	}
	
	private SimpleCDR getSimpleCDR() {
		SimpleCDR simpleCDR = new SimpleCDR();
		
		simpleCDR.setRecordId(1L);
		simpleCDR.setSequenceNum(1L);
		simpleCDR.setEventSource("+496183272499292");
		simpleCDR.setFlatEvent(false);
		simpleCDR.setCalledParty("+4901703097135");
		simpleCDR.setCallingParty("+4901703097135");
		
		DateTime endDate = new DateTime();
		DateTime startDate = endDate.minusSeconds(180);
		
		simpleCDR.setStartDate(startDate.toDate());
		simpleCDR.setEndDate(endDate.toDate());
		simpleCDR.setEventType(EventType.VOICE);
		
		return simpleCDR;
	}

}
