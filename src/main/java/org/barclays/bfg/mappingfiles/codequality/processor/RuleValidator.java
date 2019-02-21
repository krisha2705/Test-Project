package org.barclays.bfg.mappingfiles.codequality.processor;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import org.barclays.bfg.mappingfiles.codequality.processor.MappingFileSection.SectionType;
import org.barclays.bfg.mappingfiles.codequality.report.Report;
import org.barclays.bfg.mappingfiles.codequality.rules.IRule;
import org.barclays.bfg.mappingfiles.codequality.rules.RuleConfig;

public class RuleValidator {


	private ArrayList<IRule> rules = new ArrayList<IRule>();

	Report rep = new Report();

	public ArrayList<Report> execute(MapFile parsedFile) throws IOException{

		createRules();
		ArrayList<Report> reportList = new ArrayList<>();
		for (IRule rule: rules) {
			reportList.addAll(rule.execute(parsedFile));
		}

		return reportList;
	}


	private void createRules() {
		
		try {
			RuleConfig.loadRuleConfigs();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<RuleConfig> ruleConfigs = RuleConfig.getRuleConfigs();
		
		for (RuleConfig rp : ruleConfigs) {
			
			try {
				Class c;
				c = Class.forName(rp.getClassname());
				IRule rule = (IRule)(c.getDeclaredConstructor(RuleConfig.class).newInstance(rp));
				rules.add(rule);
			} catch (Exception  e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}




}
