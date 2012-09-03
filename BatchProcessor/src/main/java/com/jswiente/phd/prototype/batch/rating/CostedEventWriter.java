package com.jswiente.phd.prototype.batch.rating;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jswiente.phd.prototype.domain.Costedevent;
import com.jswiente.phd.prototype.persistence.CostedeventDAO;

@Component
public class CostedEventWriter implements ItemWriter<Costedevent> {
	
	private static final Logger logger = LoggerFactory.getLogger(CostedEventWriter.class);
	
	@Autowired
	private CostedeventDAO costedEventDAO;

	@Override
	public void write(List<? extends Costedevent> items) throws Exception {
		
		logger.debug("writing events: " + items.size());
		costedEventDAO.persistAll(items);
	}

}
