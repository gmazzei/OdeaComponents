package com.odea.components.filtroAvanzado.view;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;

import com.odea.components.filtroAvanzado.domain.Condition;
import com.odea.components.filtroAvanzado.domain.Field;
import com.odea.components.util.AdvancedFilterBundle;

public abstract class AdvancedFilter extends Panel {
	
	private AdvancedFilterBundle bundle = new AdvancedFilterBundle();
	
    private List<Condition> conditions = new ArrayList<Condition>();
    private IModel<List<Condition>> conditionsModel = new PropertyModel<List<Condition>>(this, "conditions");
    
    
    public AdvancedFilter(String id) {
        super(id);

        // Agrego la clase principal
        this.add(new AttributeModifier("class", "filtroAvanzado"));

        //Mensaje 'Buscar por:'
        this.add(new Label("findByMessage", bundle.getString("AdvancedFilter.label.findByMessage")));
        
        //Contenedor de la lista de condiciones
        final WebMarkupContainer conditionsContainer = new WebMarkupContainer("conditionsContainer");

        AdvancedFilterForm conditionForm = new AdvancedFilterForm("conditionForm") {
           
        	@Override
            protected List<Field> getFields() {
                return AdvancedFilter.this.getFields();
            }

            @Override
            protected void onSubmit(AjaxRequestTarget target, Form<Condition> form) {
                Condition condition = form.getModelObject().clone();
                AdvancedFilter.this.conditions.add(condition);
                target.add(conditionsContainer);
                AdvancedFilter.this.onSubmit(target);
            }

            @Override
            protected void onError(AjaxRequestTarget target, Form<Condition> form) {
            	//Nada
            }
            
        };

        this.add(conditionForm);

        
        //Lista de condiciones que muestra las que estan activas.
        ListView<Condition> conditionList = new ListView<Condition>("conditionList", this.conditionsModel) {
            
        	@Override
            protected void populateItem(ListItem<Condition> components) {
        		
                
        		final Condition condition = components.getModelObject();    
                components.add(new Label("condition", condition.getCondition()));
                
                
                AjaxLink<Void> eraseConditionLink = new AjaxLink<Void>("eraseConditionLink") {
                    @Override
                    public void onClick(AjaxRequestTarget target) {
                        AdvancedFilter.this.conditions.remove(condition);
                        target.add(conditionsContainer);
                        AdvancedFilter.this.onSubmit(target);
                    }

                };
                
                components.add(eraseConditionLink);
                
            }
        };

        conditionsContainer.add(conditionList);
        conditionsContainer.setOutputMarkupId(true);

        this.add(conditionsContainer);

        
        //Link para limpiar la lista
        AjaxLink<Void> cleanFilterLink = new AjaxLink<Void>("cleanFilterLink") {
            @Override
            public void onClick(AjaxRequestTarget target) {
                AdvancedFilter.this.conditions.clear();
                target.add(conditionsContainer);
                AdvancedFilter.this.onSubmit(target);
            }

        };
        cleanFilterLink.add(new Label("cleanFilterLinkLabel", AdvancedFilter.this.bundle.getString("AdvancedFilter.link.clean")));
        
        this.add(cleanFilterLink);
    }

    
    
    
    public List<Condition> getConditions() {
    	return conditions;
    }

    
    //Metodos abstractos

	protected abstract void onSubmit(AjaxRequestTarget target);

    protected abstract List<Field> getFields();
}
