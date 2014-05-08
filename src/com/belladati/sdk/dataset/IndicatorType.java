package com.belladati.sdk.dataset;

/**
 * Indicates the type of an indicator. Provides information of what content to
 * expect from the indicator.
 * 
 * @author Chris Hennigfeld
 */
public enum IndicatorType {
	/** A regular indicator containing raw data. */
	DATA,
	/** An indicator whose value is calculated through a formula. */
	FORMULA,
	/** An indicator group. */
	GROUP
}
