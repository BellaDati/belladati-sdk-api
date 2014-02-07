package com.belladati.sdk.report;

import com.belladati.sdk.filter.FilterOperation.MultiValueOperation;

/**
 * A single value of an attribute. Attribute values have a human-readable label
 * and an internal value. Attribute values are used for filtering in a
 * {@link MultiValueOperation}.
 * 
 * @author Chris Hennigfeld
 */
public interface AttributeValue {

	/**
	 * Returns the human-readable label of this attribute value.
	 * 
	 * @return the human-readable label of this attribute value
	 */
	String getLabel();

	/**
	 * Returns the internal value of this attribute value.
	 * 
	 * @return the internal value of this attribute value
	 */
	String getValue();
}
