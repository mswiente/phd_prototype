package com.jswiente.phd.prototype.persistence.test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jswiente.phd.prototype.domain.Account;
import com.jswiente.phd.prototype.persistence.AccountDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-jpa.xml")
public class AccountDAOTest {
	@Autowired
	private AccountDAO accountDAO;
	
	@Test
	public void testFindById() {
		Account account = accountDAO.findById(1L);
		assertNotNull(account);
	}
}
