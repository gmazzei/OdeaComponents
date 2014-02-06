package com.odea.components.filtroAvanzado.view;

import java.util.Arrays;
import java.util.List;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;

import com.odea.components.filtroAvanzado.domain.Condition;
import com.odea.components.filtroAvanzado.domain.Field;
import com.odea.components.filtroAvanzado.domain.Operator;


public abstract class AdvancedFilterForm extends Form<Condition> {
		
    private IModel<Condition> conditionModel = new CompoundPropertyModel<Condition>(new Condition());
    
    public AdvancedFilterForm(String id) {
        super(id);
        this.setDefaultModel(this.conditionModel);
        this.setOutputMarkupId(true);
        
        final DropDownChoice<Operator> operator = new DropDownChoice<Operator>("operator", Arrays.asList(Operator.values()));
        operator.setOutputMarkupId(true);
        operator.setRequired(true);
        this.add(operator);

        
        final DropDownChoice<Field> field = new DropDownChoice<Field>("field", this.getFields(), new FieldChoiceRender());
        field.setRequired(true);
        field.add(new AjaxFormComponentUpdatingBehavior("onchange") {

			@Override
			protected void onUpdate(AjaxRequestTarget target) {
        		List<Operator> supportedOperators = field.getModelObject().getType().getSupportedOperators();
        		operator.setChoices(supportedOperators);
        		target.add(operator);
			}
        	
        });
        this.add(field);
        
        RequiredTextField<String> value = new RequiredTextField<String>("value");
        this.add(value);

        AjaxButton addLink = new AjaxButton("add-link", this) {
            @Override
            protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
                AdvancedFilterForm.this.onSubmit(target, (Form<Condition>) form);
            }

            @Override
            protected void onError(AjaxRequestTarget target, Form<?> form) {
                AdvancedFilterForm.this.onError(target, (Form<Condition>) form);
            }

        };
        
        // Vacio el value del boton
        addLink.add(new AttributeModifier("value", ""));
        
        this.add(addLink);
    }


    protected class FieldChoiceRender implements IChoiceRenderer<Field> {
        public Object getDisplayValue(Field object) {
            return object.getName();
        }

        public String getIdValue(Field object, int index) {
            return object.getName() + "_" + index;
        }
    }

    protected abstract List<Field> getFields();

    protected abstract void onSubmit(AjaxRequestTarget target, Form<Condition> form);

    protected abstract void onError(AjaxRequestTarget target, Form<Condition> form);
}
