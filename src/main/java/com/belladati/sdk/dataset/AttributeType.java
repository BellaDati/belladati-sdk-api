package com.belladati.sdk.dataset;

/**
 * Indicates the type of an attribute. Provides information of what content to
 * expect from the attribute.
 * 
 * 
 */
public enum AttributeType {
	/** A textual attribute. No semantic meaning is attached to the value. */
	TEXT("string"),
	/** An attribute indicating a date. */
	DATE("date"),
	/** An attribute indicating a time. */
	TIME("time"),
	/** An attribute indicating geographical coordinates. */
	GEO_POINT("point");

	private final String jsonType;

	private AttributeType(String jsonType) {
		this.jsonType = jsonType;
	}

	/**
	 * Returns the attribute type matching the given string type from JSON. This
	 * method is used by the SDK internally.
	 * 
	 * @param jsonType string type from JSON
	 * @return the attribute type matching the given string type from JSON
	 */
	public static AttributeType valueOfJson(String jsonType) {
		for (AttributeType type : values()) {
			if (type.jsonType.equalsIgnoreCase(jsonType)) {
				return type;
			}
		}
		return null;
	}
}
