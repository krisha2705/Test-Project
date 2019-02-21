package org.barclays.bfg.mappingfiles.codequality.cli;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ApplicationConfiguration {
	private static final Logger LOGGER = Logger.getLogger(ApplicationConfiguration.class);

	private ApplicationConfiguration() {

	}

	@SerializedName("logdirectory")
	@Expose
	String logdirectory;

	@SerializedName("outputdirectory")
	@Expose
	String outputdirectory;

	@SerializedName("outputformat")
	@Expose
	String outputformat;

	@SerializedName("reportname")
	@Expose
	String reportname;

	@SerializedName("violationlevels")
	@Expose
	Map<String, String> ruleTypeTOViolationType = new HashMap<>();

	public String getLogdirectory() {
		return logdirectory;
	}

	public void setLogdirectory(String logdirectory) {
		this.logdirectory = logdirectory;
	}

	public String getOutputdirectory() {
		return outputdirectory;
	}

	public void setOutputdirectory(String outputdirectory) {
		this.outputdirectory = outputdirectory;
	}

	public String getOutputformat() {
		return outputformat;
	}

	public void setOutputformat(String outputformat) {
		this.outputformat = outputformat;
	}

	public String getReportname() {
		return reportname;
	}

	public void setReportname(String reportname) {
		this.reportname = reportname;
	}

	public Map<String, String> getRuleTypeTOViolationType() {
		return ruleTypeTOViolationType;
	}

	public void setRuleTypeTOViolationType(Map<String, String> ruleTypeTOViolationType) {
		this.ruleTypeTOViolationType = ruleTypeTOViolationType;
	}

	@Override
	public String toString() {
		return "ApplicationConfiguration [logdirectory=" + logdirectory + ", outputdirectory=" + outputdirectory
				+ ", outputformat=" + outputformat + ", reportname=" + reportname + ", ruleTypeTOViolationType="
				+ ruleTypeTOViolationType + "]";
	}

	public static ApplicationConfiguration loadApplicationConfiguration() throws FileNotFoundException {
		Gson gson = new Gson();
		BufferedReader br = null;

		URL url = ApplicationConfiguration.class.getClassLoader().getResource("appconfig.json");
		try {
			String filepath = url.toURI().getPath();
			br = new BufferedReader(new FileReader(filepath));
		} catch (URISyntaxException e) {
			LOGGER.error("file path not correct", e);
		}

		ApplicationConfiguration appconfig = gson.fromJson(br, ApplicationConfiguration.class);
		LOGGER.debug("ApplicationConfiguration" + appconfig);

		return appconfig;
	}
}
