/*
 * 
 */
package org.barclays.bfg.mappingfiles.codequality.cli;


import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.TreeMap;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.barclays.bfg.mappingfiles.codequality.processor.MapFile;
import org.barclays.bfg.mappingfiles.codequality.processor.MappingFileParser;
import org.barclays.bfg.mappingfiles.codequality.processor.MappingFileSection.SectionType;
import org.barclays.bfg.mappingfiles.codequality.processor.RuleValidator;
import org.barclays.bfg.mappingfiles.codequality.report.Report;
import org.barclays.bfg.mappingfiles.codequality.report.ReportPublisher;


/**
 * The Class Driver.
 */
public class Driver {

	private static final Logger LOGGER = Logger.getLogger(Driver.class);

	/** The Constant CONFIG_PARAM. */
	private static final String CONFIG_PARAM = "f";

	/** The Constant MAPPING_PARAM. */
	private static final String MAPPING_PARAM = "m";


	/**
	 * The main method.
	 *
	 * @param args
	 *            the arguments
	 */
	public static void main(String[] args) {
		Driver cli = new Driver();
		PrintStream target = System.err;

		cli.processArgs(args, target);
	}

	/**
	 * Process args.
	 *
	 * @param args
	 *            the args
	 * @param target
	 *            the target
	 */
	void processArgs(String[] args, PrintStream target) {
		ReportPublisher repoPublish = new ReportPublisher();
		
		try {
			ApplicationConfiguration.loadApplicationConfiguration();

			CLIConfig cliConfig = parse(args);

			RuleValidator objRuleValidator = new RuleValidator();

			MapFile parsedFile = MappingFileParser.parse(cliConfig.getMapingFile());

			ArrayList<Report> reportlist = objRuleValidator.execute(parsedFile);

			repoPublish.generateReport(reportlist);

		} catch (FileNotFoundException e) {
			LOGGER.error("File not found", e);
		} catch (Exception e) {
			LOGGER.error("Exception in main ", e);
			HelpFormatter formatter = new HelpFormatter();
			PrintWriter pw = new PrintWriter(target);
			formatter.printHelp(pw, formatter.getWidth(), "Parser", "", buildOptions(), formatter.getLeftPadding(),
					formatter.getDescPadding(), "", true);
			pw.flush();
		}
	}

	/**
	 * Parses the.
	 *
	 * @param args
	 *            the args
	 * @return the CLI config
	 * @throws ParseException
	 *             the parse exception
	 */
	CLIConfig parse(String[] args) throws ParseException {
		CommandLineParser parser = new DefaultParser();
		CommandLine cmd = parser.parse(buildOptions(), args);
		return deriveConfig(cmd);

	}

	/**
	 * Derive config.
	 *
	 * @param cmd
	 *            the cmd
	 * @return the CLI config
	 * @throws ParseException
	 *             the parse exception
	 */
	private CLIConfig deriveConfig(CommandLine cmd) throws ParseException {

		String conf = cmd.getOptionValue(CONFIG_PARAM);
		String mappingValue = cmd.getOptionValue(MAPPING_PARAM);

		if (StringUtils.isEmpty(conf)) {
			throw new ParseException("No configuration file supplied!");
		}

		if (StringUtils.isEmpty(mappingValue)) {
			throw new ParseException("No configuration file supplied!");
		}

		String outputPath = cmd.getOptionValue(CONFIG_PARAM);
		return new CLIConfig(outputPath, mappingValue);
	}

	/**
	 * Builds the options
	 * 
	 * @return the options
	 */
	Options buildOptions() {
		Options options = new Options();
		options.addOption(CONFIG_PARAM, true, "The config file");
		options.addOption(MAPPING_PARAM, true, "The mapping file");
		options.getOption(CONFIG_PARAM).setRequired(true);
		return options;
	}

}
