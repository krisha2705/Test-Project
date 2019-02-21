package org.barclays.bfg.mappingfiles.codequality.rules;


import java.util.List;

import org.barclays.bfg.mappingfiles.codequality.processor.MapFile;
import org.barclays.bfg.mappingfiles.codequality.report.Report;

public interface IRule {

	List<Report> execute(MapFile parsedFile);


}