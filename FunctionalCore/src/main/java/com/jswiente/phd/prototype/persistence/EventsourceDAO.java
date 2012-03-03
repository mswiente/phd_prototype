package com.jswiente.phd.prototype.persistence;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.jswiente.phd.prototype.domain.Eventsource;

@Repository
public class EventsourceDAO {

	private static final Logger logger = LoggerFactory.getLogger(EventsourceDAO.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(Eventsource transientInstance) {
		logger.debug("persisting Eventsource instance");
		try {
			entityManager.persist(transientInstance);
			logger.debug("persist successful");
		} catch (RuntimeException re) {
			logger.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Eventsource persistentInstance) {
		logger.debug("removing Eventsource instance");
		try {
			entityManager.remove(persistentInstance);
			logger.debug("remove successful");
		} catch (RuntimeException re) {
			logger.error("remove failed", re);
			throw re;
		}
	}

	public Eventsource merge(Eventsource detachedInstance) {
		logger.debug("merging Eventsource instance");
		try {
			Eventsource result = entityManager.merge(detachedInstance);
			logger.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			logger.error("merge failed", re);
			throw re;
		}
	}

	public Eventsource findById(long id) {
		logger.debug("getting Eventsource instance with id: " + id);
		try {
			Eventsource instance = entityManager.find(Eventsource.class, id);
			logger.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			logger.error("get failed", re);
			throw re;
		}
	}
}
