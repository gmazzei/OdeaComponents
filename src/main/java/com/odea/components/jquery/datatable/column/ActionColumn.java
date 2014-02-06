package com.odea.components.jquery.datatable.column;

public abstract class ActionColumn implements Jsonizable {
	
	protected String fnRender;
	protected String sDefaultContent;
	protected boolean bSortable;
	protected boolean bSearchable;
	protected boolean bUseRendered;
	protected boolean bVisible;
	protected String sClass;
	protected int aTargets;
	
	public ActionColumn() {}
	
	public ActionColumn(String fnRender, String sDefaultContent, boolean bSortable, boolean bSearchable, boolean bUseRendered, boolean bVisible, String sClass, int aTargets) {
		
		this.fnRender = fnRender;
		this.sDefaultContent = sDefaultContent;
		this.bSortable = bSortable;
		this.bSearchable = bSearchable;
		this.bUseRendered = bUseRendered;
		this.bVisible = bVisible;
		this.sClass = sClass;
		this.aTargets = aTargets;
	}
	
	
	public String toJson() {
		return "{\"fnRender\": " + this.getFnRender() + ", \"sDefaultContent\": \"" + this.getsDefaultContent() + "\", \"bSortable\": " + this.isbSortable() + ", \"bSearchable\": " + this.isbSearchable() + ", \"bUseRendered\": " + this.isbUseRendered() + ", \"bVisible\": " + this.isbVisible() + ", \"sClass\": \"" + this.getsClass() + "\", \"aTargets\": [" + this.getaTargets() + "]}";
	}
	
	public abstract String getHTMLTag();
	
	
	//GETTERS & SETTERS
	
	public String getFnRender() {
		return fnRender;
	}
	public void setFnRender(String fnRender) {
		this.fnRender = fnRender;
	}
	public String getsDefaultContent() {
		return sDefaultContent;
	}
	public void setsDefaultContent(String sDefaultContent) {
		this.sDefaultContent = sDefaultContent;
	}
	public boolean isbSortable() {
		return bSortable;
	}
	public void setbSortable(boolean bSortable) {
		this.bSortable = bSortable;
	}
	public boolean isbSearchable() {
		return bSearchable;
	}
	public void setbSearchable(boolean bSearchable) {
		this.bSearchable = bSearchable;
	}
	public boolean isbUseRendered() {
		return bUseRendered;
	}
	public void setbUseRendered(boolean bUseRendered) {
		this.bUseRendered = bUseRendered;
	}
	public boolean isbVisible() {
		return bVisible;
	}
	public void setbVisible(boolean bVisible) {
		this.bVisible = bVisible;
	}
	public String getsClass() {
		return sClass;
	}
	public void setsClass(String sClass) {
		this.sClass = sClass;
	}
	public int getaTargets() {
		return aTargets;
	}
	public void setaTargets(int aTargets) {
		this.aTargets = aTargets;
	}
	
}
