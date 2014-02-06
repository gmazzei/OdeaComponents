package com.odea.components.jquery.datatable;

import java.io.Serializable;

import com.odea.components.util.DatatableBundle;

/**
 * Entidad creada para configurar mensajes, idioma y complementos de la tabla. Los complementos pueden ser buscador, combo de cantidad de elementos por pagina, etc.
 */

public class DatatableConfiguration implements Serializable
{

	/** p: anterior siguiente - t: tabla - i: Mostrando del 1 al .. - r: contenido de la tabla - f: Busqueda - l: Cantidad a mostrar por pagina */
	private static final String	POSICIONAMIENTO_COMPLEMENTOS	= "fptril";

	/* Bundles por defecto */
	private static final String	BUNDLE_LENGTHMENU				= "DataTable.lengthMenu";
	private static final String	BUNDLE_ZERORECORDS				= "DataTable.zeroRecords";
	private static final String	BUNDLE_INFO						= "DataTable.info";
	private static final String	BUNDLE_INFOEMPTY				= "DataTable.infoEmpty";
	private static final String	BUNDLE_INFOFILTERED				= "DataTable.infoFiltered";
	// private static final String BUNDLE_SEARCH = "DataTable.search";
	// private static final String BUNDLE_PREVIOUS = "DataTable.previous";
	// private static final String BUNDLE_NEXT = "DataTable.next";

	private String				sLengthMenu;
	private String				sZeroRecords;
	private String				sInfo;
	private String				sInfoEmpty;
	private String				sInfoFiltered;
	private String				sSearch;
	private String				sPrevious;
	private String				sNext;
	private String				sDom;
	private Boolean				bPaginate;

	/**
	 * Constructor con las configuraciones por default.
	 * 
	 * @param bundle
	 *            : el bundle con los textos a utilizar.
	 */
	public DatatableConfiguration()
	{
		DatatableBundle bundle = new DatatableBundle();
		
		this.sLengthMenu = bundle.getString(BUNDLE_LENGTHMENU);
		this.sZeroRecords = bundle.getString(BUNDLE_ZERORECORDS);
		this.sInfo = bundle.getString(BUNDLE_INFO);
		this.sInfoEmpty = bundle.getString(BUNDLE_INFOEMPTY);
		this.sInfoFiltered = bundle.getString(BUNDLE_INFOFILTERED);
		this.sSearch = "";
		this.sPrevious = "";
		this.sNext = "";
		this.sDom = POSICIONAMIENTO_COMPLEMENTOS;
		this.bPaginate = true;
	}

	public DatatableConfiguration(	String sLengthMenu,
									String sZeroRecords,
									String sInfo,
									String sInfoEmpty,
									String sInfoFiltered,
									String sSearch,
									String sPrevious,
									String sNext,
									String sDom,
									Boolean bPaginate)
	{
		this.sLengthMenu = sLengthMenu;
		this.sZeroRecords = sZeroRecords;
		this.sInfo = sInfo;
		this.sInfoEmpty = sInfoEmpty;
		this.sInfoFiltered = sInfoFiltered;
		this.sSearch = "";
		this.sPrevious = "";
		this.sNext = "";
		this.sDom = sDom;
		this.bPaginate = bPaginate;
	}

	public String getLanguageJson()
	{
		StringBuilder json = new StringBuilder();

		json.append("{\"sLengthMenu\": " + this.getsLengthMenu() + ", ");
		json.append("\"sZeroRecords\": " + this.getsZeroRecords() + ", ");
		json.append("\"sInfo\": " + this.getsInfo() + ", ");
		json.append("\"sInfoEmpty\": " + this.getsInfoEmpty() + ", ");
		json.append("\"sInfoFiltered\": " + this.getsInfoFiltered() + ", ");
		json.append("\"sSearch\" : " + this.getsSearch() + ", ");
		json.append("\"oPaginate\": {\"sPrevious\": " + this.getsPrevious() + ", \"sNext\": " + this.getsNext() + "}}");

		return json.toString();
	}

	/* Metodo utilizado para setear los componentes de la tabla */
	public void setComplements(String complements)
	{
		this.sDom = complements;
	}

	public String getComplements()
	{
		return this.sDom;
	}

	/* Metodo utilizado para setear si la informacion de paginado sera visible o no (No depende de la funcionalidad de paginado) */
	public void setPagination(Boolean pagination)
	{
		this.bPaginate = pagination;
	}

	public Boolean getPagination()
	{
		return this.bPaginate;
	}

	// GETTERS & SETTERS

	public String getsLengthMenu()
	{
		if (this.sLengthMenu != null)
		{
			return "\"" + sLengthMenu + "\"";
		}
		else
		{
			return null;
		}
	}

	public void setsLengthMenu(String sLengthMenu)
	{
		this.sLengthMenu = sLengthMenu;
	}

	public String getsZeroRecords()
	{
		if (this.sZeroRecords != null)
		{
			return "\"" + sZeroRecords + "\"";
		}
		else
		{
			return null;
		}
	}

	public void setsZeroRecords(String sZeroRecords)
	{
		this.sZeroRecords = sZeroRecords;
	}

	public String getsInfo()
	{
		if (this.sInfo != null)
		{
			return "\"" + sInfo + "\"";
		}
		else
		{
			return null;
		}
	}

	public void setsInfo(String sInfo)
	{
		this.sInfo = sInfo;
	}

	public String getsInfoEmpty()
	{
		if (this.sInfoEmpty != null)
		{
			return "\"" + sInfoEmpty + "\"";
		}
		else
		{
			return null;
		}
	}

	public void setsInfoEmpty(String sInfoEmpty)
	{
		this.sInfoEmpty = sInfoEmpty;
	}

	public String getsInfoFiltered()
	{
		if (this.sInfoFiltered != null)
		{
			return "\"" + sInfoFiltered + "\"";
		}
		else
		{
			return null;
		}
	}

	public void setsInfoFiltered(String sInfoFiltered)
	{
		this.sInfoFiltered = sInfoFiltered;
	}

	public String getsSearch()
	{
		if (this.sSearch != null)
		{
			return "\"" + sSearch + "\"";
		}
		else
		{
			return null;
		}
	}

	public void setsSearch(String sSearch)
	{
		this.sSearch = sSearch;
	}

	public String getsPrevious()
	{
		if (this.sPrevious != null)
		{
			return "\"" + sPrevious + "\"";
		}
		else
		{
			return null;
		}
	}

	public void setsPrevious(String sPrevious)
	{
		this.sPrevious = sPrevious;
	}

	public String getsNext()
	{
		if (this.sNext != null)
		{
			return "\"" + sNext + "\"";
		}
		else
		{
			return null;
		}
	}

	public void setsNext(String sNext)
	{
		this.sNext = sNext;
	}

	public Boolean getbPaginate()
	{
		return bPaginate;
	}

	public void setbPaginate(Boolean bPaginate)
	{
		this.bPaginate = bPaginate;
	}

	/** p: anterior siguiente - t: tabla - i: Mostrando del 1 al .. - r: contenido de la tabla - f: Busqueda - l: Cantidad a mostrar por pagina */
	public String getsDom()
	{
		return sDom;
	}

	/** p: anterior siguiente - t: tabla - i: Mostrando del 1 al .. - r: contenido de la tabla - f: Busqueda - l: Cantidad a mostrar por pagina */
	public void setsDom(String sDom)
	{
		this.sDom = sDom;
	}

}
