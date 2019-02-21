package org.barclays.bfg.mappingfiles.codequality.processor;

public class MapFile {

	private String fileName;
	private Snippet inputBranchingDiagram;
	private Snippet outputBranchingDiagram;
	private Snippet inputRecordDetails;
	private Snippet outputRecordDetails;
	private Snippet mappingInformation;
	private Snippet extendedRules;
	private String rootSegmentName;

	
	

	MapFile(String fileName, 
			Snippet inputBranchingDiagram, 
			Snippet outputBranchingDiagram, 
			Snippet inputRecordDetails,
			Snippet outputRecordDetails, 
			Snippet mappingInformation, 
			Snippet extendedRules, 
			String rootSegmentName
			) {

		this.fileName = fileName;
		this.inputBranchingDiagram = inputBranchingDiagram;
		this.outputBranchingDiagram = outputBranchingDiagram;
		this.inputRecordDetails = inputRecordDetails;
		this.outputRecordDetails = outputRecordDetails;
		this.mappingInformation = mappingInformation;
		this.extendedRules = extendedRules;
		this.rootSegmentName = rootSegmentName;
	}


	public String fileName() {
		return fileName;
	}

	
	public Snippet inputBranchingDiagram() {
		return inputBranchingDiagram;
	}

	public Snippet outputBranchingDiagram() {
		return outputBranchingDiagram;
	}

	public Snippet inputRecordDetails() {
		return inputRecordDetails;
	}

	public Snippet outputRecordDetails() {
		return outputRecordDetails;
	}

	public Snippet mappingInformation() {
		return mappingInformation;
	}

	public Snippet extendedRules() {
		return extendedRules;
	}


	public String rootSegmentName() {
		return rootSegmentName;
	}
	
}
