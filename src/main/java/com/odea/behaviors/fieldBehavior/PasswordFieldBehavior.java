package com.odea.behaviors.fieldBehavior;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Component;
import org.apache.wicket.behavior.AbstractAjaxBehavior;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptReferenceHeaderItem;
import org.apache.wicket.request.resource.JavaScriptResourceReference;

public class PasswordFieldBehavior extends AbstractAjaxBehavior{

	private String labelPassword = "";
	
	public PasswordFieldBehavior (String labelPassword){
		this.labelPassword = labelPassword;
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
		
		tag.put("value", labelPassword);
		tag.put("type", "text");
		tag.put("tabindex", "2");
		tag.put("class", "clearInput");
		tag.put("onfocus", "onFocusPasswordInput(this)");
		tag.put("onblur", "onBlurPasswordInput(this,'" + labelPassword + "')");	
		
		super.onComponentTag(tag);
	}
}
