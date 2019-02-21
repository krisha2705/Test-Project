package org.barclays.bfg.mappingfiles.codequality.report;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;
import org.barclays.bfg.mappingfiles.codequality.cli.ApplicationConfiguration;
import org.json.JSONException;

import com.google.gson.GsonBuilder;

/**
 * The Class ReportPublisher.
 */
public class ReportPublisher {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger.getLogger(ReportPublisher.class);

	/**
	 * Generate report.
	 *
	 * @param cliConfig
	 *            the cli config
	 * @param report
	 *            the report
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public void generateReport(List<Report> report) throws IOException {

		publishToJson(report);

		publishToSummary(report);

	}

	/**
	 * Publish to summary.
	 *
	 * @param reportList
	 *            the report list
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	private void publishToSummary(List<Report> reportList) throws IOException {

		ApplicationConfiguration appConfig = ApplicationConfiguration.loadApplicationConfiguration();
		String parsingFileName = appConfig.getReportname();
		File file = new File(appConfig.getOutputdirectory() + parsingFileName + ".html");

		FileWriter writer = new FileWriter(file);
		writer.append(" Code Quality :" + (parsingFileName));

		writer.append("<style> table, th, td { border: 1px solid black; border-collapse: collapse; }"
				+ "th, td { padding: 10px; }" + "table#alter tr:nth-child(even) {background-color: #eee;}"
				+ "table#alter tr:nth-child(odd) {background-color: #fff;}"
				+ "table#alter th {color: white; background-color: gray;} </style>");

		writer.append("<table id=alter >");
		writer.append("<tr><th> " + "Ruletype:" + "</th>");
		writer.append("<th> " + "Line No:" + "</th>");
		writer.append("<th> " + "Description:" + "</th></tr>");

		try {
			for (Report report : reportList) {
				writer.append("<tr><td>" + (report.getRuleName()) + "</td>");
				writer.append("<td> " + (report.getLineno()) + "</td>");
				writer.append("<td> " + (report.getDescription()) + "</td></tr>");
			}

		} catch (Exception e) {
			LOGGER.error("Exception while printing Summary Report", e);

		} finally {
			writer.append("</table>");
			writer.close();
		}
		writer.close();
	}

	/**
	 * Publish to json.
	 *
	 * @param reportList
	 *            the report list
	 */
	public void publishToJson(List<Report> reportList) throws IOException {

		ApplicationConfiguration appConfig;
		appConfig = ApplicationConfiguration.loadApplicationConfiguration();
		String parsingFileName = appConfig.getReportname();
		File file = new File(appConfig.getOutputdirectory() + parsingFileName + ".json");
		FileWriter writer = new FileWriter(file);

		try {
			GsonBuilder gsonBuilder = new GsonBuilder();
			String jsonOutput = gsonBuilder.setPrettyPrinting().disableHtmlEscaping().create().toJson(reportList);
			writer.append(jsonOutput);
			LOGGER.debug("JSON Output :" + jsonOutput);

		} catch (JSONException e) {
			LOGGER.error("Exception while printing JSON", e);
		} catch (FileNotFoundException e) {
			LOGGER.error("FileNotFoundException while printing JSON", e);
		} finally {
			writer.close();
		}
	}
}
