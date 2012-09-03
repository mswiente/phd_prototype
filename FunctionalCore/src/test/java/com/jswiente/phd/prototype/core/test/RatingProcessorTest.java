package com.jswiente.phd.prototype.core.test;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.jswiente.phd.prototype.core.ProcessingException;
import com.jswiente.phd.prototype.core.RatingProcessor;
import com.jswiente.phd.prototype.domain.Account;
import com.jswiente.phd.prototype.domain.Costedevent;
import com.jswiente.phd.prototype.domain.EventType;
import com.jswiente.phd.prototype.domain.SimpleCDR;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-jpa.xml")
public class RatingProcessorTest {
	
	@Autowired
	private RatingProcessor ratingProcessor;

	@Test
	@Transactional
	public void testProcess() throws Exception {
		SimpleCDR simpleCDR = getSimpleCDR();
		Costedevent costedEvent = ratingProcessor.process(simpleCDR);
		
		assertNotNull(costedEvent);
		
		Account account = costedEvent.getAccount();
		assertNotNull(account);
		assertEquals(account.getAccountNum(), 1000000);
		
		BigDecimal charge = costedEvent.getCharge();
		assertNotNull(charge);
		assertEquals(charge, new BigDecimal("19.50"));
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
