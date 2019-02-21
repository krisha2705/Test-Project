package org.barclays.bfg.mappingfiles.codequality.processor;

public class MappingFileSection {
	
	public enum SectionType {INPUTBranchingDiagram,OUTPUTBranchingDiagram,INPUTRecordDetails,OUTPUTRecordDetails,MappingInformation,ExtendedRules }
	
	SectionType sectiontype;
	
	public SectionType getSectiontype() {
		return sectiontype;
	}
	
	public void setSectiontype(SectionType sectiontype) {
		this.sectiontype = sectiontype;
	}
	
	
	StringBuffer sectioncontent;
	
	public StringBuffer getSectioncontent() {
		return sectioncontent;
	}
	public void setSectioncontent(StringBuffer sectioncontent) {
		this.sectioncontent = sectioncontent;
	}
	
}
