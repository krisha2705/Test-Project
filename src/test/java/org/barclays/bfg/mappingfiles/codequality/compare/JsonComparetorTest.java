/**
 * 
 */
package org.barclays.bfg.mappingfiles.codequality.compare;

import static org.junit.Assert.*;

import java.util.List;

import org.barclays.bfg.mappingfiles.codequality.report.Report;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author prakumar53
 *
 */
public class JsonComparetorTest {
	
	private static final String RULE_TYPE = "CERROR";
	
	private static final String previousReport = "[\r\n" + 
			"{\r\n" + 
			"    \"description\": \"\",\r\n" + 
			"    \"lineno\": 207,\r\n" + 
			"    \"mappingfilename\": \"C:\\\\Users\\\\prakumar53\\\\Downloads\\\\SampleMappingFile.txt\",\r\n" + 
			"    \"problemCode\": \"while Cnt err >0 Do\",\r\n" + 
			"    \"segmentName\": \"Action.Action\",\r\n" + 
			"    \"ruleName\": \"WHILE\",\r\n" + 
			"    \"ruleType\": \"WHILE\"\r\n" + 
			"  },\r\n" + 
			"  {\r\n" + 
			"    \"description\": \"\",\r\n" + 
			"    \"lineno\": 210,\r\n" + 
			"    \"mappingfilename\": \"C:\\\\Users\\\\prakumar53\\\\Downloads\\\\SampleMappingFile.txt\",\r\n" + 
			"    \"problemCode\": \"MessageBOX(\\\"This is Wrong 5\\\");   MessageBOX(\\\"This is Wrong 6\\\")\",\r\n" + 
			"    \"segmentName\": \"Action.Action\",\r\n" + 
			"    \"ruleName\": \"MESSAGEBOX\",\r\n" + 
			"    \"ruleType\": \"MESSAGEBOX\"\r\n" + 
			"  },\r\n" + 
			"  {\r\n" + 
			"    \"description\": \"\",\r\n" + 
			"    \"lineno\": 185,\r\n" + 
			"    \"mappingfilename\": \"C:\\\\Users\\\\prakumar53\\\\Downloads\\\\SampleMappingFile.txt\",\r\n" + 
			"    \"problemCode\": \"cerror(3115,Flag_Batch_Rej,\\\"PQR\\\")\",\r\n" + 
			"    \"segmentName\": \"Action.Action\",\r\n" + 
			"    \"ruleName\": \"CERROR\",\r\n" + 
			"    \"ruleType\": \"CERROR\"\r\n" + 
			"  }\r\n" + 
			"]";
	
	
	private static final String currentReport = " [\r\n" + 
			" {\r\n" + 
			"    \"description\": \"\",\r\n" + 
			"    \"lineno\": 207,\r\n" + 
			"    \"mappingfilename\": \"C:\\\\Users\\\\prakumar53\\\\Downloads\\\\SampleMappingFile.txt\",\r\n" + 
			"    \"problemCode\": \"while Cnt err >0 Do\",\r\n" + 
			"    \"segmentName\": \"Action.Action\",\r\n" + 
			"    \"ruleName\": \"WHILE\",\r\n" + 
			"    \"ruleType\": \"WHILE\"\r\n" + 
			"  },\r\n" + 
			"  {\r\n" + 
			"    \"description\": \"\",\r\n" + 
			"    \"lineno\": 122,\r\n" + 
			"    \"mappingfilename\": \"C:\\\\Users\\\\prakumar53\\\\Downloads\\\\SampleMappingFile.txt\",\r\n" + 
			"    \"problemCode\": \"cerror(3141,Flag_Batch_Rej,\\\"fth\\\")\",\r\n" + 
			"    \"segmentName\": \"ActionPerform\",\r\n" + 
			"    \"ruleName\": \"CERROR\",\r\n" + 
			"    \"ruleType\": \"CERROR\"\r\n" + 
			"  },\r\n" + 
			"  {\r\n" + 
			"    \"description\": \"\",\r\n" + 
			"    \"lineno\": 146,\r\n" + 
			"    \"mappingfilename\": \"C:\\\\Users\\\\prakumar53\\\\Downloads\\\\SampleMappingFile.txt\",\r\n" + 
			"    \"problemCode\": \"cerror(3141,Flag_Batch_Rej,\\\"fth\\\")\",\r\n" + 
			"    \"segmentName\": \"Choice:1.Choice:1\",\r\n" + 
			"    \"ruleName\": \"CERROR\",\r\n" + 
			"    \"ruleType\": \"CERROR\"\r\n" + 
			"  },\r\n" + 
			"  {\r\n" + 
			"    \"description\": \"\",\r\n" + 
			"    \"lineno\": 164,\r\n" + 
			"    \"mappingfilename\": \"C:\\\\Users\\\\prakumar53\\\\Downloads\\\\SampleMappingFile.txt\",\r\n" + 
			"    \"problemCode\": \"cerror(3110,Flag_Batch_Rej,\\\"ABC\\\")\",\r\n" + 
			"    \"segmentName\": \"Choice.Choice\",\r\n" + 
			"    \"ruleName\": \"CERROR\",\r\n" + 
			"    \"ruleType\": \"CERROR\"\r\n" + 
			"  },\r\n" + 
			"  {\r\n" + 
			"    \"description\": \"\",\r\n" + 
			"    \"lineno\": 185,\r\n" + 
			"    \"mappingfilename\": \"C:\\\\Users\\\\prakumar53\\\\Downloads\\\\SampleMappingFile.txt\",\r\n" + 
			"    \"problemCode\": \"cerror(3115,Flag_Batch_Rej,\\\"PQR\\\")\",\r\n" + 
			"    \"segmentName\": \"Action.Action\",\r\n" + 
			"    \"ruleName\": \"CERROR\",\r\n" + 
			"    \"ruleType\": \"CERROR\"\r\n" + 
			"  }\r\n" + 
			"]";
	
	
	private static final String expecteddiffreportListAsString = "[\r\n" + 
			"ruleType: CERROR\r\n" + 
			"mappingfilename: C:\\Users\\prakumar53\\Downloads\\SampleMappingFile.txt\r\n" + 
			"description: \r\n" + 
			"violationType: null\r\n" + 
			"lineno: 122\r\n" + 
			"problemCode: cerror(3141,Flag_Batch_Rej,\"fth\")\r\n" + 
			"segmentName: ActionPerform\r\n" + 
			", \r\n" + 
			"ruleType: CERROR\r\n" + 
			"mappingfilename: C:\\Users\\prakumar53\\Downloads\\SampleMappingFile.txt\r\n" + 
			"description: \r\n" + 
			"violationType: null\r\n" + 
			"lineno: 146\r\n" + 
			"problemCode: cerror(3141,Flag_Batch_Rej,\"fth\")\r\n" + 
			"segmentName: Choice:1.Choice:1\r\n" + 
			", \r\n" + 
			"ruleType: CERROR\r\n" + 
			"mappingfilename: C:\\Users\\prakumar53\\Downloads\\SampleMappingFile.txt\r\n" + 
			"description: \r\n" + 
			"violationType: null\r\n" + 
			"lineno: 164\r\n" + 
			"problemCode: cerror(3110,Flag_Batch_Rej,\"ABC\")\r\n" + 
			"segmentName: Choice.Choice\r\n" + 
			"]";
			
			
	JsonComparetor jsonComp = null;
	

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		jsonComp = new JsonComparetor(previousReport, currentReport);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testcompareForJSONReport() {
		
		List<Report> actualdiffreportList = jsonComp.compare(RULE_TYPE);
		assertEquals(expecteddiffreportListAsString, actualdiffreportList.toString());
	}

}
