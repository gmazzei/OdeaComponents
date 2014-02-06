package com.odea.components.util;

import java.util.ResourceBundle;

public class DatatableBundle {

	private static final String bundleName = "com.odea.i18n.DatatableBundle";
	private ResourceBundle resourceBundle;
	
	public DatatableBundle() {
		this.resourceBundle = ResourceBundle.getBundle(DatatableBundle.bundleName);
	}
	
	public String getString(String key) {
		return this.resourceBundle.getString(key);
	}
	
}
