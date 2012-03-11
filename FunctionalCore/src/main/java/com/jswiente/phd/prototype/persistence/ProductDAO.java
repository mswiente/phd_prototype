package com.jswiente.phd.prototype.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jswiente.phd.prototype.domain.Product;

@Repository
@Transactional
public class ProductDAO {

	private static final Logger logger = LoggerFactory.getLogger(ProductDAO.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(Product transientInstance) {
		logger.debug("persisting Product instance");
		try {
			entityManager.persist(transientInstance);
			logger.debug("persist successful");
		} catch (RuntimeException re) {
			logger.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Product persistentInstance) {
		logger.debug("removing Product instance");
		try {
			entityManager.remove(persistentInstance);
			logger.debug("remove successful");
		} catch (RuntimeException re) {
			logger.error("remove failed", re);
			throw re;
		}
	}

	public Product merge(Product detachedInstance) {
		logger.debug("merging Product instance");
		try {
			Product result = entityManager.merge(detachedInstance);
			logger.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			logger.error("merge failed", re);
			throw re;
		}
	}

	public Product findById(long id) {
		logger.debug("getting Product instance with id: " + id);
		try {
			Product instance = entityManager.find(Product.class, id);
			logger.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			logger.error("get failed", re);
			throw re;
		}
	}
	
	public List<Product> getAllProducts() {
		logger.debug("getting all Products");
		try {
			List<Product> products = entityManager.createQuery(
					"select product from Product as product order by product.productId", Product.class)
					.getResultList();
			logger.debug("get successful");
			return products;
		} catch (RuntimeException re) {
			logger.error("get failed", re);
			throw re;
		}
	}
}
