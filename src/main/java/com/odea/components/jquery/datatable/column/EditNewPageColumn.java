package com.odea.components.jquery.datatable.column;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.cycle.RequestCycle;

import com.odea.components.util.DatatableBundle;

public class EditNewPageColumn extends ActionColumn
{
	/* Contants */
	private static final String	DEFAULT_CSS_CLASS	= "editar";
	/* Bundles */
	private static final String	TITULO				= "DataTable.acciones.editar.titulo";

	private String				imagePath			= "odeaComponents/images/dataTable/modificarIcon.gif";
	private String				htmlTitleElement;
	private String				urlParameterName;
	Class<? extends WebPage>	paginaReceptora;

	public EditNewPageColumn(Class<? extends WebPage> paginaReceptora, String urlParameterName)
	{
		DatatableBundle bundle = new DatatableBundle();

		this.urlParameterName = urlParameterName;
		this.paginaReceptora = paginaReceptora;
		this.htmlTitleElement = bundle.getString(TITULO);
	}

	public EditNewPageColumn(String imagePath, String htmlTitle, Class<? extends WebPage> paginaReceptora, String urlParameterName, int aTargets)
	{

		this.sClass = DEFAULT_CSS_CLASS;
		this.aTargets = aTargets;
		this.sDefaultContent = "";
		this.bSortable = false;
		this.bSearchable = false;
		this.bUseRendered = false;
		this.bVisible = true;
		this.fnRender = this.createFnRender(imagePath, htmlTitle, paginaReceptora, urlParameterName);
	}

	public String createFnRender(String imagePath, String htmlTitle, Class<? extends WebPage> paginaReceptora, String urlParameterName)
	{

		StringBuilder func = new StringBuilder();

		func.append("function (oObj) { ");
		func.append("elemento = oObj.aData; ");
		func.append("return \"<input type='image' src='" + imagePath + "' title='" + htmlTitle + "' onclick='modificar(\\\"" + RequestCycle.get().urlFor(paginaReceptora, null)
				+ "\\\",\" + JSON.stringify(elemento) + \", \\\"" + urlParameterName + "\\\")'/> \";}");

		return func.toString();

	}

	@Override
	public String getHTMLTag()
	{
		return "\"<input type='image' src='" + this.imagePath + "' title='" + this.htmlTitleElement + "' onclick='modificar(\\\"" + RequestCycle.get().urlFor(this.paginaReceptora, null)
				+ "\\\",\" + JSON.stringify(oObj.aData) + \", \\\"" + this.urlParameterName + "\\\")'/> \"";
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

	public String getHtmlTitleElement()
	{
		return htmlTitleElement;
	}

	public void setHtmlTitleElement(String htmlTitleElement)
	{
		this.htmlTitleElement = htmlTitleElement;
	}

	public String getUrlParameterName()
	{
		return urlParameterName;
	}

	public void setUrlParameterName(String urlParameterName)
	{
		this.urlParameterName = urlParameterName;
	}

	public Class<? extends WebPage> getPaginaReceptora()
	{
		return paginaReceptora;
	}

	public void setPaginaReceptora(Class<? extends WebPage> paginaReceptora)
	{
		this.paginaReceptora = paginaReceptora;
	}

}
