package com.odea.components.jquery.datatable.column;

public class CheckboxColumn extends ActionColumn
{
	/* Contants */
	private static final String	DEFAULT_CSS_CLASS	= "checkbox";

	private String				htmlTitleElement;
	private String				attributeName;
	private String				elementParameterName;
	private String				stateParameterName;

	public CheckboxColumn(String htmlTitleElement, String attributeName, String elementParameterName, String stateParameterName)
	{
		this.htmlTitleElement = htmlTitleElement;
		this.attributeName = attributeName;
		this.elementParameterName = elementParameterName;
		this.stateParameterName = stateParameterName;
	}

	public CheckboxColumn(String htmlTitle, String elementParameterName, String stateParameterName, String attributeName, int aTargets)
	{

		this.fnRender = this.createFnRender(attributeName, htmlTitle, elementParameterName, stateParameterName);
		this.sClass = DEFAULT_CSS_CLASS;
		this.aTargets = aTargets;

		this.sDefaultContent = "";
		this.bSortable = false;
		this.bSearchable = false;
		this.bUseRendered = false;
		this.bVisible = true;
	}

	private String createFnRender(String attributeName, String htmlTitle, String elementParameterName, String stateParameterName)
	{
		StringBuilder func = new StringBuilder();

		func.append("function (oObj) { ");
		func.append("elemento = oObj.aData; ");
		func.append("index = oObj.iDataRow;	");
		func.append("checked = elemento[\"" + attributeName + "\"] ? \"checked\" : \"\"; ");
		func.append("return \"<input type='checkbox' title='" + htmlTitle + "' onclick='cambiarEstado(\" + JSON.stringify(elemento) + \", \" + elemento[\"" + attributeName + "\"] + \", \\\""
				+ elementParameterName + "\\\", \\\"" + stateParameterName + "\\\")' + \" + checked + \"/>\";}");

		return func.toString();
	}

	@Override
	public String getHTMLTag()
	{
		return "\"<input type='checkbox' title='" + this.htmlTitleElement + "' onclick='cambiarEstado(\" + JSON.stringify(oObj.aData) + \", \" + oObj.aData[\"" + this.attributeName + "\"] + \", \\\""
				+ this.elementParameterName + "\\\", \\\"" + this.stateParameterName + "\\\")' + \" + ( oObj.aData[\"" + this.attributeName + "\"] ? \"checked\" : \"\" ) + \"/> \"";

	}

}
