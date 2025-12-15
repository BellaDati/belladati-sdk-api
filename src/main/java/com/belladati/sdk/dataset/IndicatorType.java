package com.belladati.sdk.dataset;

/**
 * Indicates the type of an indicator. Provides information of what content to
 * expect from the indicator.
 * 
 * 
 */
public enum IndicatorType {
	/** A regular indicator containing raw data. */
	DATA("DATA_INDICATOR"),
	/** An indicator whose value is calculated through a formula. */
	FORMULA("FORMULA_INDICATOR"),
	/** An indicator group. */
	GROUP("INDICATOR_GROUP");

	private final String jsonType;

	private IndicatorType(String jsonType) {
		this.jsonType = jsonType;
	}

	/**
	 * Returns the indicator type matching the given string type from JSON. This
	 * method is used by the SDK internally.
	 * 
	 * @param jsonType string type from JSON
	 * @return the indicator type matching the given string type from JSON
	 */
	public static IndicatorType valueOfJson(String jsonType) {
		for (IndicatorType type : values()) {
			if (type.jsonType.equalsIgnoreCase(jsonType)) {
				return type;
			}
		}
		return null;
	}
}
