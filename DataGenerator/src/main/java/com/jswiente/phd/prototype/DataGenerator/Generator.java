package com.jswiente.phd.prototype.DataGenerator;

import com.jswiente.phd.prototype.domain.Record;

public interface Generator<T extends Record> {
	
	/**
	 * Generates a single record with the given id
	 * @param id
	 */
	public T generate(Long id);
	
	public void init();

}