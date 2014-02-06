package com.odea.components.slickGrid;

import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.wicket.ajax.AbstractDefaultAjaxBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptContentHeaderItem;
import org.apache.wicket.markup.html.IHeaderContributor;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.util.template.PackageTextTemplate;

import com.odea.components.jquery.datatable.column.Jsonizable;
import com.odea.components.slickGrid.columns.AbstractColumn;

public abstract class SlickGrid<T extends Jsonizable> extends WebMarkupContainer implements IHeaderContributor {
	
	private static final String[] RENDERHEAD_RESOURCES = {"jquery-1.7.min.js",	"jquery-ui-1.8.16.custom.min.js", "jquery.event.drag-2.2.js",
														"slick.core.js", "slick.editors.js", "slick.cellrangedecorator.js",
														"slick.cellrangeselector.js", "slick.cellselectionmodel.js", "slick.grid.js",
														"slick.groupitemmetadataprovider.js", "slick.dataview.js", "slick.pager.js", 
														"slick.columnpicker.js", "slick.formatters.js", "slick.checkboxselectcolumn.js"};
	
	private static final String SLICKGRID_TEMPLATE = "slickgrid-template.js";
	
    private AbstractDefaultAjaxBehavior ajaxBehavior;
		
    
   
    public SlickGrid(String id) {
        super(id);
        this.setOutputMarkupId(true);
        
        this.prepare();
    }


	private void prepare() {
		
        this.ajaxBehavior = new AbstractDefaultAjaxBehavior() {
            @Override
        	public void respond(AjaxRequestTarget target) {
            	
                RequestCycle requestCycle = RequestCycle.get();
                Request request = requestCycle.getRequest();

                SlickGrid.this.onRequestParameters(request);
            }
            
        };
        
		this.add(this.ajaxBehavior);
		
    }
	
	
	
	@Override
	public void renderHead(IHeaderResponse response) {
		
    	for (String jsResource : SlickGrid.RENDERHEAD_RESOURCES) {
    		this.addJavascriptResource(jsResource);
		}
    	
		super.renderHead(response);
	}


	@Override
    protected void onAfterRender() {
    	
		String dataJson = this.toJson(this.getData());
		String columnsJson = this.toJson(this.getColumns());
		SlickGridConfiguration conf = this.getSlickGridConfiguration();
		
		if (conf.getIdAttribute() == null) {
			conf.setIdAttribute("null");
		}
		if (conf.getGroupingAttribute() == null) {
			conf.setGroupingAttribute("null");
		}
		
    	Map<String, CharSequence> map = new HashMap<String, CharSequence>(3);
    	map.put("slickGridId", this.getMarkupId());
        map.put("selector", "#" + this.getMarkupId());
        map.put("callbackUrl", this.ajaxBehavior.getCallbackUrl());	
        map.put("dataJson", dataJson);
        map.put("columnsJson", columnsJson);
        map.put("idAttribute", conf.getIdAttribute());
        map.put("groupingAttribute", conf.getGroupingAttribute());
        
        this.addJavascriptResource(SlickGrid.SLICKGRID_TEMPLATE, map);
        
        super.onAfterRender();
    }
	
	
	private void addJavascriptResource(String resourceName) {
    	PackageTextTemplate packageTextTemplate = new PackageTextTemplate(SlickGrid.class, resourceName, "text/javascript");
        String resource = packageTextTemplate.asString();
        String resourceUniqueName = Long.toString(Calendar.getInstance().getTimeInMillis());
        new JavaScriptContentHeaderItem(resource, resourceUniqueName + "js", null).render(this.getResponse());
	}
	
	private void addJavascriptResource(String resourceName, Map<String,CharSequence> map) {
		PackageTextTemplate packageTextTemplate = new PackageTextTemplate(SlickGrid.class, resourceName, "text/javascript");
        String templateResource = packageTextTemplate.asString(map);
        String templateUniqueName = Long.toString(Calendar.getInstance().getTimeInMillis());
        new JavaScriptContentHeaderItem(templateResource, templateUniqueName + "js", null).render(this.getResponse());
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
	
	
	
    //Metodos abstractos
    
    /**
     * Obtiene la colecci&oacute;n de elementos con los que ser&aacute; llenada la DataTable.
     * @param searchToken
     * @return Colecci&oacute;n de elementos.
     */
    public abstract Collection<T> getData();
    
    /**
     * Obtiene las columnas.
     * @return Coleccion de columnas.
     */
    
    public abstract Collection<? extends AbstractColumn> getColumns();
    
    /**
     * Obtiene la configuracion de la slickgrid.
     * Para mas informacion ver clase SlickGridConfiguration.
     * @return Clase de configuracion.
     */
    
    public abstract SlickGridConfiguration getSlickGridConfiguration();
    
    /**
     * Otorga al usuario la capacidad de realizar acciones con los par&aacute;metros que llegan por request. 
     * @param request
     */
    public abstract void onRequestParameters(Request request);
}
