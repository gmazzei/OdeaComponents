package com.odea.components.slickGrid.columns;

public class CheckboxColumn extends AbstractColumn {

	private String id;
	private String name;
	private String field;
	private final String formatter = "Slick.Formatters.Checkmark";
	private final String editor = "Slick.Editors.Checkbox";

	
	public CheckboxColumn() {
	
	}


	public CheckboxColumn(String id, String name, String field) {
		this.id = id;
		this.name = name;
		this.field = field;
	}


	@Override
	public String toJson() {
		StringBuilder json = new StringBuilder();
		json.append("{");
		
		json.append("id: '" + this.id + "',");
		json.append("name: '" + this.name + "',");
		json.append("field: '" + this.id + "',");
		json.append("formatter: " + this.formatter + ",");
		json.append("editor: " + this.editor);
		
		json.append("}");
		
		return json.toString();
	}

	
	//Getters & Setters

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getEditor() {
		return editor;
	}

	public String getFormatter() {
		return formatter;
	}
	
}
