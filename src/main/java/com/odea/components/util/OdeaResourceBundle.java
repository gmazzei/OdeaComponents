package com.odea.components.util;

import java.io.Serializable;
import java.util.Locale;

import org.apache.wicket.resource.loader.BundleStringResourceLoader;

public class OdeaResourceBundle implements Serializable
{
	private static final String						bundleName					= "com.odea.aplicacionSeguridad.i18n.MessageBundle";
	private static final BundleStringResourceLoader	bundleStringResourceLoader	= new BundleStringResourceLoader(bundleName);

	public OdeaResourceBundle()
	{
	}

	public String getString(String key)
	{
		return bundleStringResourceLoader.loadStringResource(getClass(), key, null, null, null);
	}

	public String getString(String key, Locale locale)
	{
		return bundleStringResourceLoader.loadStringResource(getClass(), key, locale, null, null);
	}
}