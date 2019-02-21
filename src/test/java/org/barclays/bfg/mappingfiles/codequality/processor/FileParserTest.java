package org.barclays.bfg.mappingfiles.codequality.processor;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.IOException;

import org.junit.Test;

public class FileParserTest {

	
	@Test
	public void testExtractSections() {
		MapFile mf  = parse();
		System.out.println(mf.inputBranchingDiagram().from() + "|" + mf.inputBranchingDiagram().to()  +"|" + mf.inputBranchingDiagram().code());
		System.out.println("*************************");
		System.out.println(mf.outputBranchingDiagram().from() + "|" + mf.outputBranchingDiagram().to()  +"|" + mf.outputBranchingDiagram().code());
		System.out.println("*************************");
		System.out.println(mf.inputRecordDetails().from() + "|" + mf.inputRecordDetails().to()  +"|" + mf.inputRecordDetails().code());
		System.out.println("*************************");
		System.out.println(mf.outputRecordDetails().from() + "|" + mf.outputRecordDetails().to()  +"|" + mf.outputRecordDetails().code());
		System.out.println("*************************");
		System.out.println(mf.mappingInformation().from() + "|" + mf.mappingInformation().to()  +"|" + mf.mappingInformation().code());
		System.out.println("*************************");
		System.out.println(mf.extendedRules().from() + "|" + mf.extendedRules().to()  +"|" + mf.extendedRules().code());
		System.out.println("*************************");
	}


	@Test
	public void testfindRootSegment() {
		String section = "INPUT Branching Diagram" + nl() + 
						"  -----------------------" + nl() + 
						" " + nl() + 
						"                        Name                         M/C     Min Use      Max Use        Description" + nl() + 
						" " + nl() + 
						"  Element ActionPerform*                              M               1           1" + nl() + 
						"     Content Particle Particle_1*                     C               0           1 Choice" + nl() + 
						"        Element Action*                               C               0           1 Action" + nl() + 
						"           Pcdata*                                    C               0           1" + nl() + 
						"        Element TEMP_Action*                          C               0           1 XXXXX" + nl() + 
						"           Attribute*                                 C               0           1" + nl() + 
						" ";
		
		assertEquals("ActionPerform", MappingFileParser.findRootSegment(section));
	}
	
	@Test
	public void testIndexToLineNumFirstChar() {
		assertEquals( 1 , MappingFileParser.indexToLineNumber(0, string()));
	}
	
	
	@Test
	public void testIndexToLineNumFirstLine() {
		assertEquals( 1 , MappingFileParser.indexToLineNumber(3, string()));
	}

	@Test
	public void testIndexToLineNumFirstLineEnd() {
		assertEquals( 1 , MappingFileParser.indexToLineNumber(10, string()));
	}

	@Test
	public void testIndexToLineNumFirstLineEnd2() {
		assertEquals( 1 , MappingFileParser.indexToLineNumber(12, string()));
	}
	
	@Test
	public void testIndexToLineNumFirstLineEnd3() {
		assertEquals( 1 , MappingFileParser.indexToLineNumber(12, string()));
	}

	@Test
	public void testIndexToLineNumLineBegining() {
		assertEquals( 2 , MappingFileParser.indexToLineNumber(13, string()));
	}

	@Test
	public void testIndexToLineNum3rdLine() {
		assertEquals( 3 , MappingFileParser.indexToLineNumber(30, string()));
	}


	
	@Test
	public void testIndexToLineNumLastLine() {
		assertEquals( 4 , MappingFileParser.indexToLineNumber(45, string()));
		
	}
	
	@Test
	public void testIndexToLineNumLastChar() {
		assertEquals( 4 , MappingFileParser.indexToLineNumber(48, string()));
	}

	private String string() {
		String str = "  Input   " + nl() + 
					 "          " + nl() + 
					 "  Agency :" + nl() + 
					 "  Version:" + nl();
		return str;
	}
	
	String nl() {
		return System.lineSeparator();
	}

	MapFile parse(){
		try {
			return MappingFileParser.parse("src\\test\\resources\\Sample_latest.txt");
		}catch(IOException ioe) {
			fail(ioe.getMessage());
		}
		
		return null;
	}

	
}
