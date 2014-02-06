package com.odea.components.jquery.datatable.column;

import com.odea.components.util.DatatableBundle;

public class BugzillaTicketColumn extends ActionColumn
{
	/* Contants */
	private static final String	DEFAULT_CSS_CLASS		= "bugzilla";
	private static final String	BUGZILLA_URL			= "http://odea-apps.dyndns.org/bugzilla";
	private static final String	BUGZILLA_SHOW_TICKET	= "/show_bug.cgi?id=";

	/* Bundles */
	private static final String	TITULO					= "DataTable.acciones.bugzilla.titulo";

	private String				htmlTitleElement;
	private String				attributeName;

	public BugzillaTicketColumn(String attributeName)
	{
		DatatableBundle bundle = new DatatableBundle();

		this.htmlTitleElement = bundle.getString(TITULO);
		this.attributeName = attributeName;
	}

	public BugzillaTicketColumn(String htmlTitle, String attributeName, int aTargets)
	{

		this.fnRender = this.createFnRender(htmlTitle, attributeName);

		this.sClass = DEFAULT_CSS_CLASS;
		this.aTargets = aTargets;
		this.sDefaultContent = "";
		this.bSearchable = true;
		this.bSortable = true;
		this.bVisible = true;
		this.bUseRendered = false;
	}

	private String createFnRender(String htmlTitle, String attributeName)
	{
		StringBuilder func = new StringBuilder();

		String title;
		if (htmlTitle != null)
		{
			title = "'title=\"" + htmlTitle + "\"'";
		}
		else
		{
			title = "";
		}

		func.append("function(oObj) { ");
		func.append("elemento = oObj.aData; ");
		func.append("ticket = elemento['" + attributeName + "'];  ");
		// TODO : sacar la url hardcoded para que la tenga que meter quien implementa y la parte de show_bug en una variable final estatica hasta decidir donde va (properties)
		func.append("urlBugzilla = '" + BUGZILLA_URL + BUGZILLA_SHOW_TICKET + "' + ticket; ");
		func.append("return '<a '+ " + title + " + ' target=\"blank\" href=\"' + urlBugzilla + '\">' + (ticket != null ? ticket : '') + '</a>'; }");

		return func.toString();
	}

	@Override
	public String getHTMLTag()
	{
		return "'<a title=\"" + this.htmlTitleElement + "\" target=\"blank\" href=\"" + BUGZILLA_URL + BUGZILLA_SHOW_TICKET + "' + oObj.aData['" + this.attributeName + "'] + '\">' +  (oObj.aData['"
				+ this.attributeName + "'] != null ? oObj.aData['" + this.attributeName + "'] : '') + '</a>'";
	}

}
