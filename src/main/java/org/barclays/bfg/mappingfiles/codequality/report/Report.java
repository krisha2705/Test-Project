package org.barclays.bfg.mappingfiles.codequality.report;

public class Report {

	private String description;// linetext
	private Integer lineno;
	private String mappingfilename; //
	private String problemCode;
	private String segmentName;
	private String ruleName;
	private String ruleType;
	private String violationType;

	public enum RuleType {
		MESSAGEBOX, WHILE, CERROR,
	}

	public enum ViolationType {
		ERROR, WARN, INFO
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getLineno() {
		return lineno;
	}

	public void setLineno(Integer lineno) {
		this.lineno = lineno;
	}

	public String getMappingfilename() {
		return mappingfilename;
	}

	public void setMappingfilename(String mappingfilename) {
		this.mappingfilename = mappingfilename;
	}

	public String getRuleName() {
		return ruleName;
	}

	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}

	public String getRuleType() {
		return ruleType;
	}

	public void setRuleType(String ruleType) {
		this.ruleType = ruleType;
	}

	public String getViolationType() {
		return violationType;
	}

	public void setViolationType(String violationType) {
		this.violationType = violationType;
	}

	public String getProblemCode() {
		return problemCode;
	}

	public void setProblemCode(String problemCode) {
		this.problemCode = problemCode;
	}

	public String getSegmentName() {
		return segmentName;
	}

	public void setSegmentName(String segmentName) {
		this.segmentName = segmentName;
	}

	@Override
	public String toString() {
		return System.lineSeparator() + "ruleType: " + ruleType + System.lineSeparator() + "mappingfilename: "
				+ mappingfilename + System.lineSeparator() + "description: " + description + System.lineSeparator()
				+ "violationType: " + violationType + System.lineSeparator() + "lineno: " + lineno
				+ System.lineSeparator() + "problemCode: " + problemCode 
				+System.lineSeparator() + "segmentName: " + segmentName + System.lineSeparator();
	}

}
