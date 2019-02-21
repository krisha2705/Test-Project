/**
 * 
 */
package org.barclays.bfg.mappingfiles.codequality.cli;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.net.URL;

import org.junit.Before;
import org.junit.Test;

/**
 * @author ashatre
 *
 */
public class ApplicationConfigurationTest {

	private ApplicationConfiguration applicationConfiguration;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
//		URL url = this.getClass().getClassLoader().getResource("appconfig.json");
//		String path = url.toURI().getPath();
//		System.out.println(path);
		applicationConfiguration = ApplicationConfiguration.loadApplicationConfiguration();
	}

	/**
	 * Test method for
	 * {@link org.barclays.bfg.mappingfiles.codequality.cli.ApplicationConfiguration#getLogdirectory()}.
	 */
	@Test
	public void testGetLogdirectory() {
		assertNotNull("Log Directory", applicationConfiguration.getLogdirectory());
	}

	/**
	 * Test method for
	 * {@link org.barclays.bfg.mappingfiles.codequality.cli.ApplicationConfiguration#getOutputdirectory()}.
	 */
	@Test
	public void testGetOutputdirectory() {
		assertNotNull("Output Directory", applicationConfiguration.getOutputdirectory());
	}

	/**
	 * Test method for
	 * {@link org.barclays.bfg.mappingfiles.codequality.cli.ApplicationConfiguration#getOutputformat()}.
	 */
	@Test
	public void testGetOutputformat() {
		assertNotNull("Output format", applicationConfiguration.getOutputformat());

	}

	/**
	 * Test method for
	 * {@link org.barclays.bfg.mappingfiles.codequality.cli.ApplicationConfiguration#getReportname()}.
	 */
	@Test
	public void testGetReportname() {
		assertNotNull("Report name", applicationConfiguration.getReportname());
	}

	/**
	 * Test method for
	 * {@link org.barclays.bfg.mappingfiles.codequality.cli.ApplicationConfiguration#getRuleTypeTOViolationType()}.
	 */
	@Test
	public void testGetRuleTypeTOViolationType() {
		assertNotNull("Violation Level", applicationConfiguration.getRuleTypeTOViolationType());
	}

	/**
	 * Test method for
	 * {@link org.barclays.bfg.mappingfiles.codequality.cli.ApplicationConfiguration#loadApplicationConfiguration()}.
	 */
	@Test
	public void testLoadApplicationConfiguration() {
		try {
			assertNotNull(ApplicationConfiguration.loadApplicationConfiguration());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
