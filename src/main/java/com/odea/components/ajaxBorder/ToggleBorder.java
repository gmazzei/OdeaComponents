package com.odea.components.ajaxBorder;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.border.Border;
import org.apache.wicket.markup.html.panel.Panel;

public class ToggleBorder extends Border{

	public ToggleBorder(String id, String label) {
		super(id);
		final WebMarkupContainer webmark = new WebMarkupContainer("webMark");
		webmark.setOutputMarkupId(true);
		this.addToBorder(webmark);
		
		AjaxLink ajax = new AjaxLink("al") {

			@Override
			public void onClick(AjaxRequestTarget target) {
				target.appendJavaScript("$('#"+ webmark.getMarkupId() +"').slideToggle('slow');");
			}
		};
		this.addToBorder(ajax);
		ajax.add(new Label("label",label));
	}
	
	
	

}
