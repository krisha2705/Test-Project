package org.barclays.bfg.mappingfiles.codequality.processor;

import static org.junit.Assert.*;

import org.junit.Test;

public class SnippetTest {

	@Test
	public void testSnipOKRange() {
		Snippet s = snippet().snip(11, 14);
		String expected = "  --------------" + nl() +  
						"  Pre-Session" + nl() +  
						"  //******Created By Jatin Patar 18/04/2018******//" + nl() +  
						"  String[256] var_RestApiJob,var_client_id, var_grant_type,var_username,var_password,var_access_token,var_token_type,var_TPP_ID,var_Action,var_xcorrelationid;" + nl();  
		assertEquals(11, s.from());
		assertEquals(14, s.to());
		assertEquals(expected, s.code());

		
	}

	@Test
	public void testSnipNoFrom() {
		Snippet s = snippet().snip(-1, 14);
		String expected  =  "Extended Rules" + nl() +  
							"  --------------" + nl() +  
							"  Pre-Session" + nl() +  
							"  //******Created By Jatin Patar 18/04/2018******//" + nl() +  
							"  String[256] var_RestApiJob,var_client_id, var_grant_type,var_username,var_password,var_access_token,var_token_type,var_TPP_ID,var_Action,var_xcorrelationid;" + nl();  
		assertEquals(10, s.from());
		assertEquals(14, s.to());
		assertEquals(expected, s.code());
		
	}

	@Test
	public void testSnipNoFrom2() {
		String code = 	"	sdfkjh" + nl() + 
						"	sdfsjdflk" + nl() + 
						"	" + nl() + 
						"  IntDtm:2.IntDtm:2	" + nl() + 
						"	" + nl() + 
						"  Codelists" + nl() + 
						"  ---------";
		
		Snippet s = new Snippet(code, 1, 6);
		Snippet snipped = s.snip(-1, 4);

		String expected = 	"	sdfkjh" + nl() + 
							"	sdfsjdflk" + nl() + 
							"	" + nl() + 
							"  IntDtm:2.IntDtm:2	" + nl(); 
		assertEquals(1, snipped.from());
		assertEquals(4, snipped.to());
		assertEquals(expected, snipped.code());

	}	
	
	@Test
	public void testSnipNoTo() {
		Snippet s = snippet().snip(14, -1);
		String expected = "  String[256] var_RestApiJob,var_client_id, var_grant_type,var_username,var_password,var_access_token,var_token_type,var_TPP_ID,var_Action,var_xcorrelationid;" + nl() +  
						"   Post-Session" + nl() +  
						"   ActionPerform" + nl() +  
						"  Action.Action" + nl() +  
						"  //*******************Ready For mapping********************" + nl() +  
						"  if #Action = \"Login\" then" + nl();
		assertEquals(14, s.from());
		assertEquals(19, s.to());
		assertEquals(expected, s.code());
		
	}
	
	@Test
	public void testSnipFromOutOfRange() {
			fail();
	}
	
	@Test
	public void testSnipToOutOfRange() {
		fail();
	}
	
	@Test
	public void testSnipFromIndex() {
		
		String expected = "   Post-Session" + nl() +  
						"   ActionPerform" + nl() +  
						"  Action.Action" + nl() +  
						"  //*******************Ready For mapping********************" + nl() +  
						"  if #Action = \"Login\" then";
		
		int from = snippet().code().indexOf("Post-Session");
		Snippet s = snippet().snipFromIndex(from, -1);

		assertEquals(15, s.from());
		assertEquals(19, s.to());
		assertEquals(expected, s.code());
	}
	
	
	private Snippet snippet() {
		String code = "Extended Rules" + nl() +  
				"  --------------" + nl() +  
				"  Pre-Session" + nl() +  
				"  //******Created By Jatin Patar 18/04/2018******//" + nl() +  
				"  String[256] var_RestApiJob,var_client_id, var_grant_type,var_username,var_password,var_access_token,var_token_type,var_TPP_ID,var_Action,var_xcorrelationid;" + nl() +  
				"   Post-Session" + nl() +  
				"   ActionPerform" + nl() +  
				"  Action.Action" + nl() +  
				"  //*******************Ready For mapping********************" + nl() +  
				"  if #Action = \"Login\" then";
		
		return new Snippet(code, 10, 19);
	}


	
	String nl() {
		return System.lineSeparator();
	}

}
