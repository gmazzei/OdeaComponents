package com.odea.components.jquery.datatable.column;

import com.odea.components.util.DatatableBundle;

public class DeleteColumn extends ActionColumn
{
	/* Contants */
	private static final String	DEFAULT_CSS_CLASS		= "borrar";
	/* Bundles */
	private static final String	TITULO					= "DataTable.acciones.borrar.titulo";
	private static final String	MENSAJE_CONFIRMACION	= "DataTable.acciones.borrar.confirmar";

	private String				imagePath				= "odeaComponents/images/dataTable/delete.png";
	private String				htmlTitleElement;
	private String				confirmationMessage;
	private String				requestParameter;

	public DeleteColumn(String requestParametrer)
	{
		DatatableBundle bundle = new DatatableBundle();

		this.htmlTitleElement = bundle.getString(TITULO);
		this.confirmationMessage = bundle.getString(MENSAJE_CONFIRMACION);
		this.requestParameter = requestParametrer;
	}

	public DeleteColumn(String imagePath, String htmlTitle, String requestParameter, String confirmationMessage, int aTargets)
	{

		this.fnRender = this.createFnRender(imagePath, htmlTitle, requestParameter, confirmationMessage);
		this.sClass = DEFAULT_CSS_CLASS;
		this.aTargets = aTargets;

		this.sDefaultContent = "";
		this.bSortable = false;
		this.bSearchable = false;
		this.bUseRendered = false;
		this.bVisible = true;
	}

	public String createFnRender(String imagePath, String htmlTitle, String requestParameter, String confirmationMessage)
	{
		StringBuilder func = new StringBuilder();

		func.append("function (oObj) { ");
		func.append("elemento = oObj.aData; ");
		func.append("index = oObj.iDataRow; ");
		func.append("return \"<input type='image' src='" + imagePath + "' title='" + htmlTitle + "' onclick='borrar(\" + index + \",\" + JSON.stringify(elemento) + \", \\\"" + confirmationMessage
				+ "\\\", \\\"" + requestParameter + "\\\")'/> \"; }");

		return func.toString();
	}

	@Override
	public String getHTMLTag()
	{
		return "\"<input type='image' src='" + this.imagePath + "' title='" + this.htmlTitleElement + "' onclick='borrar(\" + oObj.iDataRow + \",\" + JSON.stringify(oObj.aData) + \", \\\""
				+ this.confirmationMessage + "\\\", \\\"" + this.requestParameter + "\\\")'/> \"";
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

	public String getConfirmationMessage()
	{
		return confirmationMessage;
	}

	public void setConfirmationMessage(String confirmationMessage)
	{
		this.confirmationMessage = confirmationMessage;
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
