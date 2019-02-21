package org.barclays.bfg.mappingfiles.codequality.rules;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.barclays.bfg.mappingfiles.codequality.processor.Snippet;
import org.junit.Test;

public class RegexWhileTest {
	

	
	@Test
	public void testFindPatternWhile() {
		Snippet snippet = new Snippet
						(" while $TEMP_STR.#TEMP_CD != \"\" | $TEMP_AWER != \"\" Do " + nl() + 
						"     begin" + nl() + 
						"  //cerror(3161,Flag_Batch_Rej,\"aes\");   " + nl() + 
						"  cerror(3141,Flag_Batch_Rej,\"fth\");			//Comments   " + nl() + 
						"  while g = 2 do" + nl() + 
						"     begin" + nl() + 
						"  while v >= 3 do" + nl() + 
						"     begin" + nl() + 
						"  if #Action = \"AllUpdate\" then" + nl() + 
						"  #Create_AllUpdate_Job_Temp =\"yes\";" + nl() + 
						"  RestAPIJobFile.CreateTheAllUpdateJobfor4 " + nl() + 
						"  " + nl() + 
						"  WHILE ($TEMP_StsRnsInf:2[Loop_Batch] | $Temp[party_over:2 != \"\")Do   " + nl() + 
						"" + nl() + 
						"   while q < a do" + nl() + 
						"     begin" + nl() + 
						"   " + nl() + 
						"   while $TEMP_STS[1][i].#TEMP_sample != 12 Do" + nl() + 
						"   " + nl() + 
						"  while Cnt err >0 Do" + nl() + 
						"  while q < 4 do  " + nl() , 
						20,40						
				);
		List<Snippet> expected = new ArrayList<>();
		
		expected.add(new Snippet("while g = 2 do", 24, 24));
		expected.add(new Snippet("while v >= 3 do", 26, 26)); 
		expected.add(new Snippet("while $TEMP_STS[1][i].#TEMP_sample != 12 Do", 37, 37));
		expected.add(new Snippet("while Cnt err >0 Do" , 39, 39));
		expected.add(new Snippet("while q < 4 do" , 40, 40));

		PatternRule rule = whileRule();

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

 	PatternRule whileRule() {
		RuleConfig rc = new RuleConfig();
		rc.setCasesensitive("N");;
		rc.setClassname("");
		rc.setName("While Loop");
		rc.setRegex("(^\\s*While)(.*)(\\s+)([\\\\s|>|<|=|!]+)(\\s*)(\\d+)(\\s*)(Do)");
		return new PatternRule(rc);
	}	
	
	String nl() {
		return System.lineSeparator();
		//return null;
	}

}
