package com.jswiente.phd.prototype.DataGenerator;

import java.io.IOException;

import com.jswiente.phd.prototype.domain.RawUsageEvent;

public class FileWriter implements Writer<RawUsageEvent> {
	
	private Configuration config;
	private java.io.FileWriter out = null;
	private int partition = 0;
	private int recordNo = 0;
	
	private final static String FILE_EXTENSION = "cdr";
	
	public void writeRecord(RawUsageEvent record) throws IOException {
		if (recordNo++ % config.getPartSize() == 0) {
			if (out != null) {
				out.close();
			}
			partition++;
			String fileName = config.getOutFileName() + "_"
					+ String.format("%010d", partition) + "."
					+ FILE_EXTENSION;
			out = new java.io.FileWriter(fileName);
		}
		
		out.write(record.toString() + "\n");
	}

	public void close() throws IOException {
		
		if (out != null) {
			out.close();
		}
	}

	public void open() {
		recordNo = 0;
	}

	public void setConfig(Configuration config) {
		this.config = config;
	}
	
}
