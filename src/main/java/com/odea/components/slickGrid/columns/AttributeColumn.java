package com.odea.components.slickGrid.columns;


public class AttributeColumn extends AbstractColumn {
	
	private String id;
	private String name;
	private String field;
	private boolean searchable;

	
	//Constructors
	
	public AttributeColumn(String id, String name, String field) {
		this.id = id;
		this.name = name;
		this.field = field;
	}
	
	public AttributeColumn(String id, String name, String field, boolean searchable) {
		this.id = id;
		this.name = name;
		this.field = field;
		this.searchable = searchable;
	}




	@Override
	public String toJson() {
		StringBuilder json = new StringBuilder();
		json.append("{");
		
		json.append("id: '" + this.id + "',");
		json.append("name: '" + this.name + "',");
		json.append("field: '" + this.id + "',");
		json.append("searchable: " + this.searchable);
		
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


	public boolean isSearchable() {
		return searchable;
	}


	public void setSearchable(boolean searchable) {
		this.searchable = searchable;
	}
	
	
}
