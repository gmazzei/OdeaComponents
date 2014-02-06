package com.odea.behaviors.dirtyForm;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.AbstractAjaxBehavior;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptReferenceHeaderItem;
import org.apache.wicket.request.resource.JavaScriptResourceReference;

public class DirtyFormBehavior extends AbstractAjaxBehavior {

	@Override
	public void renderHead(Component component, IHeaderResponse response) {
		// TODO Auto-generated method stub
		JSCrearResource("dirty.js", response);
		super.renderHead(component, response);
	}

	private void JSCrearResource(String string, IHeaderResponse response) {
		JavaScriptResourceReference jrr = new JavaScriptResourceReference(DirtyFormBehavior.class, string);
		response.render(JavaScriptReferenceHeaderItem.forReference(jrr));
	}

	@Override
	public void onRequest() {
		//Nada
	}

}
