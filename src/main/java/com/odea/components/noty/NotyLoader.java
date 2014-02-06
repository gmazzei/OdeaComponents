package com.odea.components.noty;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.head.CssReferenceHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptReferenceHeaderItem;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.request.resource.CssResourceReference;
import org.apache.wicket.request.resource.JavaScriptResourceReference;

public class NotyLoader extends WebMarkupContainer {

	public NotyLoader(String id) {
		super(id);
	}

	@Override
	public void renderHead(IHeaderResponse response) {
		JSCrearResource("jquery.noty.js", response);
		JSCrearResource("promise.js", response);
		JSCrearResource("layouts/bottom.js", response);
		JSCrearResource("layouts/bottomCenter.js", response);
		JSCrearResource("layouts/bottomLeft.js", response);
		JSCrearResource("layouts/bottomRight.js", response);
		JSCrearResource("layouts/center.js", response);
		JSCrearResource("layouts/centerLeft.js", response);
		JSCrearResource("layouts/centerRight.js", response);
		JSCrearResource("layouts/inline.js", response);
		JSCrearResource("layouts/top.js", response);
		JSCrearResource("layouts/topCenter.js", response);
		JSCrearResource("layouts/topLeft.js", response);
		JSCrearResource("layouts/topRight.js", response);
		JSCrearResource("themes/default.js", response);
//		JSCrearResource("jquery-1.7.2.min.js", response);
		
		// ---------------------separacion-------------------------
		//CssCrearResource("", response); por ahora ninguno
	}

	@SuppressWarnings("unused")
	private void CssCrearResource(String string, IHeaderResponse response) {
		CssResourceReference css = new CssResourceReference(NotyLoader.class,string);
		response.render(CssReferenceHeaderItem.forReference(css));
	}

	private void JSCrearResource(String string, IHeaderResponse response) {
		JavaScriptResourceReference jrr = new JavaScriptResourceReference(NotyLoader.class, string);
		response.render(JavaScriptReferenceHeaderItem.forReference(jrr));
	}
	
	public void crearNotificacion(String texto,AjaxRequestTarget target, String posicion){
		target.appendJavaScript("generate('"+ texto +"','"+ posicion +"','notification');");
	}
	public void crearAlerta(String texto,AjaxRequestTarget target, String posicion){
		target.appendJavaScript("generate('"+ texto +"','"+ posicion +"','alert');");
	}
	public void crearInformativo(String texto,AjaxRequestTarget target, String posicion){
		target.appendJavaScript("generate('"+ texto +"','"+ posicion +"','information');");
	}
	public void crearPeligro(String texto,AjaxRequestTarget target, String posicion){
		target.appendJavaScript("generate('"+ texto +"','"+ posicion +"','warning');");
	}
	public void crearError(String texto,AjaxRequestTarget target, String posicion){
		target.appendJavaScript("generate('"+ texto +"','"+ posicion +"','error');");
	}
	public void crearExito(String texto,AjaxRequestTarget target, String posicion){
		target.appendJavaScript("generate('"+ texto +"','"+ posicion +"','success');");
	}
	public void crearNoty(AjaxRequestTarget target, String texto, String layout, String theme, String type, int timeout, String closeWith){
		String javascript = "";
		javascript+="generateNoty('";
		//texto plano entre comillas
		javascript+=texto;
		javascript+="','";
		//texto plano entre comillas
		javascript+=layout;
		javascript+="','";
		//texto plano entre comillas
		javascript+=theme;
		javascript+="','";
		//texto plano entre comillas
		javascript+=type;
		javascript+="',";
		//numeros planos entre comillas, false para mantenerlo siempre visible.
		javascript+=timeout;
		javascript+=",";
		//array de texto plano entre comillas simples ej: ['click','hover']
		javascript+=closeWith;
		javascript+=");";
		//TODO botones
//		javascript+=buttons;
		target.appendJavaScript(javascript);
	}

}
