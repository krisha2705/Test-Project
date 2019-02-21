package org.barclays.bfg.mappingfiles.codequality.rules;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.barclays.bfg.mappingfiles.codequality.processor.Snippet;
import org.junit.Test;

public class RegexMsgboxTest {
	

	
	@Test
	public void testFindPatternMessageBox() {
		Snippet snippet = new Snippet
				(
					"		messagebox  ();" + nl() +  
					"	end" + nl() +  
					"MessageBox();" + nl() +  
					"messagebox();" + nl() +  
					"	" + nl() +  
					"  MessageBOX(\"This is Wrong 1\");" + nl() +  
					"  RestAPIJobFile.CreateTheAllUpdateJobfor2" + nl() +  
					"  MESSAGEBOX  (\"This is Wrong 2\"); " + nl() +  
					"" + nl() +  
					"  RestAPIJobFile.CreateTheAllUpdateJobfor4 " + nl() +  
					"  MessageBOX(\"This is OK1\");   " + nl() +  
					"   " + nl() +  
					"  RestAPIJobFile.CreateTheLoginJob" + nl() +  
					"  MessageBOX(\"This is OK2\");   " + nl() +  
					"", 1,15						
				);
		List<Snippet> expected = new ArrayList<>();
		expected.add(new Snippet("messagebox  ()", 1, 1));
		expected.add(new Snippet("MessageBox()", 3, 3)); 
		expected.add(new Snippet("messagebox()", 4, 4));
		expected.add(new Snippet("MessageBOX(\"This is Wrong 1\")" , 6, 6));
		expected.add(new Snippet("MESSAGEBOX  (\"This is Wrong 2\")" , 8, 8));
		expected.add(new Snippet("MessageBOX(\"This is OK1\")"  ,11,11));
		expected.add(new Snippet("MessageBOX(\"This is OK2\")" , 14, 14));
    	PatternRule rule = patternRule();

		List<Snippet> actual = rule.findRulePatterns(snippet);
		assertEquals(expected.size(), actual.size());
		Iterator<Snippet> iter = actual.iterator();
		for (Snippet e : expected) {
			Snippet a = iter.next();
			assertEquals(e.code(), a.code());
			assertTrue(e.from() == a.from());
			assertTrue(e.to() == a.to());
		}
	}
	
	PatternRule patternRule() {
		RuleConfig rc = new RuleConfig();
		rc.setCasesensitive("N");;
		rc.setClassname("");
		rc.setName("MessageBox");
		rc.setRegex("(MessageBox)(\\s*)(\\()(.*)(\\))");
		return new PatternRule(rc);
	}	
	
	String nl() {
		return System.lineSeparator();
		//return null;
	}

}
