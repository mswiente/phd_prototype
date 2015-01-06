package com.jswiente.phd.prototype.persistence.test;

import java.math.BigDecimal;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.jswiente.phd.prototype.domain.Account;
import com.jswiente.phd.prototype.domain.Costedevent;
import com.jswiente.phd.prototype.domain.EventType;
import com.jswiente.phd.prototype.persistence.AccountDAO;
import com.jswiente.phd.prototype.persistence.CostedeventDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-jpa.xml")
public class CostedeventDAOTest {
	
	@Autowired
	private CostedeventDAO costedEventDao;
	
	@Autowired
	private AccountDAO accountDAO;
	
	@Test
	@Transactional
	public void testPersistCostedEvent() {
		
		Costedevent costedEvent = getCostedEvent();
		costedEventDao.persist(costedEvent);
	}
	
	private Costedevent getCostedEvent() {
		Costedevent costedEvent = new Costedevent();
		costedEvent.setRecordId(1L);
		costedEvent.setBillCycle(1);
		costedEvent.setCallingParty("123456");
		costedEvent.setCalledParty("01703097135");
		costedEvent.setStartDate(new Date());
		costedEvent.setEventType(EventType.DATA);
		costedEvent.setCharge(new BigDecimal("1.99"));
		
		Account account = accountDAO.findById(1L);
		costedEvent.setAccount(account);
		
		return costedEvent;
	}
}
