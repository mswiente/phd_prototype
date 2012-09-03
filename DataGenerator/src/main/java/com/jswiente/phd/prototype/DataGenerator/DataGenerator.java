package com.jswiente.phd.prototype.DataGenerator;

import java.util.Date;

import java.io.IOException;

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
import org.springframework.stereotype.Component;

import com.jswiente.phd.prototype.domain.Record;

@Component
public class DataGenerator {

	private Generator<? extends Record> generator;
	private Writer<Record> writer;
	private WriterFactory writerFactory;

	private static WriterFactory.Type writerType = null;
	private static String outFile = null;
	private static int noOfRecords = 0;
	private static int partSize = 0;

	private final static String TYPE_OPTION = "type";
	private final static String FILENAME_OPTION = "fileName";
	private final static String PARTSIZE_OPTION = "partSize";
	private final static String RECORDNO_OPTION = "recordNo";
	private final static String HELP_OPTION = "help";

	private final static String FILE_EXTENSION = "cdr";

	private static final Logger logger = LoggerFactory
			.getLogger(DataGenerator.class);

	private void run() throws IOException {

		Date start = new Date();
		logger.info("Generating usage events...");

		writer.open();
		try {
			for (int i = 0; i < noOfRecords; i++) {

				Record record = generator.generate(new Long(i + 1));
				writer.writeRecord(record);
			}
		} finally {
			writer.close();
		}

		long duration = new Date().getTime() - start.getTime();
		logger.info("finished processing of " + noOfRecords + " records in "
				+ duration / 60 + " sec.");
	}

	private void init() {
		
		//configure writer
		writer = writerFactory.createWriter(writerType);

		switch (writerType) {
		case FILE: {
			((FileWriter) writer).setOutFile(outFile);
			((FileWriter) writer).setPartSize(partSize);
		}
		default:
		case JMS:
			// nothing to do yet...
		}

		generator.init();
	}

	private void parseCmdLineArgs(String[] args) {
		CommandLineParser parser = new GnuParser();
		try {
			// parse the command line arguments
			Options options = getOptions();
			CommandLine line = parser.parse(options, args);

			if (line.hasOption(HELP_OPTION)) {
				HelpFormatter formatter = new HelpFormatter();
				formatter.printHelp("DataGenerator", options);
				System.exit(0);
				return;
			}

			if (line.hasOption(TYPE_OPTION)) {
				writerType = WriterFactory.Type.valueOf(line
						.getOptionValue(TYPE_OPTION));
			} else {
				writerType = WriterFactory.Type.FILE;
			}

			if (line.hasOption(FILENAME_OPTION)) {
				outFile = line.getOptionValue(FILENAME_OPTION);
			}

			if (line.hasOption(RECORDNO_OPTION)) {
				noOfRecords = Integer.valueOf(line
						.getOptionValue(RECORDNO_OPTION));
			}

			if (line.hasOption(PARTSIZE_OPTION)) {
				partSize = Integer
						.valueOf(line.getOptionValue(PARTSIZE_OPTION));
			} else {
				partSize = noOfRecords;
			}
		} catch (ParseException exp) {
			logger.error("Parsing failed.  Reason: " + exp.getMessage(), exp);
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

		Options options = new Options();
		options.addOption(help);
		options.addOption(type);
		options.addOption(fileName);
		options.addOption(partitionSize);
		options.addOption(noOfRecords);

		return options;
	}

	@Required
	public void setGenerator(Generator<? extends Record> generator) {
		this.generator = generator;
	}

	@Required
	public void setWriterFactory(WriterFactory writerFactory) {
		this.writerFactory = writerFactory;
	}

	public static void main(String[] args) throws IOException {

		ApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] { "spring-config.xml", "spring-jpa.xml" });

		DataGenerator dataGenerator = context.getBean(DataGenerator.class);
		dataGenerator.parseCmdLineArgs(args);
		dataGenerator.init();
		dataGenerator.run();

		((ClassPathXmlApplicationContext) context).close();
	}
}
