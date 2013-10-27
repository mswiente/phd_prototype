package com.jswiente.phd.prototype.DataGenerator;

import java.util.Date;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.GnuParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Component;

import com.jswiente.phd.prototype.domain.RawUsageEvent;
import com.jswiente.phd.prototype.utils.LogUtils;

@Component
@ManagedResource
public class DataGenerator {
	
	private Generator<RawUsageEvent> generator;
	private Writer<RawUsageEvent> writer;
	private WriterFactory writerFactory;

	private Configuration config;

	private final static String TYPE_OPTION = "type";
	private final static String FILENAME_OPTION = "fileName";
	private final static String PARTSIZE_OPTION = "partSize";
	private final static String RECORDNO_OPTION = "recordNo";
	private final static String CONTINUOUS_OPTION = "continuous";
	private final static String INTERVAL_OPTION = "interval";
	private final static String THROUGHPUT_OPTION = "throughput";
	private final static String HELP_OPTION = "help";
	

	private static final Logger logger = LoggerFactory
			.getLogger(DataGenerator.class);

	private void run() throws Exception {

		Date start = new Date();
		logger.info("Generating usage events...");

		writer.open();
		try {
			if (config.isContinuous()) {
				int i = 1;
				while (true) {
					RawUsageEvent record = generator.generate(new Long(i++));
					LogUtils.logEvent(LogUtils.Event.RECORD_GEN, record);
					writer.writeRecord(record);
					Thread.sleep(1000);
				}
			}
			else {
				for (int i = 0; i < config.getNoOfRecords(); i++) {
					
					RawUsageEvent record = generator.generate(new Long(i + 1));
					LogUtils.logEvent(LogUtils.Event.RECORD_GEN, record);
					writer.writeRecord(record);
				}
			}
		} finally {
			writer.close();
		}

		long duration = new Date().getTime() - start.getTime();
		logger.info("finished processing of " + config.getNoOfRecords() + " records in "
				+ duration / 60 + " sec.");
	}

	private void init(String[] args) throws Exception {
		
		config = parseCmdLineArgs(args);
		
		//configure writer
		writer = writerFactory.createWriter(config);
		generator.init();
	}

	private Configuration parseCmdLineArgs(String[] args) throws Exception {
		Configuration configuration = new Configuration();
		CommandLineParser parser = new GnuParser();
		try {
			// parse the command line arguments
			Options options = getOptions();
			CommandLine line = parser.parse(options, args);

			if (line.hasOption(HELP_OPTION)) {
				HelpFormatter formatter = new HelpFormatter();
				formatter.printHelp("DataGenerator", options);
				System.exit(0);
				return null;
			}

			if (line.hasOption(TYPE_OPTION)) {
				configuration.setWriterType(WriterFactory.Type.valueOf(line
						.getOptionValue(TYPE_OPTION)));
			}

			if (line.hasOption(FILENAME_OPTION)) {
				configuration.setOutFileName(line.getOptionValue(FILENAME_OPTION));
			}

			if (line.hasOption(RECORDNO_OPTION)) {
				configuration.setNoOfRecords(Integer.valueOf(line
						.getOptionValue(RECORDNO_OPTION)));
			}

			if (line.hasOption(PARTSIZE_OPTION)) {
				configuration.setPartSize(Integer
						.valueOf(line.getOptionValue(PARTSIZE_OPTION)));
			} 
			
			if (line.hasOption(CONTINUOUS_OPTION)) {
				configuration.setContinuous(true);
			}
			
			if (line.hasOption(THROUGHPUT_OPTION)) {
				configuration.setThroughput(Integer.valueOf(line.getOptionValue(THROUGHPUT_OPTION)));
			}
			
			if (line.hasOption(INTERVAL_OPTION)) {
				configuration.setInterval(Long.valueOf(line.getOptionValue(INTERVAL_OPTION)));
			}
			
			return configuration;
			
			
		} catch (ParseException exp) {
			logger.error("Parsing failed.  Reason: " + exp.getMessage(), exp);
			throw new Exception(exp);
		}
	}

	@SuppressWarnings("static-access")
	private static Options getOptions() {

		Option help = new Option(HELP_OPTION, "Print this help message");

		Option type = OptionBuilder.withArgName("type").hasArg()
				.withDescription("Output type [FILE|JMS]").create(TYPE_OPTION);

		Option fileName = OptionBuilder.withArgName("name").hasArg()
				.withDescription("Name of the output file")
				.create(FILENAME_OPTION);

		Option partitionSize = OptionBuilder.withArgName("size").hasArg()
				.withDescription("Number of records in a partition")
				.create(PARTSIZE_OPTION);
		Option noOfRecords = OptionBuilder.withArgName("number").hasArg()
				.withDescription("Number of records").create(RECORDNO_OPTION);
		
		Option isContinuous = OptionBuilder.withDescription("Continuous mode").create(CONTINUOUS_OPTION);
		
		Option throughput = OptionBuilder.withArgName("number").withDescription("Throughput per second").create(THROUGHPUT_OPTION);
		
		Option interval = OptionBuilder.withArgName("milliseconds").withDescription("Interval (milliseconds)").create(INTERVAL_OPTION);

		Options options = new Options();
		options.addOption(help);
		options.addOption(type);
		options.addOption(fileName);
		options.addOption(partitionSize);
		options.addOption(noOfRecords);
		options.addOption(isContinuous);
		options.addOption(throughput);
		options.addOption(interval);

		return options;
	}

	@Required
	public void setGenerator(Generator<RawUsageEvent> generator) {
		this.generator = generator;
	}

	@Required
	public void setWriterFactory(WriterFactory writerFactory) {
		this.writerFactory = writerFactory;
	}

	public static void main(String[] args) throws Exception {

		ApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] { "spring-config.xml", "spring-jpa.xml" });

		DataGenerator dataGenerator = context.getBean(DataGenerator.class);
		dataGenerator.init(args);
		dataGenerator.run();

		((ClassPathXmlApplicationContext) context).close();
	}
	
	
}
