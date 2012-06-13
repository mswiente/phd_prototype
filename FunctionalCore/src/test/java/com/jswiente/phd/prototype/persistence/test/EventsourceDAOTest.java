package com.jswiente.phd.prototype.persistence.test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jswiente.phd.prototype.domain.Eventsource;
import com.jswiente.phd.prototype.persistence.EventsourceDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-jpa.xml")
public class EventsourceDAOTest {
	@Autowired
	private EventsourceDAO eventSourceDAO;
	
	@Test
	public void testFindById() {
		Eventsource eventSource = eventSourceDAO.findById(1L);
		assertNotNull(eventSource);
	}
}
