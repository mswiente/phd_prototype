package com.jswiente.phd.prototype.persistence;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jswiente.phd.prototype.domain.Customerproducttariff;

@Repository
@Transactional
public class CustomerproducttariffDAO {

	private static final Logger logger = LoggerFactory.getLogger(CustomerproducttariffDAO.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(Customerproducttariff transientInstance) {
		logger.debug("persisting Customerproducttariff instance");
		try {
			entityManager.persist(transientInstance);
			logger.debug("persist successful");
		} catch (RuntimeException re) {
			logger.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Customerproducttariff persistentInstance) {
		logger.debug("removing Customerproducttariff instance");
		try {
			entityManager.remove(persistentInstance);
			logger.debug("remove successful");
		} catch (RuntimeException re) {
			logger.error("remove failed", re);
			throw re;
		}
	}

	public Customerproducttariff merge(Customerproducttariff detachedInstance) {
		logger.debug("merging Customerproducttariff instance");
		try {
			Customerproducttariff result = entityManager
					.merge(detachedInstance);
			logger.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			logger.error("merge failed", re);
			throw re;
		}
	}

	public Customerproducttariff findById(long id) {
		logger.debug("getting Customerproducttariff instance with id: " + id);
		try {
			Customerproducttariff instance = entityManager.find(
					Customerproducttariff.class, id);
			logger.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			logger.error("get failed", re);
			throw re;
		}
	}
}
