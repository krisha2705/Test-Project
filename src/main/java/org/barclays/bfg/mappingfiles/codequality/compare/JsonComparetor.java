/**
 * 
 */
package org.barclays.bfg.mappingfiles.codequality.compare;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;
import org.barclays.bfg.mappingfiles.codequality.report.Report;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * @author prakumar53
 *
 */
public class JsonComparetor {
	
	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger.getLogger(JsonComparetor.class);
	
	
	String lastReportFile;
	String currentReportFile;
	
	public JsonComparetor(String lastReportFile, String currentReportFile) {
		super();
		this.lastReportFile = lastReportFile;
		this.currentReportFile = currentReportFile;
	}
	
	List<Report> compare(String ruleTypeString) {
		
		LOGGER.debug("Started compare for " + ruleTypeString);
		Gson gson = new Gson();
		ArrayList<Report> diffReportList = new ArrayList<>();
		Type type = new TypeToken<List<Report>>() {}.getType();
		List<Report> lastReportList = gson.fromJson(this.lastReportFile, type);
		List<Report> currentReportList = gson.fromJson(this.currentReportFile, type);
		
		if (Collections.disjoint(lastReportList, currentReportList)) {
			
			for (Report ruleNew: currentReportList) {
				if (ruleNew.getRuleType().equals(ruleTypeString)) {
					for (Report ruleOld: lastReportList) {
						if ( (ruleOld.getRuleType().equals(ruleTypeString)) &&
							 (!ruleOld.getProblemCode().equals(ruleNew.getProblemCode()))){
							LOGGER.debug("New violation found in " +ruleTypeString);
							diffReportList.add(ruleNew);
							break;
						}
					}
				}
			}
		}
		
		LOGGER.debug("Completed compare");
		return diffReportList;
		
	}

}
