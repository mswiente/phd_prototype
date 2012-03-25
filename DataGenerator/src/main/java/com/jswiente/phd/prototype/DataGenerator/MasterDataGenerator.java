package com.jswiente.phd.prototype.DataGenerator;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.jswiente.phd.prototype.persistence.CustomerproducttariffDAO;
import com.jswiente.phd.prototype.persistence.EventsourceDAO;
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
	@Autowired
	private CustomerproducttariffDAO customerProductTariffDao;
	@Autowired
	private EventsourceDAO eventSourceDAO;
	
	private long customerProductId = 1;
	private long customerProductTariffId = 1;
	private long eventSourceId = 0;
	
	private Map<Long, Tariff> productTariffsMap = new HashMap<Long, Tariff>();   
	
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
		
		//building productsTariffsMap
		int i = 0;
		for (Product product : products) {
			productTariffsMap.put(product.getProductId(), tariffs.get(i++));
		}
			
		List<Account> accounts = generateAccounts(customers);
		accountDAO.persistAll(accounts);
		generateCustomerProducts(accounts, products, tariffs);
		
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
	
	private void generateCustomerProducts(List<Account> accounts, List<Product> allProducts, List<Tariff> allTariffs) {
		Date startDate = DataUtils.getFirstDayOfMonth().toDate();
		for (Account account : accounts) {
			int productQty = DataUtils.getRandomInteger(10);
			List<Product> products = DataUtils.getRandomElements(allProducts, productQty);
			int productSeq = 1;
			for (Product product : products) {
				Customerproduct customerProduct = new Customerproduct();
				customerProduct.setCustomerProductId(customerProductId++);
				customerProduct.setAccountNum(account.getAccountNum());
				customerProduct.setCustomer(account.getCustomer());
				customerProduct.setProduct(product);
				customerProduct.setProductSeq(productSeq++);
				customerProduct.setEndDate(null);
				customerProduct.setStartDate(startDate);
				
				Customerproducttariff customerProductTariff = new Customerproducttariff();
				customerProductTariff.setCustomerProductTariffId(customerProductTariffId++);
				customerProductTariff.setCustomerproduct(customerProduct);
				customerProductTariff.setTariff(productTariffsMap.get(product.getProductId()));
				customerProductTariff.setStartDate(startDate);
				customerProductTariff.setEndDate(null);
				
				Eventsource eventSourceVoice = new Eventsource();
				eventSourceVoice.setEventSourceId(eventSourceId++);
				eventSourceVoice.setCustomerproduct(customerProduct);
				eventSourceVoice.setEventType(EventType.VOICE);
				eventSourceVoice.setEventSource(generateEventSource(account.getCustomer().getPhone(), productSeq));
				
				Eventsource eventSourceData = new Eventsource();
				eventSourceData.setEventSourceId(eventSourceId++);
				eventSourceData.setCustomerproduct(customerProduct);
				eventSourceData.setEventType(EventType.DATA);
				eventSourceData.setEventSource(generateEventSource(account.getCustomer().getPhone(), productSeq));
				
				customerProductDAO.persist(customerProduct);
				customerProductTariffDao.persist(customerProductTariff);
				eventSourceDAO.persist(eventSourceVoice);
				eventSourceDAO.persist(eventSourceData);
			}
		}
	}
	
	private static String generateEventSource(String input, int productSeq) {
		return "+49" + input.replaceAll("/", "").replaceAll(" ", "").substring(1) + productSeq;
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
