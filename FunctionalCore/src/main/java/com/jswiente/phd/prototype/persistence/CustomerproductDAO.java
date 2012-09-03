package com.jswiente.phd.prototype.persistence;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jswiente.phd.prototype.domain.Customerproduct;

@Repository
@Transactional
public class CustomerproductDAO {

	private static final Logger logger = LoggerFactory.getLogger(CustomerproductDAO.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(Customerproduct transientInstance) {
		logger.debug("persisting Customerproduct instance");
		try {
			entityManager.persist(transientInstance);
			logger.debug("persist successful");
		} catch (RuntimeException re) {
			logger.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Customerproduct persistentInstance) {
		logger.debug("removing Customerproduct instance");
		try {
			entityManager.remove(persistentInstance);
			logger.debug("remove successful");
		} catch (RuntimeException re) {
			logger.error("remove failed", re);
			throw re;
		}
	}

	public Customerproduct merge(Customerproduct detachedInstance) {
		logger.debug("merging Customerproduct instance");
		try {
			Customerproduct result = entityManager.merge(detachedInstance);
			logger.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			logger.error("merge failed", re);
			throw re;
		}
	}

	public Customerproduct findById(long id) {
		logger.debug("getting Customerproduct instance with id: " + id);
		try {
			Customerproduct instance = entityManager.find(
					Customerproduct.class, id);
			logger.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			logger.error("get failed", re);
			throw re;
		}
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
}
