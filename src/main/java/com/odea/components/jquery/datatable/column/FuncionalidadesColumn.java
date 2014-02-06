package com.odea.components.jquery.datatable.column;

import java.util.ArrayList;

public class FuncionalidadesColumn extends ActionColumn
{
	/* Contants */
	private static final String	DEFAULT_CSS_CLASS	= "funcionalidades";

	public FuncionalidadesColumn(ArrayList<ActionColumn> acciones, int aTargets)
	{

		this.fnRender = this.createFnRender(acciones);
		this.aTargets = aTargets;

		this.sClass = DEFAULT_CSS_CLASS;
		this.sDefaultContent = "";
		this.bSortable = false;
		this.bSearchable = false;
		this.bUseRendered = false;
		this.bVisible = true;
	}

	public String createFnRender(ArrayList<ActionColumn> acciones)
	{

		StringBuilder func = new StringBuilder();

		func.append("function (oObj) { ");
		func.append("return \"\"");

		for (ActionColumn actionColumn : acciones)
		{
			func.append(" + ");
			func.append(actionColumn.getHTMLTag());
		}

		func.append(";}");

		return func.toString();

	}

	/* No devuelve nada ya que no debe ser invocado */
	public String getHTMLTag()
	{
		return null;
	}

}
