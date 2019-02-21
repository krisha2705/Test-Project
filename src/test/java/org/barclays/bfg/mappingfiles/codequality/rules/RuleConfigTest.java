/**
 * 
 */
package org.barclays.bfg.mappingfiles.codequality.rules;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

/**
 * @author ashatre
 *
 */
public class RuleConfigTest {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		RuleConfig.loadRuleConfigs();
	}

	/**
	 * Test method for {@link org.barclays.bfg.mappingfiles.codequality.rules.RuleConfig#getRuleConfig()}.
	 */
	@Test
	public void testGetRuleConfig() {
		List<RuleConfig> list = RuleConfig.getRuleConfigs();
		assertTrue("Rule patterns list is not empty", list.size()>0);
	}

	/**
	 * Test method for {@link org.barclays.bfg.mappingfiles.codequality.rules.RuleConfig#getRulePattern(java.lang.String)}.
	 */
	@Test
	public void testGetRulePattern() {
		assertTrue("given whilefunction Pattern found ", RuleConfig.getRuleConfig("whilefunction").getName().length()>0);
		assertTrue("given messagebox Pattern found ", RuleConfig.getRuleConfig("messagebox").getName().length()>0);;
	
	}

	
}
