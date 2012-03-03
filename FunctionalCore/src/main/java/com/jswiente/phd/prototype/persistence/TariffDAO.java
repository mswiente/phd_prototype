package com.jswiente.phd.prototype.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.jswiente.phd.prototype.domain.Tariff;

@Repository
public class TariffDAO {

	private static final Logger logger = LoggerFactory.getLogger(TariffDAO.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(Tariff transientInstance) {
		logger.debug("persisting Tariff instance");
		try {
			entityManager.persist(transientInstance);
			logger.debug("persist successful");
		} catch (RuntimeException re) {
			logger.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Tariff persistentInstance) {
		logger.debug("removing Tariff instance");
		try {
			entityManager.remove(persistentInstance);
			logger.debug("remove successful");
		} catch (RuntimeException re) {
			logger.error("remove failed", re);
			throw re;
		}
	}

	public Tariff merge(Tariff detachedInstance) {
		logger.debug("merging Tariff instance");
		try {
			Tariff result = entityManager.merge(detachedInstance);
			logger.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			logger.error("merge failed", re);
			throw re;
		}
	}

	public Tariff findById(long id) {
		logger.debug("getting Tariff instance with id: " + id);
		try {
			Tariff instance = entityManager.find(Tariff.class, id);
			logger.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			logger.error("get failed", re);
			throw re;
		}
	}
	
	public List<Tariff> getAllTariffs() {
		logger.debug("Getting all Tariffs");
		try {
			List<Tariff> tariffs = entityManager.createQuery(
					"select tariff from Tariff as tariff", Tariff.class)
					.getResultList();
			logger.debug("get successful");
			return tariffs;
		} catch (RuntimeException re) {
			logger.error("get failed", re);
			throw re;
		}
	}
}
