package com.belladati.sdk.util;

import java.util.Locale;

/**
 * An item whose name can be localized in multiple languages.
 * 
 * @author Chris Hennigfeld
 */
public interface Localizable {

	/**
	 * Returns the name of this item in the given locale. Returns the default
	 * name if no localization exists.
	 * 
	 * @param locale locale to look for
	 * @return the name of this item in the given locale, or the default name
	 */
	String getName(Locale locale);

	/**
	 * Checks if this item has a localization for the given locale.
	 * 
	 * @param locale locale to check
	 * @return <tt>true</tt> if a localization exists
	 */
	boolean hasLocalization(Locale locale);
}
