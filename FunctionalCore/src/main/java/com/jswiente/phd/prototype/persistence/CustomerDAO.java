package com.jswiente.phd.prototype.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jswiente.phd.prototype.domain.Customer;

@Repository
@Transactional
public class CustomerDAO {

	private static final Logger logger = LoggerFactory.getLogger(CustomerDAO.class);

	@PersistenceContext(type=PersistenceContextType.EXTENDED)
	private EntityManager entityManager;

	public void persist(Customer transientInstance) {
		logger.debug("persisting Customer instance");
		try {
			entityManager.persist(transientInstance);
			logger.debug("persist successful");
		} catch (RuntimeException re) {
			logger.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Customer persistentInstance) {
		logger.debug("removing Customer instance");
		try {
			entityManager.remove(persistentInstance);
			logger.debug("remove successful");
		} catch (RuntimeException re) {
			logger.error("remove failed", re);
			throw re;
		}
	}

	public Customer merge(Customer detachedInstance) {
		logger.debug("merging Customer instance");
		try {
			Customer result = entityManager.merge(detachedInstance);
			logger.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			logger.error("merge failed", re);
			throw re;
		}
	}

	public Customer findById(long id) {
		logger.debug("getting Customer instance with id: " + id);
		try {
			Customer instance = entityManager.find(Customer.class, id);
			logger.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			logger.error("get failed", re);
			throw re;
		}
	}
	
	public List<Customer> getAllCustomers() {
		logger.debug("getting all Customers");
		try {
			List<Customer> customers = entityManager.createQuery(
					"select customer from Customer as customer", Customer.class)
					.getResultList();
			logger.debug("get successful");
			return customers;
		} catch (RuntimeException re) {
			logger.error("get failed", re);
			throw re;
		}
	}
}
