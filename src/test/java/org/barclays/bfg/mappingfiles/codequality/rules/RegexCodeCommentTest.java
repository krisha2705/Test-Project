package org.barclays.bfg.mappingfiles.codequality.rules;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.barclays.bfg.mappingfiles.codequality.processor.Snippet;
import org.junit.Test;

public class RegexCodeCommentTest {
	

	
	@Test
	public void testFindPatternWhile() {
		Snippet snippet = new Snippet
						(" while $TEMP_STR.#TEMP_CD != \"\" | $TEMP_AWER != \"\" Do " + nl() + 
						"     begin" + nl() + 
						"  //cerror(3161,Flag_Batch_Rej,\"aes\");   " + nl() + 
						"  cerror(3141,Flag_Batch_Rej,\"fth\");			//Comments   " + nl() + 
						"//  while g = 2 do" + nl() + 
						"     begin" + nl() + 
						"  //while v >= 3 do" + nl() + 
						"     begin" + nl() + 
						"  if #Action = \"AllUpdate\" then" + nl() + 
						"  #Create_AllUpdate_Job_Temp =\"yes\";" + nl() + 
						"  RestAPIJobFile.CreateTheAllUpdateJobfor4 " + nl() + 
						"  " + nl() + 
						"  WHILE ($TEMP_StsRnsInf:2[Loop_Batch] | $Temp[party_over:2 != \"\")Do   " + nl() + 
						"" + nl() + 
						"   // while q < 18 do" + nl() + 
						"     begin" + nl() + 
						"   " + nl() + 
						"   while $TEMP_STS[1][i].#TEMP_sample != 12 Do // " + nl() + 
						"   " + nl() + 
						"  while Cnt err >0 Do" + nl() + 
						"  while q < 4 do  " + nl() , 
						20,40						
				);
		List<Snippet> expected = new ArrayList<>();
		
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
	
	
	@Test
	public void testFindPatternMessageBox() {
		Snippet snippet = new Snippet
				(
					"/		messagebox  ();" + nl() +  
					"	end" + nl() +  
					"//MessageBox();" + nl() +  
					"//   messagebox();" + nl() +  
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
		expected.add(new Snippet("MessageBOX(\"This is Wrong 1\")" , 6, 6));
		expected.add(new Snippet("MESSAGEBOX  (\"This is Wrong 2\")" , 8, 8));
		expected.add(new Snippet("MessageBOX(\"This is OK1\")"  ,11,11));
		expected.add(new Snippet("MessageBOX(\"This is OK2\")" , 14, 14));
    	PatternRule rule = msgRule();

		List<Snippet> actual = rule.findRulePatterns(snippet);
//		assertEquals(expected.size(), actual.size());
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
 	
	PatternRule msgRule() {
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
