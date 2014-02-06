package com.odea.components.jquery.datatable.column;

import com.odea.components.util.DatatableBundle;

public class EditarPopUpColumn extends ActionColumn
{
	/* Contants */
	private static final String	DEFAULT_CSS_CLASS	= "editar";
	/* Bundles */
	private static final String	TITULO				= "DataTable.acciones.editar.titulo";

	private String				imagePath			= "odeaComponents/images/dataTable/modificarIcon.gif";
	private String				imagePassPath			= "odeaComponents/images/dataTable/passwordIcon.png";

	private String				htmlTitleElement;
	private String				requestParameter;

	public EditarPopUpColumn(String requestParametrer)
	{
		DatatableBundle bundle = new DatatableBundle();

		this.htmlTitleElement = bundle.getString(TITULO);
		this.requestParameter = requestParametrer;
	}

	public EditarPopUpColumn(String imagePath, String htmlTitle, String requestParameter, int aTargets)
	{

		this.fnRender = this.createFnRender(imagePath, htmlTitle, requestParameter);
		this.sClass = DEFAULT_CSS_CLASS;
		this.aTargets = aTargets;

		this.sDefaultContent = "";
		this.bSortable = false;
		this.bSearchable = false;
		this.bUseRendered = false;
		this.bVisible = true;
	}

	public String createFnRender(String imagePath, String htmlTitle, String requestParameter)
	{
		StringBuilder func = new StringBuilder();

		func.append("function (oObj) { ");
		func.append("elemento = oObj.aData; ");
		func.append("index = oObj.iDataRow; ");
		func.append("return \"<input type='image' src='" + imagePath + "' title='" + htmlTitle + "' onclick='modificarPopUp(\" + index + \",\" + JSON.stringify(elemento) + \", \\\""
				+ requestParameter + "\\\")'/> \"; }");

		return func.toString();
	}

	@Override
	public String getHTMLTag()
	{
		return "\"<input type='image' src='" + this.imagePath + "' title='" + this.htmlTitleElement + "' onclick='modificarPopUp(\" + oObj.iDataRow + \",\" + JSON.stringify(oObj.aData) + \", \\\""
				+ this.requestParameter + "\\\")'/> \"";
	}
	
	// Setea el tipo de imagen a candado
	public void isPassword(){
		this.imagePath = this.imagePassPath;
	}

	/* Setters & Getters */

	public String getImagePath()
	{
		return imagePath;
	}

	public void setImagePath(String imagePath)
	{
		this.imagePath = imagePath;
	}

	public String getHTMLTitleElement()
	{
		return htmlTitleElement;
	}

	public void setHTMLTitleElement(String hTMLTitleElement)
	{
		htmlTitleElement = hTMLTitleElement;
	}

	public String getRequestParameter()
	{
		return requestParameter;
	}

	public void setRequestParameter(String requestParameter)
	{
		this.requestParameter = requestParameter;
	}

}
