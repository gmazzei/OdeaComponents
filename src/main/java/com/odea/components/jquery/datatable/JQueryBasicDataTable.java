package com.odea.components.jquery.datatable;

import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.wicket.Application;
import org.apache.wicket.AttributeModifier;
import org.apache.wicket.ajax.AbstractDefaultAjaxBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.head.JavaScriptContentHeaderItem;
import org.apache.wicket.markup.html.IHeaderContributor;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.request.handler.TextRequestHandler;
import org.apache.wicket.util.template.PackageTextTemplate;

import com.odea.components.ajax.AbstractInitializableComponentBehavior;
import com.odea.components.jquery.datatable.column.ActionColumn;
import com.odea.components.jquery.datatable.column.AttributeColumn;
import com.odea.components.jquery.datatable.column.Jsonizable;

public abstract class JQueryBasicDataTable<T extends Jsonizable> extends WebMarkupContainer implements IHeaderContributor {
	
    private static final String JSON_CONTENT_TYPE = "application/json";
    private static final String ENCODING = Application.get().getMarkupSettings().getDefaultMarkupEncoding();
	
    private AbstractDefaultAjaxBehavior ajaxBehavior;
    
	public JQueryBasicDataTable(String id) {
        super(id);
        this.setOutputMarkupId(true);
        this.add(new AttributeModifier("class", new Model<String>("display")));
        
        this.prepare();
    }


	private void prepare() {
    	
		this.add(new AbstractInitializableComponentBehavior(){
			public String getInitJSCall() {
				return "initTable();";
			}
		});
    	
		
        this.ajaxBehavior = new AbstractDefaultAjaxBehavior() {
            @Override
        	public void respond(AjaxRequestTarget target) {
            	
                RequestCycle requestCycle = RequestCycle.get();
                Request request = requestCycle.getRequest();

                String jsonResultList = JQueryBasicDataTable.this.formatColumnData(JQueryBasicDataTable.this.getSearchResults());
                
                TextRequestHandler jsonHandler = new TextRequestHandler(JSON_CONTENT_TYPE, ENCODING, jsonResultList);
                requestCycle.scheduleRequestHandlerAfterCurrent(jsonHandler);    	
           
                JQueryBasicDataTable.this.onRequestParameters(request, target);
            }
            
        };
        
		this.add(this.ajaxBehavior);
		
    }
	
    @Override
    protected void onAfterRender() {
    	
    	PackageTextTemplate jquery = new PackageTextTemplate(JQueryBasicDataTable.class, "jquery.js", "text/javascript");
        String jQueryResource = jquery.asString();
        String jQueryUniqueName = Long.toString(Calendar.getInstance().getTimeInMillis());
        new JavaScriptContentHeaderItem(jQueryResource, jQueryUniqueName + "js", null).render(this.getResponse());
        
        
        PackageTextTemplate jqueryDatatables = new PackageTextTemplate(JQueryBasicDataTable.class, "jquery.dataTables.min.js", "text/javascript");
        String jQueryDatatablesResource = jqueryDatatables.asString();
        String dataTablesUniqueName = Long.toString(Calendar.getInstance().getTimeInMillis());
        new JavaScriptContentHeaderItem(jQueryDatatablesResource, dataTablesUniqueName + "js", null).render(this.getResponse());
        
    	
        DatatableConfiguration config = this.getDatatableConfiguration();
        
        Map<String, CharSequence> map = new HashMap<String, CharSequence>(3);
        map.put("dataTableId", this.getMarkupId());
        map.put("selector", "#" + this.getMarkupId());
        map.put("callbackUrl", this.ajaxBehavior.getCallbackUrl());
        map.put("columns", this.toJson(this.getAttributeColumns()));
        map.put("columnasAccion", this.toJson(this.getActionColumns()));
        map.put("language", config.getLanguageJson());
        map.put("complements", config.getComplements());
        map.put("pagination", config.getPagination().toString());
        
        PackageTextTemplate datatabletemplate = new PackageTextTemplate(JQueryBasicDataTable.class, "datatabletemplate.js", "text/javascript");
        String templateResource = datatabletemplate.asString(map);
        String templateUniqueName = Long.toString(Calendar.getInstance().getTimeInMillis());
        new JavaScriptContentHeaderItem(templateResource, templateUniqueName + "js", null).render(this.getResponse());
        
        super.onAfterRender();
    }
    
	
    
	//Metodos para formatear columnas a JSON

    private String formatColumnData(Collection<T> results) {
        return "{ \"aaData\": " + this.toJson(results) + "}";
    }
    
    
    private String toJson(Collection<? extends Jsonizable> elementList) {
        
    	StringBuilder json = new StringBuilder();
    	json.append("[");
    	
    	Integer i = 1;
    	
    	for (Jsonizable element : elementList) {
    		json.append(element.toJson());
    	
    		if (i < elementList.size()) {
    			json.append(", ");				
			}
    		
    		i++;
		}
    	
    	json.append("]");
    	
    	return json.toString();
    }

    
    public void refresh(AjaxRequestTarget target) {
    	target.appendJavaScript("recargarTabla()");
    }
	

    //Metodos abstractos
    
    /**
     * Obtiene la colecci&oacute;n de elementos con los que ser&aacute; llenada la DataTable.
     * @param searchToken
     * @return Colecci&oacute;n de elementos.
     */
    public abstract Collection<T> getSearchResults();

    /**
     * Obtiene las columnas que hacen referencia a los atributos que se quiere visualizar&aacute;n en la tabla.
     * @return Colecci&oacute;n de elementos.
     */
    public abstract List<AttributeColumn> getAttributeColumns();
    
    /**
     * Obtiene las columnas del tipo checkbox, edici&oacute;n, borrado, etc.
     * @return
     */
    public abstract List<ActionColumn> getActionColumns();
    
    /**
     * Otorga al usuario la capacidad de realizar acciones con los par&aacute;metros que llegan v&iacute;a ajax. 
     * @param request
     * @param target
     */
    public abstract void onRequestParameters(Request request, AjaxRequestTarget target);
    
    /**
     * Obtiene la configuracion de idioma y los complementos que ser&aacute;n visibles.
     * Los complementos pueden ser el buscador, la cantidad de registros por p&aacute;gina, etc.
     * @return Instancia de DatatableConfiguration.
     */
    
    public abstract DatatableConfiguration getDatatableConfiguration();
    
}
