package org.barclays.bfg.mappingfiles.codequality.rules;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.barclays.bfg.mappingfiles.codequality.processor.MapFile;
import org.barclays.bfg.mappingfiles.codequality.processor.Snippet;
import org.barclays.bfg.mappingfiles.codequality.report.Report;

public class PatternRule implements IRule {

	private static final Logger LOGGER = Logger.getLogger (PatternRule.class);
	private List<Report> violations;
	private RuleConfig ruleConfig;
	private MapFile parsedFile;
	
	public PatternRule(RuleConfig rp) {
		this.ruleConfig = rp;
		LOGGER.debug("Rule created with config: "+ rp);
	}
	
	@Override
	public List<Report> execute(MapFile parsedFile) {
		
		LOGGER.debug("Will Start execution of rule: "+ ruleConfig.getName()+ ": on file" + parsedFile.fileName());
		this.parsedFile = parsedFile;
		violations = new ArrayList<>();
		
		Snippet postSession = trimToPostSession(parsedFile.extendedRules());
		
		List<Snippet> occurences = findRulePatterns(postSession);
		for (Snippet s : occurences){
			String p = enclosingSegmentName(s.from(), postSession);
			accumulateViolation(s, p);
		}
		
		LOGGER.debug("Violations added by rule: "+ ruleConfig.getName()+ ": on file " + parsedFile.fileName() + " : " + violations);
		return violations;
		
	}

	String getRulePattern() {
		return ruleConfig.getRegex();
	}

	void accumulateViolation(Snippet offendingSnippet, String enclosingSegmentName) {
		Report r = new Report();
		r.setMappingfilename(parsedFile.fileName());
		r.setRuleName(ruleConfig.getName());
		r.setRuleType(ruleConfig.getName());
		r.setLineno(offendingSnippet.from());
		r.setDescription(ruleConfig.getDescription());
		r.setSegmentName(enclosingSegmentName);
		r.setProblemCode(offendingSnippet.code());
		violations.add(r);
	}

	

	/*
	 * Given a snippet, navigate it from bottom to top to return the first occurrence of the segment pattern
	 * If no segment name found then returns the root Segment
	 */
	String enclosingSegmentName(int startFrom, Snippet postSession) {
		
		String encSegmentName = "";
		//scope down the postSession
		Snippet snipped = postSession.snip(-1, startFrom);

		String [] split = snipped.code().split(System.lineSeparator());
		int size = split.length;
		for (int i = size-1 ; i >= 0;  i--) {
			encSegmentName = findFirstSegment(split[i]);
			if (!encSegmentName.isEmpty()) return encSegmentName;
		}
		return rootSegment(); 
	}


	/*
	 * if the passed string matches the segment regex, extract the segment name from the string and return it; else return an empty string ("")
	 */
	String findFirstSegment(String s) {
		// TODO move to config
        String regex1 = "(^(?:[A-Z][a-z]+))(:)(\\d+)\\.((?:[A-Z][a-z]+))(:)((\\d+)$)";
        String regex2 = "(^(?:[A-Z][a-z]+))\\.((?:[A-Z][a-z]+))$";
	    s = s.trim();
        
        String segment = match(regex1, s);
        if (segment.isEmpty()) segment = match(regex2, s);
        return segment;
	}
        

	private String match(String regex1, String s) {

        Pattern p = Pattern.compile(regex1, Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
        Matcher m = p.matcher(s);
        String match = "";
        
        if (m.find())
        {
           String [] segparts = m.group().split("\\.");
           if (segparts.length==2) return (segparts[0].equals(segparts[1]))? m.group():"";
        }
        
        return match;
	}
                

	/*
	 * Scans a code snippet for the regex configured for this rule
	 * Returns a list of code snippets; each snippet entry contains the pattern extract found in the passed snippet and the line number it was found at
	 */
	List<Snippet> findRulePatterns( Snippet snippet) {
	//iterate line by line through the snippet and look for the pattern in each line	
		List<Snippet> patterns = new ArrayList<>();
		Matcher m;

		int lineIndx = snippet.from();
		Pattern p = Pattern.compile(getRulePattern(), Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
		String delim = System.lineSeparator();

		try (Scanner scan = new Scanner(snippet.code())) {
			scan.useDelimiter(delim);
			while(scan.hasNext()) {
				String content = scan.next().trim();
				if (content.matches("^.*//.*(;|\bDo)")) { //ignore lines which are commented out
					lineIndx ++; 
					continue;
				}
				m = p.matcher(content);
				if (m.find()) {
					String s = m.group().trim();
					patterns.add(new Snippet(s, lineIndx, lineIndx));
				}
				lineIndx ++;
			}
		}
		return patterns;
	}

	/*
	 * return the 'Post-session' part of the file.
	 *TODO: move this code to the Mapfile class
	 */
	Snippet trimToPostSession(Snippet extRules) {

		String postSession = "POST-SESSION"; //TODO. Remove hard coding
		
		String section = extRules.code().toUpperCase(); //new String so that case is preserved in the original
		int index;
		String delim = System.lineSeparator();
		
		/*
		 * Find either the index where the string post-sesison begins OR 
		 * if not found, then the index where the rootsegment is first defined
		 */
		if ((index = section.indexOf(postSession.toUpperCase())) == -1) {
			index = section.indexOf(rootSegment().toUpperCase());
		}
//		index = section.lastIndexOf(delim, index)+ delim.length(); //get the complete line.//TODO why the complete line?
		Snippet s = extRules.snipFromIndex(index, -1);
		
		return s;
	}
	
	
	private String rootSegment() {
//		return "ActionPerform";
		return parsedFile.rootSegmentName();
	}




}


