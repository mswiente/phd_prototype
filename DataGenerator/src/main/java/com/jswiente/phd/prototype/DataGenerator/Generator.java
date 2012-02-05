package com.jswiente.phd.prototype.DataGenerator;

import com.jswiente.phd.prototype.domain.Record;

public interface Generator {
	
	/**
	 * Generates a single record with the given id
	 * @param id
	 */
	public abstract Record generate(Long id);

}