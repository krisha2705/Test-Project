package org.barclays.bfg.mappingfiles.codequality.cli;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.ParseException;
import org.apache.commons.lang3.StringUtils;
import org.barclays.bfg.mappingfiles.codequality.cli.CLIConfig;
import org.barclays.bfg.mappingfiles.codequality.cli.Driver;
import org.junit.Test;


/**
 * The Class DriverTest.
 */
public class DriverTest {

	/**
	 * Test log help.
	 */
	@Test
	public void testLogHelp() {
		HelpFormatter formatter = new HelpFormatter();
		formatter.printHelp("parser", new Driver().buildOptions());
	}

	/**
	 * Test required args missing.
	 */
	@Test
	public void testRequiredArgsMissing() {
		try {
			generateCommandline("");
			fail("This should have failed, no args were supplied");
		} catch (ParseException expected) {
			assertTrue(expected.getMessage().contains("No configuration file supplied"));
		}
	}

	/**
	 * Tests CSV Output format is read correctly from command line
	 *
	 * @throws ParseException the parse exception
	 * @throws FileNotFoundException 
	 */
	@Test
	public void testArgCsv() throws ParseException, FileNotFoundException {
		CLIConfig config = generateCommandline(" -f config.xml -m config.txt");
		ApplicationConfiguration appConfig = ApplicationConfiguration.loadApplicationConfiguration();

		String format = appConfig.getOutputformat().toString();
//		assertEquals(CLIConfig.Format.CSV,format);
		assertEquals("config.xml", config.getPathToConfig());

	}

	/**
	 * Tests JSON Output format is read correctly from command line
	 *
	 * @throws ParseException the parse exception
	 * @throws FileNotFoundException 
	 */
	
	@Test
	public void testArgJson() throws ParseException, FileNotFoundException {
		CLIConfig config = generateCommandline("-f config.xml -m config.txt");
		ApplicationConfiguration appConfig = ApplicationConfiguration.loadApplicationConfiguration();

		String format = appConfig.getOutputformat().toString();
//		assertEquals(CLIConfig.Format.JSON,format);
		assertEquals("config.xml", config.getPathToConfig());

	}

	/**
	 * Test arg default to CSV.
	 *
	 * @throws ParseException the parse exception
	 * @throws FileNotFoundException 
	 */
	@Test
	public void testArgDefaultToCSV() throws ParseException, FileNotFoundException {
		CLIConfig config = generateCommandline(" -f config.xml -m config.txt");
		ApplicationConfiguration appConfig = ApplicationConfiguration.loadApplicationConfiguration();

		String format = appConfig.getOutputformat().toString();
		assertEquals(CLIConfig.Format.CSV,format);
		assertEquals("config.xml", config.getPathToConfig());

	}


	/*
	 * Tests to assure invalid formats don't work
	 */
	@Test
	public void testArgBadFormat() {
		try {
			generateCommandline(" -o testing -f config.xml");
			fail("This should have failed, testing is not a valid format");
		} catch (ParseException expected) {
			assertTrue(expected.getMessage().contains("testing is not a valid format"));
		}
	}
	

	/**
	 * The Class OutputHelper.
	 */
	private class OutputHelper {
		
		/** The baos. */
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		/** The target. */
		PrintStream target = new PrintStream(baos, true);

		/**
		 * Gets the output as string.
		 *
		 * @return the output as string
		 */
		public String getOutputAsString() {
			return  new String(baos.toByteArray(), StandardCharsets.UTF_8);
		}
	}
	
	/**
	 * Test help output for no args.
	 */
	@Test
	public void testHelpOutputForNoArgs() {
		Driver iface = new Driver();
		OutputHelper helper  = new OutputHelper();
		iface.processArgs(new String[]{},   
				helper.target);

		assertTrue(String.format("Wrong message: %s", helper.getOutputAsString()),
				helper.getOutputAsString().contains("usage: "));

	}

	/**
	 * Generate commandline.
	 *
	 * @param cli the cli
	 * @return the CLI config
	 * @throws ParseException the parse exception
	 */
	private CLIConfig generateCommandline(String cli) throws ParseException {
		String[] args = StringUtils.split(cli, ' ');
		return new Driver().parse(args);
	}
}