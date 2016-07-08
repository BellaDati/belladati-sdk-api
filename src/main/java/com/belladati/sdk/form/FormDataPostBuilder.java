package com.belladati.sdk.form;

import java.math.BigDecimal;

import com.belladati.sdk.util.PostBuilder;

/**
 * Builder used to initiate form data that should be send to BellaDati.
 * 
 * @author Lubomir Elko
 */
public interface FormDataPostBuilder extends PostBuilder {

	/**
	 * Adds the element text value.
	 * 
	 * @param elementId ID of the form element
	 * @param value element value
	 */
	void addTextValue(String elementId, String value);

	/**
	 * Adds the element number value.
	 * 
	 * @param elementId ID of the form element
	 * @param value element value
	 */
	void addNumberValue(String elementId, BigDecimal value);

	/**
	 * Adds the element boolean value.
	 * 
	 * @param elementId ID of the form element
	 * @param value element value
	 */
	void addBooleanValue(String elementId, boolean value);

}
