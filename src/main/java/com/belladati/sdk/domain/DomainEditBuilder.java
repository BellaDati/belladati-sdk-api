package com.belladati.sdk.domain;

import com.belladati.sdk.util.PostBuilder;

/**
 * Builder used to initiate domain object that should be edited.
 * 
 * 
 */
public interface DomainEditBuilder extends PostBuilder {

	/**
	 * Sets the domain's description.
	 * 
	 * @param description the domain's description
	 */
	void setDescription(String description);

	/**
	 * Sets the domain's date format.
	 * 
	 * @param dateFormat the domain's date format
	 */
	void setDateFormat(String dateFormat);

	/**
	 * Sets the domain's time format.
	 * 
	 * @param timeFormat the domain's time format
	 */
	void setTimeFormat(String timeFormat);

	/**
	 * Sets the domain's time zone.
	 * 
	 * @param timeZone the domain's time zone
	 */
	void setTimeZone(String timeZone);

	/**
	 * Sets the locale string set for the domain.
	 * 
	 * @param locale the locale string set for the domain
	 */
	void setLocale(String locale);

	/**
	 * Adds the new parameter specified by given key and value.
	 * 
	 * @param key parameter key
	 * @param value parameter value
	 */
	void addParameter(String key, String value);

}
