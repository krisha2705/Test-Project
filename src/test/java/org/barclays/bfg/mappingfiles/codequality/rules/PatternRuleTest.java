package org.barclays.bfg.mappingfiles.codequality.rules;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;

import org.barclays.bfg.mappingfiles.codequality.processor.MappingFileSection.SectionType;
import org.barclays.bfg.mappingfiles.codequality.processor.Snippet;
import org.junit.Test;

public class PatternRuleTest {

	
	@Test
	public void testTrimWithPostSession() {
		String expected = "   Post-Session" + nl() +  
							"   ActionPerform" + nl() +  
							"  Action.Action" + nl() +  
							"  //*******************Ready For mapping********************" + nl() +  
							"  if #Action = \"Login\" then"; 
		PatternRule rule = new PatternRule(null);
		Snippet s = rule.trimToPostSession(createFileSectionsWithPostSession());
		assertEquals(expected, s.code());
	}
	
	@Test
	public void testTrimWithoutPostSession() {
		String expected = 	"   ActionPerform" + nl() +  
							"   " + nl() +  
							"  Action.Action" + nl() +  
							"  //*******************Ready For mapping********************" + nl() +  
							"  if #Action = \"Login\" then"; 
		PatternRule rule = new PatternRule(null);
		Snippet s = rule.trimToPostSession(createFileSectionsWithoutPostSession());
		assertEquals(expected, s.code()); //this will throw NPE. Requires mocking in PatternRule Class
	}
	

	@Test
	public void testEnclosingSegmentName() {
		String code = createDataForEnclosingSegment();
		Snippet enclosing  = new Snippet (code, 1, 34);
		PatternRule rule = new PatternRule(null);
		assertEquals("IntDtm:2.IntDtm:2", rule.enclosingSegmentName(34, enclosing));
		assertEquals("IntDtm:2.IntDtm:2", rule.enclosingSegmentName(31, enclosing));
		assertEquals("IntDtm:2.IntDtm:2", rule.enclosingSegmentName(32, enclosing));
		assertEquals("ActionPerform", rule.enclosingSegmentName(6, enclosing)); //this will fail. Requires mocking of PatternRule

	}

	
	private String createDataForEnclosingSegment() {
		String code =	"   Post-Session" + nl() +  
						"   ActionPerform" + nl() +  
						"  if #Action = \"Login\" then" + nl()+
						"  RestAPIJobFile.CreateTheOnboardJob" + nl() + 
						"  #CreateTheOnboardJob=\"x-correlation-id : \"+var_xcorrelationid+\"^0D\"+\"^0A\"+\"authorization : \"+var_token_type+\" \"+var_access_token;" + nl() + 
						"   " + nl() + 
						"  RestAPIJobFile.CreateTheStatusJob" + nl() + 
						"  #CreateTheStatusJob=\"x-correlation-id : \"+var_xcorrelationid+\"^0D\"+\"^0A\"+\"authorization : \"+var_token_type+\" \"+var_access_token;" + nl() + 
						"  RestAPIJobFile.CreateTheAllUpdateJob" + nl() + 
						"  #CreateTheAllUpdateJob=\"x-correlation-id : \"+var_xcorrelationid+\"^0D\"+\"^0A\"+\"authorization : \"+var_token_type+\" \"+var_access_token;" + nl() + 
						"  " + nl() + 
						"  IntDtm.IntDtm" + nl() + 
						"  " + nl() + 
						"  if (x==y) asdhkahf" + nl() + 
						"  else" + nl() + 
						"  cerror (12234,\"Invalid file\")" + nl() + 
						"" + nl() + 
						"  PtDt.PtDt" + nl() + 
						"	fskjfl" + nl() + 
						"	while x > 9999" + nl() + 
						"	do" + nl() + 
						"		messagebox()" + nl() + 
						"	end" + nl() + 
						"" + nl() + 
						"  ActDt.ActDt" + nl() + 
						"	skdlfkj" + nl() + 
						"	sdfsdlkfj" + nl() + 
						"	sdfkjh" + nl() + 
						"	sdfsjdflk" + nl() + 
						"	" + nl() + 
						"  IntDtm:2.IntDtm:2	" + nl() + 
						"	" + nl() + 
						"  Codelists" + nl() + 
						"  ---------";
		return code;
	}

	
	Snippet createFileSectionsWithoutPostSession(){
		String value = "Extended Rules" + nl() +  
				"  --------------" + nl() +  
				"  Pre-Session" + nl() +  
				"  //******Created By Jatin Patar 18/04/2018******//" + nl() +  
				"  String[256] var_RestApiJob,var_client_id, var_grant_type,var_username,var_password,var_access_token,var_token_type,var_TPP_ID,var_Action,var_xcorrelationid;" + nl() +  
				"   ActionPerform" + nl() +  
				"   " + nl() +  
				"  Action.Action" + nl() +  
				"  //*******************Ready For mapping********************" + nl() +  
				"  if #Action = \"Login\" then"; 
		return new Snippet(value, 1, 11);
	}
	
	Snippet createFileSectionsWithPostSession(){
		String value = "Extended Rules" + nl() +  
				"  --------------" + nl() +  
				"  Pre-Session" + nl() +  
				"  //******Created By Jatin Patar 18/04/2018******//" + nl() +  
				"  String[256] var_RestApiJob,var_client_id, var_grant_type,var_username,var_password,var_access_token,var_token_type,var_TPP_ID,var_Action,var_xcorrelationid;" + nl() +  
				"   Post-Session" + nl() +  
				"   ActionPerform" + nl() +  
				"  Action.Action" + nl() +  
				"  //*******************Ready For mapping********************" + nl() +  
				"  if #Action = \"Login\" then"; 

		return new Snippet(value, 1, 11);
	}
	
	List<String> createSegments(){
		return new ArrayList<>
				(
				Arrays.asList(
						"Action.Action", 
							"RestAPIJobFile.CreateTheLoginJob", 
							"RestAPIJobFile.CreateTheOnboardJob" , 
							"RestAPIJobFile.CreateTheStatusJob", 
							"RestAPIJobFile.CreateTheAllUpdateJob" , 
							"IntDtm.IntDtm" , 
							"PtDt.PtDt" , 
							"ActDt.ActDt" , 
							"IntDtm:2.IntDtm:2", 
							"ActionPerform"
							
							)
					);
						
	}
	
	String nl() {
		return System.lineSeparator();
		//return null;
	}

}
