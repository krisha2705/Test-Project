/**
 * 
 */
package org.barclays.bfg.mappingfiles.codequality.rules;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

/**
 * @author ashatre
 *
 */
public class RulePatternsTest {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		RuleConfig.loadRuleConfigs();
	}

	/**
	 * Test method for {@link org.barclays.bfg.mappingfiles.codequality.rules.RulePatterns#getRulePatterns()}.
	 */
	@Test
	public void testGetRulePatterns() {
		List<RuleConfig> list = RuleConfig.getRuleConfigs();
		assertTrue("Rule patterns list is not empty", list.size()>0);
	}

	/**
	 * Test method for {@link org.barclays.bfg.mappingfiles.codequality.rules.RulePatterns#getRulePattern(java.lang.String)}.
	 */
	@Test
	public void testGetRulePattern() {
		assertTrue("given whilefunction Pattern found ", RuleConfig.getRuleConfig("whilefunction").getName().length()>0);
		assertTrue("given messagebox Pattern found ", RuleConfig.getRuleConfig("messagebox").getName().length()>0);;
	
	}

	
}
