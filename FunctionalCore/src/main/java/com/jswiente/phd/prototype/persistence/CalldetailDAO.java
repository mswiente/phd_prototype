package com.jswiente.phd.prototype.persistence;

// Generated 20.03.2012 22:03:30 by Hibernate Tools 3.4.0.CR1

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jswiente.phd.prototype.domain.Calldetail;

/**
 * Home object for domain model class Calldetail.
 * @see com.jswiente.phd.prototype.persistence.Calldetail
 * @author Hibernate Tools
 */
@Stateless
public class CalldetailDAO {

	private static final Logger logger = LoggerFactory.getLogger(CalldetailDAO.class);

	@PersistenceContext(type=PersistenceContextType.EXTENDED)
	private EntityManager entityManager;

	public void persist(Calldetail transientInstance) {
		logger.debug("persisting Calldetail instance");
		try {
			entityManager.persist(transientInstance);
			logger.debug("persist successful");
		} catch (RuntimeException re) {
			logger.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Calldetail persistentInstance) {
		logger.debug("removing Calldetail instance");
		try {
			entityManager.remove(persistentInstance);
			logger.debug("remove successful");
		} catch (RuntimeException re) {
			logger.error("remove failed", re);
			throw re;
		}
	}

	public Calldetail merge(Calldetail detachedInstance) {
		logger.debug("merging Calldetail instance");
		try {
			Calldetail result = entityManager.merge(detachedInstance);
			logger.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			logger.error("merge failed", re);
			throw re;
		}
	}

	public Calldetail findById(long id) {
		logger.debug("getting Calldetail instance with id: " + id);
		try {
			Calldetail instance = entityManager.find(Calldetail.class, id);
			logger.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			logger.error("get failed", re);
			throw re;
		}
	}
}
