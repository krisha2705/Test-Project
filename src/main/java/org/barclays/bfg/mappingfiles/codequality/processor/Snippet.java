package org.barclays.bfg.mappingfiles.codequality.processor;

import java.lang.invoke.MethodHandles;
import java.util.Scanner;

import org.apache.log4j.Logger;

/*
 * Holds a subset of the source file.
 * Members from and to indicate the lines number in the source file that this snippet holds
 * Member code is the actual code snippet between lines 'from' and 'to'
 */
public class Snippet {
	//TODO: handle for to/from range including zero
	private static final Logger LOGGER = Logger.getLogger (Snippet.class);
	private String code;
	private int from;
	private int to;
	public static Snippet EMPTY = new Snippet ("", 0, 0);
	
	public Snippet(String code, int from, int to) {
		super();
		this.code = code;
		this.from = from;
		this.to = to;
	}

	public String code() {
		return code;
	}

	public int from() {
		return from;
	}

	public int to() {
		return to;
	}
	
	
	public Snippet snip(int from, int to) {
		
		String delim = System.lineSeparator();
		StringBuffer snippedSbf = new StringBuffer();
		if (from == -1) from = from();
		if (to == -1) to = to();
		
		int lnCnt = from();
		try (Scanner s = new Scanner(code())) {
			s.useDelimiter(delim);
			String line;
			while(s.hasNext()) {
				line = s.next();
				if (lnCnt >= from && lnCnt<=to) snippedSbf.append(line + delim);
				lnCnt ++;
			}
		}
		String snippedStr = snippedSbf.toString();
		//if this is the last line of the file, then remove the newline character
		if (to == to()) snippedStr = snippedStr.substring(0, snippedStr.lastIndexOf(delim));
		
		return new Snippet(snippedStr, from, to);
	}


	public Snippet snipFromIndex(int from, int to) {

		if (from == -1) from = 0;
		if (to == -1) to = code().length();

		int fromLn = this.from() + code().substring(0, from).split(System.lineSeparator()).length - 1;
		int toLn = this.to() - code().substring(to).split(System.lineSeparator()).length + 1;

		Snippet s = snip(fromLn, toLn);
		
/*		LOGGER.info("Snipped: lines from " + s.from() +  " to " + s.to +
					" from indexes " + fromIdx + " and " + toIdx);
*/		return s;
		
	}
	
	@Override
	public String toString() {
		return "from: " + from + " to: " + to + " code: " +  codeClip();

	}
	
	
	private String codeClip() {
		if (code == null) return "";
		if (code.length() < 100 ) return code;
		return code.substring(0, 50) + "<<........>>" + code.substring(code.length() - 50);
	}
	

}
