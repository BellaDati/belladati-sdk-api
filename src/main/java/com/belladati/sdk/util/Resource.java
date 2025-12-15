package com.belladati.sdk.util;

import com.fasterxml.jackson.databind.JsonNode;

/**
 * A unique resource from the BellaDati API.
 * 
 * 
 */
public interface Resource extends IdElement {

	/**
	 * Returns the ID of this resource.
	 * 
	 * @return the ID of this resource
	 */
	String getId();

	/**
	 * Returns the name of this resource.
	 * 
	 * @return the name of this resource
	 */
	String getName();

	/**
	 * Returns String value of given field from given JSON or empty String if there is no field with this name.
	 * 
	 * @param json JsonNode representing source data
	 * @param field field name
	 * 
	 * @return String value or empty String
	 */
	public default String getStringOrEmpty(JsonNode json, String field) {
		return json.hasNonNull(field) ? json.get(field).asText() : "";
	}

	/**
	 * Returns boolean value of given field from given JSON or default value if there is no field with this name.
	 * 
	 * @param json JsonNode representing source data
	 * @param field field name
	 * @param defaultValue default boolean value
	 * 
	 * @return boolean value or default value
	 */
	public default boolean getBooleanOrDefault(JsonNode json, String field, boolean defaultValue) {
		return json.hasNonNull(field) ? json.get(field).asBoolean() : defaultValue;
	}

}
