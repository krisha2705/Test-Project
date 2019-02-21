package org.barclays.bfg.mappingfiles.codequality.rules;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class RegexSegmentTest {

	
	@Test
	public void testExtractSegment() {
		PatternRule rule = new PatternRule(null);
        String s;
        s = "IntDtm:2.IntDtm:2";
        assertEquals(s, rule.findFirstSegment(s));
        s = "ActDt.ActDt";
        assertEquals(s, rule.findFirstSegment(s));
        s = "ActDt.ActDt  ";
        assertEquals(s.trim(), rule.findFirstSegment(s));
        s = "  ActDt.ActDt";
        assertEquals(s.trim(), rule.findFirstSegment(s));
        s = "1.1";
        assertEquals("", rule.findFirstSegment(s));
        s = "A.1";
        assertEquals("", rule.findFirstSegment(s));
        s = "Ab:1.Bc:1";
        assertEquals("", rule.findFirstSegment(s));
        s = "Ab:1.Ab:2";
        assertEquals("", rule.findFirstSegment(s));
        s = "ActDtActDt";
        assertEquals("", rule.findFirstSegment(s));
        s = "CodeList";
        assertEquals("", rule.findFirstSegment(s));
        s = "A.B";
        assertEquals("", rule.findFirstSegment(s));
	}
	
}
