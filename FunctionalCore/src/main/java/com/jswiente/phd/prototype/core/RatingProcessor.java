package com.jswiente.phd.prototype.core;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.jswiente.phd.prototype.domain.Account;
import com.jswiente.phd.prototype.domain.Costedevent;
import com.jswiente.phd.prototype.domain.Customerproduct;
import com.jswiente.phd.prototype.domain.Customerproducttariff;
import com.jswiente.phd.prototype.domain.Eventsource;
import com.jswiente.phd.prototype.domain.SimpleCDR;
import com.jswiente.phd.prototype.domain.Tariff;
import com.jswiente.phd.prototype.persistence.AccountDAO;
import com.jswiente.phd.prototype.persistence.EventsourceDAO;

public class RatingProcessor implements DataProcessor<SimpleCDR, Costedevent> {
	
	private static final Logger logger = LoggerFactory.getLogger(RatingProcessor.class);
	
	@Autowired
	private EventsourceDAO eventSourceDAO;
	
	@Autowired
	private AccountDAO accountDAO;

	@Override
	public Costedevent process(SimpleCDR callDetailRecord) {
		
		logger.debug("processing callDetailRecord with id: " + callDetailRecord.getRecordId());
		Costedevent output = new Costedevent();
		
		Eventsource eventSource = eventSourceDAO.getEventSource(callDetailRecord.getEventSource(), callDetailRecord.getEventType());
		if (eventSource == null) {
			logger.error("Eventsource not found with " +
					"eventSource: " + callDetailRecord.getEventSource() + 
					", eventType: " + callDetailRecord.getEventType());
		}
		
		Customerproduct customerProduct = eventSource.getCustomerproduct();
		if (customerProduct == null) {
			logger.error("Customerproduct not found");
		}
		
		Account account = accountDAO.getAccount(customerProduct.getAccountNum());
		if (account == null) {
			logger.error("Account not found");
		}
		output.setAccount(account);
		
		Customerproducttariff customerProductTariff = getValidCustomerProductTariff(customerProduct);
		if (customerProductTariff == null) {
			logger.error("no valid CustomerProductTariff found");
		}
		
		Tariff tariff = customerProductTariff.getTariff();
		BigDecimal eventPrice = calculatePrice(callDetailRecord, tariff);
		
		output.setCharge(eventPrice);
		
		return output;
	}
	
	private Customerproducttariff getValidCustomerProductTariff(Customerproduct customerProduct) {
		Set<Customerproducttariff> customerProductTariffs = customerProduct.getCustomerproducttariffs();
		Date now = new Date();
		for (Customerproducttariff customerProductTariff : customerProductTariffs) {
			Date startDate = customerProductTariff.getStartDate();
			Date endDate = customerProductTariff.getEndDate();
			if (startDate.getTime() <= now.getTime() && now.getTime() <= endDate.getTime()) {
				return customerProductTariff;
			}
		}
		return null;
	}
	
	private BigDecimal calculatePrice(SimpleCDR callDetailRecord, Tariff tariff) {
		//TODO add implementation...
		return new BigDecimal(10);
	}

}
