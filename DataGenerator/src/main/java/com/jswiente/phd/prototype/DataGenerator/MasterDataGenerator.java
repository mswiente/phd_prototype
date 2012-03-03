package com.jswiente.phd.prototype.DataGenerator;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


import com.jswiente.phd.prototype.domain.*;
import com.jswiente.phd.prototype.persistence.AccountDAO;
import com.jswiente.phd.prototype.persistence.CustomerDAO;
import com.jswiente.phd.prototype.persistence.CustomerproductDAO;
import com.jswiente.phd.prototype.persistence.ProductDAO;
import com.jswiente.phd.prototype.persistence.TariffDAO;
import com.jswiente.phd.prototype.utils.DataUtils;

@Component
public class MasterDataGenerator {
	
	private static final Logger logger = LoggerFactory.getLogger(MasterDataGenerator.class);
	
	@Autowired
	private CustomerDAO customerDAO;
	@Autowired
	private AccountDAO accountDAO;
	@Autowired
	private ProductDAO productDAO;
	@Autowired
	private TariffDAO tariffDAO;
	@Autowired
	private CustomerproductDAO customerProductDAO;
	
	private long customerProductId = 0;
	
	public MasterDataGenerator() {
	}
	
	@Transactional
	private void generate() {
		logger.debug("getting customer from db...");
		List<Customer> customers = customerDAO.getAllCustomers();
		logger.debug("customers fetched: " + customers.size());
		
		logger.debug("getting products from db...");
		List<Product> products = productDAO.getAllProducts();
		logger.debug("products fetched: " + products.size());
		
		logger.debug("getting tariffs from db...");
		List<Tariff> tariffs = tariffDAO.getAllTariffs();
		logger.debug("tariffs fetched: " + tariffs.size());
			
		List<Account> accounts = generateAccounts(customers);
		List<Customerproduct> customerProducts = generateCustomerProducts(accounts, products, tariffs);
		
		accountDAO.persistAll(accounts);
	}
	
	private List<Account> generateAccounts(List<Customer> customers) {
		logger.debug("generating accounts...");
		List<Account> accounts = new ArrayList<Account>();
		long accountId = 1;
		long accountNum = 1000000;
		for (Customer customer : customers) {
			Account account = new Account();
			account.setAccountId(accountId++);
			account.setAccountNum(accountNum++);
			account.setBillCylce(1L);
			account.setLastBillDate(null);
			account.setNextBillDate(DataUtils.getNextBillDate(new Date()));
			account.setCustomer(customer);
			
			accounts.add(account);
		}
		
		return accounts;
	}
	
	private List<Customerproduct> generateCustomerProducts(List<Account> accounts, List<Product> allProducts, List<Tariff> allTariffs) {
		List<Customerproduct> customerProducts = new ArrayList<Customerproduct>();
		for (Account account : accounts) {
			int productQty = DataUtils.getRandomInteger(10);
			List<Product> products = DataUtils.getRandomElements(allProducts, productQty);
			int productSeq = 1;
			for (Product product : products) {
				Customerproduct customerProduct = new Customerproduct();
				customerProduct.setCustomerProductId(customerProductId);
				customerProduct.setAccountNum(account.getAccountNum());
				customerProduct.setCustomer(account.getCustomer());
				customerProduct.setProduct(product);
				customerProduct.setProductSeq(productSeq++);
				customerProduct.setEndDate(null);
				customerProduct.setStartDate(DataUtils.getFirstDayOfMonth().toDate());
				
				customerProducts.add(customerProduct);
			}
		}
		
		return customerProducts;
	}
	
	public static void main(String[] args) {
		logger.info("Starting generation of master data...");
		
		ApplicationContext context =
		    new ClassPathXmlApplicationContext(new String[] {"spring-config.xml", "spring-jpa.xml"});
		
		MasterDataGenerator generator = context.getBean(MasterDataGenerator.class);
		generator.generate();
		logger.info("Finished generation of master data.");
	}
}
