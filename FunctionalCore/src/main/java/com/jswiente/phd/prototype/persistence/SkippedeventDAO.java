package com.jswiente.phd.prototype.persistence;

// Generated 20.03.2012 22:03:30 by Hibernate Tools 3.4.0.CR1

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jswiente.phd.prototype.domain.Skippedevent;

/**
 * Home object for domain model class Skippedevent.
 * @see com.jswiente.phd.prototype.persistence.Skippedevent
 * @author Hibernate Tools
 */
@Stateless
public class SkippedeventDAO {

	private static final Logger logger = LoggerFactory.getLogger(SkippedeventDAO.class);

	@PersistenceContext(type=PersistenceContextType.EXTENDED)
	private EntityManager entityManager;

	public void persist(Skippedevent transientInstance) {
		logger.debug("persisting Skippedevent instance");
		try {
			entityManager.persist(transientInstance);
			logger.debug("persist successful");
		} catch (RuntimeException re) {
			logger.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Skippedevent persistentInstance) {
		logger.debug("removing Skippedevent instance");
		try {
			entityManager.remove(persistentInstance);
			logger.debug("remove successful");
		} catch (RuntimeException re) {
			logger.error("remove failed", re);
			throw re;
		}
	}

	public Skippedevent merge(Skippedevent detachedInstance) {
		logger.debug("merging Skippedevent instance");
		try {
			Skippedevent result = entityManager.merge(detachedInstance);
			logger.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			logger.error("merge failed", re);
			throw re;
		}
	}

	public Skippedevent findById(long id) {
		logger.debug("getting Skippedevent instance with id: " + id);
		try {
			Skippedevent instance = entityManager.find(Skippedevent.class, id);
			logger.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			logger.error("get failed", re);
			throw re;
		}
	}
}
