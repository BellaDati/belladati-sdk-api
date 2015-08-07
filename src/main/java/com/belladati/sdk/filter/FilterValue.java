package com.belladati.sdk.filter;

import com.belladati.sdk.dataset.AttributeValue;

/**
 * An attribute value that can be created on the fly for filtering. This value
 * is not linked to any attribute.
 * <p>
 * Can be used to create filters for known attribute values without having to
 * make an API request loading the values first.
 * 
 * @author Chris Hennigfeld
 */
public class FilterValue implements AttributeValue {
	private final String label;
	private final String value;

	/**
	 * Creates a filter value with the given value string. The same string is
	 * used as the value's label.
	 * 
	 * @param value value string for this value
	 */
	public FilterValue(String value) {
		this(value, value);
	}

	/**
	 * 
	 * Creates a filter value with the given label and value strings.
	 * 
	 * @param label label for this value
	 * @param value value string for this value
	 */
	public FilterValue(String label, String value) {
		this.label = label;
		this.value = value;
	}

	@Override
	public String getLabel() {
		return label;
	}

	@Override
	public String getValue() {
		return value;
	}

	@Override
	public String toString() {
		return label;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof AttributeValue) {
			return value.equals(((AttributeValue) obj).getValue());
		}
		return false;
	}

	@Override
	public int hashCode() {
		return value.hashCode();
	}
}