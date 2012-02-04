package com.jswiente.phd.prototype.DataGenerator;

import java.util.Date;

import java.io.FileWriter;
import java.io.IOException;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.GnuParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.lang.StringUtils;

import com.jswiente.phd.prototype.DataGenerator.data.Record;

public class DataGenerator {
	
	private static String outFile = null;
	private static int noOfRecords = 0;
	private static int partSize = 0;
	
	private final static String FILENAME_OPTION = "fileName";
	private final static String PARTSIZE_OPTION = "partSize";
	private final static String RECORDNO_OPTION = "recordNo";
	
	private final static String FILE_EXTENSION = "cdr";
	
	private static void run() throws IOException {
		
		FileWriter out = null; 
		Generator generator = new SimpleCDRGenerator();
		
		Date start = new Date();
		System.out.println("Generating testdata...");
		
		try {
			int partition = 0;
			for (int i = 0; i < noOfRecords; i++) {
				
				if (i % partSize == 0)
				{
					if (out != null) {
						out.close();
					}
					partition++;
					String fileName = outFile + "_" + String.format("%010d", partition) + "." + FILE_EXTENSION;
					out = new FileWriter(fileName);
				}
				
				Record cdr = generator.generate(new Long(i+1));
				out.write(cdr.toString() + "\n");
			}
		} finally {
			if (out != null) { 
				out.close();
			}
		}
		
		long duration = new Date().getTime() - start.getTime();
		System.out.println("finished processing of " + noOfRecords + " records in " + duration/60 + " sec.");
	}
	
	@SuppressWarnings("static-access")
	private static Options initOptions() {
		
		Option help = new Option("help", "Print this help message");
		Option fileName = OptionBuilder.withArgName("name")
									.hasArg()
									.isRequired()
									.withDescription("Name of the output file")
									.create(FILENAME_OPTION);
		
		Option partitionSize = OptionBuilder.withArgName("size")
									.hasArg()
									.withDescription("Number of records in a partition")
									.create(PARTSIZE_OPTION);
		Option noOfRecords = OptionBuilder.withArgName("number")
									.hasArg()
									.isRequired()
									.withDescription("Number of records")
									.create(RECORDNO_OPTION);
		
		
		Options options = new Options();
		options.addOption(help);
		options.addOption(fileName);
		options.addOption(partitionSize);
		options.addOption(noOfRecords);
		
		return options;
	}
	
	public static void main(String[] args) throws IOException {
		
		CommandLineParser parser = new GnuParser();
	    try {
	        // parse the command line arguments
	        CommandLine line = parser.parse( initOptions(), args );
	        
	        if (line.hasOption(FILENAME_OPTION)) {
	        	outFile = line.getOptionValue(FILENAME_OPTION);
	        }
	        
	        if (line.hasOption(RECORDNO_OPTION)) {
	        	noOfRecords = Integer.valueOf(line.getOptionValue(RECORDNO_OPTION));
	        }
	        
	        if (line.hasOption(PARTSIZE_OPTION)) {
	        	partSize = Integer.valueOf(line.getOptionValue(PARTSIZE_OPTION));
	        } else {
	        	partSize = noOfRecords;
	        }
	        
	        run();
	    }
	    catch( ParseException exp ) {
	        System.err.println( "Parsing failed.  Reason: " + exp.getMessage() );
	    }
		
	}
}
