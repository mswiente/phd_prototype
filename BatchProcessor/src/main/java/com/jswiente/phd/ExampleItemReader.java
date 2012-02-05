package com.jswiente.phd;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.stereotype.Component;

/**
 * {@link ItemReader} with hard-coded input data.
 */

@Component("reader")
public class ExampleItemReader implements ItemReader<String> {
	
	private static final Log log = LogFactory.getLog(ExampleItemReader.class);
	
	private String[] input = {"Hello world!", null};
	
	private int index = 0;
	
	/**
	 * Reads next record from input
	 */
	public String read() throws Exception {
		log.info("itemreader called");
		if (index < input.length) {
			return input[index++];
		}
		else {
			return null;
		}
		
	}

}
