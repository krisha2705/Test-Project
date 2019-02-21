package org.barclays.bfg.mappingfiles.codequality.rules;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class RuleConfig {
	private static final Logger LOGGER = Logger.getLogger(RuleConfig.class);
	private String name;
	private String regex;
	private String casesensitive;
	private String classname;
	private String description;
	private static List<RuleConfig> ruleConfigs;



	public RuleConfig() {
		name="";
		regex="";
		casesensitive="";
		classname="";
		description = "";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRegex() {
		return regex;
	}

	public void setRegex(String regex) {
		this.regex = regex;
	}

	public String getCasesensitive() {
		return casesensitive;
	}

	public void setCasesensitive(String casesensitive) {
		this.casesensitive = casesensitive;
	}
	public String getClassname() {
		return classname;
	}

	public void setClassname(String classname) {
		this.classname = classname;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescripton(String description) {
		this.description = description;
	}
	
	public static void loadRuleConfigs() throws FileNotFoundException {
		Gson gson = new Gson();
		BufferedReader br = null;
		URL url = RuleConfig.class.getClassLoader().getResource("rulepatterns.json");
		try {
			String filepath = url.toURI().getPath();
			br = new BufferedReader(new FileReader(filepath));
		} catch (URISyntaxException e) {
			LOGGER.error("file path not correct", e);
		}
		Type type = new TypeToken<List<RuleConfig>>(){}.getType();
		
		ruleConfigs = (List<RuleConfig>) gson.fromJson(br, type);
		LOGGER.debug("No. of RulePatterns" + ruleConfigs.size());
		
	}
	
	public static List<RuleConfig> getRuleConfigs() {
		return ruleConfigs;
	}
	
	public static RuleConfig getRuleConfig(String rule)
	{
		for (RuleConfig ruleConfig : ruleConfigs) {
			if (rule.equals(ruleConfig.getName()))
				return ruleConfig;
		}
		return new RuleConfig();
	}
	
	@Override
	public String toString() {
		return 	"name: " + name + System.lineSeparator() +   
				"regex: " + regex + System.lineSeparator() +  
				"casesensitive: " + casesensitive + System.lineSeparator() + 
				"classname:  " + classname + System.lineSeparator(); 
	}
}