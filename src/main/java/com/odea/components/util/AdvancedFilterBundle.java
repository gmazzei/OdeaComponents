package com.odea.components.util;

import java.io.Serializable;
import java.util.ResourceBundle;

public class AdvancedFilterBundle implements Serializable {
	
	private static final String bundleName = "com.odea.i18n.AdvancedFilterBundle";
	private static ResourceBundle resourceBundle;
	
	public AdvancedFilterBundle() {
		resourceBundle = ResourceBundle.getBundle(AdvancedFilterBundle.bundleName);
	}
	
	public String getString(String key) {
		return resourceBundle.getString(key);
	}
	
}