package com.odea.components.slickGrid;

public class SlickGridConfiguration {

	private String idAttribute;
	private String groupingAttribute = null; 

	
	public SlickGridConfiguration() {
		
	}	
	
	public SlickGridConfiguration(String idAttribute, String groupingAttribute) {
		this.idAttribute = idAttribute;
		this.groupingAttribute = groupingAttribute;
	}

	//Getters & Setters

	public String getIdAttribute() {
		return idAttribute;
	}
	
	public void setIdAttribute(String idAttribute) {
		this.idAttribute = idAttribute;
	}
	
	public String getGroupingAttribute() {
		return groupingAttribute;
	}

	public void setGroupingAttribute(String groupingAttribute) {
		this.groupingAttribute = groupingAttribute;
	}
	
}
