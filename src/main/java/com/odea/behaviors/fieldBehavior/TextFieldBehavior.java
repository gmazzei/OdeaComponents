package com.odea.behaviors.fieldBehavior;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Component;
import org.apache.wicket.behavior.AbstractAjaxBehavior;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptReferenceHeaderItem;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.resource.JavaScriptResourceReference;

public class TextFieldBehavior extends AbstractAjaxBehavior {

	private String labelUsuario = "";
	
	public TextFieldBehavior(String labelUsuario){
		this.labelUsuario = labelUsuario;
	}
	
	@Override
	public void renderHead(Component component, IHeaderResponse response) {
		JSCrearResource("inputLabels.js", response);
		super.renderHead(component, response);
	}
	
	private void JSCrearResource(String string, IHeaderResponse response) {
		JavaScriptResourceReference jrr = new JavaScriptResourceReference(TextFieldBehavior.class, string);
		response.render(JavaScriptReferenceHeaderItem.forReference(jrr));
	}

	@Override
	public void onRequest() {
		//Nada
	}

	@Override
	protected void onComponentTag(ComponentTag tag) {
		tag.put("class", "clearInput");
		tag.put("tabindex", "1");
		tag.put("onfocus", "onFocusTextboxInput(this)");
		tag.put("onblur", "onBlurTextboxInput(this,'" + labelUsuario + "')");
		tag.put("value", labelUsuario);		
		
		super.onComponentTag(tag);
	}
	
	

}
