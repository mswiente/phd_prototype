package com.jswiente.phd.prototype.core;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jswiente.phd.prototype.domain.Account;
import com.jswiente.phd.prototype.domain.Costedevent;
import com.jswiente.phd.prototype.domain.Customerproduct;
import com.jswiente.phd.prototype.domain.Customerproducttariff;
import com.jswiente.phd.prototype.domain.Eventsource;
import com.jswiente.phd.prototype.domain.SimpleCDR;
import com.jswiente.phd.prototype.domain.Tariff;
import com.jswiente.phd.prototype.persistence.AccountDAO;
import com.jswiente.phd.prototype.persistence.EventsourceDAO;
import com.jswiente.phd.prototype.utils.DataUtils;
import com.jswiente.phd.prototype.utils.LogUtils;
import com.jswiente.phd.prototype.utils.Stopwatch;

@Service
public class RatingProcessor implements DataProcessor<SimpleCDR, Costedevent> {
	
	private static final Logger logger = LoggerFactory.getLogger(RatingProcessor.class);
	
	@Autowired
	private EventsourceDAO eventSourceDAO;
	
	@Autowired
	private AccountDAO accountDAO;

	@Transactional
	public Costedevent process(SimpleCDR callDetailRecord) throws ProcessingException, Exception {
		
		Stopwatch stopwatch = Stopwatch.start(LogUtils.Event.RATING_START.toString(), callDetailRecord.getRecordId());
		Costedevent output = new Costedevent();
		
		output.setRecordId(callDetailRecord.getRecordId());
		output.setSequenceNum(callDetailRecord.getSequenceNum());
		output.setStartDate(callDetailRecord.getStartDate());
		output.setEndDate(callDetailRecord.getEndDate());
		output.setEventType(callDetailRecord.getEventType());
		output.setCallingParty(callDetailRecord.getCallingParty());
		output.setCalledParty(callDetailRecord.getCalledParty());
		
		Eventsource eventSource = eventSourceDAO.getEventSource(callDetailRecord.getEventSource(), callDetailRecord.getEventType());
		if (eventSource == null) {
			logger.error("Eventsource not found with " +
					"eventSource: " + callDetailRecord.getEventSource() + 
					", eventType: " + callDetailRecord.getEventType());
		}
		
		Customerproduct customerProduct = eventSource.getCustomerproduct();
		if (customerProduct == null) {
			logger.error("Customerproduct not found");
			throw new ProcessingException("Customerproduct not found");
		}
		
		Account account = accountDAO.getAccount(customerProduct.getAccountNum());
		if (account == null) {
			logger.error("Account not found");
			throw new ProcessingException("Account not found");
		}
		output.setAccount(account);
		
		Customerproducttariff customerProductTariff = getValidCustomerProductTariff(customerProduct);
		if (customerProductTariff == null) {
			logger.error("no valid CustomerProductTariff found");
			throw new ProcessingException("no valid CustomerProductTariff found");
		}
		
		Tariff tariff = customerProductTariff.getTariff();
		BigDecimal eventPrice = calculatePrice(callDetailRecord, tariff);
		
		output.setCharge(eventPrice);
		
		//Simulating some more processing time...
		Thread.sleep(10);
		
		LogUtils.logElapsedTime(stopwatch.stop());
		
		return output;
	}
	
	private Customerproducttariff getValidCustomerProductTariff(Customerproduct customerProduct) {
		Set<Customerproducttariff> customerProductTariffs = customerProduct.getCustomerproducttariffs();
		Date now = new Date();
		for (Customerproducttariff customerProductTariff : customerProductTariffs) {
			Date startDate = customerProductTariff.getStartDate();
			Date endDate = customerProductTariff.getEndDate();
			if (endDate == null) {
				endDate = new DateTime(9999, 12, 31, 23, 59).toDate();
			}
			if (startDate.getTime() <= now.getTime() && now.getTime() <= endDate.getTime()) {
				return customerProductTariff;
			}
		}
		return null;
	}
	
	private BigDecimal calculatePrice(SimpleCDR callDetailRecord, Tariff tariff) {
		
		BigDecimal usageCharges = tariff.getUsageCharges();
		Integer eventDuration = ((Double) Math.floor(DataUtils.getEventDuration(callDetailRecord)/3600)).intValue();
		
		BigDecimal price = usageCharges.multiply(new BigDecimal(eventDuration));
		
		return price;
	}

	public void setEventSourceDAO(EventsourceDAO eventSourceDAO) {
		this.eventSourceDAO = eventSourceDAO;
	}

	public void setAccountDAO(AccountDAO accountDAO) {
		this.accountDAO = accountDAO;
	}

}
