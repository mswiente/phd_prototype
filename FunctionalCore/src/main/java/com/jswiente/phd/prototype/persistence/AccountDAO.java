package com.jswiente.phd.prototype.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jswiente.phd.prototype.domain.Account;

@Repository
@Transactional
public class AccountDAO {

	private static final Logger logger = LoggerFactory.getLogger(AccountDAO.class);

	@PersistenceContext(type=PersistenceContextType.EXTENDED)
	private EntityManager entityManager;

	public void persist(Account transientInstance) {
		logger.debug("persisting Account instance");
		try {
			entityManager.persist(transientInstance);
			logger.debug("persist successful");
		} catch (RuntimeException re) {
			logger.error("persist failed", re);
			throw re;
		}
	}
	
	public void persistAll(List<Account> accounts) {
		for (Account account : accounts) {
			persist(account);
		}
	}

	public void remove(Account persistentInstance) {
		logger.debug("removing Account instance");
		try {
			entityManager.remove(persistentInstance);
			logger.debug("remove successful");
		} catch (RuntimeException re) {
			logger.error("remove failed", re);
			throw re;
		}
	}

	public Account merge(Account detachedInstance) {
		logger.debug("merging Account instance");
		try {
			Account result = entityManager.merge(detachedInstance);
			logger.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			logger.error("merge failed", re);
			throw re;
		}
	}

	public Account findById(long id) {
		logger.debug("getting Account instance with id: " + id);
		try {
			Account instance = entityManager.find(Account.class, id);
			logger.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			logger.error("get failed", re);
			throw re;
		}
	}
	
	public Account getAccount(Long accountNum) {
		logger.debug("getting Account with accountNum: " + accountNum);
		try {
			Account account = entityManager.createQuery(
					"select account from Account as account where account.accountNum = ?1", Account.class)
					.setParameter(1, accountNum)
					.getSingleResult();
			
			return account;
		} catch (RuntimeException re) {
			logger.error("get failed", re);
			throw re;
		}
	}
}
