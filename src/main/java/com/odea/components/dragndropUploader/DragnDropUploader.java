package com.odea.components.dragndropUploader;

import java.util.Calendar;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.head.JavaScriptContentHeaderItem;
import org.apache.wicket.markup.html.IHeaderContributor;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.upload.FileUpload;
import org.apache.wicket.markup.html.form.upload.FileUploadField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.lang.Bytes;
import org.apache.wicket.util.template.PackageTextTemplate;

import com.odea.components.util.DragnDropUploaderBundle;

public abstract class DragnDropUploader extends Panel implements IHeaderContributor {  
	
	private DragnDropUploaderBundle bundle = new DragnDropUploaderBundle(); 
	
	private static final String[] JS_RESOURCES = {	"js/jquery.min.js", 
													"js/jquery.ezdz.js", 
													"js/dragndrop-template.js" };
	
	
	public DragnDropUploader(String id) {
		super(id);
		
		Form<?> form = new Form<Void>("form");
		this.add(form);
		
		form.setMultiPart(true);
 		form.setMaxSize(Bytes.megabytes(100));
 
 		final FileUploadField fileUploadInput = new FileUploadField("fileUpload");
 		fileUploadInput.setOutputMarkupId(true);
 		form.add(fileUploadInput);
 		
 		Label selectFileMessage = new Label("selectFileMessage", this.bundle.getString("messages.selectFileMessage"));
		form.add(selectFileMessage);
		
		AjaxButton submitButton = new AjaxButton("submitButton", Model.of(this.bundle.getString("button.submitButton"))) {

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				FileUpload fileUpload = fileUploadInput.getFileUpload();
				if (fileUpload != null) {
					DragnDropUploader.this.uploadFile(fileUpload);
				}
			}
			
		};
 
		form.add(submitButton);
 
	}


	@Override
	protected void onAfterRender() {
		
		for (String resourcePath : this.JS_RESOURCES) {
			this.addJavascriptResource(resourcePath);
		}
		
		super.onAfterRender();
	}
	
	private void addJavascriptResource(String resourcePath) {
		
		PackageTextTemplate resource = new PackageTextTemplate(DragnDropUploader.class, resourcePath, "text/javascript");
	    String resourceString = resource.asString();
	    String resourceUniqueName = Long.toString(Calendar.getInstance().getTimeInMillis());
	    new JavaScriptContentHeaderItem(resourceString, resourceUniqueName + "js", null).render(this.getResponse());
		
	}


	//Metodos abstractos
	protected abstract void uploadFile(FileUpload fileUpload);
	
}
