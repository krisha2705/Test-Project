package org.barclays.bfg.mappingfiles.codequality.processor;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.util.Scanner;

import org.apache.log4j.Logger;
import org.barclays.bfg.mappingfiles.codequality.cli.Driver;

public class MappingFileParser {
	
	private static final Logger LOGGER = Logger.getLogger (MappingFileParser.class);
	private static final String CODELISTS = "Codelists";
	private static final String EXTENDED_RULES = "Extended Rules";
	private static final String MAPPING_INFORMATION = "Mapping Information";
	private static final String INPUT_RECORD_DETAILS = "INPUT Record Details";
	private static final String OUTPUT_RECORD_DETAILS = "OUTPUT Record Details";
	private static final String OUTPUT_BRANCHING_DIAGRAM = "OUTPUT Branching Diagram";
	private static final String INPUT_BRANCHING_DIAGRAM = "INPUT Branching Diagram";
	

	
	private MappingFileParser() {
		//forbid creation from outside
	}

	public static MapFile parse(String filename)throws IOException {
		
		File f = new File(filename);

		try (FileInputStream fStream = new FileInputStream(f)){
		
			String fileContent = readFile(f, fStream);

			Snippet ibd = extractSection(fileContent, INPUT_BRANCHING_DIAGRAM,OUTPUT_BRANCHING_DIAGRAM);
			Snippet obd  = extractSection(fileContent, OUTPUT_BRANCHING_DIAGRAM,INPUT_RECORD_DETAILS);
			Snippet ird = extractSection(fileContent, INPUT_RECORD_DETAILS,OUTPUT_RECORD_DETAILS);
			Snippet ord = extractSection(fileContent, OUTPUT_RECORD_DETAILS,MAPPING_INFORMATION);
			Snippet mi = extractSection(fileContent, MAPPING_INFORMATION, EXTENDED_RULES);
			Snippet er = extractSection(fileContent, EXTENDED_RULES,CODELISTS);
			
			String seg = findRootSegment(ibd.code());
			
			LOGGER.debug("File " + filename + " parsed. With Root Section: "  + seg);	
			
			return new MapFile(filename, ibd, obd, ird, ord, mi, er, seg);

		}
	}


	static String readFile(File f, FileInputStream fStream) throws IOException {
		String fileContent;
		byte[] fileBytes = new byte[(int) f.length()];
		fStream.read(fileBytes);
		fileContent = new String(fileBytes);
		return fileContent;
	}
	

	static Snippet extractSection(String content, String start, String end) {
		int startIndx = content.indexOf(start);
		int endIndx = content.indexOf(end);
		String extractedtext = content.substring(startIndx, endIndx);
		int startLnNum = indexToLineNumber(startIndx, content.toString());
		int endLnNum = indexToLineNumber(endIndx, content.toString());

		Snippet s = new Snippet(extractedtext,startLnNum, endLnNum );

		LOGGER.info("Section Extracted: " + s);
		return s;

	}


	/*
	 * Scans the input branching diagram section to return the name of the root segment
	 *TODO review the logic; this is for earlier, junit tests are running fine though
	 */
	static String findRootSegment(String str ) {
		Scanner scan = new Scanner(str);
		scan.useDelimiter(System.lineSeparator());
		StringBuilder sb = new StringBuilder() ;
		while(scan.hasNext()) {
			String line = scan.next();
			if (line.contains("Element")) {
				String s = line;
				String[] arr = s.split(" ");
				for ( String ss : arr) {
					if (ss.contains("*")) {
						ss = ss.substring(0, ss.length() - 1);
						sb.append(ss);
					}
				}
				break;
			}
		}
		String msegment = sb.toString();
		scan.close();
		LOGGER.info("Root Segment : " + msegment + "  found in : " + str);
		return msegment;
	}
	

	
	static int indexToLineNumber(int startIndx, String str) {
		String sub = str.substring(0, startIndx);
//		int count = sub.length() - sub.replace(System.lineSeparator(), "").length();
		int count = sub.split(System.lineSeparator()).length;
		return count;
	}

	

	
}
