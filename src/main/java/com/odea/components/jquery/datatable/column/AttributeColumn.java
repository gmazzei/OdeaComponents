package com.odea.components.jquery.datatable.column;

public class AttributeColumn implements Jsonizable
{

	private String	mDataProp;

	public AttributeColumn(String mDataProp)
	{
		this.mDataProp = mDataProp;
	}

	public String toJson()
	{
		return "{ \"mDataProp\": \"" + this.mDataProp + "\"}";
	}

	// GETTERS & SETTERS

	public String getmDataProp()
	{
		return mDataProp;
	}

	public void setmDataProp(String mDataProp)
	{
		this.mDataProp = mDataProp;
	}

}
