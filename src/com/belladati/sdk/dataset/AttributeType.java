package com.belladati.sdk.dataset;

/**
 * Indicates the type of an attribute. Provides information of what content to
 * expect from the attribute.
 * 
 * @author Chris Hennigfeld
 */
public enum AttributeType {
	/** A textual attribute. No semantic meaning is attached to the value. */
	TEXT,
	/** An attribute indicating a date. */
	DATE,
	/** An attribute indicating a time. */
	TIME,
	/** An attribute indicating geographical coordinates. */
	GEO_POINT
}
