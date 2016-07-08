package com.belladati.sdk.form;

/**
 * Indicates the type of an import form element.
 * 
 * @author Lubomir Elko
 */
public enum FormElementType {

	/** Field for username values. */
	USERNAME("username"),

	/** Field for text values. */
	TEXTFIELD("textfield"),

	/** Field for dates. */
	DATEFIELD("datefield"),

	/** Field for boolean values. */
	CHECKBOX("checkbox"),

	/** Field for timestamps. */
	TIMESTAMP("timestamp"),

	/** Field for selection. */
	SELECT("select");

	private final String jsonValue;

	private FormElementType(String jsonValue) {
		this.jsonValue = jsonValue;
	}

	public String getJsonValue() {
		return jsonValue;
	}

	/**
	 * Returns the element type matching the given string type from JSON. This
	 * method is used by the SDK internally.
	 * 
	 * @param jsonType string type from JSON
	 * @return the element type matching the given string type from JSON
	 */
	public static FormElementType valueOfJson(String jsonType) {
		for (FormElementType type : values()) {
			if (type.jsonValue.equalsIgnoreCase(jsonType)) {
				return type;
			}
		}
		return null;
	}

}
