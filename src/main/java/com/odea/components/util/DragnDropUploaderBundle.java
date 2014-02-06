package com.odea.components.util;

import java.io.Serializable;
import java.util.ResourceBundle;

public class DragnDropUploaderBundle implements Serializable {
	
	private static final String bundleName = "com.odea.i18n.DragnDropUploaderBundle";
	private static ResourceBundle resourceBundle;
	
	public DragnDropUploaderBundle() {
		resourceBundle = ResourceBundle.getBundle(DragnDropUploaderBundle.bundleName);
	}
	
	public String getString(String key) {
		return resourceBundle.getString(key);
	}
	
}