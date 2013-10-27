package com.jswiente.phd.prototype.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jswiente.phd.prototype.domain.Costedevent;

@Repository
@Transactional
public class CostedeventDAO {

	private static final Logger logger = LoggerFactory.getLogger(CostedeventDAO.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(Costedevent transientInstance) {
		try {
			entityManager.persist(transientInstance);
		} catch (RuntimeException re) {
			logger.error("persist failed", re);
			throw re;
		}
	}
	
	public void persistAll(List<? extends Costedevent> costedEvents) {
		for (Costedevent event : costedEvents) {
			persist(event);
		}
	}

	public void remove(Costedevent persistentInstance) {
		logger.debug("removing Costedevent instance");
		try {
			entityManager.remove(persistentInstance);
			logger.debug("remove successful");
		} catch (RuntimeException re) {
			logger.error("remove failed", re);
			throw re;
		}
	}

	public Costedevent merge(Costedevent detachedInstance) {
		logger.debug("merging Costedevent instance");
		try {
			Costedevent result = entityManager.merge(detachedInstance);
			logger.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			logger.error("merge failed", re);
			throw re;
		}
	}

	public Costedevent findById(long id) {
		logger.debug("getting Costedevent instance with id: " + id);
		try {
			Costedevent instance = entityManager.find(Costedevent.class, id);
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
